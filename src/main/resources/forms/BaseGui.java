/*
 * Created by JFormDesigner on Fri Sep 13 13:49:09 CEST 2019
 */

package org.hpms.gui.views;

import org.hpms.gui.utils.ListEntryCellRenderer;

import javax.swing.*;
import java.awt.*;

/**
 * @author N
 */
public class BaseGui {

    private static final BaseGui INSTANCE = new BaseGui();

    public static BaseGui getInstance() {
        return INSTANCE;
    }

    private BaseGui() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - N
        mainFrame = new JFrame();
        mainMenu = new JMenuBar();
        projectBtn = new JMenu();
        newProjectBtn = new JMenuItem();
        loadProjectBtn = new JMenuItem();
        saveProjectBtn = new JMenuItem();
        settingsProjectBtn = new JMenuItem();
        exitProjectBtn = new JMenuItem();
        toolsBtn = new JMenu();
        newRoomToolsBtn = new JMenuItem();
        newSecGrToolsBtn = new JMenuItem();
        newEventTolsBtn = new JMenuItem();
        buildBtn = new JMenu();
        runtimeBuildBtn = new JMenuItem();
        settingsBuildBtn = new JMenuItem();
        helpBtn = new JMenu();
        tutorialsHelpBtn = new JMenuItem();
        aboutHelpsBtn = new JMenuItem();
        hSplitter = new JSplitPane();
        vSplitter = new JSplitPane();
        roomsTabs = new JTabbedPane();
        roomsTab = new JScrollPane();
        roomsList = new JList();
        functionsTab = new JScrollPane();
        functionsList = new JList();
        toolsTab = new JTabbedPane();
        sgTab = new JScrollPane();
        sgList = new JList();
        eventsTab = new JScrollPane();
        eventsList = new JList();
        workArea = new JScrollPane();
        infoLabel = new JLabel();

