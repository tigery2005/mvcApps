/*
 * Edit Log:
 * Alvin 03/14: Completed Implementation
 */
package LifeLab;

import java.awt.*;
import java.util.*;
import CALab.Cell;
import LifeLab.Society;

public class Agent extends Cell {
    private int status = 0;
    private int ambience = 0;

    @Override
    public void update() {
        if (status == 0) {
                if (Society.rebirth.contains(ambience)){
                    status = 1;
                }
        }
        else if (status == 1) {
                if (Society.death.contains(ambience)) {
                    status = 0;
                }
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
            double x = random.nextDouble() * 100;

            if( x < Society.percentAlive) {
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
        return ambience;
    }

    @Override
    public int getState() {return status;}

    @Override
    public void observe() {

        ambience = 0;

        for(Cell s : neighbors) {
            if (s.getState() == 1) {
                ambience = ambience + 1;
            }
        }
        notifySubscribers();
    }

    @Override
    public void interact() {
    }
}
