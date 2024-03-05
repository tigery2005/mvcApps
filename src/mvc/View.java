/*
 * Edit Log:
 * Tiger 03/04: Created File
 *
 */

package mvc;

import javax.swing.*;

public class View extends JPanel implements Subscriber {
    private Model model;

    public View(Model model) {
        this.model = model;
        model.subscribe(this);
    }

    public void update() {
        repaint();
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        model.unsubscribe(this);
        this.model = model;
        model.subscribe(this);
        update();
    }
}
