package org.hpms.gui.logic;

import org.hpms.gui.luagen.LuaStatement;
import org.hpms.gui.luagen.components.LuaExpression;
import org.hpms.gui.luagen.components.LuaFunctionDeclare;
import org.hpms.gui.luagen.components.LuaInstance;
import org.hpms.gui.luagen.components.LuaScript;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TemplateGenerator {

    private final LuaScript sceneScriptTemplate;

    private final LuaScript configScriptTemplate;


    public TemplateGenerator() {

        configScriptTemplate = buildConfigScript();
        sceneScriptTemplate = buildScript();
    }

    private LuaScript buildConfigScript() {
        Map<String, LuaStatement> configChunk = new LinkedHashMap<>();

        // Main config.
        configChunk.put("config", new LuaScript(new LinkedHashMap<>()));

        // Models buffer.
        ((LuaScript) configChunk.get("config")).getChunks().put("models_buffer", new LuaScript(new LinkedHashMap<>(), true));

        // Images buffer.
        ((LuaScript) configChunk.get("config")).getChunks().put("images_buffer", new LuaScript(new LinkedHashMap<>(), true));

        // First script.
        ((LuaScript) configChunk.get("config")).getChunks().put("first_script", new LuaExpression("ROOM_ID_PLACEHOLDER"));


        LuaScript configScript = new LuaScript(configChunk);
        configScript.setParentIndent("");
        return configScript;
    }

    private LuaScript buildScript() {
        Map<String, LuaStatement> sceneChunk = new LinkedHashMap<>();

        // Main scene.
        sceneChunk.put("scene", new LuaScript(new LinkedHashMap<>()));

        // Scene configurations.
        ((LuaScript) sceneChunk.get("scene")).getChunks().put("name", new LuaExpression("ROOM_NAME_PLACEHOLDER"));
        ((LuaScript) sceneChunk.get("scene")).getChunks().put("version", new LuaExpression("ROOM_VERSION_PLACEHOLDER"));
        ((LuaScript) sceneChunk.get("scene")).getChunks().put("mode", new LuaExpression("ROOM_MODE_PLACEHOLDER"));
        ((LuaScript) sceneChunk.get("scene")).getChunks().put("quit", new LuaExpression("false"));

        // Setup callback.
        LuaInstance worldParam = new LuaInstance("world", LuaInstance.Type.OBJECT);
        LuaInstance cameraParam = new LuaInstance("camera", LuaInstance.Type.OBJECT);
        List<LuaInstance> setupParams = new ArrayList<>();
        setupParams.add(worldParam);
        setupParams.add(cameraParam);
        ((LuaScript) sceneChunk.get("scene")).getChunks().put("setup", new LuaFunctionDeclare("setup", setupParams, new ArrayList<>(), false));

        // Input callback.
        LuaInstance keyParam = new LuaInstance("key", LuaInstance.Type.OBJECT);
        List<LuaInstance> inputParams = new ArrayList<>();
        inputParams.add(keyParam);
        ((LuaScript) sceneChunk.get("scene")).getChunks().put("input", new LuaFunctionDeclare("input", inputParams, new ArrayList<>(), false));

        // Update callback.
        worldParam = new LuaInstance("world", LuaInstance.Type.OBJECT);
        cameraParam = new LuaInstance("camera", LuaInstance.Type.OBJECT);
        List<LuaInstance> updateParams = new ArrayList<>();
        updateParams.add(worldParam);
        updateParams.add(cameraParam);
        ((LuaScript) sceneChunk.get("scene")).getChunks().put("update", new LuaFunctionDeclare("update", updateParams, new ArrayList<>(), false));

        // Cleanup callback.
        ((LuaScript) sceneChunk.get("scene")).getChunks().put("cleanup", new LuaFunctionDeclare("cleanup", false));


        LuaScript sceneScript = new LuaScript(sceneChunk);
        sceneScript.setParentIndent("");
        return sceneScript;

    }

    public LuaScript getSceneScriptTemplate() {
        return sceneScriptTemplate;
    }

    public LuaScript getConfigScriptTemplate() {
        return configScriptTemplate;
    }
}
