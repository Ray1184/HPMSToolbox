package org.hpms.gui.control.delegate;

import org.hpms.gui.control.Controllers;
import org.hpms.gui.control.ToolsController;
import org.hpms.gui.data.ProjectModel;
import org.hpms.gui.logic.ProjectManager;
import org.hpms.gui.utils.EasyDocumentListener;
import org.hpms.gui.views.BaseGui;
import org.hpms.gui.views.CreateNewSectorGroup;

public class CreateSectorGroupDelegate {

    private final CreateNewSectorGroup createNewSectorGroup;

    public CreateSectorGroupDelegate() {
        createNewSectorGroup = new CreateNewSectorGroup(BaseGui.getInstance().getMainFrame());
        initListeners();
    }


    public void createSectorGroup() {
        createNewSectorGroup.getNewSgNameTxt().setText("");
        createNewSectorGroup.pack();
        createNewSectorGroup.setVisible(true);
        createNewSectorGroup.getOkBtn().setEnabled(!createNewSectorGroup.getNewSgNameTxt().getText().isEmpty());
    }

    private void initListeners() {
        createNewSectorGroup.getOkBtn().setEnabled(false);
        createNewSectorGroup.getCancBtn().addActionListener(e -> createNewSectorGroup.dispose());
        createNewSectorGroup.getNewSgNameTxt().getDocument().addDocumentListener((EasyDocumentListener) e -> {
            if (createNewSectorGroup.getNewSgNameTxt().getText().isEmpty()) {
                createNewSectorGroup.getOkBtn().setEnabled(false);
            } else {
                createNewSectorGroup.getOkBtn().setEnabled(true);
            }
        });

        createNewSectorGroup.getOkBtn().addActionListener(e -> {
            String selectedRoom = ((ToolsController) Controllers.TOOLS_CONTROLLER.getController()).getSelectedRoom();
            ProjectModel model = ProjectManager.getInstance().getProjectModel();
            ProjectModel.RoomModel roomModel = model.getRooms().get(selectedRoom);
            if (roomModel == null) {
                return;
            }
            String sgName = createNewSectorGroup.getNewSgNameTxt().getText();
            ProjectModel.RoomModel.SectorGroup sg = new ProjectModel.RoomModel.SectorGroup();
            sg.setId(sgName);
            roomModel.getSectorGroupById().put(sgName, sg);
            ((ToolsController) Controllers.TOOLS_CONTROLLER.getController()).setSelectedSg(sgName);
            Controllers.updateAll();
            createNewSectorGroup.dispose();

        });
    }
}
