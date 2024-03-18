/*
 * Edit Log:
 * Danny 03/16: Completed Implementation
 * Tiger 03/17: Bug Fixes
 */
package CALab;

import javax.swing.*;

import mvc.*;
import java.awt.*;

public class GridView  extends View {

    private CellView cellViews[][];
    protected int row;
    protected int col;
    public GridView(Model model) {
        super(model);
        setModel(model);
        update();
    }
    public void setModel(Model model){
        removeAll();
        int dim = ((Grid)model).getDim();
        this.setLayout(new GridLayout(dim, dim));
        this.cellViews = new CellView[dim][dim];
        for (int i=0;i<dim;i++){
            for(int j=0;j<dim;j++){
                CellView cell = new CellView(((Grid)model).getCell(i, j));
                cellViews[i][j] = cell;
                this.add(cell);
            }
        }
        super.setModel(model);
    }

    public void update() {

        // call update method of each CellView
        for (CellView[] row : cellViews) {
            for (CellView c : row) {
                c.update();
            }
        }
        this.repaint();
    }

}