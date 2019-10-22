package org.hpms.gui.control;

import org.hpms.gui.views.BaseGui;

public class MainController {

    private final BaseGui gui;

    private MenuController menuController;

    public MainController() {
        gui = BaseGui.getInstance();
        initControllers();
    }

    private void initControllers() {
        menuController = new MenuController();
    }

    public void update() {
    }

}
