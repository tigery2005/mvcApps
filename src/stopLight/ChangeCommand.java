/*
 * Edit Log:
 * Tiger 03/04: Created File
 * Tiger 03/04: Copied Over Provided Code
 *
 */

package stopLight;

import mvc.*;

public class ChangeCommand extends Command {

    public ChangeCommand(Model model) {
        super(model);
    }

    public void execute() {
        Stoplight light = (Stoplight)model;
        light.change();
    }

}
