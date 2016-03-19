package com.company;

/**
 * Created by Vik on 3/12/2016.
 */
public class RM extends Machine {

    VM vm;
    Memory external;
    Instructions instructions;

    private void init(int vm_blocks, int external_blocks) {
        registers.addRegister(new Register("PTR", 4));
        registers.addRegister(new Register("B", 2));
        registers.addRegister(new Register("IC", 2, "00"));
        registers.addRegister(new Register("C", 1, "0"));
        registers.addRegister(new Register("R", 4));
        registers.addRegister(new Register("PI", 1, "0"));
        registers.addRegister(new Register("SI", 1, "0"));
        registers.addRegister(new Register("TI", 2, "30"));
        registers.addRegister(new Register("CH1", 1, "0"));
        registers.addRegister(new Register("CH2", 1, "0"));
        registers.addRegister(new Register("CH3", 1, "0"));
        registers.addRegister(new Register("MODE", 1, "S"));
        vm = new VM(vm_blocks, this);
        external = new Memory(external_blocks);
        instructions = new Instructions(this, vm);
        vm.setInstructions(instructions);
    }

    RM(int rm_blocks, int vm_blocks, int external_blocks) {
        super(rm_blocks);
        init(vm_blocks, external_blocks);
    }

    public Memory getExternal() {
        return external;
    }

    @Override
    boolean run() {
        return false;
    }
}
