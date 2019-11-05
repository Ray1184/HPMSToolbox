package org.hpms.gui.control.delegate;

import org.hpms.gui.control.Controllers;
import org.hpms.gui.logic.ProjectManager;
import org.hpms.gui.utils.ErrorManager;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LoadProjectDelegate {
    public LoadProjectDelegate() {
    }

    public void loadProject() {
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("HPMSToolBox Projects", "hproj");
        f.setFileFilter(filter);
        f.showSaveDialog(null);
        f.setAcceptAllFileFilterUsed(false);
        f.setDialogTitle("Select Project to load");
        try {
            if (f.getSelectedFile() == null) {
                return;
            }
            ProjectManager.getInstance().buildFromFile(f.getSelectedFile().getAbsolutePath());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ErrorManager.createReadOnlyJTextField(ex), "Error", JOptionPane.PLAIN_MESSAGE);
        }
        Controllers.updateAll();

    }
}