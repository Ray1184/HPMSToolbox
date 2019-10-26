package org.hpms.gui.control.wizard;

import org.hpms.gui.views.BaseGui;
import org.hpms.gui.views.CreateNewEventWizard;
import org.hpms.gui.views.CreateNewEventWizardStepNameType;

public class EventWizardDelegate {

    public void build() {
        CreateNewEventWizard wizardWindow = new CreateNewEventWizard(BaseGui.getInstance().getMainFrame());
        CreateNewEventWizardStepNameType wizardName = new CreateNewEventWizardStepNameType();
        wizardName.setVisible(true);
        wizardWindow.pack();
        wizardWindow.setVisible(true);
        wizardWindow.getSubContainerWizard().add(wizardName);

    }
}
