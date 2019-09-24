package org.hpms.gui;

import org.hpms.gui.data.ProjectModel;
import org.hpms.gui.logic.ProjectManager;
import org.hpms.gui.logic.TemplateGenerator;
import org.hpms.gui.views.BaseGui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static final String VERSION = "0.1a";
    private static final String EMAIL = "toolbox.hpms@gmail.com";

    public static void main(String[] args) {
       // javax.swing.SwingUtilities.invokeLater(Main::createAndShowGUI);

        TemplateGenerator gen = new TemplateGenerator();
        System.out.println(gen.getConfigScriptTemplate().getCode());
    }

    private static void createAndShowGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            JOptionPane.showMessageDialog(null, createReadOnlyJTextField(e), "Error", JOptionPane.PLAIN_MESSAGE);

        }

        try {
            registerSerializables();
            JFrame frame = new BaseGui().getMainFrame();
            frame.pack();
            frame.setVisible(true);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, createReadOnlyJTextField(e), "Error", JOptionPane.PLAIN_MESSAGE);
            System.exit(-1);
        }


    }

    private static Component createReadOnlyJTextField(Exception e) {
        StringBuilder sb = new StringBuilder();
        sb.append("HPMS ToolBox version ")
                .append(VERSION)
                .append("\n\nError Message: ")
                .append(e.toString())
                .append("\n\nError Stack Trace:\n");
        for (StackTraceElement st : e.getStackTrace()) {
            sb.append(st.toString())
                    .append("\n");
        }
        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setColumns(80);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        JScrollPane inner = new JScrollPane(textArea);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("An error occurred while using HPMS ToolBox. Please copy and send following error details to: " + EMAIL);
        panel.add(label);
        panel.add(new JLabel(" "));
        panel.add(inner);
        return panel;

    }

    private static void registerSerializables() {
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.class);
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.ProjectPreferences.class);
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.BuildSettings.class);
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.UserSettings.class);
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.RoomModel.class);
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.RoomModel.Event.class);
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.RoomModel.Event.Action.class);
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.RoomModel.Event.Condition.class);
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.RoomModel.Event.TriggerType.class);
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.RoomModel.SectorGroup.class);
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.RoomModel.SectorGroup.Sector.class);
        ProjectManager.KRYO_SERIALIZER.register(ProjectModel.RoomModel.SectorGroup.Sector.PerimetralSide.class);
        ProjectManager.KRYO_SERIALIZER.register(ArrayList.class);
        ProjectManager.KRYO_SERIALIZER.register(HashMap.class);
    }
}
