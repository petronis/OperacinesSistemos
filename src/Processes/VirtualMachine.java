package Processes;

import com.company.Process;
import com.company.ProcessPlaner;
import com.company.ResourcePlaner;

/**
 * Created by Vik on 5/29/2016.
 */
public class VirtualMachine extends Process {
    ResourcePlaner resourcePlaner;
    public VirtualMachine(String name, int state, Process father, ResourcePlaner resourcePlaner) {
        super(name, state, father, resourcePlaner);
        this.resourcePlaner = resourcePlaner;
    }

    @Override
    public void work(ProcessPlaner processPlaner) {
        System.out.println("VirtualMachine work");
        try {
            getRm().getInstructions().change_mode();
            getRm().run();
            getResourcePlaner().freeResource("Pertraukimas");
            System.out.println("Fathers name " + getFather().getProcessName());
            String msg = getFather().getProcessName();
            resourcePlaner.findResource("Pertraukimas").setMessage(msg);
            processPlaner.RemovingProcessesFromList(this);
            processPlaner.IsThereAnyReadyProcess();
        } catch (Exception e) {
            e.printStackTrace();
        }
        processPlaner.RemovingProcessesFromList(this);
    }
}
