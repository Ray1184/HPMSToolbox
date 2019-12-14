package org.hpms.gui.control.evtflow;

import org.hpms.gui.luagen.LuaStatement;
import org.hpms.gui.luagen.components.LuaInstance;

import javax.swing.*;
import java.awt.*;

public class VariableCreationStep implements WorkflowStep {

    private static final Color COLOR = new Color(150, 170, 255);

    protected LuaInstance statement;

    private WorkflowStep parent;

    @Override
    public LuaStatement getStatement() {
        return statement;
    }

    public void setStatement(LuaInstance statement) {
        this.statement = statement;
    }

    @Override
    public JFrame getGui() {
        return null;
    }

    @Override
    public String getDescription() {
        return "DEFINE " + statement.getName() + (statement.getValue() == null ? "" : " = " + statement.getValue());
    }

    @Override
    public int getIndent() {
        return getParent() == null ? INDENT : getParent().getIndent() + INDENT;
    }

    @Override
    public WorkflowStep getParent() {
        return parent;
    }

    /**
     * Setter for property 'parent'.
     *
     * @param parent Value to set for property 'parent'.
     */
    @Override
    public void setParent(WorkflowStep parent) {
        this.parent = parent;
    }

    @Override
    public Color getLabelColor() {
        return COLOR;
    }
}
