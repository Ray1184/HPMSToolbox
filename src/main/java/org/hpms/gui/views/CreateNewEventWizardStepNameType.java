/*
 * Created by JFormDesigner on Thu Oct 24 14:37:26 CEST 2019
 */

package org.hpms.gui.views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * @author N
 */
public class CreateNewEventWizardStepNameType extends JPanel {
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - N
    private JPanel subContainer;
    private JLabel newEvtLabel;
    private JTextField newEvtTxt;
    private JPanel subContainer2;
    private JCheckBox eventTypeChkbx;
    private JPanel subContainer3;
    private JLabel eventLcLbl;
    private JComboBox eventLclCombo;
    private JPanel subContainer4;
    private JButton cancBtn;
    private JButton nextBtn;
    public CreateNewEventWizardStepNameType() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - N
        subContainer = new JPanel();
        newEvtLabel = new JLabel();
        newEvtTxt = new JTextField();
        subContainer2 = new JPanel();
        eventTypeChkbx = new JCheckBox();
        subContainer3 = new JPanel();
        eventLcLbl = new JLabel();
        eventLclCombo = new JComboBox();
        subContainer4 = new JPanel();
        cancBtn = new JButton();
        nextBtn = new JButton();

        //======== this ========
        setBorder(new EmptyBorder(0, 0, 0, 0));
        setLayout(new GridLayout(4, 0));

        //======== subContainer ========
        {
            subContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));

            //---- newEvtLabel ----
            newEvtLabel.setText("Event Name:");
            subContainer.add(newEvtLabel);

            //---- newEvtTxt ----
            newEvtTxt.setPreferredSize(new Dimension(400, 30));
            subContainer.add(newEvtTxt);
        }
        add(subContainer);

        //======== subContainer2 ========
        {
            subContainer2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));

            //---- eventTypeChkbx ----
            eventTypeChkbx.setText(" Room instance event (common static function if unchecked)");
            subContainer2.add(eventTypeChkbx);
        }
        add(subContainer2);

        //======== subContainer3 ========
        {
            subContainer3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));

            //---- eventLcLbl ----
            eventLcLbl.setText("Event Lifecycle Invocation:");
            subContainer3.add(eventLcLbl);

            //---- eventLclCombo ----
            eventLclCombo.setPreferredSize(new Dimension(300, 30));
            subContainer3.add(eventLclCombo);
        }
        add(subContainer3);

        //======== subContainer4 ========
        {
            subContainer4.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
            ((FlowLayout) subContainer4.getLayout()).setAlignOnBaseline(true);

            //---- cancBtn ----
            cancBtn.setText("Cancel");
            subContainer4.add(cancBtn);

            //---- nextBtn ----
            nextBtn.setText("Next");
            subContainer4.add(nextBtn);
        }
        add(subContainer4);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    /**
     * Getter for property 'newEvtTxt'.
     *
     * @return Value for property 'newEvtTxt'.
     */
    public JTextField getNewEvtTxt() {
        return newEvtTxt;
    }

    /**
     * Getter for property 'eventTypeChkbx'.
     *
     * @return Value for property 'eventTypeChkbx'.
     */
    public JCheckBox getEventTypeChkbx() {
        return eventTypeChkbx;
    }

    /**
     * Getter for property 'eventLclCombo'.
     *
     * @return Value for property 'eventLclCombo'.
     */
    public JComboBox getEventLclCombo() {
        return eventLclCombo;
    }

    /**
     * Getter for property 'cancBtn'.
     *
     * @return Value for property 'cancBtn'.
     */
    public JButton getCancBtn() {
        return cancBtn;
    }

    /**
     * Getter for property 'nextBtn'.
     *
     * @return Value for property 'nextBtn'.
     */
    public JButton getNextBtn() {
        return nextBtn;
    }
}
