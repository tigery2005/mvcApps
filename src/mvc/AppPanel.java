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
    private ControlPanel controls;
    private View view;

    public AppPanel(AppFactory factory) {
        model = factory.makeModel();
        view = factory.makeView(model);
        controls = new ControlPanel();
        this.setLayout((new GridLayout(1, 2)));
        this.add(controls);
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

    class ControlPanel extends JPanel {
        public ControlPanel() {
            setBackground(Color.PINK);

            //TODO: change layout and button functions, add 4th button
            JPanel p = new JPanel();
            p.setLayout(new GridLayout(3,1));
            p.setBackground(Color.PINK);

            JButton parse = new JButton("Parse");
            parse.addActionListener(AppPanel.this);
            p.add(parse);

            JButton execute = new JButton("Execute");
            execute.addActionListener(AppPanel.this);
            p.add(execute);

            JButton clear = new JButton("Clear");
            clear.addActionListener(AppPanel.this);
            p.add(clear);

            add(p);
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
                    String fName = Utilities.getFileName((String) null, false);
                    ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
                    os.writeObject(this.mac);
                    os.close();
                    break;
                }

                case "Open": {

                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        String fName = Utilities.getFileName((String) null, true);
                        ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                        mac = (MiniMac) is.readObject();
                        view.setMac(mac);
                        is.close();
                    }

                    break;

                }

                case "New": {
                    mac = new MiniMac();
                    view.setMac(mac);
                    break;
                }

                case "Quit": {
                    System.exit(0);
                    break;
                }

                case "About": {
                    Utilities.inform("This is a MiniMac! Created by Tiger Yang.");
                    break;
                }

                case "Help": {
                    String[] cmmds = new String[]{
                            "Parse: Parses an inputted file of instructions which are sent to the MiniMac",
                            "Execute: Runs the instructions provided to the MiniMac",
                            "Clear: Clears the MiniMac's memory",
                    };
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
}
