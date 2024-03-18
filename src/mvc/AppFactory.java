/*
 * Edit Log:
 * Tiger 03/04: Created File
 * Tiger 03/04: Completed Implementation
 */

package mvc;


public interface AppFactory {
    Model makeModel();

    View makeView(Model model);

    String getTitle();

    String[] getHelp();

    String about();

    String[] getEditCommands();

    Command makeEditCommand(Model model, String name, Object source);
}
