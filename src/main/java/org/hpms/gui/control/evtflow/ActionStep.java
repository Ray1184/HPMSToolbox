package org.hpms.gui.control.evtflow;

import org.hpms.gui.luagen.LuaStatement;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ActionStep implements WorkflowStep {

    private static final Color COLOR = new Color(255, 190, 150);

    protected LuaStatement statement;

    private WorkflowStep parent;

    private String id;

    public ActionStep() {
        id = "AS_" + UUID.randomUUID().toString();
    }

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

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActionStep that = (ActionStep) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
