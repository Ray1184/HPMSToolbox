package org.hpms.gui.control.evtflow;

import org.hpms.gui.luagen.LuaStatement;
import org.hpms.gui.luagen.components.LuaIfStatement;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ConditionStep implements WorkflowStep {

    private static final Color COLOR = new Color(255, 255, 100);
    private final List<WorkflowStep> children = new ArrayList<>();
    private final List<WorkflowStep> elseChildren = new ArrayList<>();
    protected LuaIfStatement statement;
    private WorkflowStep parent;

    @Override
    public LuaStatement getStatement() {
        return statement;
    }

    public void setStatement(LuaIfStatement statement) {
        this.statement = statement;
    }

    @Override
    public JFrame getGui() {
        return null;
    }

    @Override
    public String getDescription() {
        return "IF " + statement.getCondition().getCode() + " THEN:";
    }

    @Override
    public int getIndent() {
        return getParent() == null ? INDENT : getParent().getIndent() + INDENT;
    }


    public List<WorkflowStep> getChildren() {
        return children;
    }


    public List<WorkflowStep> getElseChildren() {
        return elseChildren;
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
