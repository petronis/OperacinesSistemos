package Processes;

import com.company.Process;
import com.company.ResourcePlaner;

/**
 * Created by lukas on 2016-05-22.
 */
public class JobToDisk extends Process {
    public JobToDisk(String name, int state, Process father, ResourcePlaner resourcePlaner) {
        super(name, state, father);
        this.ProcessNeedsResource(resourcePlaner.findResource("Užduoties duomenys supervizorinėje atmintyje"));
        this.ProcessNeedsResource(resourcePlaner.findResource("Užduoties programa supervizorinėje atmintyje"));
    }
}
