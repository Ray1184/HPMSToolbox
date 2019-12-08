package org.hpms.gui.luagen.components;

import org.hpms.gui.luagen.LuaStatement;

import java.util.ArrayList;
import java.util.List;

public class LuaFunctionDeclare extends LuaFunctionCall {

    private List<LuaStatement> body;

    private String info;

    private boolean external;

    public LuaFunctionDeclare() {
    }

    public LuaFunctionDeclare(LuaInstance returnInstance, String name, List<LuaInstance> parameters, List<LuaStatement> body, boolean external) {
        super(returnInstance, name, parameters);
        this.body = body;
        this.external = external;
    }

    public LuaFunctionDeclare(LuaInstance returnInstance, String name, List<LuaStatement> body, boolean external) {
        super(returnInstance, name);
        this.body = body;
        this.external = external;
    }

    public LuaFunctionDeclare(String name, List<LuaInstance> parameters, List<LuaStatement> body, boolean external) {
        super(name, parameters);
        this.body = body;
        this.external = external;
    }

    public LuaFunctionDeclare(String name, boolean external) {
        super(name);
        body = new ArrayList<>();
        this.external = external;
    }

    public List<LuaStatement> getBody() {
        return body;
    }

    public void setBody(List<LuaStatement> body) {
        this.body = body;
    }

    public boolean isExternal() {
        return external;
    }

    public void setExternal(boolean external) {
        this.external = external;
    }

    @Override
    public String getCode() {
        StringBuilder sb = new StringBuilder();

        if (external) {
            if (info != null && !info.isEmpty()) {
                for (String tok : info.split("\n"))
                    sb.append("--- ")
                            .append(tok)
                            .append("\n");
            }
            sb.append("function ")
                    .append(name)
                    .append("(");
        } else {
            sb.append("function")
                    .append("(");
        }
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
        sb.append(")\n");
        if (body.isEmpty()) {
            LuaExpression empty = new LuaExpression("-- TODO\n");
            empty.setParentIndent(parentIndent + INDENTATION);
            sb.append(empty.getCode());
        }
        for (LuaStatement stat : body) {
            stat.setParentIndent(parentIndent + INDENTATION);
            sb.append(stat.getCode());
        }

        if (!external) {
            // Remove one indent to align end with declaration
            sb.append(parentIndent.replaceFirst(INDENTATION, ""));
        }
        sb.append("end");
        return sb.toString();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
