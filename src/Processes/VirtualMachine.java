package Processes;

import com.company.Process;
import com.company.ProcessPlaner;
import com.company.ResourcePlaner;

/**
 * Created by Vik on 5/29/2016.
 */
public class VirtualMachine extends Process {
    public VirtualMachine(String name, int state, Process father, ResourcePlaner resourcePlaner) {
        super(name, state, father, resourcePlaner);
    }

    @Override
    public void work(ProcessPlaner processPlaner) {
        try {
            getRm().getInstructions().change_mode();
            getRm().run();
            getResourcePlaner().freeResource("Pertraukimas");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
