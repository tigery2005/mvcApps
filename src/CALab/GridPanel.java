package CALab;

import mvc.AppFactory;
import mvc.AppPanel;

import javax.swing.*;

public class GridPanel extends AppPanel {
    private JButton run1;
    private JButton run50;
    private JButton populate;
    private JButton clear;
    public GridPanel(AppFactory factory) {
        super(factory);

        run1 = new JButton("Run 1");
        run1.addActionListener(this);
        getControl().add(run1);

        run50 = new JButton("Run 50");
        run50.addActionListener(this);
        getControl().add(run50);

        populate = new JButton("Populate");
        populate.addActionListener(this);
        getControl().add(populate);

        clear = new JButton("Clear");
        clear.addActionListener(this);
        getControl().add(clear);

        getControl().revalidate();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ;
            AppFactory factory = new SocietyFactory();
            AppPanel panel = new GridPanel(factory);
            //panel.display();
        });
    }

}