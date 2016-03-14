package com.company;

import Exceptions.Error;

/**
 * Created by Vik on 3/12/2016.
 */
public class Instructions {
    Machine machine;
    Instructions (Machine machine) {
        this.machine = machine;
    }
    // instructions list
    private String IntToHex(int n, int size) throws Error {
        String temp = Integer.toHexString(n);
        if (temp.length() < size) {
            return temp;
        } else {
            throw new Error("Too big number in hex conversion");
        }
    }

    public void addition(int address){
        Register r =  machine.getRegister("R");
        try {
            r.setContent(IntToHex(machine.getData().getBlockInt(address) + r.getContentInt(), r.getSize()));
        } catch (Error e ) { // TODO: 3/14/2016 add interrupt structure
            System.err.println(e.getMessage());
        }
    }

    public void substitution(int address) {
        Register r =  machine.getRegister("R");
        try {
            r.setContent(IntToHex(r.getContentInt() - machine.getData().getBlockInt(address), r.getSize()));
        } catch (Error e ) { // TODO: 3/14/2016 add interrupt structure
            System.err.println(e.getMessage());
        }
    }



}
