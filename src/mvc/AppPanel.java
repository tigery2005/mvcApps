/*
 * Edit Log:
 * Tiger 03/04: Created File
 * Tiger 03/04: Completed Implementation
 * Tiger 03/17: Bug Fixes
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
    private AppFactory factory;

    public AppPanel(AppFactory factory) {
        model = factory.makeModel();
        view = factory.makeView(model);
        control = new ControlPanel();
        this.factory = factory;
        this.setLayout((new GridLayout(1, 2)));
        this.add(control);
        this.add(view);

        JFrame frame = new SafeFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle(factory.getTitle());
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        //frame.pack();
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Save As", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", factory.getEditCommands(), this);
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


    public void actionPerformed(ActionEvent e) {
        String cmmd = e.getActionCommand();
        try {
            switch (cmmd) {

                case "Save": {
                    Utilities.save(model, false);
                    break;
                }

                case "Save As": {
                    Utilities.save(model, true);
                    break;
                }

                case "Open": {
                    this.model.unsubscribe(this);
                    model = Utilities.open(model);
                    this.model.subscribe(this);
                    view.setModel(model);
                    model.changed();
                    break;
                }

                case "New": {
                    Utilities.saveChanges(model);
                    this.model.unsubscribe(this);
                    model = factory.makeModel();
                    this.model.subscribe(this);
                    view.setModel(model);
                    model.changed();
                    break;
                }

                case "Quit": {
                    Utilities.saveChanges(model);
                    System.exit(0);
                    break;
                }

                case "About": {
                    Utilities.inform(factory.about());
                    break;
                }

                case "Help": {
                    String[] cmmds = factory.getHelp();
                    Utilities.inform(cmmds);
                    break;

                }

                default: {
                    Command newCommand = factory.makeEditCommand(model, cmmd,null);
                    if(newCommand == null) {
                        throw new Exception("Unrecognized command: " + cmmd);
                    }
                    newCommand.execute();
                }
            }

        } catch (Exception ex) {
            Utilities.error(ex);
        }
    }


    @Override
    public void update() {
    }

    public ControlPanel getControl() {
        return control;
    }
}
