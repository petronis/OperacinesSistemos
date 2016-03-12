package com.company;

/**
 * Created by Vik on 3/12/2016.
 */
public abstract class Machine {
    Memory data;
    Registers registers;
    /* runs machine using defined algorithm */
    abstract boolean run();

    public Memory getData() {
        return data;
    }

}
