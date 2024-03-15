package LifeLab;

import CALab.*;
import mvc.*;
import javax.swing.*;

public class SocietyPanel extends GridPanel {
    public SocietyPanel(AppFactory factory) {
        super(factory);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppFactory factory = new SocietyFactory();
            AppPanel panel = new SocietyPanel(factory);
            //panel.display();
        });
    }

}