package org.hpms.gui.control.evtflow;

import org.hpms.gui.luagen.LuaStatement;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ActionStep implements WorkflowStep {

    private static final Color COLOR = new Color(255, 190, 150);

    protected LuaStatement statement;

    private WorkflowStep parent;

    @Override
    public LuaStatement getStatement() {
        return statement;
    }

    public void setStatement(LuaStatement statement) {
        this.statement = statement;
    }

    @Override
    public JFrame getGui() {
        return null;
    }

    @Override
    public String getDescription() {
        return statement.getCode();
    }

    @Override
    public int getIndent() {
        return getParent() == null ? INDENT : getParent().getIndent() + INDENT;
    }


    public List<WorkflowStep> getChildren() {
        return null;
    }


    public List<WorkflowStep> getElseChildren() {
        return null;
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
