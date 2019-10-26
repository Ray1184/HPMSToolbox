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
        // Generated using JFormDesigner Evaluation license - Ray
        subContainer = new JPanel();
        newProjLabel = new JLabel();
        newProjNameTxt = new JTextField();
        subContainer2 = new JPanel();
        checkBox1 = new JCheckBox();
        subContainer3 = new JPanel();
        newProjLabel4 = new JLabel();
        comboBox2 = new JComboBox();
        subContainer4 = new JPanel();
        okBtn = new JButton();
        cancBtn = new JButton();

        //======== this ========
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing.
        border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn" , javax. swing .border . TitledBorder. CENTER
        ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .awt . Font
        . BOLD ,12 ) ,java . awt. Color .red ) , getBorder () ) );  addPropertyChangeListener(
        new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062ord\u0065r"
        .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );
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

        //======== subContainer2 ========
        {
            subContainer2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));

            //---- checkBox1 ----
            checkBox1.setText(" Room instance event (common static function if unchecked)");
            subContainer2.add(checkBox1);
        }
        add(subContainer2);

        //======== subContainer3 ========
        {
            subContainer3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));

            //---- newProjLabel4 ----
            newProjLabel4.setText("Event Lifecycle invocation:");
            subContainer3.add(newProjLabel4);

            //---- comboBox2 ----
            comboBox2.setPreferredSize(new Dimension(300, 30));
            subContainer3.add(comboBox2);
        }
        add(subContainer3);

        //======== subContainer4 ========
        {
            subContainer4.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
            ((FlowLayout)subContainer4.getLayout()).setAlignOnBaseline(true);

            //---- okBtn ----
            okBtn.setText("Cancel");
            subContainer4.add(okBtn);

            //---- cancBtn ----
            cancBtn.setText("Next");
            subContainer4.add(cancBtn);
        }
        add(subContainer4);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Ray
    private JPanel subContainer;
    private JLabel newProjLabel;
    private JTextField newProjNameTxt;
    private JPanel subContainer2;
    private JCheckBox checkBox1;
    private JPanel subContainer3;
    private JLabel newProjLabel4;
    private JComboBox comboBox2;
    private JPanel subContainer4;
    private JButton okBtn;
    private JButton cancBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
