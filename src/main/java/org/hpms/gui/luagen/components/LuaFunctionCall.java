package org.hpms.gui.luagen.components;

import org.hpms.gui.luagen.LuaStatement;

import java.util.List;

public class LuaFunctionCall implements LuaStatement {

    protected LuaInstance returnInstance;

    protected String name;

    protected List<LuaInstance> parameters;

    protected String parentIndent;

    public LuaFunctionCall() {}

    public LuaFunctionCall(LuaInstance returnInstance, String name, List<LuaInstance> parameters) {
        this.returnInstance = returnInstance;
        this.name = name;
        this.parameters = parameters;
        parentIndent = "";
    }

    public LuaFunctionCall(LuaInstance returnInstance, String name) {
        this(returnInstance, name, null);
    }

    public LuaFunctionCall(String name, List<LuaInstance> parameters) {
        this(null, name, parameters);
    }

    public LuaFunctionCall(String name) {
        this(null, name, null);
    }

    /**
     * Getter for property 'returnInstance'.
     *
     * @return Value for property 'returnInstance'.
     */
    public LuaInstance getReturnInstance() {
        return returnInstance;
    }

    /**
     * Setter for property 'returnInstance'.
     *
     * @param returnInstance Value to set for property 'returnInstance'.
     */
    public void setReturnInstance(LuaInstance returnInstance) {
        this.returnInstance = returnInstance;
    }

    /**
     * Getter for property 'name'.
     *
     * @return Value for property 'name'.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for property 'name'.
     *
     * @param name Value to set for property 'name'.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for property 'parameters'.
     *
     * @return Value for property 'parameters'.
     */
    public List<LuaInstance> getParameters() {
        return parameters;
    }

    /**
     * Setter for property 'parameters'.
     *
     * @param parameters Value to set for property 'parameters'.
     */
    public void setParameters(List<LuaInstance> parameters) {
        this.parameters = parameters;
    }

    @Override
    public ValidationResult validate() {
        return new ValidationResult(true);
    }

    @Override
    public String getCode() {
        StringBuilder sb = new StringBuilder();

        sb.append(parentIndent);
        if (returnInstance != null) {
            sb.append(returnInstance.getCode())
                    .append(" ")
                    .append("=")
                    .append(" ");
        }
        sb.append(name)
                .append("(");
        boolean first = true;
        if (parameters != null) {
            for (LuaInstance param : parameters) {
                if (!first) {
                    sb.append(", ");
                }
                first = false;
                sb.append(param.getCode());
            }
        }
        sb.append(")");
        return sb.toString();
    }

    @Override
    public String getParentIndent() {
        return parentIndent;
    }

    @Override
    public void setParentIndent(String parentIndent) {
        this.parentIndent = parentIndent;
    }
}
