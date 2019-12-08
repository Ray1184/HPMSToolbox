/*
 * Created by JFormDesigner on Thu Oct 24 11:12:06 CEST 2019
 */

package forms;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * @author N
 */
public class LoadProject extends JFrame {
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - N
    private JPanel container;
    private JLabel chooseProjectLbl;
    private JPanel subContainer;
    private JTextField projectTxt;
    private JButton projectLoadBtn;
    private JPanel subContainer2;
    private JButton confirmProjectBtn;
    public LoadProject() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - N
        container = new JPanel();
        chooseProjectLbl = new JLabel();
        subContainer = new JPanel();
        projectTxt = new JTextField();
        projectLoadBtn = new JButton();
        subContainer2 = new JPanel();
        confirmProjectBtn = new JButton();

        //======== this ========
        setTitle("Select Your Project");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(new CardLayout(10, 10));

        //======== container ========
        {
            container.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(
                    0, 0, 0, 0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder
                    .BOTTOM, new java.awt.Font("D\u0069alog", java.awt.Font.BOLD, 12), java.awt.Color.
                    red), container.getBorder()));
            container.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                @Override
                public void propertyChange(java.
                                                   beans.PropertyChangeEvent e) {
                    if ("\u0062order".equals(e.getPropertyName())) throw new RuntimeException();
                }
            });
            container.setLayout(new GridLayout(3, 0));

            //---- chooseProjectLbl ----
            chooseProjectLbl.setText("Select your project.");
            chooseProjectLbl.setBorder(new EmptyBorder(10, 10, 20, 20));
            chooseProjectLbl.setHorizontalAlignment(SwingConstants.CENTER);
            container.add(chooseProjectLbl);

            //======== subContainer ========
            {
                subContainer.setLayout(new FlowLayout());

                //---- projectTxt ----
                projectTxt.setMinimumSize(new Dimension(300, 46));
                projectTxt.setPreferredSize(new Dimension(400, 30));
                subContainer.add(projectTxt);

                //---- projectLoadBtn ----
                projectLoadBtn.setText("...");
                subContainer.add(projectLoadBtn);
            }
            container.add(subContainer);

            //======== subContainer2 ========
            {
                subContainer2.setLayout(new FlowLayout());

                //---- confirmProjectBtn ----
                confirmProjectBtn.setText("Load Project");
                confirmProjectBtn.setPreferredSize(new Dimension(200, 46));
                subContainer2.add(confirmProjectBtn);
            }
            container.add(subContainer2);
        }
        contentPane.add(container, "card1");
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    /**
     * Getter for property 'projectTxt'.
     *
     * @return Value for property 'projectTxt'.
     */
    public JTextField getProjectTxt() {
        return projectTxt;
    }

    /**
     * Getter for property 'projectLoadBtn'.
     *
     * @return Value for property 'projectLoadBtn'.
     */
    public JButton getProjectLoadBtn() {
        return projectLoadBtn;
    }

    /**
     * Getter for property 'confirmProjectBtn'.
     *
     * @return Value for property 'confirmProjectBtn'.
     */
    public JButton getConfirmProjectBtn() {
        return confirmProjectBtn;
    }
}
