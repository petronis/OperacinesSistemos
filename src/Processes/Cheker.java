package Processes;

import com.company.Process;
import com.company.ProcessPlaner;
import com.company.ResourcePlaner;

/**
 * Created by lukas on 2016-05-22.
 */
public class Cheker extends Process {
    public Cheker(String name, int state, Process father, ResourcePlaner resourcePlaner) {
        super(name, state, father);
        this.ProcessNeedsResource(resourcePlaner.findResource("Užduotis supervizorinėje atminyje"));
    }

    @Override
    public void run() {
        while(true) {
            if (this.ProcessHasAllResource()) {
                // Todo programos saraso iniciavimas
                // todo Skaitomas zodis
                // todo Ar tai data? -> ar tai code? -> atlaisviname resursa -> skaitome zodi iki eof -> baigiame darba su cheker
            }
        }
    }

    public void work(ProcessPlaner processPlaner){

    }
}
