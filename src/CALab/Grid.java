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
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                // TODO: What is uniform????
                Cell newCell = makeCell(true);
                newCell.row = i;
                newCell.col = j;
                newCell.myGrid = this;
                cells[i][j] = newCell;
            }
        }
        for (Cell[] row : cells) {
            for (Cell c : row) {
                c.neighbors = getNeighbors(c, 1);
            }
        }
    }

    // called when Populate button is clicked
    public void repopulate(boolean randomly) {
        // randomly set the status of each cell
        for (Cell[] row : cells) {
            for (Cell c : row) {
                if (randomly) {
                    c.reset(true);
                }
                else {
                    c.reset(false);
                }
                // notify subscribers
                c.notifySubscribers();
            }
        }
    }


    public Set<Cell> getNeighbors(Cell asker, int radius) {
        /*
        return the set of all cells that can be reached from the asker in radius steps.
        If radius = 1 this is just the 8 cells touching the asker.
        Tricky part: cells in row/col 0 or dim - 1.
        The asker is not a neighbor of itself.
        */
        Set<Cell> neighbors = new HashSet<>();

        for (int i = -radius; i <= radius; i++) {
            for (int j = -radius; j <= radius; j++) {
                if (i == 0 && j == 0) continue; // Skip the asker cell itself
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
        for (Cell[] row : cells) {
            for (Cell c : row) {
                c.observe();
                c.notifySubscribers();
            }
        }
    }

    public void interact() {
        // ???
        for (Cell[] row : cells) {
            for (Cell c : row) {
                c.interact();
                c.notifySubscribers();
            }
        }
    }

    public void update() {
        // ???
        for (Cell[] row : cells) {
            for (Cell c : row) {
                c.update();
                c.notifySubscribers();
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

