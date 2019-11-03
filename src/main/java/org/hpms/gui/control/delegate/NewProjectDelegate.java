package org.hpms.gui.control.delegate;

import org.hpms.gui.AppInfo;
import org.hpms.gui.control.Controllers;
import org.hpms.gui.logic.ProjectManager;
import org.hpms.gui.utils.EasyDocumentListener;
import org.hpms.gui.utils.ErrorManager;
import org.hpms.gui.views.BaseGui;
import org.hpms.gui.views.CreateNewProject;

import javax.swing.*;
import java.io.File;

public class NewProjectDelegate {
    public NewProjectDelegate() {
    }

    public void newProject() {
        CreateNewProject newProject = new CreateNewProject(BaseGui.getInstance().getMainFrame());
        newProject.pack();
        newProject.getOkBtn().setEnabled(false);
        newProject.setVisible(true);
        newProject.getCancBtn().addActionListener(e -> newProject.dispose());
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
            try {
                ProjectManager.getInstance().persistToFile(AppInfo.getCurrentWorkspace() + File.separator + projectName + ".hproj");
                Controllers.updateAll();
                newProject.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ErrorManager.createReadOnlyJTextField(ex), "Error", JOptionPane.PLAIN_MESSAGE);
                newProject.dispose();
                BaseGui.getInstance().getMainFrame().dispose();
            }
        });

    }
}