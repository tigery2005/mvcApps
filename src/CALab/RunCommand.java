package CALab;

import mvc.*;

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