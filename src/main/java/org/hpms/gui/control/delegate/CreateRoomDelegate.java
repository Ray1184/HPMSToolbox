package org.hpms.gui.control.delegate;

import org.hpms.gui.control.Controllers;
import org.hpms.gui.control.w3d.W3DManager;
import org.hpms.gui.data.ProjectModel;
import org.hpms.gui.logic.ProjectManager;
import org.hpms.gui.utils.EasyDocumentListener;
import org.hpms.gui.utils.ErrorManager;
import org.hpms.gui.utils.ListEntry;
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
            room.setName(createNewRoom.getRoomNameTxt().getText().trim());
            room.setPipelineType(((Labels.PipelineTypeItem) createNewRoom.getRoomTypeCombo().getSelectedItem()).getPipelineType());
            project.getRooms().put(createNewRoom.getRoomNameTxt().getText().trim(), room);
            //JList roomsList = BaseGui.getInstance().getRoomsList();
            //roomsList.setSelectedValue(new ListEntry(createNewRoom.getRoomNameTxt().getText(), new ImageIcon("icons/room.png")), true);
            W3DManager.getInstance().reloadNew = true;
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