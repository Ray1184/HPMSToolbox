package org.hpms.gui.luagen.parser;

import org.hpms.gui.luagen.LuaStatement;
import org.hpms.gui.luagen.components.LuaExpression;
import org.hpms.gui.luagen.components.LuaFunctionDeclare;
import org.hpms.gui.luagen.components.LuaInstance;
import org.hpms.gui.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LuaFunctionParser {

    public static LuaFunctionDeclare parse(String code) throws Exception {
        if (code == null) {
            throw new Exception("Null function not allowed.");
        }
        code = code.trim();
        StringBuilder codeWithoutFunctionComment = new StringBuilder();
        boolean inFunction = false;
        for (String s : code.split("\n")) {
            if (s.trim().startsWith("function ")) {
                inFunction = true;
            }
            if (s.trim().equals("end") || s.trim().startsWith("end ") || s.trim().startsWith("end--")) {
                inFunction = false;
            }
            if (!s.trim().startsWith("--") || inFunction) {
                codeWithoutFunctionComment.append(s).append("\n");
            }

        }
        code = codeWithoutFunctionComment.toString();
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
                Utils.singletonList(body), true);
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

    public static LuaFunctionDeclare parse(String name, String argsString, String bodyCode) throws Exception {
        List<LuaInstance> paramsList = new ArrayList<>();
        String[] args = argsString.trim().split(",");
        for (String p : args) {
            LuaInstance param = new LuaInstance(p.trim(), LuaInstance.Type.OBJECT);
            paramsList.add(param);
        }


        LuaExpression body = new LuaExpression(bodyCode);
        body.setParentIndent("");
        LuaInstance dummyReturn = new LuaInstance("", LuaInstance.Type.OBJECT);
        LuaFunctionDeclare f = new LuaFunctionDeclare(dummyReturn, name, paramsList,
                Utils.singletonList(body), true);
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

    public static String getBody(LuaFunctionDeclare functionDeclare) {
        if (functionDeclare == null) {
            return "";
        }
        StringBuilder bodyBuilder = new StringBuilder();
        boolean first = true;
        for (LuaStatement stat : functionDeclare.getBody()) {
            if (!first) {
                bodyBuilder.append("\n");
            }
            bodyBuilder.append(stat.getCode());
            first = false;
        }
        return bodyBuilder.toString();
    }

    public static String getParams(LuaFunctionDeclare functionDeclare) {
        if (functionDeclare == null) {
            return "";
        }
        StringBuilder argsBuilder = new StringBuilder();
        boolean first = true;
        for (LuaInstance arg : functionDeclare.getParameters()) {
            if (!first) {
                argsBuilder.append(",");
            }
            argsBuilder.append(arg.getCode());
            first = false;
        }
        return argsBuilder.toString();
    }
}
