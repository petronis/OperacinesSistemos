package Processes;

import com.company.Process;

/**
 * Created by lukas on 2016-05-22.
 */
public class JobToDisk extends Process {
    public JobToDisk(String name, int state, Process father) {
        super(name, state, father);
    }
}
