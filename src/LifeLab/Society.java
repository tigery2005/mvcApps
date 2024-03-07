package LifeLab;

import CALab.*;
import java.util.*;

public class Society extends Grid {
    public static int percentAlive = 50;
    public static Set<Integer> rebirth = new HashSet<>();
    public static Set<Integer> death = new HashSet<>();

    static {
        rebirth.add(3);
        death.add(0);
        death.add(1);
        death.add(4);
        death.add(5);
        death.add(6);
        death.add(7);
        death.add(8);
    }

    public Society(int dim) {
        super(dim);
    }

    public Cell makeCell(boolean uniform) {
        return new Agent();
    }

}
