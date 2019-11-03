package org.hpms.gui.control;

import org.hpms.gui.control.delegate.CreateEventDelegate;
import org.hpms.gui.control.delegate.LoadProjectDelegate;
import org.hpms.gui.control.delegate.NewProjectDelegate;
import org.hpms.gui.logic.ProjectManager;
import org.hpms.gui.views.BaseGui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MenuController implements Controller, ActionListener {

    private final NewProjectDelegate newProjectDelegate = new NewProjectDelegate();
    private final LoadProjectDelegate loadProjectDelegate = new LoadProjectDelegate();
    private final CreateEventDelegate createEventDelegate = new CreateEventDelegate();
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
                newProjectDelegate.newProject();
                break;
            case "LOAD_PROJECT":
                loadProjectDelegate.loadProject();
                break;
            case "PREFERENCES":

                break;
            case "EXIT_PROJECT":

                break;
            case "CREATE_ROOM":

                break;
            case "CREATE_SECTOR_GROUP":

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
