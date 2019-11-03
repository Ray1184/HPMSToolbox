package org.hpms.gui.control;

import org.hpms.gui.control.delegate.EditEventDelegate;
import org.hpms.gui.data.ProjectModel;
import org.hpms.gui.logic.ProjectManager;
import org.hpms.gui.utils.EasyMouseListener;
import org.hpms.gui.views.BaseGui;

import javax.swing.*;

public class ToolsController implements Controller {

    private final EditEventDelegate editEventDelegate = new EditEventDelegate();


    private String selectedRoom;
    private String selectedEvent;
    private String selectedSg;

    @Override
    public void init() {

        JList roomsList = BaseGui.getInstance().getRoomsList();
        roomsList.addListSelectionListener(e -> {
            selectedRoom = (String) roomsList.getSelectedValue();
        });
        JList eventList = BaseGui.getInstance().getEventsList();
        eventList.addListSelectionListener(e -> {
            selectedEvent = (String) eventList.getSelectedValue();
        });
        eventList.addMouseListener((EasyMouseListener) e -> {
            if (e.getClickCount() >= 2) {
            }
        });
        JList sgList = BaseGui.getInstance().getSgList();
        sgList.addListSelectionListener(e -> {
            selectedSg = (String) sgList.getSelectedValue();
        });
    }

    @Override
    public void update() {
        JList roomsList = BaseGui.getInstance().getRoomsList();
        JList eventList = BaseGui.getInstance().getEventsList();
        JList sgList = BaseGui.getInstance().getSgList();


        ProjectModel projectModel = ProjectManager.getInstance().getProjectModel();
        if (projectModel == null) {
            return;
        }
        DefaultListModel<String> rModel = new DefaultListModel<>();
        DefaultListModel<String> eModel = new DefaultListModel<>();
        DefaultListModel<String> sgModel = new DefaultListModel<>();


        roomsList.removeAll();
        roomsList.setModel(rModel);
        eventList.removeAll();
        eventList.setModel(eModel);
        sgList.removeAll();
        sgList.setModel(sgModel);

        for (ProjectModel.RoomModel room : projectModel.getRooms().values()) {
            rModel.addElement(room.getName());
        }


        for (String evt : projectModel.getCommonFunctions().keySet()) {
            eModel.addElement(evt);
        }

        if (selectedRoom != null) {

            for (String evt : projectModel.getRooms().get(selectedRoom).getEventsById().keySet()) {
                eModel.addElement(evt);
            }


            for (String sg : projectModel.getRooms().get(selectedRoom).getSectorGroupById().keySet()) {
                sgModel.addElement(sg);
            }
        }


    }


    public String getSelectedRoom() {
        return selectedRoom;
    }

    public String getSelectedEvent() {
        return selectedEvent;
    }

    public String getSelectedSg() {
        return selectedSg;
    }
}
