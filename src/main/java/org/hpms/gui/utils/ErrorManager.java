package org.hpms.gui.utils;

import javax.swing.*;

import static org.hpms.gui.AppInfo.EMAIL;
import static org.hpms.gui.AppInfo.VERSION;

public class ErrorManager {

    public static Object createReadOnlyJTextField(Exception e) {
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
}
