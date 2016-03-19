package com.company;

import Exceptions.Error;
import Interrupts.Halt;
import Interrupts.LoadFromMemory;
import Interrupts.SetTimer;
import Interrupts.WriteToMemory;

import java.util.Date;

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

    public void reset_R_register() throws  Exception {
        machine.getRegister("R").setContent("0000");
    }

    public void set_timer(int time) throws Exception {
        if (check_MODE()) {
            machine.getRegister("TI").setContent(time);
        } else {
            // in user mode, so need to save all registers, and then throw interupt and change to Supervision mode
            throw new SetTimer("Can't do set timer in user mode");
        }
    }

    public void load_chanel1() throws Exception {
        machine.getRegister("R").setContent(rm.getRegister("CH1").getContentStr());
    }

    public void load_chanel2() throws Exception {
        machine.getRegister("R").setContent(rm.getRegister("CH2").getContentStr());
    }

    public void load_chanel3() throws Exception {
        machine.getRegister("R").setContent(rm.getRegister("CH3").getContentStr());
    }

    public void load_IC() throws Exception {
        machine.getRegister("R").setContent(rm.getRegister("IC").getContentStr());
    }

    public void load_R_from_memory(int address) throws Exception {
        machine.getRegister("R").setContent(machine.getData().getBlock(address));
    }

    public void load_B_from_memory(int address) throws Exception {
        machine.getRegister("B").setContent(machine.getData().getBlock(address));
    }

    public void save_in_memory_from_R(int address) throws Exception {
        machine.getData().put_block(address, machine.getRegister("R").getContent());
    }

    public void compare(int address) throws Exception {
        if (machine.getData().getBlock(address) == machine.getRegister("R").getContentStr()) {
            machine.getRegister("C").setContent("1");
        } else {
            machine.getRegister("C").setContent("0");
        }
    }

    public void function_call(int address) throws Exception { // function call
        if (machine.getRegister("C").getContentStr() == "1") {
            Register b = machine.getRegister("B"), ic = machine.getRegister("IC");
            Memory data = machine.getData();
            b.inc(1);
            ic.inc(1);
            data.put_block(b.getContentInt(), ic.getContent());
            ic.setContent(address);
        }
    }

    public void function_return() throws Exception {
        Register b = machine.getRegister("B"), ic = machine.getRegister("IC");
        ic.setContent(b.getContentInt());
        b.inc(-1);
    }

    public void set_C_0() throws Exception {
        machine.getRegister("C").setContent(0);
    }

    public void invert_C() throws Exception {
        Register c = machine.getRegister("C");
        if (c.getContentInt() == 1) {
            c.setContent(0);
        } else {
            set_C_0();
        }
    }

    public void compare_negative() throws Exception {
        Register r = machine.getRegister("R");
        if (r.getContentInt() < (int)Math.pow(2, r.getSize() * 4 - 1 )) {
            machine.getRegister("C").setContent(1);
        } else {
            set_C_0();
        }
    }

    public void halt() throws Halt {
        throw new Halt("Stop process");
    }

}
