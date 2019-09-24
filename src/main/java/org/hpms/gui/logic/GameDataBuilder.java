package org.hpms.gui.logic;

import org.hpms.gui.data.ProjectModel;
import org.hpms.gui.export.room.RoomXMLData;

public class GameDataBuilder {

    private ProjectModel projectModel;

    public GameDataBuilder(ProjectModel projectModel) {
        this.projectModel = projectModel;
    }

    public void build(String path, boolean pack) {

    }

    private static RoomXMLData fromModelToXML(ProjectModel.RoomModel roomModel) {
        RoomXMLData xmlData = new RoomXMLData();
        return xmlData;
    }
}
