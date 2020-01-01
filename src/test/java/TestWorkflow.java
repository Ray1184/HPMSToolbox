import org.hpms.gui.AppInfo;
import org.hpms.gui.control.evtflow.ConditionStep;
import org.hpms.gui.control.evtflow.WorkflowManager;
import org.hpms.gui.control.evtflow.WorkflowStep;
import org.hpms.gui.luagen.components.LuaExpression;
import org.hpms.gui.luagen.components.LuaIfStatement;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestWorkflow implements ActionListener {

    private static int y = 10;
    private final JPanel workflowContainer;
    private WorkflowManager workflowManager;

    public static void main(String[] args) {

        new TestWorkflow();


    }

    public TestWorkflow() {
        JFrame frame = new JFrame("Test WF");
        JPanel panel = (JPanel) frame.getContentPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        workflowManager = new WorkflowManager();
        workflowContainer = new JPanel();
        workflowContainer.setLayout(null);
        JPanel buttonsContainer = new JPanel();
        buttonsContainer.setLayout(new FlowLayout());

        JButton condBtn = new JButton("New Condition");
        condBtn.setActionCommand("COND");
        condBtn.addActionListener(this);
        JButton actBtn = new JButton("New Action");
        actBtn.setActionCommand("ACT");
        actBtn.addActionListener(this);
        JButton varCrtBtn = new JButton("New Variable");
        varCrtBtn.setActionCommand("VARC");
        varCrtBtn.addActionListener(this);
        JButton varSetBtn = new JButton("Set Variable");
        varSetBtn.setActionCommand("VARS");
        varSetBtn.addActionListener(this);

        buttonsContainer.add(condBtn);
        buttonsContainer.add(actBtn);
        buttonsContainer.add(varCrtBtn);
        buttonsContainer.add(varSetBtn);



        /*VariableCreationStep variableCreationStep = new VariableCreationStep();
        variableCreationStep.setStatement(new LuaInstance("test_var", LuaInstance.Type.NUMBER, null, 5));
        ConditionStep conditionStep = new ConditionStep();
        LuaIfStatement.LuaCondition.LuaSingleCondition singCond = new LuaIfStatement.LuaCondition.LuaSingleCondition(new LuaExpression("!created"));

        LuaIfStatement.LuaCondition condition = new LuaIfStatement.LuaCondition(singCond);

        LuaIfStatement ifSt = new LuaIfStatement(condition);
        conditionStep.setStatement(ifSt);

        ActionStep actionStep = new ActionStep();
        actionStep.setStatement(new LuaExpression("hpms.create_entity('PLAYER______________________\n-- jweijgiwejgj')"));

        //conditionStep.getChildren().add(actionStep);

        addTestLabel(variableCreationStep, workflowContainer);
        addTestLabel(conditionStep, workflowContainer);*/
        //for (WorkflowStep s : conditionStep.getChildren()) {
        //    s.setParent(conditionStep);
        //    addLabel(s, panel);
        //}


        panel.add(workflowContainer);
        panel.add(buttonsContainer);
        frame.setSize(800, 600);
        frame.setVisible(true);
        //frame.pack();

    }

    private static void addTestLabel(WorkflowStep workflowStep, JComponent panel) {
        JLabel label = new JLabel(workflowStep.getDescription());
        String tooltip = "<html>" + workflowStep.getDescription().replaceAll("\\n", "<br/>") + "</html>";
        label.setToolTipText(tooltip);
        Font f = new Font("Courier New", Font.PLAIN, AppInfo.FONT_SIZE);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        String exp = JOptionPane.showInputDialog(new JFrame(), e.getActionCommand());
        switch (e.getActionCommand()) {
            case "COND":
                ConditionStep step = new ConditionStep();
                LuaIfStatement.LuaCondition.LuaSingleCondition singCond = new LuaIfStatement.LuaCondition.LuaSingleCondition(new LuaExpression(exp));
                LuaIfStatement.LuaCondition cond = new LuaIfStatement.LuaCondition(singCond);
                cond.setParentIndent("");
                LuaIfStatement stat = new LuaIfStatement(cond);
                step.setStatement(stat);
                workflowManager.addStep(step);
                break;
            case "ACT":
                break;
            case "VARC":
                break;
            case "VARS":
                break;

        }

        workflowManager.repaint(workflowContainer);
    }
}
