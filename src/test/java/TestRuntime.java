import org.hpms.gui.data.ProjectModel;
import org.hpms.gui.logic.GameDataBuilder;
import org.hpms.gui.logic.ProjectManager;
import org.hpms.gui.logic.ScriptBuilder;
import org.hpms.gui.luagen.LuaStatement;
import org.hpms.gui.luagen.components.LuaExpression;
import org.hpms.gui.luagen.components.LuaFunctionDeclare;
import org.hpms.gui.luagen.components.LuaIfStatement;
import org.hpms.gui.luagen.components.LuaInstance;

import java.io.FileNotFoundException;
import java.util.*;

public class TestRuntime {

    /// TEST ONLY
    public static void main(String[] args) {
        testScriptGen();
    }

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


        Map<String, LuaFunctionDeclare> funList = new HashMap<>();
        funList.put("CHK_Sector", fn);
        funList.put("CHK_Sector_0", fn2);
        project.setCommonFunctions(funList);

        room.setEventsById(evtsById);
        project.getRooms().put(room.getName(), room);
        ScriptBuilder sb = new ScriptBuilder(project);
        try {
            sb.createScripts("C:\\HPMSTest");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
