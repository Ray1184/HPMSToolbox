package org.hpms.gui.control.delegate;

import org.hpms.gui.control.Controllers;
import org.hpms.gui.data.ProjectModel;
import org.hpms.gui.logic.ProjectManager;
import org.hpms.gui.luagen.components.LuaFunctionDeclare;
import org.hpms.gui.luagen.parser.LuaFunctionParser;
import org.hpms.gui.utils.EasyDocumentListener;
import org.hpms.gui.utils.ErrorManager;
import org.hpms.gui.views.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CreateEventDelegate {
    public static final String NEW_EVENT = "NEW_EVENT";
    public static final String STATIC_FUNCTION = "STATIC_FUNCTION";
    public static final String ROOM_EVENT = "ROOM_EVENT";

    private final CreateNewEventWizard mainPanel;
    private final CreateNewEventWizardStepNameType newEvent;
    private final CreateNewEventWizardStaticFunction staticFunction;

    // Context data
    private String functionName;
    private String eventName;
    private String originalCode;

    public CreateEventDelegate() {
        mainPanel = new CreateNewEventWizard(BaseGui.getInstance().getMainFrame());
        newEvent = new CreateNewEventWizardStepNameType();
        staticFunction = new CreateNewEventWizardStaticFunction();
        initListeners();
    }

    public void createEvent() {
        configureWizardPhases();
        mainPanel.pack();
        mainPanel.setVisible(true);
    }

    private void configureWizardPhases() {
        JPanel wizardManager = mainPanel.getWizardManager();
        buildNewEventPanel();
        wizardManager.add(newEvent, NEW_EVENT);
        wizardManager.add(staticFunction, STATIC_FUNCTION);
        initListeners();
    }


    private void buildNewEventPanel() {
        mainPanel.setTitle("Create New Event");
        newEvent.getEventLclCombo().addItem(new Labels.TriggerTypeItem("Init Phase (triggered 1 time on room startup)", ProjectModel.RoomModel.Event.TriggerType.INIT));
        newEvent.getEventLclCombo().addItem(new Labels.TriggerTypeItem("Loop Phase (triggered each update)", ProjectModel.RoomModel.Event.TriggerType.LOOP));
        newEvent.getEventLclCombo().addItem(new Labels.TriggerTypeItem("Cleanup Phase (triggered 1 time on room leaving)", ProjectModel.RoomModel.Event.TriggerType.CLEANUP));
        newEvent.getNextBtn().setEnabled(false);
        newEvent.getNewEvtTxt().setText("");
        newEvent.getEventLclCombo().setEnabled(false);


    }


    private boolean codeChanged() {
        return !originalCode.equals(staticFunction.getCodeArea().getText());
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

    private void initListeners() {
        mainPanel.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                collapse();
            }
        });


        newEvent.getNewEvtTxt().getDocument().addDocumentListener((EasyDocumentListener) e -> {
            if (newEvent.getNewEvtTxt().getText().isEmpty()) {
                newEvent.getNextBtn().setEnabled(false);
            } else {
                newEvent.getNextBtn().setEnabled(true);
            }
        });


        staticFunction.getCancBtn().addActionListener(e -> {
            if (!codeChanged()) {
                CardLayout ly = (CardLayout) mainPanel.getWizardManager().getLayout();
                ly.show(mainPanel.getWizardManager(), NEW_EVENT);
                collapse();
            } else {
                JFrame frame = new JFrame();
                int dialogResult = JOptionPane.showConfirmDialog(frame, "You will loose your change, do you want to proceed?", "Warning", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    CardLayout ly = (CardLayout) mainPanel.getWizardManager().getLayout();
                    ly.show(mainPanel.getWizardManager(), NEW_EVENT);
                    collapse();
                }
            }
        });

        staticFunction.getOkBtn().addActionListener(e -> {
            String codeTxt = staticFunction.getCodeArea().getText();
            String params = staticFunction.getArgsTxt().getText();
            try {
                LuaFunctionDeclare function = LuaFunctionParser.parse(functionName, params, codeTxt);
                ProjectManager.getInstance().getProjectModel().getCommonFunctions().put(eventName, function);
                Controllers.updateAll();
                mainPanel.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ErrorManager.createReadOnlyJTextField(ex), "Error", JOptionPane.PLAIN_MESSAGE);
            }
        });

        newEvent.getEventTypeChkbx().addChangeListener(e -> {
            if (newEvent.getEventTypeChkbx().isSelected()) {
                newEvent.getEventLclCombo().setEnabled(true);
            } else {
                newEvent.getEventLclCombo().setEnabled(false);
            }
        });

        newEvent.getCancBtn().addActionListener(e -> mainPanel.dispose());
        newEvent.getNextBtn().addActionListener(e -> {

            if (newEvent.getEventTypeChkbx().isSelected()) {
                CardLayout ly = (CardLayout) mainPanel.getWizardManager().getLayout();
                ly.show(mainPanel.getWizardManager(), ROOM_EVENT);
            } else {
                functionName = newEvent.getNewEvtTxt().getText().trim();
                eventName = functionName;
                functionName = functionName.replaceAll(" ", "_");
                functionName = functionName.replaceAll("-", "_");
                functionName = functionName.toLowerCase();
                if (Character.isDigit(functionName.charAt(0))) {
                    functionName = "f_" + functionName;
                }
                staticFunction.getArgsTxt().setText("");
                staticFunction.getCodeArea().setText("-- Event " + newEvent.getNewEvtTxt().getText().trim()
                        + "\n\n-- Write function body here.");
                originalCode = staticFunction.getCodeArea().getText();
                expand();
                CardLayout ly = (CardLayout) mainPanel.getWizardManager().getLayout();
                ly.show(mainPanel.getWizardManager(), STATIC_FUNCTION);

            }


        });
    }

}
