package LifeLab;

import java.awt.*;
import java.util.*;
import CALab.Cell;

public class Agent extends Cell {
    private int status = 0;
    private int ambience = 8;

    @Override
    public void update() {
        observe();

    }

    @Override
    public void nextState() {
        //if ambience is certain numver then die or rebirth
    }

    @Override
    public void reset(boolean randomly) {

    }

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public int getStatus() {
        return status;
    }

    public void observe() {
        if (status == 0 && ambience == 3){
            status = 1;
        }
        else if (status == 1 && (ambience != 3)){
            status = 0;
        }
    }

    @Override
    public void interact() {
    }
}
