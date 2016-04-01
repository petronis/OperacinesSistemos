package com.company;

import Interrupts.SupervisorInterrupt;
import Interrupts.TimerEnd;

/**
 * Created by Vik on 3/12/2016.
 */
public class VM extends Machine {

    RM rm;
    Instructions instructions;

    private void init() {
        registers.addRegister(new Register("IC", 3, "000"));
        registers.addRegister(new Register("C", 1, "0"));
        registers.addRegister(new Register("R", 5));
        registers.addRegister(new Register("B", 2));
    }

    VM(int blocks, RM rm, Register ptr) {
        super(blocks);
        setData(new VirtualMemory(rm.getData().getBlock(), blocks, ptr, rm.getData()));
        this.rm = rm;
        init();
    }

    public void setInstructions(Instructions instructions) {
        this.instructions = instructions;
    }

    @Override
    void run()  {
        instructions.check_machine_mode();
        Register ic = getRegister("IC"), ti = rm.getRegister("TI");
        String command;
        try {
            System.out.println("User");
            while (!instructions.check_MODE() && ti.getContentInt() > 0) {
                System.out.println(ic.getName() + " " + ic.getContentInt());
                System.out.println(ti.getName() + " " + ti.getContentInt());
                System.out.println("gets: " + getData().getBlock(ic.getContentInt()));
                command = getData().getBlock(ic.getContentInt());
                instructions.interpreter(command);
                ic.inc(1);
                ti.inc(-1);
                if (ti.getContentInt() == 0) {
                    throw new TimerEnd("TI is 0");
                }
            }
    }catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
