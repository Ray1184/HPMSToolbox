package org.hpms.gui.luagen.parser;

import org.hpms.gui.luagen.LuaStatement;
import org.hpms.gui.luagen.components.LuaExpression;
import org.hpms.gui.luagen.components.LuaFunctionDeclare;
import org.hpms.gui.luagen.components.LuaInstance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LuaFunctionParser {

    public static LuaFunctionDeclare parse(String code) throws Exception {
        if (code == null) {
            throw new Exception("Null function not allowed.");
        }
        code = code.trim();
        int startDeclareIndex = code.indexOf("function") + "function".length();
        int startArgsIndex = code.indexOf("(");
        int endArgsIndex = code.indexOf(")");
        int endFunctionIndex = code.lastIndexOf("end");

        if (startArgsIndex < 0 || startArgsIndex < 0 || endArgsIndex < 0 || endFunctionIndex < 0) {
            throw new Exception("Invalid function.");
        }


        String declare = code.substring(startDeclareIndex, startArgsIndex).trim();

        if (declare.isEmpty() || Character.isDigit(declare.charAt(0))) {
            throw new Exception("Invalid function.");
        }

        String params = code.substring(startArgsIndex + 1, endArgsIndex).trim();


        List<LuaInstance> paramsList = new ArrayList<>();
        if (!params.isEmpty()) {
            params = params.replaceAll("[\\t\\n\\r]+", " ").trim();
            String tokens[] = params.split(",");
            for (String p : tokens) {
                LuaInstance param = new LuaInstance(p.trim(), LuaInstance.Type.OBJECT);
                paramsList.add(param);
            }
        }
        String bodyCode = code.substring(endArgsIndex + 1, endFunctionIndex).trim();

        LuaExpression body = new LuaExpression(bodyCode);
        LuaInstance dummyReturn = new LuaInstance("", LuaInstance.Type.OBJECT);
        LuaFunctionDeclare f = new LuaFunctionDeclare(dummyReturn, declare, paramsList,
                Collections.singletonList(body), true);
        LuaStatement.ValidationResult v = f.validate();
        if (!v.isValid()) {
            StringBuilder sb = new StringBuilder();
            if (v.getReasons() != null && !v.getReasons().isEmpty()) {
                sb.append("Invalid function. Reasons:");
            } else {
                sb.append("Invalid function.");
            }
            for (String reason : v.getReasons()) {
                sb.append("- ")
                        .append(reason);
            }
            throw new Exception(sb.toString());
        }

        return f;

    }
}
