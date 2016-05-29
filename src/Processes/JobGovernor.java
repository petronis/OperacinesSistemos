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
            resourcePlaner.findResource("Pakrovimo paketas").setFree(true);
            if (firstTime) {
                System.out.println("first time in JobGovernor");
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
                // TODO: 2016-05-23 Create VM
                System.out.println("JobGov has all res");
                virtualMachine = new VirtualMachine("VirtualMachine", 3, this, resourcePlaner);
                this.changeState(3);

                this.releaseAllResource();
                processPlaner.RemovingProcessesFromList(this);
                processPlaner.AddingProcessesToWaitingList(this);
                processPlaner.AddingProcessesToWaitingList(virtualMachine, 1);
                firstTime = true;
                processPlaner.IsThereAnyReadyProcess();


                // TODO: 2016-05-23 Stop VM
                // TODO: 2016-05-23 What Interrupt is it if GD good, if not terminante JobGovernor with VM
            }
        } else {
            if (resourcePlaner.findResource("Interrupt").getMessage().equals("SI")) {
                if (getRm().getRegister("SI").getContentInt() == 8) {
                    resourcePlaner.findResource("OS darbo pabaiga").setFree(true);
                    this.changeState(3);
                    processPlaner.RemovingProcessesFromList(this);
                    processPlaner.IsThereAnyReadyProcess();
                } else {
                }

            } else {
                processPlaner.RemovingProcessesFromList(this);
            }
        }
    }
}
