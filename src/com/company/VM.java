package com.company;

/**
 * Created by Vik on 3/12/2016.
 */
public class VM extends Machine {

    RM rm;
    Instructions instructions;

    private void init() {
        registers.addRegister(new Register("IC", 2, "00"));
        registers.addRegister(new Register("C", 1, "0"));
        registers.addRegister(new Register("R", 5));
        registers.addRegister(new Register("B", 2));
    }

    VM(int blocks, RM rm) {
        super(blocks);
        this.rm = rm;
        init();
    }

    public void setInstructions(Instructions instructions) {
        this.instructions = instructions;
    }

    @Override
    boolean run() {
        return false;
    }
}
