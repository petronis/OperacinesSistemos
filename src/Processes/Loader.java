package Processes;

import com.company.Process;
import com.company.ProcessPlaner;
import com.company.ResourcePlaner;

/**
 * Created by lukas on 2016-05-22.
 */
public class Loader extends Process {
    ResourcePlaner resourcePlaner;
    public Loader(String name, int state, Process father, ResourcePlaner resourcePlaner) {
        super(name, state, father,resourcePlaner);
        this.resourcePlaner = resourcePlaner;
        this.ProcessNeedsResource(resourcePlaner.findResource("Pakrovimo paketas"));
        this.ProcessNeedsResource(resourcePlaner.findResource("Kanalų įrenginys"));
    }


    public void work(ProcessPlaner processPlaner){
        while(true) {
            if (this.ProcessHasAllResource(this)) {
                // TODO: 2016-05-23 Programa uzloadina i vartotojo atminti
            }
        }
    }
}
