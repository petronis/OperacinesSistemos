package Processes;

import com.company.Process;
import com.company.ResourcePlaner;

/**
 * Created by lukas on 2016-05-22.
 */
public class Cheker extends Process {
    public Cheker(String name, int state, Process father, ResourcePlaner resourcePlaner) {
        super(name, state, father);
        this.ProcessNeedsResource(resourcePlaner.findResource("Užduotis supervizorinėje atminyje"));
    }
}
