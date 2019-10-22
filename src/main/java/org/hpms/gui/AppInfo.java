package org.hpms.gui;

public class AppInfo {

    public static final String VERSION = "0.1a";
    public static final String EMAIL = "toolbox.hpms@gmail.com";

    public static String currentWorkspace;

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
