package org.hpms.gui.control.w3d;

import com.threed.jpct.*;
import org.hpms.gui.control.Controllers;
import org.hpms.gui.control.ToolsController;
import org.hpms.gui.data.ProjectModel;
import org.hpms.gui.data.W3DUserData;
import org.hpms.gui.logic.ProjectManager;
import org.hpms.gui.utils.FloorUtils;
import org.hpms.gui.utils.GraphicsUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class W3DManager {

    private static final W3DManager INSTANCE = new W3DManager();
    private final Map<Integer, Object3D> polygons;
    private final Map<String, List<Object3D>> pickedPolygonsByRoom;
    public volatile boolean refresh;
    public volatile boolean reload;
    public volatile boolean reloadNew;
    public volatile boolean refreshSectorGroups;
    public volatile String currentRoom;
    private World selectedWorld;
    private World nonSelectedWorld;
    private boolean initialized;
    private W3DPicker picker;
    private boolean clicking;
    private String previousRoom;
    private Map<String, ViewSettings> cameraMap;
    private TransformationHolder t;
    private W3DManager() {
        pickedPolygonsByRoom = new HashMap<>();
        polygons = new HashMap<>();
        cameraMap = new HashMap<>();

    }

    public static W3DManager getInstance() {
        return INSTANCE;
    }

    public void create(World selectedWorld, World nonSelectedWorld, FrameBuffer frameBuffer) {
        this.selectedWorld = selectedWorld;
        this.nonSelectedWorld = nonSelectedWorld;
        t = new TransformationHolder(selectedWorld, nonSelectedWorld);
        cameraMap.put("default", new ViewSettings(t.selectedWorld.getCamera(), t.nonSelectedWorld.getCamera(), t.distance));
        picker = new W3DPicker(frameBuffer);


        initialized = true;
    }

    public void updateBuffer(FrameBuffer buffer) {
        picker.updateFrameBuffer(buffer);
    }

    private void checkPickingResults(int mouseX, int mouseY, World fromWorld, World toWorld, boolean addingAttempt) {
        if (polygons.isEmpty()) {
            return;
        }
        int[] pickingRes = picker.getPickingResult(fromWorld, mouseX, mouseY);
        if (pickingRes != null) {
            Object3D picked = polygons.get(pickingRes[0]);
            if (picked != null) {
                //if (picked.getUserObject() != null && picked.getUserObject() instanceof String
                //        && picked.getUserObject().equals(GraphicsUtils.LINE_ID)) {
                //    return;
                //}
                currentRoom = ((ToolsController) Controllers.TOOLS_CONTROLLER.getController()).getSelectedRoom();
                if (currentRoom == null) {
                    return;
                }
                pickedPolygonsByRoom.putIfAbsent(currentRoom, new ArrayList<>());
                fromWorld.removeObject(picked.getID());
                toWorld.addObject(picked);
                if (addingAttempt) {
                    if (!pickedPolygonsByRoom.get(currentRoom).contains(picked)) {
                        pickedPolygonsByRoom.get(currentRoom).add(picked);
                        W3DUserData data = (W3DUserData) picked.getUserObject();
                        data.setChanging(true);
                    }
                } else {
                    W3DUserData data = (W3DUserData) picked.getUserObject();
                    String selectedSg = ((ToolsController) Controllers.TOOLS_CONTROLLER.getController()).getSelectedSg();
                    if (selectedSg.equals(data.getSectorGroupID())) {
                        // Remove only if picked sector group equals to selected.
                        data.setSectorGroupID(null);
                        pickedPolygonsByRoom.get(currentRoom).remove(picked);
                    }

                }
                updateSectorGroups(addingAttempt);
            }
        }
    }


    public void update(int mouseX, int mouseY) {
        if (!initialized) {
            return;
        }
        if (clicking && !readOnlySectors()) {
            clicking = false;
            checkPickingResults(mouseX, mouseY, nonSelectedWorld, selectedWorld, true);
            checkPickingResults(mouseX, mouseY, selectedWorld, nonSelectedWorld, false);
        }

        if (reloadNew) {
            reload(true);
            reloadNew = false;
        } else if (reload) {
            reload(false);
            reload = false;
        } else if (refresh) {
            refresh();
            refresh = false;
        } else if (refreshSectorGroups) {
            refreshSectorGroups();
            refreshSectorGroups = false;
        }

    }

    private void reload(boolean newMap) {
        if (!initialized) {
            return;
        }
        polygons.clear();
        ProjectModel project = ProjectManager.getInstance().getProjectModel();
        currentRoom = ((ToolsController) Controllers.TOOLS_CONTROLLER.getController()).getSelectedRoom();

        if (currentRoom == null) {
            refresh();
            return;
        }
        if (!cameraMap.containsKey(currentRoom)) {
            Camera sel = new Camera();
            Camera nonSel = new Camera();
            TransformationHolder.restoreCam(sel, 50.0f);
            TransformationHolder.restoreCam(nonSel, 50.0f);
            cameraMap.put(currentRoom, new ViewSettings(sel, nonSel, 50.0f));
        }

        String walkmapPath = ProjectManager.getInstance().getProjectModel().getProjectFloorsPath() + File.separator + currentRoom + "_floor";
        polygons.putAll(GraphicsUtils.loadModelExplodedAsMap(walkmapPath, 1, object3D -> {
            FloorUtils.calculateData(object3D);
            if (!newMap) {
                FloorUtils.reassignSectors(object3D, project, currentRoom);
            }

            return object3D;
        }));

        refresh();

    }

    private void refresh() {
        nonSelectedWorld.removeAll();
        selectedWorld.removeAll();
        for (Object3D object3D : polygons.values()) {
            object3D.build();
        }
        refreshSectorGroups();

    }

    public void refreshSectorGroups() {

        String selectedSg = ((ToolsController) Controllers.TOOLS_CONTROLLER.getController()).getSelectedSg();

        for (Object3D object3D : polygons.values()) {

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

    private void updateSectorGroups(boolean addingAttempt) {
        String selectedSg = ((ToolsController) Controllers.TOOLS_CONTROLLER.getController()).getSelectedSg();
        if (selectedSg == null) {
            return;
        }
        ProjectModel model = ProjectManager.getInstance().getProjectModel();
        if (model == null) {
            return;
        }
        // Variable in tools controller may be not up to date.
        String room = currentRoom != null ? currentRoom : ((ToolsController) Controllers.TOOLS_CONTROLLER.getController()).getSelectedRoom();
        ProjectModel.RoomModel roomModel = model.getRooms().get(room);

        if (!roomModel.getSectorGroupById().containsKey(selectedSg)) {
            return;
        }
        ProjectModel.RoomModel.SectorGroup sectorGroup = roomModel.getSectorGroupById().get(selectedSg);
        sectorGroup.getSectors().clear();
        for (Object3D candidate : pickedPolygonsByRoom.get(room)) {
            W3DUserData data = (W3DUserData) candidate.getUserObject();
            /*if (addingAttempt) {
                sectorGroup.getSectors().add(data.getSectorData());
                if (data.isChanging()) {
                    data.setSectorGroupID(sectorGroup.getId());
                    data.setChanging(false);
                }
            } else {
                sectorGroup.getSectors().remove(data.getSectorData());
                data.setSectorGroupID(null);
            }*/
            sectorGroup.getSectors().add(data.getSectorData());
            if (data.isChanging()) {
                data.setSectorGroupID(sectorGroup.getId());
                data.setChanging(false);
            }
        }

        refreshSectorGroups();

    }

    public TransformationHolder getTransformation() {
        return t;
    }

    public boolean checkChangeCam() {
        if (currentRoom == null || !cameraMap.containsKey(currentRoom)) {
            return false;
        }
        if (!currentRoom.equals(previousRoom)) {
            previousRoom = currentRoom;
            t.selectedWorld.setCameraTo(cameraMap.get(currentRoom).selCamera);
            t.nonSelectedWorld.setCameraTo(cameraMap.get(currentRoom).nonSelCamera);
            t.distance = cameraMap.get(currentRoom).distance;
            return true;
        }

        return false;

    }

    public void keyReleased(int e) {
        if (initialized && currentRoom != null) {
            System.out.println("EVENT");
            if (e == KeyEvent.VK_R) {
                Camera sel = new Camera();
                Camera nonSel = new Camera();
                TransformationHolder.restoreCam(sel, 50.0f);
                TransformationHolder.restoreCam(nonSel, 50.0f);
                cameraMap.put(currentRoom, new ViewSettings(sel, nonSel, 50.0f));
            }
        }
    }

    public synchronized boolean readOnlySectors() {
        return ((ToolsController) Controllers.TOOLS_CONTROLLER.getController()).getSelectedSg() == null;
    }

    public static class ViewSettings {
        public Camera selCamera;

        public Camera nonSelCamera;

        public float distance;

        public ViewSettings(Camera selCamera, Camera nonSelCamera, float distance) {
            this.selCamera = selCamera;
            this.nonSelCamera = nonSelCamera;
            this.distance = distance;
        }
    }

    public static class TransformationHolder {

        public World selectedWorld;
        public World nonSelectedWorld;
        public float distance;
        public float offX;
        public float offY;

        private SimpleVector pos;
        private SimpleVector dir;

        public TransformationHolder(World selWorld, World nonSelWorld) {

            selectedWorld = selWorld;
            nonSelectedWorld = nonSelWorld;
            distance = 50.0f;
            offX = 0.0f;
            offY = 0.0f;
            restoreCam(selectedWorld.getCamera(), distance);
            restoreCam(nonSelectedWorld.getCamera(), distance);
            pos = new SimpleVector();
            dir = new SimpleVector();

        }

        public static void restoreCam(Camera c, float distance) {
            c.moveCamera(Camera.CAMERA_MOVEUP, distance);
            c.rotateCameraX((float) Math.PI / 2f);
        }

        @Override
        public String toString() {
            return "VIEW INFO\n---------------\nP(X): " + String.format("%.3f", selectedWorld.getCamera().getPosition(pos).x) +
                    "\nP(Y): " + String.format("%.3f", selectedWorld.getCamera().getPosition(pos).y) +
                    "\nP(Z): " + String.format("%.3f", selectedWorld.getCamera().getPosition(pos).z) +
                    "\n---------------\nD(X): " + String.format("%.3f", selectedWorld.getCamera().getDirection(dir).x) +
                    "\nD(Y): " + String.format("%.3f", selectedWorld.getCamera().getDirection(dir).y) +
                    "\nD(Z): " + String.format("%.3f", selectedWorld.getCamera().getDirection(dir).z) +
                    "\n---------------\nPRESS R TO RESET";
        }
    }


}
