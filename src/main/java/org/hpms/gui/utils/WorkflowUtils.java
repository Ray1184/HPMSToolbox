package org.hpms.gui.utils;

import org.hpms.gui.AppInfo;
import org.hpms.gui.control.evtflow.WorkflowStep;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;

public class WorkflowUtils {

    public static JLabel createLabel(WorkflowStep workflowStep, JComponent parent, int y) {
        JLabel label = new JLabel(workflowStep.getDescription());
        String tooltip = "<html>" + workflowStep.getDescription().replaceAll("\\n", "<br/>") + "</html>";
        label.setToolTipText(tooltip);
        Font f = new Font("Courier New", Font.PLAIN, AppInfo.FONT_SIZE);
        label.setFont(f);
        label.setForeground(Color.BLACK);
        label.setOpaque(true);
        label.setBackground(workflowStep.getLabelColor());
        Dimension size = label.getPreferredSize();
        int width = size.width * 1.1 < 300 ? (int) (size.width * 1.1) : 300;
        label.setBounds(workflowStep.getIndent() * 30, y, width, size.height);
        label.setBorder(new SoftBevelBorder(BevelBorder.RAISED, workflowStep.getLabelColor(), Color.DARK_GRAY));
        return label;

    }
}
