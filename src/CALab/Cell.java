/*
 * Edit Log:
 * Alvin 03/15: Completed Implementation
 */

package CALab;
import mvc.*;

import java.awt.*;
import java.util.*;
import java.io.*;
import mvc.*;

public abstract class Cell extends Publisher implements Serializable {

    protected int row = 0, col = 0;
    protected Set<Cell> neighbors = new HashSet<Cell>();
    protected Grid myGrid = null;
    protected Cell partner = null;
    protected Color color = Color.red;


    // choose a random neighbor as a partner
    public void choosePartner() {
        if (partner == null && neighbors != null) {
			partner = null;
            ArrayList<Cell> arr = new ArrayList <Cell> (neighbors.size());
            //arr = neighbors.toArray(arr);
            //Cell[] arr_new = new Cell[arr.length-1];

            Random random = new Random();

            boolean found = false;

            while(!found) {
                int x = random.nextInt(arr.size());
                if (arr.get(x).partner == null) {
                    found = true;
                    partner = arr.get(x).partner;
                }
                //remove arr[0] from temp array
                else {
                    arr.remove(x);
                }
            }
            /*
			Set partner to null
			Convert neighbors set to a local array
			Starting at a random position in the array search for a neighbor without a partner
			Make the first such neighbor (if any) the partner and set its partner field to this
			*/
        }

    }

    public void unpartner() {
        if (partner != null) {
            if (partner.partner != null) {
                partner.partner = null;
            }
            partner = null;
        }
    }

    public abstract Color getColor();
    public abstract int getStatus();
    public abstract int getState();
    // observer neighbors' states
    public abstract void observe();
    // interact with a random neighbor
    public abstract void interact();
    // update my state
    public abstract void update();
    // set status to status + 1 mod whatever
    public abstract void nextState();
    // set status to a random or initial value
    public abstract void reset(boolean randomly);

}

