package org.hpms.gui.control.w3d;

import com.threed.jpct.*;
import org.hpms.gui.data.ProjectModel;
import org.hpms.gui.data.W3DUserData;
import org.hpms.gui.utils.GraphicsUtils;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class W3DHelper {

    private final World selectedWorld;
    private final World nonSelectedWorld;
    private boolean initialized;
    private final Map<Integer, Object3D> polygons;
    private final W3DPicker picker;
    private boolean wireframe;
    private boolean clicking;

    public W3DHelper(World selectedWorld, World nonSelectedWorld, FrameBuffer frameBuffer) {
        this.selectedWorld = selectedWorld;
        this.nonSelectedWorld = nonSelectedWorld;
        polygons = new HashMap<>();
        picker = new W3DPicker(frameBuffer);
        wireframe = false;
    }

    public void init() {

        polygons.putAll(GraphicsUtils.loadModelExplodedAsMap("D:\\floor2_exploded.obj", 1, object3D -> {
            object3D.setAdditionalColor(Color.YELLOW);
            return calculateData(object3D);
        }));

        for (Object3D object3D : polygons.values()) {
            object3D.build();

            nonSelectedWorld.addObject(object3D);
        }


        initialized = true;
    }

    public void update(int mouseX, int mouseY) {
        if (clicking) {
            clicking = false;
            checkPickingResults(mouseX, mouseY, nonSelectedWorld, selectedWorld);
            checkPickingResults(mouseX, mouseY, selectedWorld, nonSelectedWorld);


        }
    }

    private void checkPickingResults(int mouseX, int mouseY, World fromWorld, World toWorld) {
        int[] pickingRes = picker.getPickingResult(fromWorld, mouseX, mouseY);
        if (pickingRes != null) {
            Object3D picked = polygons.get(pickingRes[0]);
            if (picked != null) {
                fromWorld.removeObject(picked.getID());
                toWorld.addObject(picked);
            }
        }
    }


    public boolean initialized() {
        return initialized;
    }

    /**
     * Getter for property 'wireframe'.
     *
     * @return Value for property 'wireframe'.
     */
    public boolean isWireframe() {
        return wireframe;
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            clicking = true;
        }
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {
        clicking = false;
    }

    private static Object3D calculateData(Object3D object3D) {
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
        W3DUserData userData = new W3DUserData(sectorData);
        object3D.setUserObject(userData);
        return object3D;
    }
}
