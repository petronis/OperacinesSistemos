package com.company;

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
        r.setContent(machine.getData().getBlock(address) + r.getContentInt());
    }
}
