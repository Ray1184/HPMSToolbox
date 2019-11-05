package org.hpms.gui.control;

import org.hpms.gui.control.delegate.EditEventDelegate;
import org.hpms.gui.data.ProjectModel;
import org.hpms.gui.logic.ProjectManager;
import org.hpms.gui.utils.EasyMouseListener;
import org.hpms.gui.views.BaseGui;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ToolsController implements Controller {

    private final EditEventDelegate editEventDelegate = new EditEventDelegate();


    private String selectedRoom;
    private String selectedEvent;
    private String selectedSg;

    public static final int ROOM = 0;
    public static final int SG = 1;
    public static final int EVENT = 2;

    @Override
    public void init() {

        JList roomsList = BaseGui.getInstance().getRoomsList();
        roomsList.addListSelectionListener(e -> selectedRoom = (String) roomsList.getSelectedValue());

        JList eventList = BaseGui.getInstance().getEventsList();
        eventList.addListSelectionListener(e -> selectedEvent = (String) eventList.getSelectedValue());
        eventList.addMouseListener((EasyMouseListener) e -> {
            if (e.getClickCount() >= 2) {
                editEventDelegate.editEvent();
            }
        });
        eventList.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (KeyEvent.VK_CANCEL == e.getKeyCode()) {
                    JFrame frame = new JFrame();
                    int dialogResult = JOptionPane.showConfirmDialog(frame, "Are you sure to delete event " + selectedEvent + "?", "Warning", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        deleteItem(EVENT);
                        frame.dispose();

                    }
                }
            }
        });

        JList sgList = BaseGui.getInstance().getSgList();
        sgList.addListSelectionListener(e -> selectedSg = (String) sgList.getSelectedValue());
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

    private void deleteItem(int item) {
        ProjectModel project = ProjectManager.getInstance().getProjectModel();
        if (project == null) {
            return;
        }
        switch (item) {
            case ROOM:
                if (selectedRoom != null) {
                    project.getRooms().remove(selectedRoom);
                }
                break;
            case SG:
                if (selectedSg != null && selectedRoom != null && project.getRooms().get(selectedRoom) != null) {
                    project.getRooms().get(selectedRoom).getSectorGroupById().remove(selectedSg);
                }
                break;
            case EVENT:
                if (selectedEvent != null) {
                    project.getCommonFunctions().remove(selectedEvent);
                    if (selectedRoom != null && project.getRooms().get(selectedRoom) != null) {
                        project.getRooms().get(selectedRoom).getEventsById().remove(selectedEvent);
                    }
                }
                break;
        }
        Controllers.updateAll();
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
