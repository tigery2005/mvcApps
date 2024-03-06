package CALab;

import mvc.AppFactory;
import mvc.AppPanel;
import stopLight.StoplightPanel;
import mvc.*;

import javax.swing.*;

public class CAPanel extends AppPanel{
    private JButton RUN1;
    private JButton RUN50;
    private JButton POPULATE;
    private JButton CLEAR;

    public CAPanel (AppFactory factory) {
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
        AppFactory factory = new CAFactory();
        AppPanel panel = new CAPanel(factory);
        //panel.display();
    }
}
