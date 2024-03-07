/*
 * Edit Log:
 * Tiger 03/04: Created File
 *
 */

package mvc;


public abstract class Command {
    private Model model;

    public Command(Model model) {
        this.model = model;
    }

    public abstract void execute();

    public Model getModel() {
        return model;
    }
}
