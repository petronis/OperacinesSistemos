package Processes;

import com.company.Process;
import com.company.ProcessPlaner;
import com.company.ResourcePlaner;

/**
 * Created by lukas on 2016-05-23.
 */
public class JobGovernor extends Process{

    ResourcePlaner resourcePlaner;
    public JobGovernor(String name, int state, Process father,ResourcePlaner resourcePlaner) {
        super(name, state, father);
        this.resourcePlaner = resourcePlaner;
        this.ProcessNeedsResource(resourcePlaner.findResource("Vartotojo atmintis"));
        this.ProcessNeedsResource(resourcePlaner.findResource("Pakrovimo paketas"));
        this.ProcessNeedsResource(resourcePlaner.findResource("Loader complete"));
        this.ProcessNeedsResource(resourcePlaner.findResource("Supervizorine atmintis"));
    }

    @Override
    public void work(ProcessPlaner processPlaner) {
        while(true){
            if(this.ProcessHasAllResource()){
                // TODO: 2016-05-23 Create VM
                this.ProcessNeedsResource(resourcePlaner.findResource("Interrupt"));
                // TODO: 2016-05-23 Stop VM 
                // TODO: 2016-05-23 What Interrupt is it if GD good, if not terminante JobGovernor with VM 
            }
        }
    }
}
