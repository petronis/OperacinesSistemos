package Processes;

import com.company.Process;
import com.company.ProcessPlaner;
import com.company.ResourcePlaner;

/**
 * Created by lukas on 2016-05-22.
 */
public class PrintLine extends Process {
    public PrintLine(String name, int state, Process father, ResourcePlaner resourcePlaner) {
        super(name, state, father);
//        this.ProcessNeedsResource(resourcePlaner.findResource("Užduotis supervizorinėje atminyje"));
    }


    @Override
    public void run() {
        while(true) {
            if (this.ProcessHasAllResource()) {
                // TODO: 2016-05-23 I pranesimo lauka pasiunciame Supervizorineje atmintyje esanti pranesima
            }
        }
    }

    public void work(ProcessPlaner processPlaner){

    }
}
