package Processes;

import com.company.Process;
import com.company.ProcessPlaner;
import com.company.ResourcePlaner;

/**
 * Created by lukas on 2016-05-22.
 */
public class MainProc extends Process {

    JobGovernor jobGovernor;

    public MainProc(String name, int state, Process father, ResourcePlaner resourcePlaner) {
        super(name, state, father, resourcePlaner);
        this.ProcessNeedsResource(resourcePlaner.findResource("Programa parengta"));
    }

    @Override
    public void run() {
        int timer = 0;
        while(true) {
            if (this.ProcessHasAllResource()) {
                if (timer == 0) {
                    timer++;
//                    create jobGovernor
//                    jobGovernor = new JobGovernor();
                } else {
//                    something went wrong in JobGovernor, so need to destroy JobGovernor and try again

                    timer = 0;
                }
            }
        }
    }

    public void work(ProcessPlaner processPlaner){

    }
}
