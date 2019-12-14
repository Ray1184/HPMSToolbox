package org.hpms.gui.control.evtflow;

import org.hpms.gui.luagen.LuaStatement;

import javax.swing.*;
import java.awt.*;

public interface WorkflowStep {

    int INDENT = 1;

    LuaStatement getStatement();

    JFrame getGui();

    String getDescription();

    int getIndent();

    WorkflowStep getParent();

    void setParent(WorkflowStep parent);

    Color getLabelColor();
}
