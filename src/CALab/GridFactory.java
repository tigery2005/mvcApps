package CALab;

import mvc.*;
import stopLight.Stoplight;
import stopLight.StoplightView;

import java.util.Objects;

public class GridFactory implements AppFactory {
    public Model makeModel() {
        return new Grid();
    }
    public View makeView(Model m) {
        return new StoplightView((Stoplight)m);
    }

    public String[] getEditCommands() { return new String[] {"Change"}; }

    // source added 3/15 to support text fields
    public Command makeEditCommand(Model model, String type, Object source) {
        if (Objects.equals(type, "RUN1"))
            return new run1Command(model);
        else if (Objects.equals(type, "RUN50"))
            return new run50Command(model);
        else if (Objects.equals(type, "POPULATE"))
            return new populateCommand(model);
        else if (Objects.equals(type, "CLEAR"))
            return new clearCommand(model);
        else
            return null;

    }

    public String getTitle() { return "CALab"; }

    public String[] getHelp() {
        return new String[] {""};
    }

    public String about() {
        return "";
    }
}
