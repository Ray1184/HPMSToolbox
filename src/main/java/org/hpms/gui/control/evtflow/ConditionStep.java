package org.hpms.gui.control.evtflow;

import org.hpms.gui.luagen.LuaStatement;
import org.hpms.gui.luagen.components.LuaIfStatement;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ConditionStep implements WorkflowStep {

    private static final Color COLOR = new Color(255, 255, 100);

    private final String id;
    protected LuaIfStatement statement;
    private WorkflowStep parent;

    public ConditionStep() {
        id = "CS_" + UUID.randomUUID().toString();
    }

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
        ConditionStep that = (ConditionStep) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
