package CALab;

import javax.swing.*;

import mvc.*;
import java.awt.*;

public class GridView  extends View {

    private CellView cellViews[][];

    private int row;

    private int col;

    public GridView(Model model) {
        super(model);
        CellView cell = new CellView(((Grid)model).getCell(row, col);
        cellViews[row][col] = cell;

    }

    public void update(String msg, Object oldState, Object newState) {
        // call update method of each CellView
        for (int i=0;i<row;i++) {
            for (CellView u : cellViews[i]) {
                u.update(msg,oldState,newState);
                //update each cell
            }
        }
    }

}