package Processes;

import com.company.Process;
import com.company.ProcessPlaner;
import com.company.ResourcePlaner;

/**
 * Created by lukas on 2016-05-22.
 */
public class PrintLine extends Process {
    ResourcePlaner resourcePlaner;
    public PrintLine(String name, int state, Process father, ResourcePlaner resourcePlaner) {
        super(name, state, father,resourcePlaner);
        this.resourcePlaner = resourcePlaner;
//        this.ProcessNeedsResource(resourcePlaner.findResource("Užduotis supervizorinėje atminyje"));
    }



    public void work(ProcessPlaner processPlaner){
        while(true) {
            if (this.ProcessHasAllResource(this)) {
                // TODO: 2016-05-23 I pranesimo lauka pasiunciame Supervizorineje atmintyje esanti pranesima
            }
        }
    }
}
