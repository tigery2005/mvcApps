package LifeLab;

import java.awt.*;
import java.util.*;
import CALab.Cell;

public class Agent extends Cell {
    private int status = 0;
    private int ambience = 8;

    @Override
    public void update() {
        if (status == 0 && ambience == 3) {
            status = 1;
            getColor();
        }
        else if (status == 1 && (ambience != 3)) {
            status = 0;
            getColor();
        }
        notifySubscribers();
    }

    @Override
    public void nextState() {
        if (status == 0) {
            status = 1;
            getColor();
        }
        else {
            status = 0;
            getColor();
        }
        notifySubscribers();
    }

    @Override
    public void reset(boolean randomly) {
        if (randomly){
            Random random = new Random();
            status = random.nextInt(1);
            getColor();
        }
        else {
            status = 0;
            getColor();
        }
        notifySubscribers();
    }

    @Override
    public Color getColor() {
        if (status == 0) {
            return color = Color.red;
        }
        else {
            return color = Color.green;
        }
    }

    @Override
    public int getStatus() {
        return status;
    }
    @Override
    public void observe() {
        //set to array
        Cell arrNei[] = new Cell[neighbors.size()];
        int i = 0;
        for (Cell x : neighbors)
            arrNei[i++] = x;

        for (int j = 0; j < neighbors.size(); j++) {
            if (arrNei[j].getStatus() == 0) {
                ambience = ambience - 1;
            }
        }
    }

    @Override
    public void interact() {
    }
}
