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
    private AppFactory factory;

    public AppPanel(AppFactory factory) {
        model = factory.makeModel();
        view = factory.makeView(model);
        control = new ControlPanel();
        this.factory = factory;
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
        String cmmd = e.getActionCommand();
        try {
            switch (cmmd) {
                case "Execute": {
                    mac.execute();
                    break;
                }

                case "Parse": {
                    String fileName = JOptionPane.showInputDialog(null, "Enter file name:");
                    if (fileName == null || fileName.isEmpty()) {
                        System.out.println("No file name entered. Exiting.");
                        break;
                    }

                    try {
                        currentProgram.clear();
                        StringBuilder contentBuilder = new StringBuilder();
                        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                contentBuilder.append(line).append("\n");
                                currentProgram.add(line);
                            }
                        }

                        String program = contentBuilder.toString();
                        mac.setProgram(MiniMacParser.parse(program));
                        mac.notifySubscribers();
                    }

                    catch (IOException exc) {
                        exc.printStackTrace();
                    }

                    break;
                }

                case "Clear": {
                    mac.clear();
                    break;
                }


                case "Save": {
                    Utilities.save(model, false);
                    break;
                }

                case "Open": {
                    model = Utilities.open(model);
                    view.setModel(model);
                    break;

                }

                case "New": {
                    model = factory.makeModel();
                    view.setModel(model);
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
                    throw new Exception("Unrecognized command: " + cmmd);
                }
            }

        } catch (Exception ex) {
            Utilities.error(ex);
        }
    }

    //TODO implement update()
    @Override
    public void update() {
    }

    public ControlPanel getControl() {
        return control;
    }
}
