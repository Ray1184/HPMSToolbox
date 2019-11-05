package org.hpms.gui.views;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
/*
 * Created by JFormDesigner on Sat Oct 26 17:44:38 CEST 2019
 */


/**
 * @author Ray
 */
public class CreateNewEventWizardStaticFunction extends JPanel {
    public CreateNewEventWizardStaticFunction() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - N
        subContainer3 = new JPanel();
        argsLabel = new JLabel();
        argsTxt = new JTextField();
        subContainer = new JPanel();
        subContainer2 = new JPanel();
        cancBtn = new JButton();
        okBtn = new JButton();

        //======== this ========
        setBorder(new EmptyBorder(0, 0, 0 ,0));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //======== subContainer3 ========
        {
            subContainer3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));

            //---- argsLabel ----
            argsLabel.setText("Args (comma separated): ");
            subContainer3.add(argsLabel);

            //---- argsTxt ----
            argsTxt.setPreferredSize(new Dimension(400, 30));
            subContainer3.add(argsTxt);
        }
        add(subContainer3);

        //======== subContainer ========
        {
            subContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));
        }
        add(subContainer);

        //======== subContainer2 ========
        {
            subContainer2.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
            ((FlowLayout)subContainer2.getLayout()).setAlignOnBaseline(true);

            //---- cancBtn ----
            cancBtn.setText("Back");
            subContainer2.add(cancBtn);

            //---- okBtn ----
            okBtn.setText("Confirm");
            subContainer2.add(okBtn);
        }
        add(subContainer2);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        codeArea = new RSyntaxTextArea();
        codeArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_LUA);
        codeArea.setCodeFoldingEnabled(true);
        RTextScrollPane sp = new RTextScrollPane(codeArea);

        subContainer.add(sp);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - N
    private JPanel subContainer3;
    private JLabel argsLabel;
    private JTextField argsTxt;
    private JPanel subContainer;
    private JPanel subContainer2;
    private JButton cancBtn;
    private JButton okBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private RSyntaxTextArea codeArea;

    /**
     * Getter for property 'codeArea'.
     *
     * @return Value for property 'codeArea'.
     */
    public RSyntaxTextArea getCodeArea() {
        return codeArea;
    }

    public JButton getOkBtn() {
        return okBtn;
    }

    public JButton getCancBtn() {
        return cancBtn;
    }

    /**
     * Getter for property 'argsTxt'.
     *
     * @return Value for property 'argsTxt'.
     */
    public JTextField getArgsTxt() {
        return argsTxt;
    }
}
