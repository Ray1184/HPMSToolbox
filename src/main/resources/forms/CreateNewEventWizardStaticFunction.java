import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import java.awt.*;
import javax.swing.*;
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
        // Generated using JFormDesigner Evaluation license - Ray
        newProjLabel = new JLabel();
        subContainer = new JPanel();
        subContainer2 = new JPanel();
        okBtn = new JButton();
        cancBtn = new JButton();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (
        new javax. swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion"
        , javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM
        , new java .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 )
        , java. awt. Color. red) , getBorder( )) );  addPropertyChangeListener (
        new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
        ) {if ("\u0062order" .equals (e .getPropertyName () )) throw new RuntimeException( )
        ; }} );
        setLayout(new GridLayout(3, 0));

        //---- newProjLabel ----
        newProjLabel.setText("Write function declaration and code:");
        newProjLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(newProjLabel);

        //======== subContainer ========
        {
            subContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));
        }
        add(subContainer);

        //======== subContainer2 ========
        {
            subContainer2.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
            ((FlowLayout)subContainer2.getLayout()).setAlignOnBaseline(true);

            //---- okBtn ----
            okBtn.setText("Back");
            subContainer2.add(okBtn);

            //---- cancBtn ----
            cancBtn.setText("Confirm");
            subContainer2.add(cancBtn);
        }
        add(subContainer2);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        codeArea = new RSyntaxTextArea();
        codeArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_LUA);
        codeArea.setCodeFoldingEnabled(true);
        codeArea.setText("function my_function(args)");
        RTextScrollPane sp = new RTextScrollPane(codeArea);
        subContainer.add(sp);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Ray
    private JLabel newProjLabel;
    private JPanel subContainer;
    private JPanel subContainer2;
    private JButton okBtn;
    private JButton cancBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private RSyntaxTextArea codeArea;
}
