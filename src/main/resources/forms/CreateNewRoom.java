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
        roomNameLabel = new JLabel();
        roomNameTxt = new JTextField();
        subContainer5 = new JPanel();
        roomTypeLabel = new JLabel();
        roomTypeCombo = new JComboBox();
        subContainer3 = new JPanel();
        sgMapLbl = new JLabel();
        sgMapTxt = new JTextField();
        sgMapLoadBtn = new JButton();
        subContainer4 = new JPanel();
        generateChk = new JCheckBox();
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
            container.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder
            ( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border. TitledBorder. CENTER, javax. swing. border
            . TitledBorder. BOTTOM, new java .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ), java. awt
            . Color. red) ,container. getBorder( )) ); container. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void
            propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .getPropertyName () )) throw new RuntimeException( )
            ; }} );
            container.setLayout(new GridLayout(5, 0, 10, 0));

            //======== subContainer ========
            {
                subContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));

                //---- roomNameLabel ----
                roomNameLabel.setText("Room Name:");
                roomNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
                subContainer.add(roomNameLabel);

                //---- roomNameTxt ----
                roomNameTxt.setPreferredSize(new Dimension(400, 30));
                subContainer.add(roomNameTxt);
            }
            container.add(subContainer);

            //======== subContainer5 ========
            {
                subContainer5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));

                //---- roomTypeLabel ----
                roomTypeLabel.setText("Room Type:");
                subContainer5.add(roomTypeLabel);

                //---- roomTypeCombo ----
                roomTypeCombo.setPreferredSize(new Dimension(410, 30));
                subContainer5.add(roomTypeCombo);
            }
            container.add(subContainer5);

            //======== subContainer3 ========
            {
                subContainer3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));

                //---- sgMapLbl ----
                sgMapLbl.setText("Sector Map:");
                subContainer3.add(sgMapLbl);

                //---- sgMapTxt ----
                sgMapTxt.setMinimumSize(new Dimension(200, 46));
                sgMapTxt.setPreferredSize(new Dimension(300, 30));
                subContainer3.add(sgMapTxt);

                //---- sgMapLoadBtn ----
                sgMapLoadBtn.setText("...");
                sgMapLoadBtn.setPreferredSize(new Dimension(105, 46));
                subContainer3.add(sgMapLoadBtn);
            }
            container.add(subContainer3);

            //======== subContainer4 ========
            {
                subContainer4.setLayout(new FlowLayout());

                //---- generateChk ----
                generateChk.setText("<html>Generate sector groups? (available only for .dae format)<br/>(not supported yet)</html>");
                subContainer4.add(generateChk);
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
    private JLabel roomNameLabel;
    private JTextField roomNameTxt;
    private JPanel subContainer5;
    private JLabel roomTypeLabel;
    private JComboBox roomTypeCombo;
    private JPanel subContainer3;
    private JLabel sgMapLbl;
    private JTextField sgMapTxt;
    private JButton sgMapLoadBtn;
    private JPanel subContainer4;
    private JCheckBox generateChk;
    private JPanel subContainer2;
    private JButton okBtn;
    private JButton cancBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    /**
     * Getter for property 'roomNameTxt'.
     *
     * @return Value for property 'roomNameTxt'.
     */
    public JTextField getRoomNameTxt() {
        return roomNameTxt;
    }

    /**
     * Getter for property 'roomTypeCombo'.
     *
     * @return Value for property 'roomTypeCombo'.
     */
    public JComboBox getRoomTypeCombo() {
        return roomTypeCombo;
    }

    /**
     * Getter for property 'sgMapTxt'.
     *
     * @return Value for property 'sgMapTxt'.
     */
    public JTextField getSgMapTxt() {
        return sgMapTxt;
    }

    /**
     * Getter for property 'sgMapLoadBtn'.
     *
     * @return Value for property 'sgMapLoadBtn'.
     */
    public JButton getSgMapLoadBtn() {
        return sgMapLoadBtn;
    }

    /**
     * Getter for property 'generateChk'.
     *
     * @return Value for property 'generateChk'.
     */
    public JCheckBox getGenerateChk() {
        return generateChk;
    }

    /**
     * Getter for property 'okBtn'.
     *
     * @return Value for property 'okBtn'.
     */
    public JButton getOkBtn() {
        return okBtn;
    }

    /**
     * Getter for property 'cancBtn'.
     *
     * @return Value for property 'cancBtn'.
     */
    public JButton getCancBtn() {
        return cancBtn;
    }
}
