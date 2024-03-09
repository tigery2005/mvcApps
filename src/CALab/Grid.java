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
            for (Cell u : cells[i]){
                u = makeCell(false);
            }
        }//fill in cells
        for (int i=0;i<dim;i++) {
            for (Cell u : cells[i]){
                u.neighbors = getNeighbors(u,1);
            }//update the neighbours with radius of 1 (8 cells in particular)
        }
    }

    // called when Populate button is clicked
    public void repopulate(boolean randomly) {
        if (randomly) {
            // randomly set the status of each cell
            for (int i=0;i<dim;i++) {
                for (Cell u : cells[i]){
                    u = makeCell(Math.random()>0.5);
                    //Math.random return [0-1). >0.5 is 50% chance true/false
                }
            }
        } else {
            // set the status of each cell to 0 (dead)
            for (int i=0;i<dim;i++) {
                for (Cell u : cells[i]){
                    u = makeCell(false);
                    //set all of them to false
                }
            }

        }
        this.notifySubscribers();
        // notify subscribers
    }


    public Set<Cell> getNeighbors(Cell asker, int radius) {
        /*
        return the set of all cells that can be reached from the asker in radius steps.
        If radius = 1 this is just the 8 cells touching the asker.
        Tricky part: cells in row/col 0 or dim - 1.
        The asker is not a neighbor of itself.
        */

        Set<Cell> Ans = new HashSet<Cell>();
        for(int i=asker.col-radius;i<asker.col+radius;i++){
            for(int j=asker.row-radius;j<asker.row+radius;j++){
                // i,j are the coords. if radius=1 that is all 8 cells around it
                if(i==asker.col && j==asker.row) continue;
                int x = (i+dim)%dim;
                int y = (j+dim)%dim;
                //x and y deals with corner cases like row/col 0 or dim-1.
                Ans.add(cells[x][y]);
            }
        }
        return Ans;
    }

    // overide these
    public int getStatus() { return 0; }
    public Color getColor() { return Color.GREEN; }

    // cell phases:

    public void observe() {
        // call each cell's observe method and notify subscribers
        for (int i=0;i<dim;i++) {
            for (Cell u : cells[i]) {
                u.observe();
            }
        }
    }

    public void interact() {
        // ???
        for (int i=0;i<dim;i++) {
            for (Cell u : cells[i]) {
                u.interact();
            }
        }

    }

    public void update() {
        for (int i=0;i<dim;i++) {
            for (Cell u : cells[i]) {
                u.update();
            }
        }
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
