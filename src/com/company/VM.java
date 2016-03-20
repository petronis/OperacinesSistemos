package com.company;

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

    VM(int blocks, RM rm) {
        super(blocks);
        init();
        this.rm = rm;
        init();
    }

    public void setInstructions(Instructions instructions) {
        this.instructions = instructions;
    }

    @Override
    void run() {
        instructions.check_machine_mode();
        Register ic = getRegister("IC");
        try {
            while (!instructions.check_MODE()) {
                String command = new String(getData().getBlock(ic.getContentInt()));
                instructions.interpreter(command);
                ic.inc(1);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
