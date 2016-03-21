package com.company;

import Interrupts.Interrupt;
import Interrupts.SupervisorInterrupt;

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
        setData(new VirtualMemory(rm.getData().getBlock(), blocks, ptr, data));
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
            while (!instructions.check_MODE() && ti.getContentInt() > 0) {
                System.out.println(ic.getName() + " " + ic.getContentInt());
                command = getData().getBlock(ic.getContentInt());
                instructions.interpreter(command);
                ic.inc(1);
                ti.inc(-1);
            }
        } catch (SupervisorInterrupt s) {
            try {
                // TODO: 2016-03-21 interrupt system
                instructions.change_mode(); // change to S mode
                command = getData().getBlock(ic.getContentInt());
                instructions.interpreter(command);
                // save registers in RM do instruction, and come back to vm mode
                // takes [PTR] + IC
                instructions.change_mode(); // change back to U mode
                this.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
