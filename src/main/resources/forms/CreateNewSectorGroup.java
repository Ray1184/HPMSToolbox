/*
 * Created by JFormDesigner on Thu Oct 24 12:23:54 CEST 2019
 */

package forms;

import java.awt.*;
import javax.swing.*;

/**
 * @author N
 */
public class CreateNewSectorGroup extends JDialog {
    public CreateNewSectorGroup(Window owner) {
        super(owner);
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - N
        container = new JPanel();
        subContainer = new JPanel();
        newSgLabel = new JLabel();
        newSgNameTxt = new JTextField();
        subContainer2 = new JPanel();
        okBtn = new JButton();
        cancBtn = new JButton();

        //======== this ========
        setTitle("Create New Sector Group");
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(new CardLayout(10, 10));

        //======== container ========
        {
            container.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border
            .EmptyBorder ( 0, 0 ,0 , 0) ,  "JFor\u006dDesi\u0067ner \u0045valu\u0061tion" , javax. swing .border . TitledBorder. CENTER ,javax
            . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .awt . Font. BOLD ,
            12 ) ,java . awt. Color .red ) ,container. getBorder () ) ); container. addPropertyChangeListener( new java. beans
            .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "bord\u0065r" .equals ( e.
            getPropertyName () ) )throw new RuntimeException( ) ;} } );
            container.setLayout(new GridLayout(2, 0, 10, 0));

            //======== subContainer ========
            {
                subContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));

                //---- newSgLabel ----
                newSgLabel.setText("Sector Group:");
                subContainer.add(newSgLabel);

                //---- newSgNameTxt ----
                newSgNameTxt.setPreferredSize(new Dimension(400, 30));
                subContainer.add(newSgNameTxt);
            }
            container.add(subContainer);

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
    private JLabel newSgLabel;
    private JTextField newSgNameTxt;
    private JPanel subContainer2;
    private JButton okBtn;
    private JButton cancBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    /**
     * Getter for property 'newSgNameTxt'.
     *
     * @return Value for property 'newSgNameTxt'.
     */
    public JTextField getNewSgNameTxt() {
        return newSgNameTxt;
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
