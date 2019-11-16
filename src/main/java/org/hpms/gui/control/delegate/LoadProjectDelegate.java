package org.hpms.gui.control.delegate;

import org.hpms.gui.control.Controllers;
import org.hpms.gui.control.ToolsController;
import org.hpms.gui.logic.ProjectManager;
import org.hpms.gui.utils.ErrorManager;
import org.hpms.gui.views.BaseGui;

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
            if (f.getSelectedFile() == null || !f.getSelectedFile().exists() || !f.getSelectedFile().getName().endsWith(".hproj")) {
                return;
            }
            ProjectManager.getInstance().buildFromFile(f.getSelectedFile().getAbsolutePath());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ErrorManager.createReadOnlyJTextField(ex), "Error", JOptionPane.PLAIN_MESSAGE);
        }
        Controllers.updateAll();
        if (ProjectManager.getInstance().getProjectModel().getRooms().size() > 0) {
            BaseGui.getInstance().getRoomsList().setSelectedIndex(0);
        }

    }
}