package org.hpms.gui.luagen.components;

import org.hpms.gui.luagen.LuaStatement;

public class LuaExpression implements LuaStatement {

    private String parentIndex;

    private String expression;

    public LuaExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public String getCode() {
        return parentIndex + expression;
    }

    @Override
    public String getParentIndent() {
        return parentIndex;
    }

    @Override
    public void setParentIndent(String parentIndex) {
        this.parentIndex = parentIndex;
    }

    @Override
    public ValidationResult validate() {
        return new ValidationResult(true);
    }
}
