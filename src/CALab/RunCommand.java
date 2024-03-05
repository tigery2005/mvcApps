package CALab;

import mvc.Command;
import mvc.Model;

public class RunCommand extends Command {
    private int cycles;
    public RunCommand(Model model, int cycles) {
        super(model);
        this.cycles = cycles;
    }

    public void execute() {
        Grid grid = (Grid) getModel();
        grid.updateLoop(cycles);
    }
}