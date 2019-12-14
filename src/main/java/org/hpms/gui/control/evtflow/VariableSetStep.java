package org.hpms.gui.control.evtflow;

import java.awt.*;

public class VariableSetStep extends VariableCreationStep {

    private static final Color COLOR = new Color(140, 255, 170);


    @Override
    public String getDescription() {
        return "SET " + statement.getName() + " = " + statement.getValue();
    }

    @Override
    public Color getLabelColor() {
        return COLOR;
    }
}
