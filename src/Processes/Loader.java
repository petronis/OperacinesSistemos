package Processes;

import com.company.Process;
import com.company.ResourcePlaner;

/**
 * Created by lukas on 2016-05-22.
 */
public class Loader extends Process {
    public Loader(String name, int state, Process father, ResourcePlaner resourcePlaner) {
        super(name, state, father);
        this.ProcessNeedsResource(resourcePlaner.findResource("Pakrovimo paketas"));
        this.ProcessNeedsResource(resourcePlaner.findResource("Kanalų įrenginys"));
    }
}
