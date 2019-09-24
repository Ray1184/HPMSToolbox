package org.hpms.gui.logic;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.hpms.gui.data.ProjectModel;
import org.hpms.gui.export.room.RoomXMLData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;

public class ProjectManager {

    private static final ProjectManager INSTANCE = new ProjectManager();

    public static final Kryo KRYO_SERIALIZER = new Kryo();

    private ProjectModel projectModel;

    public static ProjectManager getInstance() {
        return INSTANCE;
    }

    public void buildEmptyProject() {
        projectModel = new ProjectModel();
        projectModel.getUserSettings().setLastZoomFactor(1.0f);
        projectModel.getSettings().setDebug(false);
        projectModel.getSettings().setExplodeBuild(false);
    }

    public void buildFromFile(String projectPath) throws FileNotFoundException {
        Input input = new Input(new FileInputStream(projectPath));
        projectModel = KRYO_SERIALIZER.readObject(input, ProjectModel.class);
        input.close();
    }

    public void persistToFile(String projectPath) throws FileNotFoundException {
        Output output = new Output(new FileOutputStream(projectPath));
        if (projectModel == null) {
            buildEmptyProject();
        }
        KRYO_SERIALIZER.writeObject(output, projectModel);
        output.close();
    }

    public ProjectModel getProjectModel() {
        return projectModel;
    }

    public void generateGameData(String path) {
    }




}
