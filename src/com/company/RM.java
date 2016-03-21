package com.company;

/**
 * Created by Vik on 3/12/2016.
 */
public class RM extends Machine {

    VM vm;
    Memory external;
    Instructions instructions;
    Object input;
    Object output;

    private void init(int vm_blocks, int external_blocks) {
        registers.addRegister(new Register("PTR", 4));
        registers.addRegister(new Register("B", 3));
        registers.addRegister(new Register("IC", 3, "000"));
        registers.addRegister(new Register("C", 1, "0"));
        registers.addRegister(new Register("R", 5));
        registers.addRegister(new Register("PI", 1, "0"));
        registers.addRegister(new Register("SI", 1, "0"));
        registers.addRegister(new Register("TI", 2, "30"));
        registers.addRegister(new Register("CH1", 1, "0"));
        registers.addRegister(new Register("CH2", 1, "0"));
        registers.addRegister(new Register("CH3", 1, "0"));
        registers.addRegister(new Register("MODE", 1, "S"));
        vm = new VM(vm_blocks, this, 0);
        external = new Memory(external_blocks);
        instructions = new Instructions(this, vm);
        vm.setInstructions(instructions);
    }

    RM(int rm_blocks, int vm_blocks, int external_blocks) {
        super(rm_blocks);
        init(vm_blocks, external_blocks);
    }

    public Instructions getInstructions() {
        return instructions;
    }

    public Memory getExternal() {
        return external;
    }

    @Override
    void run() {
        instructions.check_machine_mode();
        Register ic = getRegister("IC"), ti = getRegister("TI");
        String command;
        try {
            while (instructions.check_MODE() && ti.getContentInt() > 0) {
                System.out.println(ic.getName() + " " + ic.getContentInt());
                command = new String(getData().getBlock(ic.getContentInt()));
                instructions.interpreter(command);
                ic.inc(1);
                ti.inc(-1);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
