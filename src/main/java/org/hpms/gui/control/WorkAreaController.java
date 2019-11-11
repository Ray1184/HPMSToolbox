package org.hpms.gui.control;

import org.hpms.gui.control.w3d.W3DArea;
import org.hpms.gui.data.ProjectModel;
import org.hpms.gui.logic.ProjectManager;
import org.hpms.gui.utils.ErrorManager;
import org.hpms.gui.views.BaseGui;

import javax.swing.*;

public class WorkAreaController implements Controller {
    private final W3DArea w3DArea = new W3DArea(BaseGui.getInstance().getWorkArea());


    @Override
    public void init() {
        try {
            new SwingWorker<Void, Void>() {

                @Override
                protected Void doInBackground() throws Exception {
                    w3DArea.loop();
                    return null;
                }

                @Override
                protected void done() {
                    super.done();
                    System.out.println("WHY HERE");
                }
            }.execute();


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, ErrorManager.createReadOnlyJTextField(e), "Error", JOptionPane.PLAIN_MESSAGE);
            BaseGui.getInstance().getMainFrame().dispose();
        }
    }

    @Override
    public void update() {

        ProjectModel project = ProjectManager.getInstance().getProjectModel();
        if (project == null) {
            return;
        }
        float x = project.getUserSettings().getLastMapX();
        float y = project.getUserSettings().getLastMapY();
        float zoom = project.getUserSettings().getLastZoomFactor();
        BaseGui gui = BaseGui.getInstance();
        String roomId = (String) gui.getRoomsList().getSelectedValue();
        if (roomId == null) {
            return;
        }
        ProjectModel.RoomModel room = project.getRooms().get(roomId);
        if (room == null) {
            return;
        }


    }


}
