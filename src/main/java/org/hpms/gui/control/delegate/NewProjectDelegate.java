package org.hpms.gui.control.delegate;

import org.hpms.gui.AppInfo;
import org.hpms.gui.control.Controllers;
import org.hpms.gui.logic.ProjectManager;
import org.hpms.gui.utils.EasyDocumentListener;
import org.hpms.gui.views.BaseGui;
import org.hpms.gui.views.CreateNewProject;

import javax.swing.*;
import java.io.File;

import static org.hpms.gui.utils.ErrorManager.createReadOnlyJTextField;

public class NewProjectDelegate {
    private final CreateNewProject newProject;

    public NewProjectDelegate() {
        newProject = new CreateNewProject(BaseGui.getInstance().getMainFrame());
        initListeners();
    }

    private void initListeners() {
        newProject.getNewProjNameTxt().getDocument().addDocumentListener((EasyDocumentListener) e -> {
            if (newProject.getNewProjNameTxt().getText() != null && newProject.getNewProjNameTxt().getText().length() > 0) {
                newProject.getOkBtn().setEnabled(true);
            } else {
                newProject.getOkBtn().setEnabled(false);
            }
        });
        newProject.getOkBtn().addActionListener(e -> {
            String projectName = newProject.getNewProjNameTxt().getText();
            ProjectManager.getInstance().buildEmptyProject();
            ProjectManager.getInstance().getProjectModel().setRuntimeName(projectName);
            ProjectManager.getInstance().getProjectModel().setProjectPath(AppInfo.getCurrentWorkspace());
            ProjectManager.getInstance().getProjectModel().setProjectName(projectName);
            try {
                ProjectManager.getInstance().persistToFile(AppInfo.getCurrentWorkspace() + File.separator + projectName + ".hproj");
                File projModels = new File(AppInfo.getCurrentWorkspace() + File.separator + projectName + "_data" + File.separator + "models");
                File projTextures = new File(AppInfo.getCurrentWorkspace() + File.separator + projectName + "_data" + File.separator + "textures");
                File projAudio = new File(AppInfo.getCurrentWorkspace() + File.separator + projectName + "_data" + File.separator + "audio");
                File projFloors = new File(AppInfo.getCurrentWorkspace() + File.separator + projectName + "_data" + File.separator + "floors");
                boolean res = projModels.mkdirs();
                res &= projTextures.mkdirs();
                res &= projAudio.mkdirs();
                res &= projFloors.mkdirs();
                if (!res) {
                    throw new Exception("An error occurred while creating project structure.");
                }
                ProjectManager.getInstance().getProjectModel().setProjectModelsPath(projModels.getAbsolutePath());
                ProjectManager.getInstance().getProjectModel().setProjectTexturesPath(projTextures.getAbsolutePath());
                ProjectManager.getInstance().getProjectModel().setProjectAudioPath(projAudio.getAbsolutePath());
                ProjectManager.getInstance().getProjectModel().setProjectFloorsPath(projFloors.getAbsolutePath());

                Controllers.updateAll();
                newProject.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, createReadOnlyJTextField(ex), "Error", JOptionPane.PLAIN_MESSAGE);
                System.exit(-1);

            }
        });
    }

    public void newProject() {

        newProject.pack();
        newProject.getOkBtn().setEnabled(false);
        newProject.setVisible(true);
        newProject.getCancBtn().addActionListener(e -> newProject.dispose());


    }
}