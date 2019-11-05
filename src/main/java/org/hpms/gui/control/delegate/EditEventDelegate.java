package org.hpms.gui.control.delegate;

import org.hpms.gui.control.Controllers;
import org.hpms.gui.control.ToolsController;
import org.hpms.gui.data.ProjectModel;
import org.hpms.gui.logic.ProjectManager;
import org.hpms.gui.luagen.components.LuaFunctionDeclare;
import org.hpms.gui.luagen.parser.LuaFunctionParser;
import org.hpms.gui.utils.ErrorManager;
import org.hpms.gui.views.BaseGui;
import org.hpms.gui.views.CreateNewEventWizard;
import org.hpms.gui.views.CreateNewEventWizardStaticFunction;
import org.hpms.gui.views.CreateNewEventWizardStepNameType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EditEventDelegate {

    public static final String NEW_EVENT = "NEW_EVENT";
    public static final String STATIC_FUNCTION = "STATIC_FUNCTION";
    public static final String ROOM_EVENT = "ROOM_EVENT";

    private final CreateNewEventWizard mainPanel;
    private final CreateNewEventWizardStepNameType newEvent;
    private final CreateNewEventWizardStaticFunction staticFunction;
    private String originalCode;
    private String eventName;

    public EditEventDelegate() {
        mainPanel = new CreateNewEventWizard(BaseGui.getInstance().getMainFrame());
        newEvent = new CreateNewEventWizardStepNameType();
        staticFunction = new CreateNewEventWizardStaticFunction();
        initListeners();

    }

    public void editEvent() {

        ToolsController toolsController = (ToolsController) Controllers.TOOLS_CONTROLLER.getController();
        eventName = toolsController.getSelectedEvent();
        ProjectModel projectModel = ProjectManager.getInstance().getProjectModel();
        if (projectModel == null) {
            return;
        }
        if (projectModel.getCommonFunctions().containsKey(eventName)) {
            JPanel wizardManager = mainPanel.getWizardManager();
            editStaticFunction(projectModel.getCommonFunctions().get(eventName));
            wizardManager.add(staticFunction, STATIC_FUNCTION);
            expand();
            mainPanel.setVisible(true);
        } else if (projectModel.getRooms().containsKey(toolsController.getSelectedRoom()) &&
                projectModel.getRooms().get(toolsController.getSelectedRoom()).getEventsById().containsKey(eventName)) {
            editRoomEvent();
        }
    }


    private void editStaticFunction(LuaFunctionDeclare oldFunction) {
        staticFunction.getCodeArea().setText(LuaFunctionParser.getBody(oldFunction));
        staticFunction.getArgsTxt().setText(LuaFunctionParser.getParams(oldFunction));
        originalCode = staticFunction.getCodeArea().getText();

        staticFunction.getCancBtn().setText("Cancel");


    }

    private void collapse() {
        staticFunction.getCodeArea().setRows(0);
        staticFunction.getCodeArea().setColumns(0);
        mainPanel.pack();
    }

    private void expand() {
        staticFunction.getCodeArea().setRows(30);
        staticFunction.getCodeArea().setColumns(80);
        mainPanel.pack();
    }

    private boolean codeChanged() {
        return !originalCode.equals(staticFunction.getCodeArea().getText());
    }

    private void editRoomEvent() {
    }

    private void initListeners() {
        mainPanel.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                collapse();
            }
        });

        staticFunction.getCancBtn().addActionListener(e -> {
            if (!codeChanged()) {
                collapse();
                mainPanel.dispose();
            } else {
                JFrame frame = new JFrame();
                int dialogResult = JOptionPane.showConfirmDialog(frame, "You will loose your change, do you want to proceed?", "Warning", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    frame.dispose();
                    collapse();
                    mainPanel.dispose();
                }
            }

        });

        staticFunction.getOkBtn().addActionListener(e -> {
            String codeTxt = staticFunction.getCodeArea().getText();
            String params = staticFunction.getArgsTxt().getText();
            try {
                LuaFunctionDeclare function = LuaFunctionParser.parse(eventName, params, codeTxt);
                ProjectManager.getInstance().getProjectModel().getCommonFunctions().put(eventName, function);
                Controllers.updateAll();
                mainPanel.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ErrorManager.createReadOnlyJTextField(ex), "Error", JOptionPane.PLAIN_MESSAGE);
            }
        });
    }
}

