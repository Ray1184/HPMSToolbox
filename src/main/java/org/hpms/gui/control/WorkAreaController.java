package org.hpms.gui.control;

import org.hpms.gui.control.w3d.W3DArea;
import org.hpms.gui.views.BaseGui;

import javax.swing.*;

import static org.hpms.gui.utils.ErrorManager.createReadOnlyJTextField;

public class WorkAreaController implements Controller {
    private final W3DArea w3DArea = new W3DArea(BaseGui.getInstance().getWorkArea());


    @Override
    public void init() {
        try {
            new SwingWorker<Void, Void>() {

                private Exception exception;


                @Override
                protected Void doInBackground() {
                    try {
                        w3DArea.loop();
                    } catch (Exception e) {
                        exception = e;
                    }
                    return null;
                }

                @Override
                protected void done() {
                    super.done();
                    if (exception != null) {
                        JOptionPane.showMessageDialog(null, createReadOnlyJTextField(exception), "Error", JOptionPane.PLAIN_MESSAGE);
                        System.exit(-1);
                    }
                }
            }.execute();


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, createReadOnlyJTextField(e), "Error", JOptionPane.PLAIN_MESSAGE);
            System.exit(-1);

        }
    }

    @Override
    public void update() {


    }


}
