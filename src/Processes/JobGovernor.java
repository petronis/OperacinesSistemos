package Processes;

import Exceptions.WrongContentSize;
import com.company.Process;
import com.company.ProcessPlaner;
import com.company.ResourcePlaner;
import com.sun.corba.se.impl.orbutil.concurrent.Sync;

/**
 * Created by lukas on 2016-05-23.
 */
public class JobGovernor extends Process {
    VirtualMachine virtualMachine;
    ResourcePlaner resourcePlaner;
    boolean firstTime = true;
    public JobGovernor(String name, int state, Process father,ResourcePlaner resourcePlaner) {
        super(name, state, father,resourcePlaner);
        this.resourcePlaner = resourcePlaner;
        this.ProcessNeedsResource(resourcePlaner.findResource("Vartotojo atmintis"));
    }


    @Override
    public void work(ProcessPlaner processPlaner) {
        System.out.println(this.getProcessName() + " is working");
//        System.out.println(resourcePlaner.findResource("Iš Interupt").getMessage()+this.getProcessName());
        if (resourcePlaner.findResource("Iš Interupt").getMessage() == null) {

            System.out.println(getProcessName() + " very first time");
            if (firstTime) {
                resourcePlaner.findResource("Pakrovimo paketas").setFree(true);
                System.out.println("JobGovernor needs Loader complete resource");
                firstTime = false;
                this.changeState(3);
                processPlaner.RemovingProcessesFromList(this);
                processPlaner.AddingProcessesToWaitingList(this);
//                processPlaner.AddingProcessesToWaitingList(this, 2);
                int place = processPlaner.getProcessFromListByName("Loader");
                processPlaner.RemovingProcessesFromList(processPlaner.processesList.get(place));
                processPlaner.AddingProcessesToWaitingList(processPlaner.processesList.get(place),1);
            } else {
                this.ProcessNeedsResource(resourcePlaner.findResource("Loader complete"));
                this.ProcessNeedsResource(resourcePlaner.findResource("Supervizorinės atminties"));
                System.out.println("Job Governor is working now " + this.getProcessName());
                if (this.getProcessName().equals("JobGovernor2"))
                    System.out.println();
                getProcessWantResources().occupy();
//                if (this.ProcessHasAllResource(this)) {
                virtualMachine = new VirtualMachine("VirtualMachine", 3, this, resourcePlaner);
                this.changeState(3);
                this.releaseAllResource();
                this.ProcessNeedsResource(resourcePlaner.findResource("Vartotojo atmintis"));
                processPlaner.RemovingProcessesFromList(this);
                processPlaner.AddingProcessesToWaitingList(this);
                processPlaner.AddingProcessesToWaitingList(virtualMachine, 1);
//                    firstTime = true;
//                }
            }

        }else if (!resourcePlaner.findResource("Iš Interupt").getMessage().contentEquals(this.getProcessName())) {

            System.out.println(getProcessName() + " very first time");
            if (firstTime) {
                resourcePlaner.findResource("Pakrovimo paketas").setFree(true);
                System.out.println("JobGovernor needs Loader complete resource");
                firstTime = false;
                this.changeState(3);
                processPlaner.RemovingProcessesFromList(this);
                processPlaner.AddingProcessesToWaitingList(this);
//                processPlaner.AddingProcessesToWaitingList(this, 2);
                int place = processPlaner.getProcessFromListByName("Loader");
                processPlaner.RemovingProcessesFromList(processPlaner.processesList.get(place));
                processPlaner.AddingProcessesToWaitingList(processPlaner.processesList.get(place),1);
            } else {
                this.ProcessNeedsResource(resourcePlaner.findResource("Loader complete"));
                this.ProcessNeedsResource(resourcePlaner.findResource("Supervizorinės atminties"));
                System.out.println("Job Governor is working now " + this.getProcessName());
                if (this.getProcessName().equals("JobGovernor2"))
                    System.out.println();
                getProcessWantResources().occupy();
//                if (this.ProcessHasAllResource(this)) {
                    virtualMachine = new VirtualMachine("VirtualMachine", 3, this, resourcePlaner);
                    this.changeState(3);
                    this.releaseAllResource();
                    this.ProcessNeedsResource(resourcePlaner.findResource("Vartotojo atmintis"));
                    processPlaner.RemovingProcessesFromList(this);
                    processPlaner.AddingProcessesToWaitingList(this);
                    processPlaner.AddingProcessesToWaitingList(virtualMachine, 1);
//                    firstTime = true;
//                }
            }
        } else {
            System.out.println("JobGovernor after interrupt");
            System.out.println("Press Enter...");
            new java.util.Scanner(System.in).nextLine();
            if (resourcePlaner.findResource("Interrupt").getMessage().equals("SI")) {
                if (getRm().getRegister("SI").getContentInt() == 8) {
                    this.releaseAllResource();
                    resourcePlaner.findResource("OS darbo pabaiga").setFree(true);
//                    System.out.println("Press Enter...");
//                    new java.util.Scanner(System.in).nextLine();
                    this.changeState(3);
                    processPlaner.RemovingProcessesFromList(this);
                } else if(getRm().getRegister("SI").getContentInt() == 7){
                    // TODO: 2016-05-30 JOB GOVERNOR SUNAIKINIMAS
                    resourcePlaner.findResource("Programa parengta").setFree(true);
                    processPlaner.setTimer(1);
//
//                    try {
//                        getRm().getVm().getRegister("IC").setContent(0);
//                    } catch (WrongContentSize wrongContentSize) {
//                        wrongContentSize.printStackTrace();
//                    }
                    getRm().setNeedToChangeMyMODE(true);
                    if (getRm().getInstructions().check_MODE()){
                        try {
                            getRm().getInstructions().change_mode();
                            getRm().getInstructions().check_machine_mode();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    try {
//                        getRm().iterate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    this.changeState(3);
                    processPlaner.RemovingProcessesFromList(this);
                    getRm().setNeedToChangeMyMODE(true);
                }
                else {
                    try {
                        changeState(3);
                        getRm().iterate();
                        virtualMachine.work(processPlaner);
                        processPlaner.AddingProcessesToWaitingList(this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                if (resourcePlaner.findResource("Interrupt").getMessage().equals("TI")) {
                    if (getRm().getRegister("TI").getContentInt() == 0){
                        try {
                            getRm().getRegister("TI").setContent(30);
                        } catch (WrongContentSize wrongContentSize) {
                            wrongContentSize.printStackTrace();
                        }
                        this.changeState(3);
                        processPlaner.RemovingProcessesFromList(this);
                        processPlaner.AddingProcessesToWaitingList(this);
                        resourcePlaner.findResource("Interrupt").getMessage().equals("");
                        resourcePlaner.findResource("Iš Interupt").setMessage("");
                        getRm().setNeedToChangeMyMODE(true);
                    }
                } else {
                    this.changeState(3);
                    processPlaner.RemovingProcessesFromList(this);
                }
            }
        }
    }
}
