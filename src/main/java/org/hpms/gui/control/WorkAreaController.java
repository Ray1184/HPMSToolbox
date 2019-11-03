package org.hpms.gui.control;

import org.hpms.gui.data.ProjectModel;
import org.hpms.gui.logic.ProjectManager;
import org.hpms.gui.views.BaseGui;

import java.awt.*;
import java.util.Map;

import static org.hpms.gui.data.ProjectModel.DEFAULT_SG_NAME;

public class WorkAreaController implements Controller {

    private Graphics g;

    private boolean changed;

    public static final int NORMALIZE_RATIO = 10;

    /**
     * Setter for property 'g'.
     *
     * @param g Value to set for property 'g'.
     */
    public void setG(Graphics g) {
        this.g = g;
    }

    @Override
    public void init() {

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
        for (Map.Entry<String, ProjectModel.RoomModel.SectorGroup> sectorGroup : room.getSectorGroupById().entrySet()) {
            if (sectorGroup.getKey().equals(DEFAULT_SG_NAME)) {
                ((Graphics2D) g).setStroke(new BasicStroke(1));
                g.setColor(Color.white);
                for (ProjectModel.RoomModel.SectorGroup.Sector sector : sectorGroup.getValue().getSectors()) {
                    g.drawPolygon(normalize3(new float[]{sector.getX1(), sector.getX2(), sector.getX3()}, x, zoom), normalize3(new float[]{sector.getZ1(), sector.getZ2(), sector.getZ3()}, y, zoom), 3);
                }
                g.dispose();
            }
        }


    }

    /**
     * Getter for property 'changed'.
     *
     * @return Value for property 'changed'.
     */
    public boolean isChanged() {
        return changed;
    }



    private static int[] normalize3(float[] floats, float offset, float zoom) {
        return new int[]{
                (int) (floats[0] * NORMALIZE_RATIO * zoom) + (int) offset,
                (int) (floats[1] * NORMALIZE_RATIO * zoom) + (int) offset,
                (int) (floats[2] * NORMALIZE_RATIO * zoom) + (int) offset};
    }


}
