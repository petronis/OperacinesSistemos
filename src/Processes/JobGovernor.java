package Processes;

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
        if (resourcePlaner.findResource("Iš Interupt").getMessage() != this.getProcessName()) {
            if (firstTime) {
                resourcePlaner.findResource("Pakrovimo paketas").setFree(true);
                System.out.println("JobGovernor needs Loader complete resource");
                firstTime = false;
                this.changeState(3);
                processPlaner.RemovingProcessesFromList(this);
                processPlaner.AddingProcessesToWaitingList(this, 2);
                processPlaner.IsThereAnyReadyProcess();
            }
            this.ProcessNeedsResource(resourcePlaner.findResource("Loader complete"));
            this.ProcessNeedsResource(resourcePlaner.findResource("Supervizorinės atminties"));
            System.out.println("Job Governor is working now");
            if (this.ProcessHasAllResource(this)) {
                virtualMachine = new VirtualMachine("VirtualMachine", 3, this, resourcePlaner);
                this.changeState(3);
                this.releaseAllResource();
                this.ProcessNeedsResource(resourcePlaner.findResource("Vartotojo atmintis"));
                processPlaner.RemovingProcessesFromList(this);
                processPlaner.AddingProcessesToWaitingList(this);
                processPlaner.AddingProcessesToWaitingList(virtualMachine, 1);
                firstTime = true;
                processPlaner.IsThereAnyReadyProcess();
            }
        } else {
            System.out.println("JobGovernor after interrupt");
            if (resourcePlaner.findResource("Interrupt").getMessage().equals("SI")) {
                if (getRm().getRegister("SI").getContentInt() == 8) {
                    this.releaseAllResource();
                    resourcePlaner.findResource("OS darbo pabaiga").setFree(true);
                    this.changeState(3);
                    processPlaner.RemovingProcessesFromList(this);
                    processPlaner.IsThereAnyReadyProcess();
                } else if(getRm().getRegister("SI").getContentInt() == 7){
                    // TODO: 2016-05-30 JOB GOVERNOR SUNAIKINIMAS
                    System.out.println("LukoProcessPlannerSUCKS!!");
                    this.changeState(3);
                    processPlaner.RemovingProcessesFromList(this);
                    processPlaner.IsThereAnyReadyProcess();
                }
                else {
                    try {
                        getRm().iterate();
                        virtualMachine.work(processPlaner);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                processPlaner.RemovingProcessesFromList(this);
            }
        }
    }
}