        //======== mainFrame ========
        {
            mainFrame.setBackground(new Color(102, 102, 102));
            mainFrame.setTitle("HPMS GUI");
            mainFrame.setMinimumSize(new Dimension(800, 450));
            Container mainFrameContentPane = mainFrame.getContentPane();
            mainFrameContentPane.setLayout(new BorderLayout());

            //======== mainMenu ========
            {
                mainMenu.setForeground(new Color(60, 63, 65));
                mainMenu.setOpaque(false);

                //======== projectBtn ========
                {
                    projectBtn.setText("Project");

                    //---- newProjectBtn ----
                    newProjectBtn.setText("New...");
                    newProjectBtn.setActionCommand("NEW_PROJECT");
                    projectBtn.add(newProjectBtn);

                    //---- loadProjectBtn ----
                    loadProjectBtn.setText("Load...");
                    loadProjectBtn.setActionCommand("LOAD_PROJECT");
                    projectBtn.add(loadProjectBtn);

                    //---- saveProjectBtn ----
                    saveProjectBtn.setText("Save");
                    saveProjectBtn.setActionCommand("SAVE");
                    projectBtn.add(saveProjectBtn);
                    projectBtn.addSeparator();

                    //---- settingsProjectBtn ----
                    settingsProjectBtn.setText("Preferences...");
                    settingsProjectBtn.setActionCommand("PREFERENCES");
                    projectBtn.add(settingsProjectBtn);
                    projectBtn.addSeparator();

                    //---- exitProjectBtn ----
                    exitProjectBtn.setText("Exit");
                    exitProjectBtn.setActionCommand("EXIT_PROJECT");
                    projectBtn.add(exitProjectBtn);
                }
                mainMenu.add(projectBtn);

                //======== toolsBtn ========
                {
                    toolsBtn.setText("Tools");

                    //---- newRoomToolsBtn ----
                    newRoomToolsBtn.setText("Create Room...");
                    newRoomToolsBtn.setActionCommand("CREATE_ROOM");
                    toolsBtn.add(newRoomToolsBtn);

                    //---- newSecGrToolsBtn ----
                    newSecGrToolsBtn.setText("Create Sector Group...");
                    newSecGrToolsBtn.setActionCommand("CREATE_SECTOR_GROUP");
                    toolsBtn.add(newSecGrToolsBtn);

                    //---- newEventTolsBtn ----
                    newEventTolsBtn.setText("Create Event...");
                    newEventTolsBtn.setActionCommand("CREATE_EVENT");
                    toolsBtn.add(newEventTolsBtn);
                }
                mainMenu.add(toolsBtn);

                //======== buildBtn ========
                {
                    buildBtn.setText("Build");

                    //---- runtimeBuildBtn ----
                    runtimeBuildBtn.setText("Build Runtime");
                    runtimeBuildBtn.setActionCommand("BUILD_RUNTIME");
                    buildBtn.add(runtimeBuildBtn);
                    buildBtn.addSeparator();

                    //---- settingsBuildBtn ----
                    settingsBuildBtn.setText("Build Settings...");
                    settingsBuildBtn.setActionCommand("BUILD_SETTINGS");
                    buildBtn.add(settingsBuildBtn);
                }
                mainMenu.add(buildBtn);

                //======== helpBtn ========
                {
                    helpBtn.setText("Help");

                    //---- tutorialsHelpBtn ----
                    tutorialsHelpBtn.setText("Tutorials");
                    tutorialsHelpBtn.setActionCommand("TUTORIALS");
                    helpBtn.add(tutorialsHelpBtn);
                    helpBtn.addSeparator();

                    //---- aboutHelpsBtn ----
                    aboutHelpsBtn.setText("About");
                    aboutHelpsBtn.setActionCommand("ABOUT");
                    helpBtn.add(aboutHelpsBtn);
                }
                mainMenu.add(helpBtn);
            }
            mainFrame.setJMenuBar(mainMenu);

            //======== hSplitter ========
            {

                //======== vSplitter ========
                {
                    vSplitter.setOrientation(JSplitPane.VERTICAL_SPLIT);

                    //======== roomsTabs ========
                    {

                        //======== roomsTab ========
                        {
                            roomsTab.setViewportView(roomsList);
                        }
                        roomsTabs.addTab("Rooms", roomsTab);

                        //======== functionsTab ========
                        {
                            functionsTab.setViewportView(functionsList);
                        }
                        roomsTabs.addTab("Functions", functionsTab);
                    }
                    vSplitter.setTopComponent(roomsTabs);

                    //======== toolsTab ========
                    {

                        //======== sgTab ========
                        {
                            sgTab.setViewportView(sgList);
                        }
                        toolsTab.addTab("Sector Groups", sgTab);

                        //======== eventsTab ========
                        {
                            eventsTab.setViewportView(eventsList);
                        }
                        toolsTab.addTab("Events", eventsTab);
                    }
                    vSplitter.setBottomComponent(toolsTab);
                }
                hSplitter.setLeftComponent(vSplitter);
                hSplitter.setRightComponent(workArea);
            }
            mainFrameContentPane.add(hSplitter, BorderLayout.CENTER);

            //---- infoLabel ----
            infoLabel.setText("  HPMS ToolBox");
            mainFrameContentPane.add(infoLabel, BorderLayout.SOUTH);
            mainFrame.pack();
            mainFrame.setLocationRelativeTo(mainFrame.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        eventsList.setCellRenderer(new ListEntryCellRenderer());
        roomsList.setCellRenderer(new ListEntryCellRenderer());
        sgList.setCellRenderer(new ListEntryCellRenderer());
        functionsList.setCellRenderer(new ListEntryCellRenderer());

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - N
    private JFrame mainFrame;
    private JMenuBar mainMenu;
    private JMenu projectBtn;
    private JMenuItem newProjectBtn;
    private JMenuItem loadProjectBtn;
    private JMenuItem saveProjectBtn;
    private JMenuItem settingsProjectBtn;
    private JMenuItem exitProjectBtn;
    private JMenu toolsBtn;
    private JMenuItem newRoomToolsBtn;
    private JMenuItem newSecGrToolsBtn;
    private JMenuItem newEventTolsBtn;
    private JMenu buildBtn;
    private JMenuItem runtimeBuildBtn;
    private JMenuItem settingsBuildBtn;
    private JMenu helpBtn;
    private JMenuItem tutorialsHelpBtn;
    private JMenuItem aboutHelpsBtn;
    private JSplitPane hSplitter;
    private JSplitPane vSplitter;
    private JTabbedPane roomsTabs;
    private JScrollPane roomsTab;
    private JList roomsList;
    private JScrollPane functionsTab;
    private JList functionsList;
    private JTabbedPane toolsTab;
    private JScrollPane sgTab;
    private JList sgList;
    private JScrollPane eventsTab;
    private JList eventsList;
    private JScrollPane workArea;
    private JLabel infoLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    public JList getRoomsList() {
        return roomsList;
    }

    public void setRoomsList(JList roomsList) {
        this.roomsList = roomsList;
    }

    public JList getSgList() {
        return sgList;
    }

    public void setSgList(JList sgList) {
        this.sgList = sgList;
    }

    public JList getEventsList() {
        return eventsList;
    }

    public void setEventsList(JList eventsList) {
        this.eventsList = eventsList;
    }

    /**
     * Getter for property 'functionsList'.
     *
     * @return Value for property 'functionsList'.
     */
    public JList getFunctionsList() {
        return functionsList;
    }

    /**
     * Setter for property 'functionsList'.
     *
     * @param functionsList Value to set for property 'functionsList'.
     */
    public void setFunctionsList(JList functionsList) {
        this.functionsList = functionsList;
    }

    public JScrollPane getWorkArea() {
        return workArea;
    }

    public void setWorkArea(JScrollPane workArea) {
        this.workArea = workArea;
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    /**
     * Getter for property 'newProjectBtn'.
     *
     * @return Value for property 'newProjectBtn'.
     */
    public JMenuItem getNewProjectBtn() {
        return newProjectBtn;
    }

    /**
     * Getter for property 'loadProjectBtn'.
     *
     * @return Value for property 'loadProjectBtn'.
     */
    public JMenuItem getLoadProjectBtn() {
        return loadProjectBtn;
    }

    /**
     * Getter for property 'settingsProjectBtn'.
     *
     * @return Value for property 'settingsProjectBtn'.
     */
    public JMenuItem getSettingsProjectBtn() {
        return settingsProjectBtn;
    }

    /**
     * Getter for property 'exitProjectBtn'.
     *
     * @return Value for property 'exitProjectBtn'.
     */
    public JMenuItem getExitProjectBtn() {
        return exitProjectBtn;
    }

    /**
     * Getter for property 'newRoomToolsBtn'.
     *
     * @return Value for property 'newRoomToolsBtn'.
     */
    public JMenuItem getNewRoomToolsBtn() {
        return newRoomToolsBtn;
    }

    /**
     * Getter for property 'newSecGrToolsBtn'.
     *
     * @return Value for property 'newSecGrToolsBtn'.
     */
    public JMenuItem getNewSecGrToolsBtn() {
        return newSecGrToolsBtn;
    }

    /**
     * Getter for property 'newEventTolsBtn'.
     *
     * @return Value for property 'newEventTolsBtn'.
     */
    public JMenuItem getNewEventTolsBtn() {
        return newEventTolsBtn;
    }

    /**
     * Getter for property 'runtimeBuildBtn'.
     *
     * @return Value for property 'runtimeBuildBtn'.
     */
    public JMenuItem getRuntimeBuildBtn() {
        return runtimeBuildBtn;
    }

    /**
     * Getter for property 'settingsBuildBtn'.
     *
     * @return Value for property 'settingsBuildBtn'.
     */
    public JMenuItem getSettingsBuildBtn() {
        return settingsBuildBtn;
    }

    /**
     * Getter for property 'tutorialsHelpBtn'.
     *
     * @return Value for property 'tutorialsHelpBtn'.
     */
    public JMenuItem getTutorialsHelpBtn() {
        return tutorialsHelpBtn;
    }

    /**
     * Getter for property 'aboutHelpsBtn'.
     *
     * @return Value for property 'aboutHelpsBtn'.
     */
    public JMenuItem getAboutHelpsBtn() {
        return aboutHelpsBtn;
    }

    public JMenuItem getSaveProjectBtn() {
        return saveProjectBtn;
    }

    public JLabel getInfoLabel() {
        return infoLabel;
    }
}
