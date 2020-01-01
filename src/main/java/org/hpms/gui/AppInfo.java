package org.hpms.gui;

import java.awt.*;

public class AppInfo {

    public static final String VERSION = "0.1a";
    public static final String EMAIL = "toolbox.hpms@gmail.com";
    public static final Dimension DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();

    public static final int FONT_SIZE = 33177600 / (DIMENSION.width * DIMENSION.height);

    private static String currentWorkspace;

    /**
     * Getter for property 'currentWorkspace'.
     *
     * @return Value for property 'currentWorkspace'.
     */
    public static String getCurrentWorkspace() {
        return currentWorkspace;
    }

    /**
     * Setter for property 'currentWorkspace'.
     *
     * @param currentWorkspace Value to set for property 'currentWorkspace'.
     */
    public static void setCurrentWorkspace(String currentWorkspace) {
        AppInfo.currentWorkspace = currentWorkspace;
    }
}
