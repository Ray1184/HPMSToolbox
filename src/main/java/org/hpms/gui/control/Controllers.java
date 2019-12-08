package org.hpms.gui.control;

public enum Controllers {

    MENU_CONTROLLER(new MenuController()),
    WORK_AREA_CONTROLLER(new WorkAreaController()),
    TOOLS_CONTROLLER(new ToolsController());


    private final Controller controller;

    Controllers(Controller controller) {
        this.controller = controller;
    }

    public static void initAll() {
        for (Controllers c : Controllers.values()) {
            c.init();
        }
    }

    public static void updateAll() {
        for (Controllers c : Controllers.values()) {
            c.update();
        }
    }

    public void init() {
        controller.init();
    }

    public void update() {
        controller.update();
    }

    /**
     * Getter for property 'controller'.
     *
     * @return Value for property 'controller'.
     */
    public Controller getController() {
        return controller;
    }


}
