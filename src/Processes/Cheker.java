package Processes;

import com.company.Process;
import com.company.ProcessPlaner;
import com.company.ResourcePlaner;

/**
 * Created by lukas on 2016-05-22.
 */
public class Cheker extends Process {
    ResourcePlaner resourcePlaner;
    public Cheker(String name, int state, Process father, ResourcePlaner resourcePlaner) {
        super(name, state, father,resourcePlaner);
        this.resourcePlaner = resourcePlaner;
        this.ProcessNeedsResource(resourcePlaner.findResource("Užduotis supervizorinėje atminyje"));
    }

    public void work(ProcessPlaner processPlaner){
        while(true) {
            if (this.ProcessHasAllResource(this)) {
                // Todo programos saraso iniciavimas
                // todo Skaitomas zodis
                // todo Ar tai data? -> ar tai code? -> atlaisviname resursa -> skaitome zodi iki eof -> baigiame darba su cheker
            }
        }
    }
}
