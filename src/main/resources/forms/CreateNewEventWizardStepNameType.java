/*
 * Created by JFormDesigner on Thu Oct 24 14:37:26 CEST 2019
 */

package forms;

import java.awt.*;
import javax.swing.*;

/**
 * @author N
 */
public class CreateNewEventWizardStepNameType extends JPanel {
    public CreateNewEventWizardStepNameType() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - N
        subContainer = new JPanel();
        newProjLabel = new JLabel();
        newProjNameTxt = new JTextField();
        subContainer5 = new JPanel();
        checkBox1 = new JCheckBox();
        subContainer6 = new JPanel();
        newProjLabel4 = new JLabel();
        comboBox2 = new JComboBox();
        subContainer2 = new JPanel();
        okBtn = new JButton();
        cancBtn = new JButton();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax.
        swing. border. EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border
        . TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog"
        ,java .awt .Font .BOLD ,12 ), java. awt. Color. red) , getBorder
        ( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java
        .beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException
        ( ); }} );
        setLayout(new GridLayout(4, 0));

        //======== subContainer ========
        {
            subContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));

            //---- newProjLabel ----
            newProjLabel.setText("Event Name:");
            subContainer.add(newProjLabel);

            //---- newProjNameTxt ----
            newProjNameTxt.setPreferredSize(new Dimension(400, 30));
            subContainer.add(newProjNameTxt);
        }
        add(subContainer);

        //======== subContainer5 ========
        {
            subContainer5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));

            //---- checkBox1 ----
            checkBox1.setText(" Room instance event (common static function if unchecked)");
            subContainer5.add(checkBox1);
        }
        add(subContainer5);

        //======== subContainer6 ========
        {
            subContainer6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));

            //---- newProjLabel4 ----
            newProjLabel4.setText("Event Lifecycle invocation:");
            subContainer6.add(newProjLabel4);

            //---- comboBox2 ----
            comboBox2.setPreferredSize(new Dimension(300, 30));
            subContainer6.add(comboBox2);
        }
        add(subContainer6);

        //======== subContainer2 ========
        {
            subContainer2.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
            ((FlowLayout)subContainer2.getLayout()).setAlignOnBaseline(true);

            //---- okBtn ----
            okBtn.setText("Next");
            subContainer2.add(okBtn);

            //---- cancBtn ----
            cancBtn.setText("Cancel");
            subContainer2.add(cancBtn);
        }
        add(subContainer2);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - N
    private JPanel subContainer;
    private JLabel newProjLabel;
    private JTextField newProjNameTxt;
    private JPanel subContainer5;
    private JCheckBox checkBox1;
    private JPanel subContainer6;
    private JLabel newProjLabel4;
    private JComboBox comboBox2;
    private JPanel subContainer2;
    private JButton okBtn;
    private JButton cancBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
