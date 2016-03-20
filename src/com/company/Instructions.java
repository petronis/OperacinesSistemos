package com.company;

import Exceptions.Error;
import Interrupts.*;

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

    public void interpreter(String command) throws Exception {
        int address;
        if (command.substring(0,2).contentEquals("AD")) {
            address = new Integer(command.substring(2, command.length()));
            addition(address);
        } else if (command.substring(0,2).contentEquals("SB")) {
            address = new Integer(command.substring(2, command.length()));
            substitution(address);
        } else if (command.substring(0,2).contentEquals("SV")) {
            address = new Integer(command.substring(2, command.length()));
            save_in_external_memory(address);
        } else if (command.substring(0,2).contentEquals("LD")) {
            address = new Integer(command.substring(2, command.length()));
            load_from_external_memory(address);
        } else if (command.substring(0,5).contentEquals("RESTR")) {
            reset_R_register();
        } else if (command.substring(0,3).contentEquals("STI")) {
            address = new Integer(command.substring(3, command.length()));
            set_timer(address);
        } else if (command.substring(0,5).contentEquals("LRCH1")) {
            load_chanel1();
        } else if (command.substring(0,5).contentEquals("LRCH2")) {
            load_chanel2();
        } else if (command.substring(0,5).contentEquals("LRCH3")) {
            load_chanel3();
        } else if (command.substring(0,5).contentEquals("LTRIC")) {
            load_IC();
        } else if (command.substring(0,2).contentEquals("LR")) {
            address = new Integer(command.substring(2, command.length()));
            load_R_from_memory(address);
        } else if (command.substring(0,2).contentEquals("LB")) {
            address = new Integer(command.substring(2, command.length()));
            load_B_from_memory(address);
        } else if (command.substring(0,2).contentEquals("SR")) {
            address = new Integer(command.substring(2, command.length()));
            save_in_memory_from_R(address);
        } else if (command.substring(0,2).contentEquals("CR")) {
            address = new Integer(command.substring(2, command.length()));
            compare(address);
        } else if (command.substring(0,2).contentEquals("RT")) {
            address = new Integer(command.substring(2, command.length()));
            function_call(address);
        } else if (command.substring(0,5).contentEquals("RETRN")) {
            function_return();
        } else if (command.substring(0,5).contentEquals("SETC0")) {
            set_C_0();
        } else if (command.substring(0,5).contentEquals("INVRC")) {
            invert_C();
        } else if (command.substring(0,5).contentEquals("HALTP")) {
            halt();
        }  else if (command.substring(0,5).contentEquals("CMODE")) {
            change_mode();
        } else {
            throw new BadOperationPlan("no such operation");
        }
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

    public boolean check_MODE() {
        if (rm.getRegister("MODE").getContentStr().contentEquals("U")) {
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
        String tmp = new Integer(machine.getData().getBlockInt(address) + r.getContentInt()).toString();
        for (int i = tmp.length(); i < r.size; i++) {
            tmp = "0" + tmp;
        }
        r.setContent(tmp);
    }

    public void substitution(int address) throws Exception {
        Register r =  machine.getRegister("R");
        String tmp = new Integer(r.getContentInt() - machine.getData().getBlockInt(address)).toString();
        for (int i = tmp.length(); i < r.size; i++) {
            tmp = "0" + tmp;
        }
        r.setContent(tmp);
    }

    public void save_in_external_memory(int address) throws Exception{
        if (check_MODE()) {
            machine.getRegister("CH3").setContent(1); // chanel 1 occupied (External memory)
            // wtf?
            rm.getExternal().put_block(address, machine.getRegister("R").getContentStr()); // if throws here interrupt need to set CH3 register to 0
            machine.getRegister("CH3").setContent(0); // chanel 0 released
        } else {
//            in user mode, so need to save all registers, and then throw interrupt and change to Supervision mode
            throw new WriteToMemory("Can't do save in external memory in user mode");
        }
    }

    public void load_from_external_memory(int address) throws Exception {
        if (check_MODE()) {
            machine.getRegister("CH3").setContent(1); // chanel 1 occupied (External memory)
            machine.getRegister("R").setContent(rm.getExternal().getBlock(address)); // if throws here interrupt need to set CH3 register to 0
            machine.getRegister("CH3").setContent(0); // chanel 0 released
        } else {
            // in user mode, so need to save all registers, and then throw interrupt and change to Supervision mode
            throw new LoadFromMemory("Can't do load from external memory in user mode");
        }
    }

    public void reset_R_register() throws  Exception {
        machine.getRegister("R").setContent("00000");
    }

    public void set_timer(int time) throws Exception {
        if (check_MODE()) {
            machine.getRegister("TI").setContent(time);
        } else {
            // in user mode, so need to save all registers, and then throw interrupt and change to Supervision mode
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
        machine.getRegister("B").setContent(machine.getData().getBlockInt(address));
    }

    public void save_in_memory_from_R(int address) throws Exception {
        machine.getData().put_block(address, machine.getRegister("R").getContentStr());
    }

    public void compare(int address) throws Exception {
        if (machine.getData().getBlock(address).contentEquals(machine.getRegister("R").getContentStr())) {
            machine.getRegister("C").setContent("1");
        } else {
            machine.getRegister("C").setContent("0");
        }
    }

    public void function_call(int address) throws Exception { // function call
        if (machine.getRegister("C").getContentStr().contentEquals("1")) {
            Register b = machine.getRegister("B"), ic = machine.getRegister("IC");
            Memory data = machine.getData();
            ic.inc(1);
            b.inc(1);
            data.put_block(b.getContentInt(), "00" + ic.getContentStr());
            ic.setContent(address);
            ic.inc(-1);
        }
    }

    public void function_return() throws Exception {
        Register b = machine.getRegister("B"), ic = machine.getRegister("IC");
        ic.setContent(machine.getData().getBlockInt(b.getContentInt()));
        b.inc(-1);
        ic.inc(-1);
    }

    public void set_C_0() throws Exception {
        machine.getRegister("C").setContent(0);
    }

    public void invert_C() throws Exception {
        Register c = machine.getRegister("C");
        if (c.getContentInt() == 1) {
            set_C_0();
        } else {
            c.setContent(1);
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

    public void change_mode() throws Exception {
        Register mode = rm.getRegister("MODE");
        if (check_MODE()) {
            mode.setContent("U");
        } else {
            mode.setContent("S");
        }
    }

    public void halt() throws Exception {
        throw new Halt("Stop process");
    }

    public void read_from_input(int address) throws Exception {
        if (check_MODE()) {
            throw new Error("Input can't work in User MODE");
        } else {
            machine.getRegister("CH1").setContent(1);
            Memory data = rm.getData();
            data.put_block(address, rm.input.toString());
            machine.getRegister("CH1").setContent(0);
        }
    }

    public void write_to_output() throws Exception {
        boolean modeCheck = check_MODE();
        if (modeCheck) {
            throw new Error("Output can't work in User MODE");
        } else {

        }
    }

}
