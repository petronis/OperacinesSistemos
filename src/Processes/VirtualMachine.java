package Processes;

import com.company.Process;
import com.company.ProcessPlaner;
import com.company.ResourcePlaner;

/**
 * Created by Vik on 5/29/2016.
 */
public class VirtualMachine extends Process {
    ResourcePlaner resourcePlaner;
    boolean firsTime = true;
    public VirtualMachine(String name, int state, Process father, ResourcePlaner resourcePlaner) {
        super(name, state, father, resourcePlaner);
        this.resourcePlaner = resourcePlaner;
    }

    @Override
    public void work(ProcessPlaner processPlaner) {
        System.out.println("VirtualMachine is work");
        try {
            getRm().getInstructions().change_mode();
            if (firsTime) {
                firsTime = false;
                getRm().run();
            } else  {
                getRm().getVm().rerun();
            }
            getResourcePlaner().freeResource("Pertraukimas");
            String msg = getFather().getProcessName();
            resourcePlaner.findResource("Pertraukimas").setMessage(msg);
            processPlaner.RemovingProcessesFromList(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        processPlaner.RemovingProcessesFromList(this);
    }
}
