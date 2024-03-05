package CALab;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;
import stopLight.ChangeCommand;
import stopLight.Stoplight;
import stopLight.StoplightView;

import java.util.Objects;

public class GridFactory implements AppFactory {

    //TODO: fix me
    public Model makeModel() {
        return;
    }

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
        if (Objects.equals(type, "Run 50"))
            return new RunCommand(model, 50);
        if (Objects.equals(type, "Populate"))
            return new PopulateCommand(model);
        if (Objects.equals(type, "Clear"))
            return new ClearCommand(model);
        return null;
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

    public String about() {
        return "CALab version 2.0. Copyright 2024 by MVC Group 3";
    }

}
