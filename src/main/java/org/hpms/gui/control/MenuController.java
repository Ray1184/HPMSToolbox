package org.hpms.gui.control;

import org.hpms.gui.AppInfo;
import org.hpms.gui.control.delegate.*;
import org.hpms.gui.data.ProjectModel;
import org.hpms.gui.logic.ProjectManager;
import org.hpms.gui.views.BaseGui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.hpms.gui.utils.ErrorManager.createReadOnlyJTextField;

public class MenuController implements Controller, ActionListener {

    private final NewProjectDelegate newProjectDelegate = new NewProjectDelegate();
    private final LoadProjectDelegate loadProjectDelegate = new LoadProjectDelegate();
    private final CreateEventDelegate createEventDelegate = new CreateEventDelegate();
    private final CreateRoomDelegate createRoomDelegate = new CreateRoomDelegate();
    private final CreateSectorGroupDelegate createSectorGroupDelegate = new CreateSectorGroupDelegate();
    private List<JMenuItem> items;

    @Override
    public void init() {
        BaseGui gui = BaseGui.getInstance();
        items = new ArrayList<>();

        items.add(gui.getNewProjectBtn());
        items.add(gui.getLoadProjectBtn());
        items.add(gui.getSaveProjectBtn());
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
                newProjectDelegate.newProject();
                break;
            case "LOAD_PROJECT":
                loadProjectDelegate.loadProject();
                break;
            case "SAVE":
                try {
                    if (ProjectManager.getInstance().noProject()) {
                        return;
                    }
                    ProjectManager.getInstance().persistToFile(ProjectManager.getInstance().getProjectModel().getProjectPath() +
                            File.separator + ProjectManager.getInstance().getProjectModel().getProjectName() + ".hproj");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, createReadOnlyJTextField(ex), "Error", JOptionPane.PLAIN_MESSAGE);
                    System.exit(-1);
                }
                break;
            case "PREFERENCES":

                break;
            case "EXIT_PROJECT":

                break;
            case "CREATE_ROOM":
                createRoomDelegate.createRoom();
                break;
            case "CREATE_SECTOR_GROUP":
                createSectorGroupDelegate.createSectorGroup();
                break;
            case "CREATE_EVENT":
                createEventDelegate.createEvent();
                break;
            case "BUILD_RUNTIME":

                break;
            case "BUILD_SETTINGS":

                break;
            case "TUTORIALS":

                break;
            case "ABOUT":

                break;
        }
    }


    @Override
    public void update() {
        for (JMenuItem item : items) {
            if (item.getActionCommand().equals("SAVE") || item.getActionCommand().equals("CREATE_ROOM") || item.getActionCommand().equals("CREATE_SECTOR_GROUP") || item.getActionCommand().equals("CREATE_EVENT") || item.getActionCommand().equals("BUILD_RUNTIME")) {
                if (ProjectManager.getInstance().noProject()) {
                    item.setEnabled(false);
                } else {
                    item.setEnabled(true);
                }
            }
        }
        StringBuilder info = new StringBuilder(" HPMS Toolbox ")
                .append(AppInfo.VERSION);
        ProjectModel project = ProjectManager.getInstance().getProjectModel();
        if (project != null) {
            info.append(" - [")
                    .append(project.getProjectPath()).append(File.separator).append(project.getProjectName())
                    .append(".hproj]");
        }
        BaseGui gui = BaseGui.getInstance();
        gui.getInfoLabel().setText(info.toString());


    }


}
