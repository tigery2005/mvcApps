package CALab;

import mvc.*;
import java.util.*;

public abstract class GridFactory implements AppFactory {

    public abstract Model makeModel();

    public View makeView(Model m) {
        return new GridView((Grid) m);
    }

    public String[] getEditCommands() {
        return new String[]{"Run 1", "Run 50", "Populate", "Clear"};
    }

    // source added 3/15 to support text fields
    public Command makeEditCommand(Model model, String type, Object source) {
        if (Objects.equals(type, "Run 1"))
            return new RunCommand(model, 1);
        else if (Objects.equals(type, "Run 50"))
            return new RunCommand(model, 50);
        else if (Objects.equals(type, "Populate"))
            return new PopulateCommand(model);
        else if (Objects.equals(type, "Clear"))
            return new ClearCommand(model);
        else return null;
    }

    public String getTitle() {
        return "CA Grid Simulator";
    }

    public String[] getHelp() {
        return new String[]{
                "Run 1: cycle through 1 update of the grid",
                "Run 50: cycle through 50 updates of the grid",
                "Populate: randomize the states of all cells",
                "Clear: set all the states of all cells to an initial value"
        };
    }

    public abstract String about();

}
