package com.company;

import Exceptions.Error;
import Interrupts.LoadFromMemory;
import Interrupts.WriteToMemory;

/**
 * Created by Vik on 3/12/2016.
 */
public class Instructions {
    RM rm;
    VM vm;
    Machine machine;
    Instructions (RM rm, VM vm) {
        this.rm = rm;
        this.vm = vm;
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

    private boolean check_MODE() {
        if (rm.getRegister("MODE").getContentStr() == "U") {
            return false;
        } else {
            return true;
        }
    }

    public void check_machine_mode() {
        if (check_MODE()) {
            machine = rm;
        } else {
            machine = vm;
        }
    }

    public void addition(int address) throws Exception {
        Register r =  machine.getRegister("R");
        r.setContent(IntToHex(machine.getData().getBlockInt(address) + r.getContentInt(), r.getSize()));
    }

    public void substitution(int address) throws Exception {
        Register r =  machine.getRegister("R");
        r.setContent(IntToHex(r.getContentInt() - machine.getData().getBlockInt(address), r.getSize()));
    }

    public void save_in_external_memory(int address) throws Exception{
        if (check_MODE()) {
            machine.getRegister("CH3").setContent(1); // chanel 1 occupied (External memory)
            rm.getExternal().put_block(address, machine.getRegister("R").getContent()); // if throws here interrupt need to set CH3 register to 0
            machine.getRegister("CH3").setContent(0); // chanel 0 released
        } else {
//            in user mode, so need to save all registers, and then throw interupt and change to Supervision mode
            throw new WriteToMemory("Can't do save in external memory in user mode");
        }
    }

    public void load_from_external_memory(int address) throws Exception {
        if (check_MODE()) {
            machine.getRegister("CH3").setContent(1); // chanel 1 occupied (External memory)
            machine.getRegister("R").setContent(rm.getExternal().getBlock(address)); // if throws here interrupt need to set CH3 register to 0
            machine.getRegister("CH3").setContent(0); // chanel 0 released
        } else {
            // in user mode, so need to save all registers, and then throw interupt and change to Supervision mode
            throw new LoadFromMemory("Can't do load from external memory in user mode");
        }
    }


}
