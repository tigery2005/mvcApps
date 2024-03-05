package CALab;

import mvc.Command;
import mvc.Model;

public class ClearCommand extends Command {
    public ClearCommand(Model model) {
        super(model);
    }

    public void execute() {
        Grid grid = (Grid) getModel();
        grid.repopulate(false);
    }
}
