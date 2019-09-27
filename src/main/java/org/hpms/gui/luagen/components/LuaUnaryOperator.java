package org.hpms.gui.luagen.components;

import org.hpms.gui.luagen.LuaStatement;
import org.hpms.gui.luagen.components.LuaInstance.Type;

import java.util.Collections;
import java.util.List;

public enum LuaUnaryOperator implements LuaStatement {

    NOT("not") {
        @Override
        public List<Type> allowedTypeOnRight() {
            return Collections.singletonList(Type.BOOLEAN);
        }
    },

    LENGTH("#") {
        @Override
        public List<Type> allowedTypeOnRight() {
            return Collections.singletonList(Type.STRING);
        }
    };

    private final String code;


    LuaUnaryOperator(String code) {
        this.code = code;
    }


    @Override
    public String getCode() {
        return code;
    }

    @Override
    public ValidationResult validate() {
        return new ValidationResult(true);
    }


    public abstract List<Type> allowedTypeOnRight();

    @Override
    public String getParentIndent() {
        return "";
    }

    @Override
    public void setParentIndent(String indent) {
        // Not used.
    }


}
