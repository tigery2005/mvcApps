/*
 * Edit Log:
 * Tiger 03/04: Created File
 * Tiger 03/15: Completed Implementation
 * Alvin 03/16: Copied Code Over
 */
package LifeLab;

import CALab.*;
import mvc.*;

public class SocietyFactory extends GridFactory {
    public Model makeModel() {
        return new Society(20);
    }
    public String about() {
        return "CALab - LifeLab version 2.0. Copyright 2024 by MVC Group 3";
    }

}
