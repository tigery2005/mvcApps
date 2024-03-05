/*
 * Edit Log:
 * Tiger 03/04: Created File
 * Tiger 03/04: Copied Over Provided Code
 *
 */

package stopLight;

import javax.swing.*;

import mvc.*;


public class StoplightPanel extends AppPanel {
    private JButton change;
    public StoplightPanel(AppFactory factory) {
        super(factory);
        change = new JButton("Change");
        change.addActionListener(this);
        getControl().add(change);
        getControl().revalidate();
    }

    public static void main(String[] args) {
        AppFactory factory = new StoplightFactory();
        AppPanel panel = new StoplightPanel(factory);
        //panel.display();
    }

}
