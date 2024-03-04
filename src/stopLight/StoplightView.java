/*
 * Edit Log:
 * Tiger 03/04: Created File
 * Tiger 03/04: Copied Over Provided Code
 *
 */

package stopLight;

import mvc.*;
import java.awt.*;

public class StoplightView extends View {

    public StoplightView(Stoplight light) {
        super(light);
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Color oldColor = gc.getColor();
        Stoplight light = (Stoplight)model;
        StoplightShape shape = new StoplightShape(light);
        shape.draw((Graphics2D) gc);
        gc.setColor(oldColor);
    }
}
