package LifeLab;

import CALab.Cell;
import CALab.Grid;

import java.util.HashSet;
import java.util.Set;

public class Society extends Grid {
    public static Set<Integer> rebirth = new HashSet<Integer>();
    public static Set<Integer> death = new HashSet<Integer>();
    public static int percentAlive = 50;

    public Society(int dim){
        super(dim);

    }
    public void clearAgents(){
        repopulate(false);
        notifySubscribers();
    }

    @Override
    public Cell makeCell(boolean uniform) {
        Cell agent = new ((Cell)Agent)();
    }
}
