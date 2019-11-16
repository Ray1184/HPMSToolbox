package org.hpms.gui.control.w3d;

import com.threed.jpct.FrameBuffer;
import com.threed.jpct.Object3D;
import com.threed.jpct.World;
import org.hpms.gui.control.Controllers;
import org.hpms.gui.control.ToolsController;
import org.hpms.gui.data.ProjectModel;
import org.hpms.gui.data.W3DUserData;
import org.hpms.gui.logic.ProjectManager;
import org.hpms.gui.utils.FloorUtils;
import org.hpms.gui.utils.GraphicsUtils;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class W3DManager {

    private World selectedWorld;
    private World nonSelectedWorld;
    private boolean initialized;
    private final Map<Integer, Object3D> polygons;
    private W3DPicker picker;
    private boolean clicking;
    private static final W3DManager INSTANCE = new W3DManager();
    private final List<Object3D> groupCandidates;

    public volatile boolean refresh;

    public volatile boolean reload;

    public volatile boolean reloadNew;

    public volatile boolean createSectorGroup;

    public volatile String currentSectorGroup;

    public volatile String currentRoom;

    public static W3DManager getInstance() {
        return INSTANCE;
    }

    private W3DManager() {
        groupCandidates = new ArrayList<>();
        polygons = new HashMap<>();
    }

    public void create(World selectedWorld, World nonSelectedWorld, FrameBuffer frameBuffer) {
        this.selectedWorld = selectedWorld;
        this.nonSelectedWorld = nonSelectedWorld;

        picker = new W3DPicker(frameBuffer);


        initialized = true;
    }


    public void update(int mouseX, int mouseY) {
        if (!initialized) {
            return;
        }
        if (clicking) {
            clicking = false;
            checkPickingResults(mouseX, mouseY, nonSelectedWorld, selectedWorld, true);
            checkPickingResults(mouseX, mouseY, selectedWorld, nonSelectedWorld, false);
        }

        if (reloadNew) {
            reload(true);
            reloadNew = false;
        }

        if (reload) {
            reload(false);
            reload = false;
        }

        if (refresh) {
            refresh(null);
            refresh = false;
        }

        if (createSectorGroup) {
            createSectorGroup();
            createSectorGroup = false;
        }
    }

    private void checkPickingResults(int mouseX, int mouseY, World fromWorld, World toWorld, boolean addingAttempt) {
        if (polygons.isEmpty()) {
            return;
        }
        int[] pickingRes = picker.getPickingResult(fromWorld, mouseX, mouseY);
        if (pickingRes != null) {
            Object3D picked = polygons.get(pickingRes[0]);
            if (picked != null) {
                fromWorld.removeObject(picked.getID());
                toWorld.addObject(picked);
                if (addingAttempt) {
                    if (!groupCandidates.contains(picked)) {
                        groupCandidates.add(picked);
                    }
                } else {
                    groupCandidates.remove(picked);
                }
            }
        }
    }


    public boolean initialized() {
        return initialized;
    }


    public void mouseClicked(MouseEvent e) {
        if (!initialized) {
            return;
        }
        if (e.getButton() == MouseEvent.BUTTON1) {
            clicking = true;
        }
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {
        clicking = false;
    }

    private void reload(boolean newMap) {
        if (!initialized) {
            return;
        }
        polygons.clear();
        ProjectModel project = ProjectManager.getInstance().getProjectModel();
        String selectedRoom = newMap ? currentRoom : ((ToolsController) Controllers.TOOLS_CONTROLLER.getController()).getSelectedRoom();
        if (selectedRoom == null) {
            refresh(null);
            return;
        }
        String walkmapPath = ProjectManager.getInstance().getProjectModel().getProjectFloorsPath() + File.separator + selectedRoom + "_floor";
        polygons.putAll(GraphicsUtils.loadModelExplodedAsMap(walkmapPath, 1, object3D -> {
            FloorUtils.calculateData(object3D);
            if (!newMap) {
                FloorUtils.recalculateSectors(object3D, project, selectedRoom);
            }

            return object3D;
        }));

        refresh(null);

    }


    private void refresh(String currentSectorGroup) {
        nonSelectedWorld.removeAll();
        selectedWorld.removeAll();

        for (Object3D object3D : polygons.values()) {
            object3D.build();

            if (currentSectorGroup == null) {
                currentSectorGroup = ((ToolsController) Controllers.TOOLS_CONTROLLER.getController()).getSelectedSg();
            }
            String selectedSg = currentSectorGroup;
            String sgId = ((W3DUserData) object3D.getUserObject()).getSectorGroupID();

            if (sgId == null) {
                nonSelectedWorld.addObject(object3D);
            } else {
                selectedWorld.addObject(object3D);
                if (sgId.equals(selectedSg)) {
                    object3D.setAdditionalColor(Color.RED);
                } else {
                    object3D.setAdditionalColor(Color.BLUE);
                }
            }

        }
    }

    private void createSectorGroup() {
        String selectedRoom = ((ToolsController) Controllers.TOOLS_CONTROLLER.getController()).getSelectedRoom();
        String sectorName = currentSectorGroup;
        if (selectedRoom == null) {
            return;
        }
        for (Object3D object3D : groupCandidates) {
            W3DUserData data = (W3DUserData) object3D.getUserObject();
            String oldSectorGroup = data.getSectorGroupID();
            data.setSectorGroupID(sectorName);

            ProjectModel model = ProjectManager.getInstance().getProjectModel();
            ProjectModel.RoomModel roomModel = model.getRooms().get(selectedRoom);
            if (roomModel == null) {
                return;
            }
            if (!roomModel.getSectorGroupById().containsKey(sectorName)) {
                roomModel.getSectorGroupById().put(sectorName, new ProjectModel.RoomModel.SectorGroup());
            }
            ProjectModel.RoomModel.SectorGroup secGroup = roomModel.getSectorGroupById().get(sectorName);
            secGroup.setId(sectorName);
            secGroup.getSectors().add(data.getSectorData());

            // If sector was present in another sector group, it must be removed.
            if (oldSectorGroup != null && roomModel.getSectorGroupById().containsKey(oldSectorGroup)) {
                roomModel.getSectorGroupById().get(oldSectorGroup).getSectors().remove(data.getSectorData());
            }

        }

        refresh(currentSectorGroup);
    }

    public synchronized boolean hasSectorCandiates() {
        return !groupCandidates.isEmpty();
    }


}
