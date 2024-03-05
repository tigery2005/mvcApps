/*
 * Edit Log:
 * Tiger 03/04: Created File
 *
 */

package mvc;

import java.util.List;

public interface AppFactory {
    Model makeModel();

    View makeView(Model model);

    String getTitle();

    String[] getHelp();

    String about();

    String[] getEditCommands();

    Command makeEditCommand(Model model, String name, Object source);
}
