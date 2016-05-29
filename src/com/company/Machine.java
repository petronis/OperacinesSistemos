package com.company;


import Exceptions.WrongContentSize;

/**
 * Created by Vik on 3/12/2016.
 */
public abstract class Machine {
    Memory data;
    Registers registers;

    Machine(int blocks) {
        data = new Memory(blocks);
        registers = new Registers();
    }

    /* runs machine using defined algorithm */
    abstract void run() throws WrongContentSize, Exception;

    public Memory getData() {
        return data;
    }

    protected void setData(Memory data) {
        this.data = data;
    }

    public void add_register(Register reg) {
        registers.addRegister(reg);
    }

    public void add_register(String name, int size) {
        registers.addRegister(new Register(name, size));
    }

    public int getRegistersSize() {
        return registers.get_size();
    }

    public Register getRegister(int n) {
        return registers.get_register(n);
    }

    public Register getRegister(String key) {
        return registers.get_register(key);
    }
}
