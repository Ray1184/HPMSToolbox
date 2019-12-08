package org.hpms.gui.control;

import org.hpms.gui.control.delegate.EditEventDelegate;
import org.hpms.gui.control.w3d.W3DManager;
import org.hpms.gui.data.ProjectModel;
import org.hpms.gui.logic.ProjectManager;
import org.hpms.gui.utils.EasyMouseListener;
import org.hpms.gui.utils.ListEntry;
import org.hpms.gui.views.BaseGui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import static org.hpms.gui.utils.ErrorManager.createReadOnlyJTextField;

public class ToolsController implements Controller {

    public static final int ROOM = 0;
    public static final int SG = 1;
    public static final int EVENT = 2;
    public static final int FUNCTION = 3;
    private final EditEventDelegate editEventDelegate = new EditEventDelegate();
    private String selectedRoom;
    private String selectedEvent;
    private String selectedSg;
    private String selectedFunction;
    private boolean addingRoom;
    private boolean deletingRoom;

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
                if (functionsList.getModel().getSize() == 0) {
                    return;
                }
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
                if (eventList.getModel().getSize() == 0) {
                    return;
                }
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
                W3DManager.getInstance().refreshSectorGroups = true;
            }
        });


        sgList.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (sgList.getModel().getSize() == 0) {
                    return;
                }
                if (KeyEvent.VK_DELETE == e.getKeyCode()) {
                    JFrame frame = new JFrame();
                    int dialogResult = JOptionPane.showConfirmDialog(frame, "Are you sure to delete sector group " + selectedSg + "?", "Warning", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        deleteItem(SG);
                        frame.dispose();

                    }
                }
            }
        });


        JList roomsList = BaseGui.getInstance().getRoomsList();
        roomsList.addListSelectionListener(e -> {
            try {
                if (roomsList.getModel().getSize() > 0) {
                    String nextSelectedRoom = null;


                    if (roomsList.getSelectedValue() != null) {
                        nextSelectedRoom = ((ListEntry) roomsList.getSelectedValue()).getValue();
                    } else if (deletingRoom) {
                        deletingRoom = false;
                        nextSelectedRoom = ((ListEntry) roomsList.getModel().getElementAt(0)).getValue();
                        roomsList.setSelectedIndex(0);
                    } else if (addingRoom) {
                        addingRoom = false;
                        manageEventsSelection();
                        manageSectorGroupsSelection();
                        recheckSelections(eventList, sgList);
                        manageEventsSelection();
                        manageSectorGroupsSelection();
                        W3DManager.getInstance().reload = true;
                        return;
                    }
                    if (roomsList.getSelectedValue() != null) {
                        if (nextSelectedRoom != null && !nextSelectedRoom.equals(selectedRoom)) {
                            selectedRoom = nextSelectedRoom;

                            // Re-check selections for room dependent items.
                            manageEventsSelection();
                            manageSectorGroupsSelection();

                            recheckSelections(eventList, sgList);

                            manageEventsSelection();
                            manageSectorGroupsSelection();
                        }
                    }
                }
                W3DManager.getInstance().reload = true;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, createReadOnlyJTextField(ex), "Error", JOptionPane.PLAIN_MESSAGE);
                System.exit(-1);
            }
        });


        roomsList.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (roomsList.getModel().getSize() == 0) {
                    return;
                }
                if (KeyEvent.VK_DELETE == e.getKeyCode()) {
                    JFrame frame = new JFrame();
                    int dialogResult = JOptionPane.showConfirmDialog(frame, "Are you sure to delete room " + selectedRoom + "?", "Warning", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        deletingRoom = true;
                        deleteItem(ROOM);
                        frame.dispose();

                    }
                }
            }
        });
    }

    private void recheckSelections(JList eventList, JList sgList) {
        if (eventList.getModel() == null || eventList.getModel().getSize() == 0) {
            selectedEvent = null;
        } else {
            selectedEvent = ((ListEntry) eventList.getModel().getElementAt(0)).getValue();

        }
        if (sgList.getModel() == null || sgList.getModel().getSize() == 0) {
            selectedSg = null;
        } else {
            selectedSg = ((ListEntry) sgList.getModel().getElementAt(0)).getValue();

        }
    }


    @Override
    public void update() {
        try {
            manageRoomsSelection();
            manageFunctionsSelection();
            manageEventsSelection();
            manageSectorGroupsSelection();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, createReadOnlyJTextField(ex), "Error", JOptionPane.PLAIN_MESSAGE);
            System.exit(-1);
        }

        if (selectedRoom == null) {
            // If all rooms/sg are deleted, show black screen on 3D board.
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
                    File f = new File(project.getProjectFloorsPath() + File.separator + selectedRoom + "_floor");
                    if (!f.delete()) {
                        f.deleteOnExit();
                    }
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

    private void manageFunctionsSelection() throws IOException {
        ProjectModel projectModel = ProjectManager.getInstance().getProjectModel();
        if (projectModel == null) {
            return;
        }
        JList fnList = BaseGui.getInstance().getFunctionsList();
        DefaultListModel<ListEntry> fnModel = new DefaultListModel<>();
        fnList.removeAll();
        fnList.setModel(fnModel);

        int index = 0;
        boolean found = false;
        for (String fn : projectModel.getCommonFunctions().keySet()) {
            if (!fn.equals(selectedFunction) && !found) {
                index++;
            } else {
                found = true;
            }
            fnModel.addElement(new ListEntry(fn, new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/icons/static_event.png")))));
        }

        if (selectedFunction == null && !fnModel.isEmpty()) {
            selectedFunction = fnModel.get(0).getValue();
            fnList.setSelectedIndex(0);
        } else {
            fnList.setSelectedIndex(index);
        }
    }

    private void manageRoomsSelection() throws IOException {
        ProjectModel projectModel = ProjectManager.getInstance().getProjectModel();
        if (projectModel == null) {
            return;
        }

        JList roomsList = BaseGui.getInstance().getRoomsList();
        DefaultListModel<ListEntry> rModel = new DefaultListModel<>();
        roomsList.removeAll();
        roomsList.setModel(rModel);

        int index = 0;
        boolean found = false;
        for (String room : projectModel.getRooms().keySet()) {
            if (!room.equals(selectedRoom) && !found) {
                index++;
            } else {
                found = true;
            }
            rModel.addElement(new ListEntry(room, new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/icons/room.png")))));
        }
        if (selectedRoom == null && !rModel.isEmpty()) {
            selectedRoom = rModel.get(0).getValue();
            roomsList.setSelectedIndex(0);
        } else {
            roomsList.setSelectedIndex(index);
        }
    }

    private void manageEventsSelection() throws IOException {
        ProjectModel projectModel = ProjectManager.getInstance().getProjectModel();
        if (projectModel == null || selectedRoom == null || !projectModel.getRooms().containsKey(selectedRoom)) {
            return;
        }

        JList eventList = BaseGui.getInstance().getEventsList();
        DefaultListModel<ListEntry> eModel = new DefaultListModel<>();
        eventList.removeAll();
        eventList.setModel(eModel);

        int index = 0;
        boolean found = false;
        for (String evt : projectModel.getRooms().get(selectedRoom).getEventsById().keySet()) {
            if (!evt.equals(selectedEvent) && !found) {
                index++;
            } else {
                found = true;
            }
            eModel.addElement(new ListEntry(evt, new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/icons/event.png")))));
        }

        if (selectedEvent == null && !eModel.isEmpty()) {
            selectedEvent = eModel.get(0).getValue();
            eventList.setSelectedIndex(0);
        } else {
            eventList.setSelectedIndex(index);
        }


    }

    private void manageSectorGroupsSelection() throws IOException {
        ProjectModel projectModel = ProjectManager.getInstance().getProjectModel();
        if (projectModel == null || selectedRoom == null || !projectModel.getRooms().containsKey(selectedRoom)) {
            return;
        }

        JList sgList = BaseGui.getInstance().getSgList();
        DefaultListModel<ListEntry> sgModel = new DefaultListModel<>();

        sgList.removeAll();
        sgList.setModel(sgModel);

        int index = 0;
        boolean found = false;
        for (String sg : projectModel.getRooms().get(selectedRoom).getSectorGroupById().keySet()) {
            if (!sg.equals(selectedSg) && !found) {
                index++;
            } else {
                found = true;
            }
            sgModel.addElement(new ListEntry(sg, new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/icons/sg.png")))));
        }

        if (selectedSg == null && !sgModel.isEmpty()) {
            selectedSg = sgModel.get(0).getValue();
            sgList.setSelectedIndex(0);
        } else {
            sgList.setSelectedIndex(index);
        }
    }


    public synchronized String getSelectedRoom() {
        return selectedRoom;
    }

    public synchronized void setSelectedRoom(String selectedRoom) {
        this.selectedRoom = selectedRoom;
    }

    public synchronized String getSelectedEvent() {
        return selectedEvent;
    }

    public synchronized void setSelectedEvent(String selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    public synchronized String getSelectedSg() {
        return selectedSg;
    }

    public synchronized void setSelectedSg(String selectedSg) {
        this.selectedSg = selectedSg;
    }

    public synchronized String getSelectedFunction() {
        return selectedFunction;
    }

    public synchronized void setSelectedFunction(String selectedFunction) {
        this.selectedFunction = selectedFunction;
    }


    public synchronized boolean isAddingRoom() {
        return addingRoom;
    }

    public synchronized void setAddingRoom(boolean addingRoom) {
        this.addingRoom = addingRoom;
    }
}
