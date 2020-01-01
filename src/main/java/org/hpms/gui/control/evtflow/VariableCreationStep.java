package org.hpms.gui.control.evtflow;

import org.hpms.gui.luagen.LuaStatement;
import org.hpms.gui.luagen.components.LuaInstance;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.UUID;

public class VariableCreationStep implements WorkflowStep {

    private static final Color COLOR = new Color(150, 170, 255);
    protected String id;

    protected LuaInstance statement;

    private WorkflowStep parent;

    public VariableCreationStep() {
        id = "VC_" + UUID.randomUUID().toString();
    }

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

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VariableCreationStep that = (VariableCreationStep) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
