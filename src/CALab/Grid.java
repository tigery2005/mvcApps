/*
 * Edit Log:
 * Danny 03/16: Completed Implementation
 * Tiger 03/17: Bug Fixes
 */
package CALab;

import java.awt.*;
import java.util.*;
import java.io.*;
import mvc.*;

public abstract class Grid extends Model {
    static private int time = 0;
    protected int dim = 20;
    protected Cell[][] cells;

    public int getDim() { return dim; }
    public int getTime() { return time; }
    public Cell getCell(int row, int col) { return cells[row][col]; }
    public abstract Cell makeCell(boolean uniform);


    public Grid(int dim) {
        this.dim = dim;
        cells = new Cell[dim][dim];
        populate();
    }
    public Grid() { this(20); }
    protected void populate() {
        // 1. use makeCell to fill in cells
        // 2. use getNeighbors to set the neighbors field of each cell
        for (int i=0;i<dim;i++) {
            for (int j=0;j<dim;j++){
                cells[i][j] = makeCell(false);
                //cells[i][j] = new Cell(i,j,this);
                cells[i][j].row = i;
                cells[i][j].col = j;
                cells[i][j].myGrid = this;
            }
        }//fill in cells
        for (int i=0;i<dim;i++) {
            for (int j=0;j<dim;j++){
                Cell u = cells[i][j];
                u.neighbors = getNeighbors(u,1);
            }//update the neighbours with radius of 1 (8 cells in particular)
        }
    }

    // called when Populate button is clicked
    public void repopulate(boolean randomly) {
            // randomly set the status of each cell
            for (int i=0;i<dim;i++) {
                for (int j=0;j<dim;j++){
                    Cell u = cells[i][j];
                    u.reset(randomly);
                    //Math.random return [0-1). >0.5 is 50% chance true/false
                }
            }
            for (int i=0;i<dim;i++) {
                for (int j=0;j<dim;j++){
                    Cell u = cells[i][j];
                    u.observe();
                }
            }



        // notify subscribers
        this.changed();
    }


    public Set<Cell> getNeighbors(Cell asker, int radius) {
        /*
        return the set of all cells that can be reached from the asker in radius steps.
        If radius = 1 this is just the 8 cells touching the asker.
        Tricky part: cells in row/col 0 or dim - 1.
        The asker is not a neighbor of itself.
        */

        Set<Cell> neighbors = new HashSet<Cell>();
        for (int i = -radius; i <= radius ; i++) {
            for (int j = -radius; j <= radius ; j++) {
                if (i == 0 && j == 0) continue;
                int row = (asker.row + i + dim) % dim;
                int col = (asker.col + j + dim) % dim;
                neighbors.add(cells[row][col]);
            }
        }
        return neighbors;
    }

    // overide these
    public int getStatus() { return 0; }
    public Color getColor() { return Color.GREEN; }

    // cell phases:

    public void observe() {
        // call each cell's observe method and notify subscribers
        for (int i=0;i<dim;i++) {
            for (int j=0;j<dim;j++){
                Cell u = cells[i][j];
                u.observe();
            }
        }
    }

    public void interact() {
        // ???
        for (int i=0;i<dim;i++) {
            for (int j=0;j<dim;j++){
                Cell u = cells[i][j];
                u.interact();
            }
        }

    }

    public void update() {
        for (int i=0;i<dim;i++) {
            for (int j=0;j<dim;j++){
                Cell u = cells[i][j];
                u.update();
            }
        }
        this.changed();
    }

    public void updateLoop(int cycles) {
        observe();
        for(int cycle = 0; cycle < cycles; cycle++) {
            interact();
            update();
            observe();
            time++;
            System.out.println("time = " + time);
        }
    }
}
