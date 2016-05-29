package Processes;

import com.company.Process;
import com.company.ProcessPlaner;
import com.company.ResourcePlaner;
import com.sun.corba.se.impl.orbutil.concurrent.Sync;

/**
 * Created by lukas on 2016-05-23.
 */
public class JobGovernor extends Process {

    ResourcePlaner resourcePlaner;
    public JobGovernor(String name, int state, Process father,ResourcePlaner resourcePlaner) {
        super(name, state, father,resourcePlaner);
        this.resourcePlaner = resourcePlaner;
        this.ProcessNeedsResource(resourcePlaner.findResource("Vartotojo atmintis"));
        this.ProcessNeedsResource(resourcePlaner.findResource("Pakrovimo paketas"));
    }


    @Override
    public void work(ProcessPlaner processPlaner) {
        VirtualMachine virtualMachine;
        this.ProcessNeedsResource(resourcePlaner.findResource("Loader complete"));
        this.ProcessNeedsResource(resourcePlaner.findResource("Supervizorinės atminties"));
        System.out.println("Job Governor is working now");
        while(true){
            if(this.ProcessHasAllResource(this)){
                // TODO: 2016-05-23 Create VM
                virtualMachine = new VirtualMachine("VirtualMachine", 2, this, resourcePlaner);
                processPlaner.AddingProcessesToWaitingList(virtualMachine);
//                this.ProcessNeedsResource(resourcePlaner.findResource("Interrupt"));
                // TODO: 2016-05-23 Stop VM
                // TODO: 2016-05-23 What Interrupt is it if GD good, if not terminante JobGovernor with VM
            }
        }
    }
}
