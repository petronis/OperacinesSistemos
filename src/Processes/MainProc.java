package Processes;

import com.company.Process;
import com.company.ProcessPlaner;
import com.company.Resource;
import com.company.ResourcePlaner;

/**
 * Created by lukas on 2016-05-22.
 */
public class MainProc extends Process {
    ResourcePlaner resourcePlaner;
    public MainProc(String name, int state, Process father, ResourcePlaner resourcePlaner) {
        super(name, state, father,resourcePlaner);
        this.resourcePlaner = resourcePlaner;
        this.ProcessNeedsResource(resourcePlaner.findResource("Programa parengta"));
    }

    public void work(ProcessPlaner processPlaner){
        while(true) {
            if (this.ProcessHasAllResource(this)) {
                // TODO: 2016-05-23 Sukurti arba sunaikinti JobGovernor
            }
        }
    }
}
