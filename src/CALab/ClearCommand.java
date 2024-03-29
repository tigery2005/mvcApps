 /*
  * Edit Log:
  * Tiger 03/06: Completed Implementation
  */

package CALab;

import mvc.*;

public class ClearCommand extends Command {
    public ClearCommand(Model model) {
        super(model);
    }

    public void execute() {
        Grid grid = (Grid) getModel();
        grid.repopulate(false);
    }
}
