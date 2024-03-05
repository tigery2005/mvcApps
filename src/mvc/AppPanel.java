/*
 * Edit Log:
 * Tiger 03/04: Created File
 *
 */

package mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class AppPanel extends JPanel implements Subscriber, ActionListener {
    private Model model;
    private ControlPanel control;
    private View view;

    public AppPanel(AppFactory factory) {
        model = factory.makeModel();
        view = factory.makeView(model);
        control = new ControlPanel();
        this.setLayout((new GridLayout(1, 2)));
        this.add(control);
        this.add(view);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle("MiniMac");
        frame.setSize(800, 600);
        frame.setVisible(true);
        //frame.pack();
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Open", "Quit"}, this);
        result.add(fileMenu);
        //TODO: change edit menu
        JMenu editMenu = Utilities.makeMenu("Edit", new String[]{"Parse", "Execute", "Clear"}, this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);
        return result;
    }

    public class ControlPanel extends JPanel {
        public ControlPanel() {
            setBackground(Color.PINK);
        }
    }

    //TODO change actions
    public void actionPerformed(ActionEvent e) {

    }

    //TODO implement update()
    @Override
    public void update() {
    }

    public ControlPanel getControl() {
        return control;
    }
}
