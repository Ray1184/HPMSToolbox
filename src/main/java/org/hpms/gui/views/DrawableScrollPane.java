package org.hpms.gui.views;

import org.hpms.gui.control.Controllers;
import org.hpms.gui.control.WorkAreaController;

import java.awt.*;

public class DrawableScrollPane extends javax.swing.JScrollPane {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((WorkAreaController) Controllers.WORK_AREA_CONTROLLER.getController()).setG(g);
        Controllers.WORK_AREA_CONTROLLER.update();
    }
}
