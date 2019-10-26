/*
 * Created by JFormDesigner on Thu Oct 24 12:25:43 CEST 2019
 */

package forms;

import java.awt.*;
import javax.swing.*;

/**
 * @author N
 */
public class CreateNewRoom extends JDialog {
    public CreateNewRoom(Window owner) {
        super(owner);
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - N
        container = new JPanel();
        subContainer = new JPanel();
        newProjLabel = new JLabel();
        newProjNameTxt = new JTextField();
        subContainer5 = new JPanel();
        newProjLabel3 = new JLabel();
        comboBox1 = new JComboBox();
        subContainer3 = new JPanel();
        newProjLabel2 = new JLabel();
        projectTxt = new JTextField();
        projectLoadBtn = new JButton();
        subContainer4 = new JPanel();
        checkBox1 = new JCheckBox();
        subContainer2 = new JPanel();
        okBtn = new JButton();
        cancBtn = new JButton();

        //======== this ========
        setTitle("Create New Room");
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(new CardLayout(10, 10));

        //======== container ========
        {
            container.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing
            . border. EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border. TitledBorder
            . CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .
            awt .Font .BOLD ,12 ), java. awt. Color. red) ,container. getBorder( )) )
            ; container. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
            ) {if ("bord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} )
            ;
            container.setLayout(new GridLayout(5, 0, 10, 0));

            //======== subContainer ========
            {
                subContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));

                //---- newProjLabel ----
                newProjLabel.setText("Room Name:");
                newProjLabel.setHorizontalAlignment(SwingConstants.CENTER);
                subContainer.add(newProjLabel);

                //---- newProjNameTxt ----
                newProjNameTxt.setPreferredSize(new Dimension(400, 30));
                subContainer.add(newProjNameTxt);
            }
            container.add(subContainer);

            //======== subContainer5 ========
            {
                subContainer5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));

                //---- newProjLabel3 ----
                newProjLabel3.setText("Room Type:");
                subContainer5.add(newProjLabel3);

                //---- comboBox1 ----
                comboBox1.setPreferredSize(new Dimension(410, 30));
                subContainer5.add(comboBox1);
            }
            container.add(subContainer5);

            //======== subContainer3 ========
            {
                subContainer3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));

                //---- newProjLabel2 ----
                newProjLabel2.setText("Sector Map:");
                subContainer3.add(newProjLabel2);

                //---- projectTxt ----
                projectTxt.setMinimumSize(new Dimension(200, 46));
                projectTxt.setPreferredSize(new Dimension(300, 30));
                subContainer3.add(projectTxt);

                //---- projectLoadBtn ----
                projectLoadBtn.setText("...");
                projectLoadBtn.setPreferredSize(new Dimension(105, 46));
                subContainer3.add(projectLoadBtn);
            }
            container.add(subContainer3);

            //======== subContainer4 ========
            {
                subContainer4.setLayout(new FlowLayout());

                //---- checkBox1 ----
                checkBox1.setText("Generate sector groups? (available only for .dae format)");
                subContainer4.add(checkBox1);
            }
            container.add(subContainer4);

            //======== subContainer2 ========
            {
                subContainer2.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
                ((FlowLayout)subContainer2.getLayout()).setAlignOnBaseline(true);

                //---- okBtn ----
                okBtn.setText("OK");
                subContainer2.add(okBtn);

                //---- cancBtn ----
                cancBtn.setText("Cancel");
                subContainer2.add(cancBtn);
            }
            container.add(subContainer2);
        }
        contentPane.add(container, "card5");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - N
    private JPanel container;
    private JPanel subContainer;
    private JLabel newProjLabel;
    private JTextField newProjNameTxt;
    private JPanel subContainer5;
    private JLabel newProjLabel3;
    private JComboBox comboBox1;
    private JPanel subContainer3;
    private JLabel newProjLabel2;
    private JTextField projectTxt;
    private JButton projectLoadBtn;
    private JPanel subContainer4;
    private JCheckBox checkBox1;
    private JPanel subContainer2;
    private JButton okBtn;
    private JButton cancBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
