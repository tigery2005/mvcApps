package CALab;

import javax.swing.*;

import mvc.*;
import java.awt.*;

public class GridView extends View {

    private CellView cellViews[][];

    public GridView(Model model) {
        /*
        Cell cell = new CellView(((Grid)model).getCell(row, col)
        cellViews[row][col] = cell
        set cell.row and cell.col here
        */
        super(model);
        int dim = ((Grid)model).getDim();
        this.setLayout(new GridLayout(dim, dim));
        this.cellViews = new CellView[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                CellView cell = new CellView(((Grid)model).getCell(i, j));
                cellViews[i][j] = cell;
                this.add(cell);
            }
        }
        update();
    }


    public void update() {
        // call update method of each CellView
        for (CellView[] row : cellViews) {
            for (CellView c : row) {
                c.update();
            }

        }
    }

}
