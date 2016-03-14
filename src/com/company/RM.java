package com.company;

/**
 * Created by Vik on 3/12/2016.
 */
public class RM extends Machine {

    private void init() {
        registers.addRegister(new Register("PTR", 4));
        registers.addRegister(new Register("B", 2));
        registers.addRegister(new Register("IC", 2, "00"));
        registers.addRegister(new Register("C", 1, "0"));
        registers.addRegister(new Register("R", 4));
        registers.addRegister(new Register("PI", 1, "0"));
        registers.addRegister(new Register("SI", 1, "0"));
        registers.addRegister(new Register("TI", 1, "30"));
        registers.addRegister(new Register("CH1", 1, "0"));
        registers.addRegister(new Register("CH2", 1, "0"));
        registers.addRegister(new Register("CH3", 1, "0"));
        registers.addRegister(new Register("MODE", 1));
    }

    RM(int blocks) {
        super(blocks);
        init();
    }

    @Override
    boolean run() {
        return false;
    }
}
