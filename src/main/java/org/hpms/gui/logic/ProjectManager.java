package org.hpms.gui.logic;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.hpms.gui.data.ProjectModel;

import java.io.FileInputStream;
import java.io.FileOutputStream;

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
        projectModel.getSettings().setCacheImages(true);
        projectModel.getSettings().setCacheModels(true);
    }

    public boolean noProject() {
        return projectModel == null;
    }

    public void buildFromFile(String projectPath) throws Exception {
        Input input = new Input(new FileInputStream(projectPath));
        projectModel = KRYO_SERIALIZER.readObject(input, ProjectModel.class);
        input.close();
    }

    public void persistToFile(String projectPath) throws Exception {
        Output output = new Output(new FileOutputStream(projectPath));
        if (projectModel == null) {
            buildEmptyProject();
        }
        KRYO_SERIALIZER.writeObject(output, projectModel);
        output.close();
    }

    public synchronized ProjectModel getProjectModel() {
        return projectModel;
    }

    public void generateGameData(String path) {
    }


}
