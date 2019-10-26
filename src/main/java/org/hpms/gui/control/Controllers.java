package org.hpms.gui.control;

import org.hpms.gui.control.wizard.EventWizardDelegate;

public enum Controllers {

    MENU_CONTROLLER(new MenuController()),
    WORK_AREA_CONTROLLER(new WorkAreaController());


    private final Controller controller;

    Controllers(Controller controller) {
        this.controller = controller;
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
