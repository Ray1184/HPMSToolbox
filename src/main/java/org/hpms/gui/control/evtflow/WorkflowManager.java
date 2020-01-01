package org.hpms.gui.control.evtflow;

import org.hpms.gui.AppInfo;
import org.hpms.gui.utils.FinalWrapper;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.util.Objects;

public class WorkflowManager {

    public static class WorkflowNode {
        public WorkflowStep currentStep;
        public WorkflowNode previousNode;
        public WorkflowNode nextNode;
        public WorkflowNode ifChildrenNode;
        public WorkflowNode elseChildrenNode;
        public WorkflowNode parentNode;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            WorkflowNode that = (WorkflowNode) o;
            return Objects.equals(currentStep, that.currentStep) &&
                    Objects.equals(previousNode, that.previousNode) &&
                    Objects.equals(nextNode, that.nextNode) &&
                    Objects.equals(ifChildrenNode, that.ifChildrenNode) &&
                    Objects.equals(elseChildrenNode, that.elseChildrenNode) &&
                    Objects.equals(parentNode, that.parentNode);
        }

        @Override
        public int hashCode() {
            return Objects.hash(currentStep, previousNode, nextNode, ifChildrenNode, elseChildrenNode, parentNode);
        }
    }


    private final WorkflowNode workflowRoot;


    public WorkflowManager() {
        workflowRoot = new WorkflowNode();
    }

    public void addStep(WorkflowStep stepToAdd) {
        if (workflowRoot.currentStep == null) {
            workflowRoot.currentStep = stepToAdd;
        } else {
            traverse(workflowRoot, node -> {
                if (node.nextNode == null) {
                    WorkflowNode nextNode = new WorkflowNode();
                    nextNode.currentStep = stepToAdd;
                    nextNode.previousNode = node;
                    node.nextNode = nextNode;
                }
            });
        }
    }

    public void addStep(WorkflowStep stepToAdd, WorkflowStep fromStep) {
        traverse(workflowRoot, node -> {
            WorkflowStep step = node.currentStep;
            if (step != null && step.equals(fromStep)) {
                WorkflowNode nextNode = new WorkflowNode();
                nextNode.currentStep = stepToAdd;
                nextNode.previousNode = node;
                node.nextNode = nextNode;
            }
        });
    }

    public void addIfChildrenStep(WorkflowStep stepToAdd, ConditionStep fromStep) {
        traverse(workflowRoot, node -> {
            WorkflowStep step = node.currentStep;
            if (step != null && step.equals(fromStep)) {
                WorkflowNode childNode = new WorkflowNode();
                childNode.currentStep = stepToAdd;
                childNode.parentNode = node;
                node.ifChildrenNode = childNode;
            }
        });
    }

    public void addElseChildrenStep(WorkflowStep stepToAdd, ConditionStep fromStep) {
        traverse(workflowRoot, node -> {
            WorkflowStep step = node.currentStep;
            if (step != null && step.equals(fromStep)) {
                WorkflowNode childNode = new WorkflowNode();
                childNode.currentStep = stepToAdd;
                childNode.parentNode = node;
                node.elseChildrenNode = childNode;
            }
        });
    }

    public static void traverse(WorkflowNode workflowRoot, WorkflowVisitor visitor) {
        visitor.visit(workflowRoot);
        if (workflowRoot.ifChildrenNode != null) {
            traverse(workflowRoot.ifChildrenNode, visitor);
        }
        if (workflowRoot.elseChildrenNode != null) {
            traverse(workflowRoot.ifChildrenNode, visitor);
        }
        if (workflowRoot.nextNode != null) {
            traverse(workflowRoot.nextNode, visitor);
        }
    }

    public void repaint(JComponent panel) {
        panel.removeAll();
        FinalWrapper<Integer> yWrapper = new FinalWrapper<>(10);
        traverse(workflowRoot, node -> {
            if (node != null && node.currentStep != null) {
                WorkflowStep step = node.currentStep;
                int newY = addLabel(step, panel, yWrapper.getValue());
                yWrapper.setValue(yWrapper.getValue() + newY);
            }
        });
    }

    private static int addLabel(WorkflowStep workflowStep, JComponent panel, int lastY) {
        JLabel label = new JLabel(workflowStep.getDescription());
        String tooltip = "<html>" + workflowStep.getDescription().replaceAll("\\n", "<br/>") + "</html>";
        label.setToolTipText(tooltip);
        Font f = new Font("Courier New", Font.PLAIN, AppInfo.FONT_SIZE);
        label.setFont(f);
        label.setForeground(Color.BLACK);
        label.setOpaque(true);
        label.setBackground(workflowStep.getLabelColor());
        Dimension size = label.getPreferredSize();
        int width = size.width * 1.1 < AppInfo.DIMENSION.width / 6 ? (int) (size.width * 1.1) : AppInfo.DIMENSION.width / 6;
        label.setBounds(workflowStep.getIndent() * 30, lastY, width, size.height);
        label.setBorder(new SoftBevelBorder(BevelBorder.RAISED, workflowStep.getLabelColor(), Color.DARK_GRAY));
        lastY += f.getSize() * 2;
        panel.add(label);
        return lastY;
    }
}
