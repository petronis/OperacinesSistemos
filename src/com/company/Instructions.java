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
    public void addition(int address){
        Register r =  machine.getRegister("R");
        // TODO: 3/14/2016 add get value from block in memory
        try {
            r.setContent(machine.getData().getBlockInt(address) + r.getContentInt());
        } catch (Error e ) {
            System.err.println(e.getMessage());
        }
    }
}
