import org.hpms.gui.control.evtflow.ActionStep;
import org.hpms.gui.control.evtflow.ConditionStep;
import org.hpms.gui.control.evtflow.VariableCreationStep;
import org.hpms.gui.control.evtflow.WorkflowStep;
import org.hpms.gui.luagen.LuaStatement;
import org.hpms.gui.luagen.components.LuaExpression;
import org.hpms.gui.luagen.components.LuaIfStatement;
import org.hpms.gui.luagen.components.LuaInstance;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TestWorkflow {

    private static int y = 10;

    public static void main(String[] args) {


        JFrame frame = new JFrame("Test WF");
        JPanel panel = (JPanel) frame.getContentPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(null);


        VariableCreationStep variableCreationStep = new VariableCreationStep();
        variableCreationStep.setStatement(new LuaInstance("test_var", LuaInstance.Type.NUMBER, null, 5));
        ConditionStep conditionStep = new ConditionStep();
        LuaIfStatement.LuaCondition.LuaSingleCondition singCond = new LuaIfStatement.LuaCondition.LuaSingleCondition(new LuaExpression("!created"));

        LuaIfStatement.LuaCondition condition = new LuaIfStatement.LuaCondition(singCond);

        List<LuaStatement> actions = new ArrayList<>();
        actions.add(new LuaExpression("e = hpms.create_entity('TestModel.hmdat')"));
        actions.add(new LuaExpression("e.rotate(2, 1, 1)"));
        actions.add(new LuaExpression("created = true"));
        LuaIfStatement ifSt = new LuaIfStatement(condition);
        conditionStep.setStatement(ifSt);

        ActionStep actionStep = new ActionStep();
        actionStep.setStatement(new LuaExpression("hpms.create_entity('PLAYER______________________\n-- jweijgiwejgj')"));

        conditionStep.getChildren().add(actionStep);

        addLabel(variableCreationStep, panel);
        addLabel(conditionStep, panel);
        for (WorkflowStep s : conditionStep.getChildren()) {
            s.setParent(conditionStep);
            addLabel(s, panel);
        }

        frame.setSize(800, 600);
        frame.setVisible(true);
        //frame.pack();


    }

    private static void addLabel(WorkflowStep workflowStep, JComponent panel) {
        JLabel label = new JLabel(workflowStep.getDescription());
        String tooltip = "<html>" + workflowStep.getDescription().replaceAll("\\n", "<br/>") + "</html>";
        label.setToolTipText(tooltip);
        Font f = new Font("Courier New", Font.PLAIN, 16);
        label.setFont(f);
        label.setForeground(Color.BLACK);
        label.setOpaque(true);
        label.setBackground(workflowStep.getLabelColor());
        Dimension size = label.getPreferredSize();
        int width = size.width * 1.1 < 300 ? (int) (size.width * 1.1) : 300;
        label.setBounds(workflowStep.getIndent() * 30, y, width, size.height);
        label.setBorder(new SoftBevelBorder(BevelBorder.RAISED, workflowStep.getLabelColor(), Color.DARK_GRAY));
        y += f.getSize() * 2;
        panel.add(label);
    }
}
