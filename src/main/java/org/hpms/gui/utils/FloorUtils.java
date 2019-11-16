package org.hpms.gui.utils;

import com.threed.jpct.GenericVertexController;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;
import org.hpms.gui.data.ProjectModel;
import org.hpms.gui.data.W3DUserData;

public class FloorUtils {

    public static void calculateData(Object3D object3D) {
        ProjectModel.RoomModel.SectorGroup.Sector sectorData = new ProjectModel.RoomModel.SectorGroup.Sector();

        object3D.getMesh().setVertexController(new GenericVertexController() {
            @Override
            public void apply() {
                SimpleVector[] vertexVector = getSourceMesh();
                sectorData.setX1(vertexVector[0].x);
                sectorData.setX2(vertexVector[1].x);
                sectorData.setX3(vertexVector[2].x);

                sectorData.setY1(vertexVector[0].y);
                sectorData.setY2(vertexVector[1].y);
                sectorData.setY3(vertexVector[2].y);

                sectorData.setZ1(vertexVector[0].z);
                sectorData.setZ2(vertexVector[1].z);
                sectorData.setZ3(vertexVector[2].z);

            }
        }, false);
        object3D.getMesh().applyVertexController();
        W3DUserData userData = object3D.getUserObject() == null ? new W3DUserData() : (W3DUserData) object3D.getUserObject();
        userData.setSectorData(sectorData);
        object3D.setUserObject(userData);
    }

    public static void reassignSectors(Object3D object3D, ProjectModel project, String room) {
        W3DUserData userData = (W3DUserData) object3D.getUserObject();

        if (userData == null || userData.getSectorData() == null || room == null || project.getRooms().get(room) == null) {
            return;
        }
        for (ProjectModel.RoomModel.SectorGroup sectorGroup : project.getRooms().get(room).getSectorGroupById().values()) {
            if (sectorGroup.getId().equals(userData.getSectorGroupID())) {
                return;
            }
            for (ProjectModel.RoomModel.SectorGroup.Sector sector : sectorGroup.getSectors()) {
                ProjectModel.RoomModel.SectorGroup.Sector objSector = userData.getSectorData();
                if (sector.equals(objSector)) {
                    userData.setSectorGroupID(sectorGroup.getId());
                    return;
                }
            }
        }

    }


}
