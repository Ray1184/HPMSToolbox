package org.hpms.gui.luagen.components;

import org.hpms.gui.luagen.LuaStatement;
import org.hpms.gui.luagen.components.LuaInstance.Type;
import org.hpms.gui.utils.Utils;

import java.util.Arrays;
import java.util.List;

public enum LuaBinaryOperator implements LuaStatement {
    PLUS("+") {
        @Override
        public List<Type> allowedTypeOnLeft() {
            return Utils.singletonList(Type.NUMBER);
        }

        @Override
        public List<Type> allowedTypeOnRight() {
            return Utils.singletonList(Type.NUMBER);
        }
    },
    MINUS("-") {
        @Override
        public List<Type> allowedTypeOnLeft() {
            return Utils.singletonList(Type.NUMBER);
        }

        @Override
        public List<Type> allowedTypeOnRight() {
            return Utils.singletonList(Type.NUMBER);
        }
    },
    STAR("*") {
        @Override
        public List<Type> allowedTypeOnLeft() {
            return Utils.singletonList(Type.NUMBER);
        }

        @Override
        public List<Type> allowedTypeOnRight() {
            return Utils.singletonList(Type.NUMBER);
        }
    },
    DIV("/") {
        @Override
        public List<Type> allowedTypeOnLeft() {
            return Utils.singletonList(Type.NUMBER);
        }

        @Override
        public List<Type> allowedTypeOnRight() {
            return Utils.singletonList(Type.NUMBER);
        }
    },
    MOD("&") {
        @Override
        public List<Type> allowedTypeOnLeft() {
            return Utils.singletonList(Type.NUMBER);
        }

        @Override
        public List<Type> allowedTypeOnRight() {
            return Utils.singletonList(Type.NUMBER);
        }
    },
    ASSIGNMENT("=") {
        @Override
        public List<Type> allowedTypeOnLeft() {
            return Arrays.asList(Type.NUMBER, Type.BOOLEAN, Type.NIL, Type.OBJECT, Type.STRING);
        }

        @Override
        public List<Type> allowedTypeOnRight() {
            return Arrays.asList(Type.NUMBER, Type.BOOLEAN, Type.NIL, Type.OBJECT, Type.STRING);
        }
    },
    AND("and") {
        @Override
        public List<Type> allowedTypeOnLeft() {
            return Utils.singletonList(Type.BOOLEAN);
        }

        @Override
        public List<Type> allowedTypeOnRight() {
            return Utils.singletonList(Type.BOOLEAN);
        }
    },
    OR("or") {
        @Override
        public List<Type> allowedTypeOnLeft() {
            return Utils.singletonList(Type.BOOLEAN);
        }

        @Override
        public List<Type> allowedTypeOnRight() {
            return Utils.singletonList(Type.BOOLEAN);
        }
    },
    EQ("==") {
        @Override
        public List<Type> allowedTypeOnLeft() {
            return Arrays.asList(Type.NUMBER, Type.BOOLEAN, Type.NIL, Type.STRING);
        }

        @Override
        public List<Type> allowedTypeOnRight() {
            return Arrays.asList(Type.NUMBER, Type.BOOLEAN, Type.NIL, Type.STRING);
        }
    },
    NOT_EQ("~=") {
        @Override
        public List<Type> allowedTypeOnLeft() {
            return Arrays.asList(Type.NUMBER, Type.BOOLEAN, Type.NIL, Type.STRING);
        }

        @Override
        public List<Type> allowedTypeOnRight() {
            return Arrays.asList(Type.NUMBER, Type.BOOLEAN, Type.NIL, Type.STRING);
        }
    },
    GREATER(">") {
        @Override
        public List<Type> allowedTypeOnLeft() {
            return Utils.singletonList(Type.NUMBER);
        }

        @Override
        public List<Type> allowedTypeOnRight() {
            return Utils.singletonList(Type.NUMBER);
        }
    },
    LESS("<") {
        @Override
        public List<Type> allowedTypeOnLeft() {
            return Utils.singletonList(Type.NUMBER);
        }

        @Override
        public List<Type> allowedTypeOnRight() {
            return Utils.singletonList(Type.NUMBER);
        }
    },
    GREATER_EQ(">=") {
        @Override
        public List<Type> allowedTypeOnLeft() {
            return Utils.singletonList(Type.NUMBER);
        }

        @Override
        public List<Type> allowedTypeOnRight() {
            return Utils.singletonList(Type.NUMBER);
        }
    },
    LESS_EQ("<=") {
        @Override
        public List<Type> allowedTypeOnLeft() {
            return Utils.singletonList(Type.NUMBER);
        }

        @Override
        public List<Type> allowedTypeOnRight() {
            return Utils.singletonList(Type.NUMBER);
        }
    },
    CONCAT("..") {
        @Override
        public List<Type> allowedTypeOnLeft() {
            return Utils.singletonList(Type.STRING);
        }

        @Override
        public List<Type> allowedTypeOnRight() {
            return Utils.singletonList(Type.STRING);
        }
    };

    private final String code;


    LuaBinaryOperator(String code) {
        this.code = code;
    }


    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getParentIndent() {
        return "";
    }

    @Override
    public void setParentIndent(String indent) {
        // Not used.
    }

    @Override
    public ValidationResult validate() {
        return new ValidationResult(true);
    }


    public abstract List<Type> allowedTypeOnLeft();

    public abstract List<Type> allowedTypeOnRight();






}
