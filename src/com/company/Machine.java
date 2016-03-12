package com.company;


/**
 * Created by Vik on 3/12/2016.
 */
public abstract class Machine {
    Memory data;
    Registers registers;
    Instructions instructions;

    Machine(int blocks) {
        data = new Memory(blocks);
        registers = new Registers();
        instructions = new Instructions(this);
    }

    /* runs machine using defined algorithm */
    abstract boolean run();

    public Memory getData() {
        return data;
    }

    public void add_register(Register reg) {
        registers.addRegister(reg);
    }

    public void add_register(String name, int size) {
        registers.addRegister(new Register(name, size));
    }

    public Register getRegister(int n) {
        return registers.get_register(n);
    }

    public Register getRegister(String key) {
        return registers.get_register(key);
    }
}
