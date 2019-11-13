package org.hpms.gui.logic;

import org.hpms.gui.AppInfo;
import org.hpms.gui.data.ProjectModel;
import org.hpms.gui.luagen.LuaStatement;
import org.hpms.gui.luagen.components.LuaExpression;
import org.hpms.gui.luagen.components.LuaFunctionDeclare;
import org.hpms.gui.luagen.components.LuaIfStatement;
import org.hpms.gui.luagen.components.LuaScript;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ScriptBuilder {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");
    private final ProjectModel projectModel;

    public ScriptBuilder(ProjectModel projectModel) {
        this.projectModel = projectModel;
    }

    public void createScripts(String dataPath) throws FileNotFoundException {
        // Create config script.
        TemplateGenerator generator = new TemplateGenerator();
        LuaScript config = generator.getConfigScriptTemplate();
        fillConfigScript(config, dataPath);

        StringBuilder configScriptContent = new StringBuilder(createHeaderComment("Config"));
        configScriptContent.append("\n\n")
                .append(config.getCode());

        try (PrintWriter out = new PrintWriter(dataPath + File.separator + ProjectModel.SCRIPTS_DIR + File.separator + "Config.lua")) {
            out.println(configScriptContent.toString());
        }

        // Create common functions script
        LuaScript common = new LuaScript(new HashMap<>());
        fillCommonScript(common);
        StringBuilder commonScriptContent = new StringBuilder(createHeaderComment("Common"));
        commonScriptContent.append("\n\n")
                .append(common.getCode());

        try (PrintWriter out = new PrintWriter(dataPath + File.separator + ProjectModel.SCRIPTS_DIR + File.separator + "Common.lua")) {
            out.println(commonScriptContent.toString());
        }


        // Foreach room, create a scene script
        for (ProjectModel.RoomModel room : projectModel.getRooms().values()) {
            generator = new TemplateGenerator();
            LuaScript scene = generator.getSceneScriptTemplate();
            fillSceneScript(scene, room);

            // Reorder map, put external functions first.
            Map<String, LuaStatement> sortedChunks = new LinkedHashMap<>();
            for (Map.Entry<String, LuaStatement> entry : scene.getChunks().entrySet()) {
                if (entry.getKey().startsWith("F_")) {
                    sortedChunks.put(entry.getKey(), entry.getValue());
                }
            }

            for (Map.Entry<String, LuaStatement> entry : scene.getChunks().entrySet()) {
                if (!entry.getKey().startsWith("F_")) {
                    sortedChunks.put(entry.getKey(), entry.getValue());
                }
            }

            LuaScript sortedScript = new LuaScript(sortedChunks);
            sortedScript.setParentIndent("");
            StringBuilder sceneScriptContent = new StringBuilder(createHeaderComment(room.getName()));
            sceneScriptContent.append("\n\n")
                    .append(sortedScript.getCode());

            try (PrintWriter out = new PrintWriter(dataPath + File.separator + ProjectModel.SCRIPTS_DIR + File.separator + room.getName() + ".lua")) {
                out.println(sceneScriptContent.toString());
            }

        }
    }

    private void fillCommonScript(LuaScript common) {
        common.setParentIndent("");
        int functionIndex = 0;
        for (LuaFunctionDeclare fun : projectModel.getCommonFunctions().values()) {
            common.getChunks().put("F_" + functionIndex++, fun);
        }

    }

    private void fillSceneScript(LuaScript scene, ProjectModel.RoomModel room) {
        // Common info.
        ((LuaScript) scene.getChunks().get("scene")).getChunks().put("name", new LuaExpression(room.getName()));
        ((LuaScript) scene.getChunks().get("scene")).getChunks().put("version", new LuaExpression(AppInfo.VERSION));
        ((LuaScript) scene.getChunks().get("scene")).getChunks().put("mode", new LuaExpression(room.getPipelineType().name()));

        int functionIndex = 0;
        for (Map.Entry<String, ProjectModel.RoomModel.Event> entry : room.getEventsById().entrySet()) {
            ProjectModel.RoomModel.Event event = entry.getValue();


            if (event.getConditionAction() != null) {
                // Build if condition
                LuaIfStatement ifStatement = event.getConditionAction().getIfStatement();
                LuaFunctionDeclare statFun = (LuaFunctionDeclare) ((LuaScript) scene.getChunks().get("scene")).getChunks().get(event.getTriggerType().getScriptPart());
                statFun.getBody().add(new LuaExpression("-- Begin Event " + event.getName()));
                statFun.getBody().add(ifStatement);
                statFun.getBody().add(new LuaExpression("-- End Event " + event.getName()));
            } else {
                LuaFunctionDeclare statFun = (LuaFunctionDeclare) ((LuaScript) scene.getChunks().get("scene")).getChunks().get(event.getTriggerType().getScriptPart());
                statFun.getBody().add(new LuaExpression("-- Begin Event " + event.getName()));
                for (LuaStatement action : event.getAction().getStatementList()) {
                    statFun.getBody().add(action);
                }
                statFun.getBody().add(new LuaExpression("-- End Event " + event.getName()));
            }
        }
    }


    private void fillConfigScript(LuaScript config, String rootPath) {
        if (projectModel.getSettings().isCacheModels()) {

            LuaScript models = (LuaScript) ((LuaScript) config.getChunks().get("config")).getChunks().get("models_buffer");

            File folder = new File(rootPath + File.separator + ProjectModel.MODELS_DIR);
            File[] listOfFiles = folder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    models.getChunks().put("M_" + i, new LuaExpression(listOfFiles[i].getName()));
                }
            }

        }

        if (projectModel.getSettings().isCacheModels()) {

            LuaScript models = (LuaScript) ((LuaScript) config.getChunks().get("config")).getChunks().get("images_buffer");

            File folder = new File(rootPath + File.separator + ProjectModel.IMAGES_DIR);
            File[] listOfFiles = folder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    models.getChunks().put("T_" + i, new LuaExpression(listOfFiles[i].getName()));
                }
            }

        }

        ((LuaScript) config.getChunks().get("config")).getChunks().put("first_script", new LuaExpression(projectModel.getFirstRoom()));

    }

    private static String createHeaderComment(String name) {
        StringBuilder sb = new StringBuilder();
        Calendar gc = Calendar.getInstance();
        sb.append("-- Script: ")
                .append(name)
                .append(".lua\n-- Date: ")
                .append(DATE_FORMAT.format(gc.getTime()))
                .append("\n-- Info: ")
                .append(AppInfo.EMAIL)
                .append("\n-- Version: ")
                .append(AppInfo.VERSION)
                .append("\n-- This script was automatically generated with HPMS ToolBox.");


        return sb.toString();

    }
}
