/*
 * Edit Log:
 * Alvin 03/16: Copied Code Over
 * Tiger 03/17: Completed Implementation
 */

package LifeLab;

import CALab.*;
import mvc.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.Set;

public class SocietyPanel extends GridPanel {
    public SocietyPanel(AppFactory factory) {
        super(factory);

        JPanel p = new JPanel();
        p.setBackground(Color.PINK);
        p.setLayout(new GridLayout(2,2));
        createLabelAndTextField(p,"rebirth: ", Society.rebirth);
        createLabelAndTextField(p,"death: ", Society.death);

        getControl().add(p);
        getControl().revalidate();
    }

    public void createLabelAndTextField(JPanel panel, String labelText, Set<Integer> set){
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(40,20));
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(label);

        JTextField textField1 = new JTextField(12);
        updateTextField(set, textField1);
        textField1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateSet();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateSet();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateSet();
            }

            private void updateSet() {
                String text = textField1.getText();
                String[] parts = text.split(",");
                set.clear();
                for (String part : parts) {
                    try {
                        int num = Integer.parseInt(part.trim());
                        set.add(num);
                    } catch (NumberFormatException ex) {}
                }
            }
        });
        panel.add(textField1);
    }

    private static void updateTextField(Set<Integer> set, JTextField textField) {
        StringBuilder sb = new StringBuilder();
        for (Integer num : set) {
            sb.append(num).append(",");
        }
        // Remove trailing comma
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        textField.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppFactory factory = new SocietyFactory();
            AppPanel panel = new SocietyPanel(factory);
            //panel.display();
        });
    }

}