package org.hpms.gui.data;

import java.awt.*;

public class W3DUserData {

    private ProjectModel.RoomModel.SectorGroup.Sector sectorData;

    private String sectorGroupID;

    private Color color;

    public W3DUserData(ProjectModel.RoomModel.SectorGroup.Sector sectorData) {
        this.sectorData = sectorData;
    }

    public W3DUserData() {
    }

    public ProjectModel.RoomModel.SectorGroup.Sector getSectorData() {
        return sectorData;
    }

    public void setSectorData(ProjectModel.RoomModel.SectorGroup.Sector sectorData) {
        this.sectorData = sectorData;
    }

    public String getSectorGroupID() {
        return sectorGroupID;
    }

    public void setSectorGroupID(String sectorGroupID) {
        this.sectorGroupID = sectorGroupID;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
