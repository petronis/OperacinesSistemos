package Processes;

import com.company.Process;
import com.company.ProcessPlaner;
import com.company.ResourcePlaner;

/**
 * Created by lukas on 2016-05-23.
 */
public class Interrupt extends Process {
    ResourcePlaner resourcePlaner;
    public Interrupt(String name, int state, Process father, ResourcePlaner resourcePlaner) {
        super(name, state, father);
        this.resourcePlaner = resourcePlaner;
        this.ProcessNeedsResource(resourcePlaner.findResource("Pertraukimas"));
    }

    @Override
    public void run() {
        while(true){
            if (this.ProcessHasAllResource()){
                // TODO: 2016-05-23 Nustatyti pertraukima
            }
        }
    }

    @Override
    public void work(ProcessPlaner processPlaner) {

    }
}
