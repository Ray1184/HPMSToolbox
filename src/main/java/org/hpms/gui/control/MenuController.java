package org.hpms.gui.control;

import org.hpms.gui.AppInfo;
import org.hpms.gui.control.wizard.EventWizardDelegate;
import org.hpms.gui.logic.ProjectManager;
import org.hpms.gui.utils.EasyDocumentListener;
import org.hpms.gui.views.BaseGui;
import org.hpms.gui.views.CreateNewEventWizard;
import org.hpms.gui.views.CreateNewProject;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.hpms.gui.utils.ErrorManager.createReadOnlyJTextField;

public class MenuController implements Controller, ActionListener {

    private List<JMenuItem> items;

    @Override
    public void init() {
        BaseGui gui = BaseGui.getInstance();
        items = new ArrayList<>();

        items.add(gui.getNewProjectBtn());
        items.add(gui.getLoadProjectBtn());
        items.add(gui.getSettingsProjectBtn());
        items.add(gui.getExitProjectBtn());
        items.add(gui.getNewRoomToolsBtn());
        items.add(gui.getNewSecGrToolsBtn());
        items.add(gui.getNewEventTolsBtn());
        items.add(gui.getRuntimeBuildBtn());
        items.add(gui.getSettingsBuildBtn());
        items.add(gui.getTutorialsHelpBtn());
        items.add(gui.getAboutHelpsBtn());

        initButtonListeners(items);
    }

    private void initButtonListeners(List<JMenuItem> items) {

        for (JMenuItem item : items) {
            item.addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "NEW_PROJECT":
                newProject();
                break;
            case "LOAD_PROJECT":
                loadProject();
                break;
            case "PREFERENCES":
                preferences();
                break;
            case "EXIT_PROJECT":
                exitProject();
                break;
            case "CREATE_ROOM":
                createRoom();
                break;
            case "CREATE_SECTOR_GROUP":
                createSectorGroup();
                break;
            case "CREATE_EVENT":
                createEvent();
                break;
            case "BUILD_RUNTIME":
                buildRuntime();
                break;
            case "BUILD_SETTINGS":
                buildSettings();
                break;
            case "TUTORIALS":
                tutorials();
                break;
            case "ABOUT":
                about();
                break;
        }
    }

    private void newProject() {
        CreateNewProject newProject = new CreateNewProject(BaseGui.getInstance().getMainFrame());
        newProject.pack();
        newProject.getOkBtn().setEnabled(false);
        newProject.setVisible(true);
        newProject.getCancBtn().addActionListener(e -> {
            newProject.dispose();
        });
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
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, createReadOnlyJTextField(ex), "Error", JOptionPane.PLAIN_MESSAGE);
                newProject.dispose();
                BaseGui.getInstance().getMainFrame().dispose();
            }
        });

    }

    private void loadProject() {

    }

    private void preferences() {

    }

    private void exitProject() {

    }

    private void createRoom() {

    }

    private void createSectorGroup() {

    }

    private void createEvent() {
        EventWizardDelegate wizard = new EventWizardDelegate();
        wizard.build();

    }

    private void buildRuntime() {

    }

    private void buildSettings() {

    }

    private void tutorials() {

    }

    private void about() {

    }

    @Override
    public void update() {
        for (JMenuItem item : items) {
            if (item.getActionCommand().equals("CREATE_ROOM") || item.getActionCommand().equals("CREATE_SECTOR_GROUP") || item.getActionCommand().equals("CREATE_EVENT") || item.getActionCommand().equals("BUILD_RUNTIME")) {
                if (ProjectManager.getInstance().noProject()) {
                    item.setEnabled(false);
                } else {
                    item.setEnabled(true);
                }
            }
        }
    }


}
