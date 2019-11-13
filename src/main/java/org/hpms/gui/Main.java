package org.hpms.gui;

import com.bulenkov.darcula.DarculaLaf;
import org.apache.commons.io.FileUtils;
import org.hpms.gui.control.Controllers;
import org.hpms.gui.data.ProjectModel;
import org.hpms.gui.logic.ProjectManager;
import org.hpms.gui.luagen.LuaStatement;
import org.hpms.gui.luagen.components.*;
import org.hpms.gui.utils.EasyDocumentListener;
import org.hpms.gui.views.BaseGui;
import org.hpms.gui.views.WorkspaceChooser;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.hpms.gui.utils.ErrorManager.createReadOnlyJTextField;

public class Main {


    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(Main::createAndShowGUI);


    }

    private static void checkWorkspace() throws Exception {
        File jarFile = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        File file = new File(jarFile.getParentFile(), "Toolbox.ini");
        if (!file.exists()) {
            showWorkspaceChooser(file);

        } else {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String path = br.readLine();
            br.close();
            if (new File(path + File.separator + ".hpms").exists() && new File(path + File.separator + ".hpms").isDirectory()) {
                AppInfo.setCurrentWorkspace(path);
                startGui();
            } else {
                showWorkspaceChooser(file);
            }
        }
    }

    private static void showWorkspaceChooser(File file) {
        WorkspaceChooser frame = new WorkspaceChooser();
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getConfirmWorkspaceBtn().setEnabled(false);
        frame.getWorkspaceLoadBtn().addActionListener(e -> {
            JFileChooser f = new JFileChooser();
            f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            f.showSaveDialog(null);
            f.setAcceptAllFileFilterUsed(false);
            f.setDialogTitle("Select Workspace Folder");
            frame.getWorkspaceTxt().setText(f.getSelectedFile().getAbsolutePath());
        });
        frame.getWorkspaceTxt().getDocument().addDocumentListener((EasyDocumentListener) e -> {
            File test = new File(frame.getWorkspaceTxt().getText());
            if (test.isDirectory()) {
                frame.getConfirmWorkspaceBtn().setEnabled(true);
            } else {
                frame.getConfirmWorkspaceBtn().setEnabled(false);
            }

        });

        frame.getConfirmWorkspaceBtn().addActionListener(e -> {
            String path = frame.getWorkspaceTxt().getText();
            try {
                file.createNewFile();
                FileUtils.write(file, path, "UTF-8");
                if (!new File(path).exists()) {
                    new File(path).mkdirs();
                }
                if (!new File(path + File.separator + ".hpms").exists()) {
                    new File(path + File.separator + ".hpms").mkdirs();
                }
                AppInfo.setCurrentWorkspace(path);
                frame.dispose();
                startGui();
            } catch (IOException ex) {
                frame.dispose();
                JOptionPane.showMessageDialog(null, createReadOnlyJTextField(ex), "Error", JOptionPane.PLAIN_MESSAGE);
            }
        });
    }

    private static void startGui() {
        BaseGui gui = BaseGui.getInstance();
        JFrame frame = gui.getMainFrame();
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Controllers.initAll();
        Controllers.updateAll();
    }


    private static void createAndShowGUI() {
        try {
            UIManager.setLookAndFeel(new DarculaLaf());
        } catch (UnsupportedLookAndFeelException e) {
            JOptionPane.showMessageDialog(null, createReadOnlyJTextField(e), "Error", JOptionPane.PLAIN_MESSAGE);

        }

        try {
            registerSerializables();
            checkWorkspace();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, createReadOnlyJTextField(e), "Error", JOptionPane.PLAIN_MESSAGE);
            System.exit(-1);
        }


    }


    private static void registerSerializables() {
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.class);
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.ProjectPreferences.class);
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.BuildSettings.class);
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.UserSettings.class);
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.RoomModel.class);
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.RoomModel.Event.class);
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.RoomModel.Event.Action.class);
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.RoomModel.Event.ConditionAction.class);
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.RoomModel.Event.TriggerType.class);
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.RoomModel.PipelineType.class);
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.RoomModel.SectorGroup.class);
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.RoomModel.SectorGroup.Sector.class);
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.RoomModel.SectorGroup.Sector.PerimetralSide.class);
        ProjectManager.KRYO_SERIALIZER.register(LuaStatement.class);
        ProjectManager.KRYO_SERIALIZER.register(LuaBinaryOperator.class);
        ProjectManager.KRYO_SERIALIZER.register(LuaExpression.class);
        ProjectManager.KRYO_SERIALIZER.register(LuaFunctionCall.class);
        ProjectManager.KRYO_SERIALIZER.register(LuaFunctionDeclare.class);
        ProjectManager.KRYO_SERIALIZER.register(LuaIfStatement.class);
        ProjectManager.KRYO_SERIALIZER.register(LuaInstance.class);
        ProjectManager.KRYO_SERIALIZER.register(LuaInstance.Type.class);
        ProjectManager.KRYO_SERIALIZER.register(LuaScript.class);
        ProjectManager.KRYO_SERIALIZER.register(LuaUnaryOperator.class);
        ProjectManager.KRYO_SERIALIZER.register(ArrayList.class);
        ProjectManager.KRYO_SERIALIZER.register(HashMap.class);
        ProjectManager.KRYO_SERIALIZER.register(LinkedHashMap.class);

    }



}
