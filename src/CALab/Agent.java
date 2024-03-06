package CALab;

import java.awt.*;
import java.util.Random;

public class Agent extends Cell{
    private int status = 0;
    private int ambiance = 8;

    public Agent() {
        super();
    }

    @Override
    public void observe() {
        ambiance = 0;
        for (Cell c : neighbors) {
            if (c.getStatus() == 1) {
                ambiance++;
            }
        }
    }

    @Override
    public void interact() {}

    @Override
    public void update() {
        //revive
        if (Society.rebirth.contains(ambiance) && status == 0) {
            status = 1;
        }
        else if (Society.death.contains(ambiance) && status == 1) {
            status = 0;
        }
    }

    @Override
    public void nextState() {
        if (status == 0) {
            status = 1;
        }
        else if (status == 1) {
            status = 0;
        }
    }

    @Override
    public void reset(boolean randomly) {
        if (randomly) {
            Random random = new Random();
            if (random.nextDouble() * 100 < Society.percentAlive) {
                status = 1;
            }
            else {
                status = 0;
            }
        }
        else {
            status = 0;
        }
    }

    @Override
    public Color getColor() {
        if (status == 0) {
            return Color.RED;
        }
        else {
            return Color.GREEN;
        }
    }

    @Override
    public int getStatus() {
        return status;
    }
}
