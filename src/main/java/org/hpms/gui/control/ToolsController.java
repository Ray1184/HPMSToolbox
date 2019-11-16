package org.hpms.gui.control;

import org.hpms.gui.control.delegate.EditEventDelegate;
import org.hpms.gui.control.w3d.W3DManager;
import org.hpms.gui.data.ProjectModel;
import org.hpms.gui.logic.ProjectManager;
import org.hpms.gui.utils.EasyMouseListener;
import org.hpms.gui.utils.ListEntry;
import org.hpms.gui.views.BaseGui;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ToolsController implements Controller {

    private final EditEventDelegate editEventDelegate = new EditEventDelegate();


    private String selectedRoom;
    private String selectedEvent;
    private String selectedSg;
    private String selectedFunction;

    public static final int ROOM = 0;
    public static final int SG = 1;
    public static final int EVENT = 2;
    public static final int FUNCTION = 3;

    @Override
    public void init() {


        JList functionsList = BaseGui.getInstance().getFunctionsList();
        functionsList.addListSelectionListener(e -> {
                    if (functionsList.getSelectedValue() != null) {
                        selectedFunction = ((ListEntry) functionsList.getSelectedValue()).getValue();
                    }
                }
        );
        functionsList.addMouseListener((EasyMouseListener) e -> {
            if (e.getClickCount() >= 2) {
                editEventDelegate.editEvent();
            }
        });

        functionsList.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (KeyEvent.VK_DELETE == e.getKeyCode()) {
                    JFrame frame = new JFrame();
                    int dialogResult = JOptionPane.showConfirmDialog(frame, "Are you sure to delete function " + selectedFunction + "?", "Warning", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        deleteItem(FUNCTION);
                        frame.dispose();

                    }
                }
            }
        });

        JList eventList = BaseGui.getInstance().getEventsList();
        eventList.addListSelectionListener(e -> {
            if (eventList.getSelectedValue() != null) {
                selectedEvent = ((ListEntry) eventList.getSelectedValue()).getValue();
            }

        });
        eventList.addMouseListener((EasyMouseListener) e -> {
            if (e.getClickCount() >= 2) {
                editEventDelegate.editEvent();
            }
        });

        eventList.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (KeyEvent.VK_DELETE == e.getKeyCode()) {
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
        sgList.addListSelectionListener(e -> {
            if (sgList.getSelectedValue() != null) {
                selectedSg = ((ListEntry) sgList.getSelectedValue()).getValue();
            }
        });


        sgList.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (KeyEvent.VK_DELETE == e.getKeyCode()) {
                    JFrame frame = new JFrame();
                    int dialogResult = JOptionPane.showConfirmDialog(frame, "Are you sure to delete sector group " + selectedRoom + "?", "Warning", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        deleteItem(SG);
                        frame.dispose();

                    }
                }
            }
        });


        JList roomsList = BaseGui.getInstance().getRoomsList();
        roomsList.addListSelectionListener(e -> {
            if (roomsList.getSelectedValue() != null) {
                selectedRoom = ((ListEntry) roomsList.getSelectedValue()).getValue();

                // Re-check selections for room dependent items.
                ProjectModel projectModel = ProjectManager.getInstance().getProjectModel();
                if (projectModel == null) {
                    return;
                }

                DefaultListModel<ListEntry> eModel = new DefaultListModel<>();
                DefaultListModel<ListEntry> sgModel = new DefaultListModel<>();
                eventList.removeAll();
                eventList.setModel(eModel);
                sgList.removeAll();
                sgList.setModel(sgModel);
                for (String evt : projectModel.getRooms().get(selectedRoom).getEventsById().keySet()) {
                    eModel.addElement(new ListEntry(evt, new ImageIcon("icons/event.png")));
                }

                if (selectedEvent == null && !eModel.isEmpty()) {
                    selectedEvent = eModel.get(0).getValue();
                    eventList.setSelectedIndex(0);
                }


                for (String sg : projectModel.getRooms().get(selectedRoom).getSectorGroupById().keySet()) {
                    sgModel.addElement(new ListEntry(sg, new ImageIcon("icons/sg.png")));
                }

                if (selectedSg == null && !sgModel.isEmpty()) {
                    selectedSg = sgModel.get(0).getValue();
                    sgList.setSelectedIndex(0);
                }

                W3DManager.getInstance().reload = true;

            }
        });


        roomsList.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (KeyEvent.VK_DELETE == e.getKeyCode()) {
                    JFrame frame = new JFrame();
                    int dialogResult = JOptionPane.showConfirmDialog(frame, "Are you sure to delete room " + selectedRoom + "?", "Warning", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        deleteItem(ROOM);
                        frame.dispose();

                    }
                }
            }
        });
    }


    @Override
    public void update() {
        JList roomsList = BaseGui.getInstance().getRoomsList();
        JList eventList = BaseGui.getInstance().getEventsList();
        JList sgList = BaseGui.getInstance().getSgList();
        JList fnList = BaseGui.getInstance().getFunctionsList();


        ProjectModel projectModel = ProjectManager.getInstance().getProjectModel();
        if (projectModel == null) {
            return;
        }
        DefaultListModel<ListEntry> rModel = new DefaultListModel<>();
        DefaultListModel<ListEntry> eModel = new DefaultListModel<>();
        DefaultListModel<ListEntry> sgModel = new DefaultListModel<>();
        DefaultListModel<ListEntry> fnModel = new DefaultListModel<>();

        roomsList.removeAll();
        roomsList.setModel(rModel);
        eventList.removeAll();
        eventList.setModel(eModel);
        sgList.removeAll();
        sgList.setModel(sgModel);
        fnList.removeAll();
        fnList.setModel(fnModel);

        for (String room : projectModel.getRooms().keySet()) {
            rModel.addElement(new ListEntry(room, new ImageIcon("icons/room.png")));
        }
        if (selectedRoom == null && !rModel.isEmpty()) {
            selectedRoom = rModel.get(0).getValue();
            roomsList.setSelectedIndex(0);
        }


        for (String fn : projectModel.getCommonFunctions().keySet()) {
            fnModel.addElement(new ListEntry(fn, new ImageIcon("icons/static_event.png")));
        }

        if (selectedFunction == null && !fnModel.isEmpty()) {
            selectedFunction = fnModel.get(0).getValue();
            fnList.setSelectedIndex(0);
        }

        if (selectedRoom != null) {

            for (String evt : projectModel.getRooms().get(selectedRoom).getEventsById().keySet()) {
                eModel.addElement(new ListEntry(evt, new ImageIcon("icons/event.png")));
            }

            if (selectedEvent == null && !eModel.isEmpty()) {
                selectedEvent = eModel.get(0).getValue();
                eventList.setSelectedIndex(0);
            }


            for (String sg : projectModel.getRooms().get(selectedRoom).getSectorGroupById().keySet()) {
                sgModel.addElement(new ListEntry(sg, new ImageIcon("icons/sg.png")));
            }

            if (selectedSg == null && !sgModel.isEmpty()) {
                selectedSg = sgModel.get(0).getValue();
                sgList.setSelectedIndex(0);
            }
        } else {
            // If all rooms are deleted, show black screen on 3D board.
            W3DManager.getInstance().reload = true;
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
                    selectedRoom = null;
                }
                break;
            case SG:
                if (selectedSg != null && selectedRoom != null && project.getRooms().get(selectedRoom) != null) {
                    project.getRooms().get(selectedRoom).getSectorGroupById().remove(selectedSg);
                    selectedSg = null;
                }
                break;
            case EVENT:
                if (selectedEvent != null && selectedRoom != null && project.getRooms().get(selectedRoom) != null) {
                    project.getRooms().get(selectedRoom).getEventsById().remove(selectedEvent);
                    selectedEvent = null;
                }
                break;
            case FUNCTION:
                if (selectedFunction != null) {
                    project.getCommonFunctions().remove(selectedFunction);
                    selectedFunction = null;
                }
                break;
        }
        Controllers.updateAll();
    }


    public synchronized String getSelectedRoom() {
        return selectedRoom;
    }

    public synchronized String getSelectedEvent() {
        return selectedEvent;
    }

    public synchronized String getSelectedSg() {
        return selectedSg;
    }

    public synchronized String getSelectedFunction() {
        return selectedFunction;
    }
}
