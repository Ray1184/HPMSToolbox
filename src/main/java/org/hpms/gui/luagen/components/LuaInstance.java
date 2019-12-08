package org.hpms.gui.luagen.components;

import org.hpms.gui.luagen.LuaStatement;

import java.util.List;

public class LuaInstance implements LuaStatement {

    public static final LuaInstance NIL = new LuaInstance("nil", Type.NIL);
    private String parentIndent;

    private String name;

    private Type type;

    private Object value;

    private List<LuaInstance> properties;

    public LuaInstance() {
    }

    public LuaInstance(String name, Type type) {
        this(name, type, null);
    }

    public LuaInstance(String name, Type type, List<LuaInstance> properties) {
        this.name = name;
        this.type = type;
        this.properties = properties;
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
     * Getter for property 'properties'.
     *
     * @return Value for property 'properties'.
     */
    public List<LuaInstance> getProperties() {
        return properties;
    }

    /**
     * Setter for property 'properties'.
     *
     * @param properties Value to set for property 'properties'.
     */
    public void setProperties(List<LuaInstance> properties) {
        this.properties = properties;
    }

    /**
     * Getter for property 'type'.
     *
     * @return Value for property 'type'.
     */
    public Type getType() {
        return type;
    }

    /**
     * Setter for property 'type'.
     *
     * @param type Value to set for property 'type'.
     */
    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public ValidationResult validate() {
        return new ValidationResult(true);
    }

    @Override
    public String getCode() {
        return name;
    }

    @Override
    public String getParentIndent() {
        return parentIndent;
    }

    @Override
    public void setParentIndent(String parentIndent) {
        this.parentIndent = parentIndent;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public enum Type {
        NUMBER,
        BOOLEAN,
        STRING,
        OBJECT,
        VOID,
        NIL
    }
}
