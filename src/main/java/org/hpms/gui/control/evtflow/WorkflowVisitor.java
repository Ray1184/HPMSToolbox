package org.hpms.gui.control.evtflow;

@FunctionalInterface
public interface WorkflowVisitor {

    void visit(WorkflowManager.WorkflowNode node);
}
