package org.hpms.gui.logic;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.hpms.gui.data.ProjectModel;
import org.hpms.gui.export.room.RoomXMLData;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class GameDataBuilder {

    private final ProjectModel projectModel;

    public GameDataBuilder(ProjectModel projectModel) {
        this.projectModel = projectModel;
    }

    public void build(String path, boolean pack) throws Exception {
        buildStructure(path);
        buildData(path);
        if (pack) {
            zipFolder(new File(path + File.separator + "data"), new File(path + File.separator + "data.hpak"));
            FileUtils.deleteDirectory(new File(path + File.separator + "data"));
        }


    }

    private void buildData(String path) throws Exception {
        // Copy all resources
        String workingResPath = projectModel.getProjectPath() + File.separator + "tmp" + File.separator + "resources";
        FileUtils.copyDirectoryToDirectory(new File(workingResPath), new File(path + File.separator + "data"));

        // Convert all models to hmdat
        String modelsDir = path + File.separator + "data" + File.separator + "resources" + File.separator + "models";
        File modelsPath = new File(modelsDir);
        File[] files = modelsPath.listFiles();
        ExternalRoutineExecutor.Routine daeRoutine = ExternalRoutineExecutor.Routine.DAE_TO_HDAT;
        for (File daeFile : files) {
            if (daeFile.isFile()) {
                daeRoutine.execute(daeFile.getAbsolutePath(), modelsDir + File.separator + daeFile.getName() + ".hmdat");
                if (!daeFile.delete()) {
                    daeFile.deleteOnExit();
                }
            }
        }

        // Copy all scripts
        ScriptBuilder scriptBuilder = new ScriptBuilder(projectModel);
        scriptBuilder.createScripts(path + File.separator + "data");

        // Copy and convert all hrdat
        ExternalRoutineExecutor.Routine xmlToBinary = ExternalRoutineExecutor.Routine.XML_TO_BINARY;
        for (ProjectModel.RoomModel room : projectModel.getRooms()) {
            RoomXMLData xmlData = fromModelToXML(room);
            File xmlFile = File.createTempFile(room.getName(), "");
            marshalRoom(xmlData, xmlFile);
            xmlToBinary.execute(xmlFile.getAbsolutePath(), workingResPath + File.separator + "floors" + File.separator + room.getName() + ".hfdat");
            if (!xmlFile.delete()) {
                xmlFile.deleteOnExit();
            }
        }

    }

    private void buildStructure(String path) throws IOException {
        ZipInputStream zipIn = new ZipInputStream(getClass().getResourceAsStream("/runtime/data.zip"));
        ZipEntry entry = zipIn.getNextEntry();

        while (entry != null) {
            String filePath = path + File.separator + entry.getName();
            if (!entry.isDirectory()) {
                extractFile(zipIn, filePath);
            } else {
                File dir = new File(filePath);
                dir.mkdir();
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
    }

    private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[4096];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }

    private static void zipFolder(File folder, File zipFile) throws IOException {
        zipFolder(folder, new FileOutputStream(zipFile));
    }

    private static void zipFolder(File folder, OutputStream outputStream) throws IOException {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream)) {
            processFolder(folder, zipOutputStream, folder.getPath().length() + 1);
        }
    }

    private static void processFolder(File folder, ZipOutputStream zipOutputStream, int prefixLength)
            throws IOException {
        for (File file : folder.listFiles()) {
            if (file.isFile()) {
                ZipEntry zipEntry = new ZipEntry(file.getPath().substring(prefixLength));
                zipOutputStream.putNextEntry(zipEntry);
                try (FileInputStream inputStream = new FileInputStream(file)) {
                    IOUtils.copy(inputStream, zipOutputStream);
                }
                zipOutputStream.closeEntry();
            } else if (file.isDirectory()) {
                processFolder(file, zipOutputStream, prefixLength);
            }
        }
    }

    private static void marshalRoom(RoomXMLData xmlData, File dest) throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(RoomXMLData.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(xmlData, sw);
        FileUtils.write(dest, sw.toString());
    }

    private static RoomXMLData fromModelToXML(ProjectModel.RoomModel roomModel) {
        RoomXMLData xmlData = new RoomXMLData();
        return xmlData;
    }
}
