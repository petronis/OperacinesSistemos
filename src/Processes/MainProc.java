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
        System.out.println("Main Proc is working");
        int timer = 0;
        jobGovernor = new JobGovernor("JobGovernor", 3, this, getResourcePlaner());;
        getProcessWantResources().occupy();
//        if (this.ProcessHasAllResource(this)) {
        if (processPlaner.getTimer() == 0) {
            timer++;
//                    create jobGovernor
            jobGovernor = new JobGovernor("JobGovernor"+jobGovernorNumbering, 3, this, getResourcePlaner());
            jobGovernorNumbering++;
            processPlaner.RemovingProcessesFromList(this);
            processPlaner.addProcessToList(jobGovernor);
            processPlaner.AddingProcessesToWaitingList(jobGovernor, 1);
            this.changeState(3);
            processPlaner.AddingProcessesToWaitingList(this);
        } else {
//                    something went wrong in JobGovernor, so need to destroy JobGovernor and try again
            processPlaner.RemovingProcessesFromList(jobGovernor);
            this.changeState(3);
            processPlaner.RemovingProcessesFromList(this);
            processPlaner.AddingProcessesToWaitingList(this,3);
            //timer = 0;
        }
//        }

    }
}
