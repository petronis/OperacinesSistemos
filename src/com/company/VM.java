package com.company;

import Exceptions.WrongContentSize;
import Interrupts.*;

/**
 * Created by Vik on 3/12/2016.
 */
public class VM extends Machine {

    RM rm;
    Instructions instructions;

    private void init() {
        registers.addRegister(new Register("IC", 3, "000"));
        registers.addRegister(new Register("C", 1, "0"));
        registers.addRegister(new Register("R", 5, "00000"));
        registers.addRegister(new Register("B", 2, "00"));
    }

    VM(int blocks, RM rm, Register ptr) {
        super(blocks);
        setData(new VirtualMemory(rm.getData().getBlock(), blocks, ptr, rm.getData()));
        this.rm = rm;
        init();
    }

    public void saveReg() throws Exception {
        rm.getRegister("C").setContent(getRegister("C").getContentInt());
        rm.getRegister("R").setContent(getRegister("R").getContentInt());
        rm.getRegister("B").setContent(getRegister("B").getContentInt());
        rm.getRegister("IC").setContent(rm.getData().getBlockInt(rm.getRegister("PTR").getContentInt()) + getRegister("IC").getContentInt());
    }

    public void loadReg() throws Exception {
        this.getRegister("C").setContent(rm.getRegister("C").getContentInt());
        this.getRegister("R").setContent(rm.getRegister("R").getContentInt());
        this.getRegister("B").setContent(rm.getRegister("B").getContentInt());
        this.getRegister("IC").setContent(rm.getData().getBlockInt(rm.getRegister("IC").getContentInt() - rm.getRegister("PTR").getContentInt()));

    }

    public void setInstructions(Instructions instructions) {
        this.instructions = instructions;
    }

    public void rerun() throws Exception{
        loadReg();
        run();
    }

    @Override
    void run() throws Exception {
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
        } catch (WrongAddress e){
                rm.getRegister("PI").setContent(1);
                e.printStackTrace();
        } catch (BadOperationPlan e){
                rm.getRegister("PI").setContent(2);
                e.printStackTrace();
        } catch (AssignError e){
            rm.getRegister("PI").setContent(3);
            e.printStackTrace();
        } catch (InsufficientSpace e){
                rm.getRegister("PI").setContent(5);
                e.printStackTrace();
        } catch (Output e){
            rm.getRegister("SI").setContent(1);
            e.printStackTrace();
        } catch (Input e){
            rm.getRegister("SI").setContent(2);
            e.printStackTrace();
        } catch (WriteToMemory e){
            rm.getRegister("SI").setContent(3);
            e.printStackTrace();
        } catch (LoadFromMemory e){
            rm.getRegister("SI").setContent(4);
            e.printStackTrace();
        } catch (SetTimer e){
            rm.getRegister("SI").setContent(5);
            e.printStackTrace();
        } catch (SetPTR e){
            rm.getRegister("SI").setContent(6);
            e.printStackTrace();
        } catch (Halt e){
            rm.getRegister("SI").setContent(7);
            e.printStackTrace();
        } catch (ShutDown e){
            rm.getRegister("SI").setContent(8);
            e.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        saveReg();
    }
}
