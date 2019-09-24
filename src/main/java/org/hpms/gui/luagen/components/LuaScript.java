package org.hpms.gui.luagen.components;

import org.hpms.gui.luagen.LuaStatement;

import java.util.Map;

public class LuaScript implements LuaStatement {


    private final Map<String, LuaStatement> chunks;

    private final boolean rawArray;

    public LuaScript(Map<String, LuaStatement> chunks, boolean rawArray) {
        this.chunks = chunks;
        this.rawArray = rawArray;
    }

    public LuaScript(Map<String, LuaStatement> chunks) {
        this(chunks, false);
    }

    private String parentIndent;

    @Override
    public String getCode() {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (Map.Entry<String, LuaStatement> entry : chunks.entrySet()) {
            sb.append(parentIndent);
            if (!rawArray) {
                sb.append(entry.getKey())
                        .append(" = ");
            }
            LuaStatement statement = entry.getValue();
            if (statement instanceof LuaExpression) {
                statement.setParentIndent("");
                sb.append("'")
                .append(statement.getCode())
                .append("'");
                if (index < chunks.size() - 1) {
                    sb.append(",")
                            .append("\n");
                }

            } else if (statement instanceof LuaScript) {
                statement.setParentIndent(parentIndent + INDENTATION);
                sb.append(" {\n")
                        .append(statement.getCode())
                        .append("\n")
                .append(parentIndent)
                .append("}");

                if (index < chunks.size() - 1) {
                    sb.append(",")
                            .append("\n");
                }

            } else {
                statement.setParentIndent(parentIndent + INDENTATION);
                sb.append(statement.getCode());
                if (index < chunks.size() - 1) {
                    sb.append(",")
                            .append("\n");
                }
            }

            index++;
        }

        return sb.toString();
    }

    @Override
    public String getParentIndent() {
        return parentIndent;
    }

    @Override
    public void setParentIndent(String indent) {
        parentIndent = indent;
    }

    @Override
    public ValidationResult validate() {
        return new ValidationResult(true);
    }

    public Map<String, LuaStatement> getChunks() {
        return chunks;
    }
}
