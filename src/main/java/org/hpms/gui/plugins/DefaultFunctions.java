package org.hpms.gui.plugins;

import org.hpms.gui.luagen.LuaStatement;
import org.hpms.gui.luagen.components.LuaExpression;
import org.hpms.gui.luagen.components.LuaFunctionCall;
import org.hpms.gui.luagen.components.LuaFunctionDeclare;
import org.hpms.gui.luagen.components.LuaInstance;
import org.hpms.gui.utils.Utils;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.*;

import static org.hpms.gui.utils.ErrorManager.createReadOnlyJTextField;

public class DefaultFunctions {


    private Map<String, LuaFunctionDeclare> functions;

    public DefaultFunctions() {
        try {
            functions = build();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, createReadOnlyJTextField(e), "Error", JOptionPane.PLAIN_MESSAGE);
            System.exit(-1);
        }
    }


    private Map<String, LuaFunctionDeclare> build() throws Exception {
        try {
            functions = new HashMap<>();

            File jarFile = new File(DefaultFunctions.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            File presets = new File(jarFile.getParentFile(), "plugins/preset_functions/default_lua_functions.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Functions.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Functions modFunctions = (Functions) jaxbUnmarshaller.unmarshal(presets);

            for (Functions.Function fun : modFunctions.getFunction()) {
                String id = fun.getId();
                StringBuilder info = new StringBuilder(fun.getDescription());
                String name = fun.getName();
                LuaInstance retType = new LuaInstance("ret", LuaInstance.Type.valueOf(fun.getReturnType().toUpperCase()));
                List<LuaInstance> params = new ArrayList<>();
                LuaStatement body = new LuaExpression(fun.getBody().trim());
                body.setParentIndent("");
                StringBuilder paramsDesc = new StringBuilder("\n");
                for (Functions.Function.Params.Param modParam : fun.getParams().getParam()) {
                    params.add(new LuaInstance(modParam.getName(), LuaInstance.Type.valueOf(modParam.getType())));
                    paramsDesc.append("@param ")
                            .append(modParam.getName())
                            .append(" : ")
                            .append(modParam.getType())
                            .append(" - ")
                            .append(modParam.getDescription() == null ? "TODO" : modParam.getDescription())
                            .append("\n");
                }

                info.append("\n").append(paramsDesc);
                LuaFunctionDeclare function = new LuaFunctionDeclare(retType, name, params, Utils.singletonList(body), true);
                function.setInfo(info.toString());
                System.out.println(function.getCode());
                functions.put(id, function);
            }

            return functions;
        } catch (Exception e) {
            throw new Exception("Wrong format for default_lua_functions.xml, please checks whether the file conforms to the format used to create default functions.\n\n" + e.toString());
        }
    }

    public Collection<LuaFunctionDeclare> getAllFunctions() {
        return functions.values();
    }

    public LuaFunctionDeclare getFunctionById(String id) {
        return functions.get(id);
    }

    public LuaFunctionCall getFunctionCall(LuaFunctionDeclare function, Object... paramsVal) {
        String name = function.getName();
        LuaInstance returnInstance = function.getReturnInstance();
        List<LuaInstance> params = function.getParameters();
        for (int i = 0; i < params.size(); i++) {
            if (i < paramsVal.length) {
                params.get(i).setValue(paramsVal[i]);
            } else {
                params.get(i).setValue("nil");
            }
        }

        return new LuaFunctionCall(returnInstance, name, params);
    }


}
