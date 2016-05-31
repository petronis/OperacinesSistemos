package com.company;

import Exceptions.WrongContentSize;
import Interrupts.*;

/**
 * Created by Vik on 3/12/2016.
 */
public class VM extends Machine {

    RM rm;
    Instructions instructions;
    int ptr;

    private void init() {
        registers.addRegister(new Register("IC", 3, "000"));
        registers.addRegister(new Register("C", 1, "0"));
        registers.addRegister(new Register("R", 5, "00000"));
        registers.addRegister(new Register("B", 2, "00"));
    }

    VM(int blocks, RM rm, Register ptr) {
        super(blocks);
        setData(new VirtualMemory(rm.getData().getBlock(), blocks, ptr, rm.getData()));
        this.ptr = ptr.getContentInt();
        this.rm = rm;
        init();
    }

    public void saveReg() throws Exception {
        rm.getRegister("C").setContent(getRegister("C").getContentInt());
        rm.getRegister("R").setContent(getRegister("R").getContentInt());
        rm.getRegister("B").setContent(getRegister("B").getContentInt());
        int i = rm.getData().getBlockInt(ptr) + getRegister("IC").getContentInt();
        rm.getRegister("IC").setContent(i);
    }

    public void loadReg() throws Exception {
        this.getRegister("C").setContent(rm.getRegister("C").getContentInt());
        this.getRegister("R").setContent(rm.getRegister("R").getContentInt());
        this.getRegister("B").setContent(rm.getRegister("B").getContentInt());
        if (rm.getData().getBlockInt(ptr) != 0){
            this.getRegister("IC").setContent(rm.getData().getBlockInt(ptr) - rm.getRegister("IC").getContentInt());
        } else{
            this.getRegister("IC").setContent(rm.getRegister("IC").getContentInt());
        }
    }

    public Instructions getInstructions() {
        return instructions;
    }

    public void setInstructions(Instructions instructions) {
        this.instructions = instructions;
    }

    public void rerun() throws Exception{
        loadReg();
        run();
    }

    @Override
    public void run() throws Exception {
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
                System.out.println("EXPT: "+e.getName());
        } catch (BadOperationPlan e){
                rm.getRegister("PI").setContent(2);
                System.out.println("EXPT: "+e.getName());
        } catch (AssignError e){
            rm.getRegister("PI").setContent(3);
            System.out.println("EXPT: "+e.getName());
        } catch (InsufficientSpace e){
                rm.getRegister("PI").setContent(5);
                System.out.println("EXPT: "+e.getName());
        } catch (Output e){
            rm.getRegister("SI").setContent(1);
            System.out.println("EXPT: "+e.getName());
        } catch (Input e){
            rm.getRegister("SI").setContent(2);
            System.out.println("EXPT: "+e.getName());
        } catch (WriteToMemory e){
            rm.getRegister("SI").setContent(3);
            System.out.println("EXPT: "+e.getName());
        } catch (LoadFromMemory e){
            rm.getRegister("SI").setContent(4);
            System.out.println("EXPT: "+e.getName());
        } catch (SetTimer e){
            rm.getRegister("SI").setContent(5);
            System.out.println("EXPT: "+e.getName());
        } catch (SetPTR e){
            rm.getRegister("SI").setContent(6);
            System.out.println("EXPT: "+e.getName());
        } catch (Halt e){
            rm.getRegister("SI").setContent(7);
            System.out.println("EXPT: "+e.getName());
        } catch (ShutDown e){
            rm.getRegister("SI").setContent(8);
            System.out.println("EXPT: "+e.getName());
        } catch (StopProcess e){
            rm.getRegister("SI").setContent(7);
            System.out.println("EXPT: "+e.getName());
        }

        catch (Exception exception) {
            exception.printStackTrace();
        }
        saveReg();
    }
}
