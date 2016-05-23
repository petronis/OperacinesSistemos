package Processes;

import com.company.Process;
import com.company.ProcessPlaner;
import com.company.Resource;
import com.company.ResourcePlaner;

/**
 * Created by lukas on 2016-05-22.
 */
public class ReadFromInterface extends StartStop {
    public ReadFromInterface(String name, int state, Process father, ResourcePlaner resourcePlaner) {
        super(name, state, father,resourcePlaner);
        this.ProcessNeedsResource(resourcePlaner.findResource("InputOutput"));
    }

    public void work(ProcessPlaner processPlaner){
        while(true) {
            if (this.ProcessHasAllResource()) {
                // TODO read input
                this.ProcessNeedsResource(resourcePlaner.findResource("Supervizorinės atminties"));
                if(this.ProcessHasAllResource()){
                    // TODO program code has to be copied to Supervizorine atminti
                    if(this.hasItTaken(resourcePlaner.findResource("Užduotis supervizorinėje atminyje"))){
                        this.removeResourcesAfterUsingIt(resourcePlaner.findResource("Užduotis supervizorinėje atminyje"));
                        // returns true if remove was successful and false if not
                    }
                    if(this.ProcessHasAllResource()){
                        System.out.println("mes esame Read form interface");
                        break;
                    }
                }
            } else{
                System.out.println("Something is wrong ReadFromInterface should not have gotten processor");
            }
        }
    }
}
