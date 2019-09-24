package org.hpms.gui.logic;

import org.hpms.gui.data.ProjectModel;
import org.hpms.gui.luagen.LuaStatement;
import org.hpms.gui.luagen.components.LuaExpression;
import org.hpms.gui.luagen.components.LuaScript;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class ScriptBuilder {

    private final ProjectModel projectModel;

    public ScriptBuilder(ProjectModel projectModel) {
        this.projectModel = projectModel;
    }

    public void createScripts(String scriptsPath) {
        TemplateGenerator generator = new TemplateGenerator();
        LuaScript config = generator.getConfigScriptTemplate();
        fillConfigScript(config);


    }

    private void fillConfigScript(LuaScript config) {
        if (projectModel.getSettings().isCacheModels()) {

            LuaScript models = (LuaScript) ((LuaScript) config.getChunks().get("config")).getChunks().get("models_buffer");

            File folder = new File(ProjectModel.MODELS_DIR);
            File[] listOfFiles = folder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    models.getChunks().put("M_" + i, new LuaExpression(listOfFiles[i].getName()));
                }
            }

        }

        if (projectModel.getSettings().isCacheModels()) {

            LuaScript models = (LuaScript) ((LuaScript) config.getChunks().get("config")).getChunks().get("images_buffer");

            File folder = new File(ProjectModel.IMAGES_DIR);
            File[] listOfFiles = folder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    models.getChunks().put("T_" + i, new LuaExpression(listOfFiles[i].getName()));
                }
            }

        }

        ((LuaScript) config.getChunks().get("config")).getChunks().put("first_script", new LuaExpression(projectModel.getFirstRoom()));

    }
}
