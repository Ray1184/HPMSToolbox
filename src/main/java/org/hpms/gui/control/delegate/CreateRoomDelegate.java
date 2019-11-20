package org.hpms.gui.control.delegate;

import org.hpms.gui.control.Controllers;
import org.hpms.gui.control.ToolsController;
import org.hpms.gui.control.w3d.W3DManager;
import org.hpms.gui.data.ProjectModel;
import org.hpms.gui.logic.ProjectManager;
import org.hpms.gui.utils.EasyDocumentListener;
import org.hpms.gui.utils.ErrorManager;
import org.hpms.gui.utils.Utils;
import org.hpms.gui.views.BaseGui;
import org.hpms.gui.views.CreateNewRoom;
import org.hpms.gui.views.Labels;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class CreateRoomDelegate {

    private final CreateNewRoom createNewRoom;

    public CreateRoomDelegate() {
        createNewRoom = new CreateNewRoom(BaseGui.getInstance().getMainFrame());
        initListeners();
    }


    public void createRoom() {
        createNewRoom.getSgMapTxt().setText("");
        createNewRoom.getRoomNameTxt().setText("");
        createNewRoom.pack();
        createNewRoom.setVisible(true);
    }

    private void initListeners() {
        createNewRoom.getRoomTypeCombo().addItem(new Labels.PipelineTypeItem("Retro 2.5D", ProjectModel.RoomModel.PipelineType.R25D));
        createNewRoom.getRoomTypeCombo().addItem(new Labels.PipelineTypeItem("GUI 2D", ProjectModel.RoomModel.PipelineType.GUI));
        createNewRoom.getRoomTypeCombo().addItem(new Labels.PipelineTypeItem("Full 3D", ProjectModel.RoomModel.PipelineType.F3D));

        createNewRoom.getGenerateChk().setEnabled(false);

        createNewRoom.getSgMapLoadBtn().addActionListener(e -> {
            JFileChooser f = new JFileChooser();
            f.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Wavefront OBJ", "obj");
            f.setFileFilter(filter);
            f.showSaveDialog(null);
            f.setAcceptAllFileFilterUsed(false);
            f.setDialogTitle("Select Floor to load");
            try {
                if (f.getSelectedFile() == null) {
                    return;
                }
                createNewRoom.getSgMapTxt().setText(f.getSelectedFile().getAbsolutePath());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ErrorManager.createReadOnlyJTextField(ex), "Error", JOptionPane.PLAIN_MESSAGE);
            }
        });

        createNewRoom.getOkBtn().setEnabled(false);

        createNewRoom.getCancBtn().addActionListener(e -> createNewRoom.dispose());

        createNewRoom.getOkBtn().addActionListener(e -> {
            ProjectModel project = ProjectManager.getInstance().getProjectModel();
            try {
                Utils.copyFile(createNewRoom.getSgMapTxt().getText(), project.getProjectFloorsPath() + File.separator + createNewRoom.getRoomNameTxt().getText().trim() + "_floor");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ErrorManager.createReadOnlyJTextField(ex), "Error", JOptionPane.PLAIN_MESSAGE);
            }
            ProjectModel.RoomModel room = new ProjectModel.RoomModel();
            String roomName = createNewRoom.getRoomNameTxt().getText().trim();
            room.setName(roomName);
            room.setPipelineType(((Labels.PipelineTypeItem) createNewRoom.getRoomTypeCombo().getSelectedItem()).getPipelineType());
            project.getRooms().put(roomName, room);
            W3DManager.getInstance().currentRoom = roomName;
            W3DManager.getInstance().reloadNew = true;

            while (W3DManager.getInstance().reloadNew) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignored) {
                }
            }
            ((ToolsController) Controllers.TOOLS_CONTROLLER.getController()).setAddingRoom(true);
            ((ToolsController) Controllers.TOOLS_CONTROLLER.getController()).setSelectedRoom(roomName);
            Controllers.updateAll();

            createNewRoom.dispose();
        });

        createNewRoom.getRoomNameTxt().getDocument().addDocumentListener((EasyDocumentListener) e -> {
            if (createNewRoom.getRoomNameTxt().getText().isEmpty() || createNewRoom.getSgMapTxt().getText().isEmpty()) {
                createNewRoom.getOkBtn().setEnabled(false);
            } else {
                createNewRoom.getOkBtn().setEnabled(true);
            }
        });

        createNewRoom.getSgMapTxt().getDocument().addDocumentListener((EasyDocumentListener) e -> {
            if (createNewRoom.getRoomNameTxt().getText().isEmpty() || createNewRoom.getSgMapTxt().getText().isEmpty()) {
                createNewRoom.getOkBtn().setEnabled(false);
            } else {
                createNewRoom.getOkBtn().setEnabled(true);
            }
        });


    }
}
