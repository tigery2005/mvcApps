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
    }
    public void setModel(Model model){
        int dim = ((Grid)model).getDim();
        setModel(model);
        setLayout(getLayout());
        row = ((Grid)model).getDim();
        col = ((Grid)model).getDim();
        for (int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                cellViews[i][j] = new CellView(((Grid)model).getCell(i, j));
            }
        }
    }

    public void update() {

        // call update method of each CellView
        for (int i=0;i<row;i++) {
            for (int j=0;j<col;j++){
                CellView u = cellViews[i][j];
                u.update();
            }
        }
        this.repaint();
    }

}