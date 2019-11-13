package org.hpms.gui.control.delegate;

import org.hpms.gui.control.w3d.W3DManager;
import org.hpms.gui.utils.EasyDocumentListener;
import org.hpms.gui.views.BaseGui;
import org.hpms.gui.views.CreateNewSectorGroup;

public class CreateSectorGroupDelegate {

    private final CreateNewSectorGroup createNewSectorGroup;

    public CreateSectorGroupDelegate() {
        createNewSectorGroup = new CreateNewSectorGroup(BaseGui.getInstance().getMainFrame());
        initListeners();
    }


    public void createSectorGroup() {
        createNewSectorGroup.pack();
        createNewSectorGroup.setVisible(true);
    }

    private void initListeners() {
        createNewSectorGroup.getOkBtn().setEnabled(false);
        createNewSectorGroup.getCancBtn().addActionListener(e -> createNewSectorGroup.dispose());
        createNewSectorGroup.getNewSgNameTxt().getDocument().addDocumentListener((EasyDocumentListener) e -> {
            if (createNewSectorGroup.getNewSgNameTxt().getText().isEmpty()) {
                createNewSectorGroup.getOkBtn().setEnabled(false);
            } else {
                createNewSectorGroup.getOkBtn().setEnabled(true);
            }
        });

        createNewSectorGroup.getOkBtn().addActionListener(e -> {
            W3DManager.getInstance().currentSectorGroup = createNewSectorGroup.getNewSgNameTxt().getText();
            W3DManager.getInstance().createSectorGroup = true;
            createNewSectorGroup.dispose();

        });
    }
}
