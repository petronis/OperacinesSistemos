package Processes;

import com.company.Process;
import com.company.ProcessPlaner;
import com.company.Resource;
import com.company.ResourcePlaner;

/**
 * Created by lukas on 2016-05-22.
 */
public class ReadFromInterface extends StartStop {
    public ReadFromInterface(String name, int state, Process father, ResourcePlaner resourcePlaner) {
        super(name, state, father,resourcePlaner);
        this.ProcessNeedsResource(resourcePlaner.findResource("InputOutput"));
    }

    public void work(ProcessPlaner processPlaner){
        if (this.ProcessHasAllResource()){

        }
    }
}
