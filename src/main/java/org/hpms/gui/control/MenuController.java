package org.hpms.gui.control;

import org.hpms.gui.logic.ProjectManager;
import org.hpms.gui.views.BaseGui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MenuController implements ActionListener {
    private final List<JMenuItem> items;

    public MenuController() {

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

    }

    private void buildRuntime() {

    }

    private void buildSettings() {

    }

    private void tutorials() {

    }

    private void about() {

    }

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
