package org.hpms.gui;

import org.apache.commons.io.FileUtils;
import org.hpms.gui.control.MainController;
import org.hpms.gui.data.ProjectModel;
import org.hpms.gui.logic.GameDataBuilder;
import org.hpms.gui.logic.ProjectManager;
import org.hpms.gui.logic.ScriptBuilder;
import org.hpms.gui.luagen.LuaStatement;
import org.hpms.gui.luagen.components.LuaExpression;
import org.hpms.gui.luagen.components.LuaFunctionDeclare;
import org.hpms.gui.luagen.components.LuaIfStatement;
import org.hpms.gui.luagen.components.LuaInstance;
import org.hpms.gui.views.BaseGui;
import org.hpms.gui.views.WorkspaceChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static org.hpms.gui.AppInfo.EMAIL;
import static org.hpms.gui.AppInfo.VERSION;

public class Main {


    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(Main::createAndShowGUI);


    }

    private static void checkWorkspace() throws Exception {
        File jarFile = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        File file = new File(jarFile.getParentFile(), "Toolbox.ini");
        if (!file.exists()) {
            WorkspaceChooser frame = new WorkspaceChooser();
            frame.pack();
            frame.setVisible(true);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.getWorkspaceLoadBtn().addActionListener(e -> {
                JFileChooser f = new JFileChooser();
                f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                f.setCurrentDirectory(new java.io.File("."));
                f.showSaveDialog(null);
                f.setAcceptAllFileFilterUsed(false);
                f.setDialogTitle("Select Workspace Folder");
                frame.getWorkspaceTxt().setText(f.getSelectedFile().getAbsolutePath());
            });
            frame.getConfirmWorkspaceBtn().addActionListener(e -> {
                String path = frame.getWorkspaceTxt().getText();
                try {
                    file.createNewFile();
                    FileUtils.write(file, path, "UTF-8");
                    if (!new File(path).exists()) {
                        new File(path).mkdirs();
                    }
                    new File(path + File.separator + ".workspace").mkdirs();
                    AppInfo.setCurrentWorkspace(path);
                    startGui();
                } catch (IOException ex) {
                    frame.dispose();
                    JOptionPane.showMessageDialog(null, createReadOnlyJTextField(ex), "Error", JOptionPane.PLAIN_MESSAGE);
                }
            });

        } else {
           startGui();
        }
    }

    private static void startGui() {
        BaseGui gui = BaseGui.getInstance();
        JFrame frame = gui.getMainFrame();
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MainController m = new MainController();
        m.update();
    }



    private static void createAndShowGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            JOptionPane.showMessageDialog(null, createReadOnlyJTextField(e), "Error", JOptionPane.PLAIN_MESSAGE);

        }

        try {
            registerSerializables();
            checkWorkspace();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, createReadOnlyJTextField(e), "Error", JOptionPane.PLAIN_MESSAGE);
            System.exit(-1);
        }


    }

    private static Object createReadOnlyJTextField(Exception e) {
        StringBuilder sb = new StringBuilder();
        sb.append("HPMS ToolBox version ")
                .append(VERSION)
                .append("\n\nError Message: ")
                .append(e.toString())
                .append("\n\nError Stack Trace:\n");
        for (StackTraceElement st : e.getStackTrace()) {
            sb.append(st.toString())
                    .append("\n");
        }
        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setColumns(80);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        JScrollPane inner = new JScrollPane(textArea);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("An error occurred while using HPMS ToolBox. Please copy and send following error details to: " + EMAIL);
        panel.add(label);
        panel.add(new JLabel(" "));
        panel.add(inner);
        return panel;

    }

    private static void registerSerializables() {
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.class);
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.ProjectPreferences.class);
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.BuildSettings.class);
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.UserSettings.class);
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.RoomModel.class);
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.RoomModel.Event.class);
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.RoomModel.Event.Action.class);
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.RoomModel.Event.ConditionAction.class);
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.RoomModel.Event.TriggerType.class);
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.RoomModel.SectorGroup.class);
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.RoomModel.SectorGroup.Sector.class);
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.RoomModel.SectorGroup.Sector.PerimetralSide.class);
        ProjectManager.KRYO_SERIALIZER.register(ArrayList.class);
        ProjectManager.KRYO_SERIALIZER.register(HashMap.class);
    }




    /// TEST ONLY

    private static void testRuntimeGen() {
        ProjectManager pm = ProjectManager.getInstance();
        pm.buildEmptyProject();
        ProjectModel project = pm.getProjectModel();
        GameDataBuilder builder = new GameDataBuilder(project);
        try {
            builder.build("C:\\HPMSTest", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testScriptGen() {
        ProjectManager pm = ProjectManager.getInstance();
        pm.buildEmptyProject();
        ProjectModel project = pm.getProjectModel();
        project.setFirstRoom("Debug_Room");
        ProjectModel.RoomModel room = new ProjectModel.RoomModel();
        room.setName("Debug_Room");
        room.setPipelineType(ProjectModel.RoomModel.PipelineType.R25D);
        Map<String, ProjectModel.RoomModel.Event> evtsById = new LinkedHashMap<>();

        ProjectModel.RoomModel.Event evt = new ProjectModel.RoomModel.Event();
        evt.setName("CREATE_DUMMY_ENTITY");
        evt.setTriggerType(ProjectModel.RoomModel.Event.TriggerType.LOOP);
        ProjectModel.RoomModel.Event.ConditionAction condAct = new ProjectModel.RoomModel.Event.ConditionAction();

        LuaIfStatement.LuaCondition.LuaSingleCondition singCond = new LuaIfStatement.LuaCondition.LuaSingleCondition(new LuaExpression("!created"));

        LuaIfStatement.LuaCondition condition = new LuaIfStatement.LuaCondition(singCond);

        List<LuaStatement> actions = new ArrayList<>();
        actions.add(new LuaExpression("e = hpms.create_entity('TestModel.hmdat')"));
        actions.add(new LuaExpression("e.rotate(2, 1, 1)"));
        actions.add(new LuaExpression("created = true"));
        LuaIfStatement ifSt = new LuaIfStatement(condition, actions);
        condAct.setIfStatement(ifSt);
        evt.setConditionAction(condAct);

        evtsById.put("CREATE_DUMMY_ENTITY", evt);


        ProjectModel.RoomModel.Event.Action act = new ProjectModel.RoomModel.Event.Action();

        LuaInstance ret = new LuaInstance("ret_type", LuaInstance.Type.BOOLEAN);
        LuaInstance param = new LuaInstance("player", LuaInstance.Type.OBJECT);
        LuaInstance param2 = new LuaInstance("sector", LuaInstance.Type.OBJECT);
        LuaExpression body = new LuaExpression("return true");

        // ----
        ProjectModel.RoomModel.Event.ConditionAction condAct2 = new ProjectModel.RoomModel.Event.ConditionAction();

        LuaIfStatement.LuaCondition.LuaSingleCondition singCond2 = new LuaIfStatement.LuaCondition.LuaSingleCondition(new LuaExpression("hpms.point_in_sector(player.x, player.y, sector)"));

        LuaIfStatement.LuaCondition condition2 = new LuaIfStatement.LuaCondition(singCond2);

        List<LuaStatement> actions2 = new ArrayList<>();
        actions2.add(new LuaExpression("return true"));
        LuaIfStatement ifSt2 = new LuaIfStatement(condition2, actions2);
        // ----
        List<LuaInstance> params = new ArrayList<>();
        params.add(param);
        params.add(param2);
        LuaFunctionDeclare fn = new LuaFunctionDeclare(ret, "check_sector", params, Collections.singletonList(ifSt2), true);
        LuaFunctionDeclare fn2 = new LuaFunctionDeclare(ret, "check_sector_0", params, Collections.singletonList(ifSt2), true);


        List<LuaFunctionDeclare> funList = new ArrayList<>();
        funList.add(fn);
        funList.add(fn2);
        project.setCommonFunctions(funList);

        room.setEventsById(evtsById);
        project.getRooms().add(room);
        ScriptBuilder sb = new ScriptBuilder(project);
        try {
            sb.createScripts("C:\\HPMSTest");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
