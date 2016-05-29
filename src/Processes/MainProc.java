package Processes;

import com.company.Process;
import com.company.ProcessPlaner;
import com.company.ResourcePlaner;

/**
 * Created by lukas on 2016-05-22.
 */
public class MainProc extends Process {
    static int jobGovernorNumbering = 1;
    JobGovernor jobGovernor = null;

    public MainProc(String name, int state, Process father, ResourcePlaner resourcePlaner) {
        super(name, state, father, resourcePlaner);
        this.ProcessNeedsResource(resourcePlaner.findResource("Programa parengta"));
    }

    public void work(ProcessPlaner processPlaner){
        int timer = 0;
        jobGovernor = new JobGovernor("JobGovernor", 3, this, getResourcePlaner());;
        while(true) {
            if (this.ProcessHasAllResource(this)) {
                if (timer == 0) {
                    timer++;
//                    create jobGovernor
                    jobGovernor = new JobGovernor("JobGovernor"+jobGovernorNumbering, 3, this, getResourcePlaner());
                    jobGovernorNumbering++;
                    processPlaner.RemovingProcessesFromList(this);
                    processPlaner.addProcessToList(jobGovernor);
                    processPlaner.AddingProcessesToWaitingList(jobGovernor, 1);
                    processPlaner.addProcessToList(this);
                    this.changeState(2);
                    processPlaner.IsThereAnyReadyProcess();
                } else {
//                    something went wrong in JobGovernor, so need to destroy JobGovernor and try again
                    processPlaner.RemovingProcessesFromList(jobGovernor);
                    timer = 0;
                }
            }
        }
    }
}
