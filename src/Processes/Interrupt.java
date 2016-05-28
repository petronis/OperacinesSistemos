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
        super(name, state, father, resourcePlaner);
        this.resourcePlaner = resourcePlaner;
        this.ProcessNeedsResource(resourcePlaner.findResource("Pertraukimas"));
    }

    @Override
    public void work(ProcessPlaner processPlaner) {
        while(true){
            if (this.ProcessHasAllResource(this)){
                // TODO: 2016-05-23 Nustatyti pertraukima
            }
        }
    }
}
