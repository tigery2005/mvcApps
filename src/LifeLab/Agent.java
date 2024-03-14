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
        }
        else if (status == 1 && (ambience != 3)) {
            status = 0;
        }
        notifySubscribers();
    }

    @Override
    public void nextState() {
        if (status == 0) {
            status = 1;
        }
        else {
            status = 0;
        }
        notifySubscribers();
    }

    @Override
    public void reset(boolean randomly) {
        if (randomly){

            Random random = new Random();
            int x = random.nextInt(100);

            if( x > Society.percentAlive) {
                status = 1;
            }
            else {
                status = 0;
            }
        }
        else {
            status = 0;
        }
        notifySubscribers();
    }

    @Override
    public Color getColor() {
        if (status == 0) {
            return Color.red;
        }
        else {
            return Color.green;
        }
    }

    @Override
    public int getStatus() {
        return status;
    }
    @Override
    public void observe() {

        ambience = 0;

        for(Cell s : neighbors) {
            if (s.getStatus() == 1) {
                ambience = ambience + 1;
            }
        }
    }

    @Override
    public void interact() {
    }
}
