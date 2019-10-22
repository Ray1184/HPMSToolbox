package org.hpms.gui.control;

public enum Controllers {

    MENU_CONTROLLER(new MenuController());


    private Controller controller;

    Controllers(Controller controller) {
        this.controller = controller;
    }

    public void init() {
        controller.init();
    }

    public void update() {
        controller.update();
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


}
