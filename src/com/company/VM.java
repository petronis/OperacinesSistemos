package com.company;

/**
 * Created by Vik on 3/12/2016.
 */
public class VM extends Machine {

    private void init() {
        registers.addRegister(new Register("IC", 2, "00"));
        registers.addRegister(new Register("C", 1, "0"));
        registers.addRegister(new Register("R", 4));
        registers.addRegister(new Register("B", 2));
    }

    VM(int blocks) {
        super(blocks);
    }

    @Override
    boolean run() {
        return false;
    }
}