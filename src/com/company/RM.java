package com.company;

import Exceptions.WrongContentSize;

/**
 * Created by Vik on 3/12/2016.
 */
public class RM extends Machine {

    VM vm;
    Memory external, supervision;
    Instructions instructions;
    Object input;
    Object output;
    boolean needToChangeMyMODE;

    private void init(int vm_blocks, int external_blocks, int supervision_blocks) {
        registers.addRegister(new Register("PTR", 3, "090"));
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
//        vm = new VM(vm_blocks, this, getRegister("PTR"));
        external = new Memory(external_blocks);
        supervision = new Memory(supervision_blocks);
        instructions = new Instructions(this, vm);
    }

    RM(int rm_blocks, int vm_blocks, int external_blocks, int supervision_blocks) {
        super(rm_blocks);
        init(vm_blocks, external_blocks, supervision_blocks);
        this.needToChangeMyMODE = false;
    }

    public void setNeedToChangeMyMODE(boolean needToChangeMyMODE) {
        this.needToChangeMyMODE = needToChangeMyMODE;
    }

    public boolean getNeedToChangeMyMODE() {
        return needToChangeMyMODE;
    }

    public VM getVm() {
        return vm;
    }

    public Instructions getInstructions() {
        return instructions;
    }

    public Memory getExternal() {
        return external;
    }

    public Memory getSupervision() {
        return supervision;
    }

    @Override
    public void run() {
        System.out.println("IC VALUE __________ " +getRegister("IC").getContentInt());
        if (getNeedToChangeMyMODE()){
            try {
                instructions.change_mode();
                setNeedToChangeMyMODE(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        instructions.check_machine_mode();
        Register ic = getRegister("IC"), ti = getRegister("TI");
        String command;
        if(!instructions.check_MODE()) {
            try {
                vm.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                instructions.check_machine_mode();
                System.out.println("Supervisor");
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

    public void resetI()  {
        try {
            getRegister("SI").setContent(0);
            getRegister("PI").setContent(0);
            getRegister("TI").setContent(30);
        } catch (WrongContentSize wrongContentSize) {
            wrongContentSize.printStackTrace();
        }
    }

    public void iterate() throws Exception{
        Register ic = getRegister("IC"), ti = getRegister("TI");
        String command;
        if (this.getRegister("MODE").getContentStr().contentEquals("U")) {
            this.getInstructions().change_mode();
        }
        instructions.check_machine_mode();
        System.out.println("Supervisor iter");
        System.out.println(ic.getName() + " " + ic.getContentInt());
        command = new String(getData().getBlock(ic.getContentInt()));
        instructions.interpreter(command);
        ic.inc(1);
        ti.inc(-1);
    }

    public VM createVm(){
        try {
            vm = new VM(100, this, getRegister("PTR"));
            vm.setInstructions(instructions);
            Register ptr = this.getRegister("PTR");
            ptr.inc(1);
            String dataSize = new Integer(vm.getData().getSize()).toString();
            int size = dataSize.length();
            for (int i = 0; i < 5 - size; i++) {
                dataSize = "0" + dataSize;
            }
            this.data.put_block(ptr.getContentInt(), dataSize);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return vm;
    }
}
