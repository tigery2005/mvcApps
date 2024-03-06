package CALab;

import mvc.AppFactory;
import mvc.AppPanel;

import javax.swing.*;

public class GridPanel extends AppPanel{
    private JButton RUN1;
    private JButton RUN50;
    private JButton POPULATE;
    private JButton CLEAR;

    public GridPanel(AppFactory factory) {
        super(factory);
        RUN1 = new JButton("RUN1");
        RUN1.addActionListener(this);
        getControl().add(RUN1);
        RUN50 = new JButton("RUN50");
        RUN50.addActionListener(this);
        getControl().add(RUN50);
        POPULATE = new JButton("POPULATE");
        POPULATE.addActionListener(this);
        getControl().add(POPULATE);
        CLEAR = new JButton("CLEAR");
        CLEAR.addActionListener(this);
        getControl().add(CLEAR);
        getControl().revalidate();
    }

    public static void main(String[] args) {
        AppFactory factory = new GridFactory();
        AppPanel panel = new GridPanel(factory);
        //panel.display();
    }
}
