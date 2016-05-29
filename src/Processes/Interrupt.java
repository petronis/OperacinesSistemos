package Processes;

import Interrupts.SupervisorInterrupt;
import com.company.Process;
import com.company.ProcessPlaner;
import com.company.ResourcePlaner;

/**
 * Created by lukas on 2016-05-23.
 */
public class Interrupt extends Process {
    ResourcePlaner resourcePlaner;
    public Interrupt(String name, int state, Process father, ResourcePlaner resourcePlaner) {
        super(name, state, father, resourcePlaner);
        this.resourcePlaner = resourcePlaner;
        this.ProcessNeedsResource(resourcePlaner.findResource("Pertraukimas"));
    }

    @Override
    public void work(ProcessPlaner processPlaner) {
        int SI, PI, TI;
        while(true){
            if (this.ProcessHasAllResource(this)){
                SI = getRm().getRegister("SI").getContentInt();
                PI = getRm().getRegister("PI").getContentInt();
                TI = getRm().getRegister("TI").getContentInt();
                if (TI == 0){
                    // timer interrupt
                } else if (PI > 0) {
//                    Program interrupt
                } else if (SI > 0) {
//                    SupervisorInterrupt
                } else  {
//                    no interrupt, means VM still working
                }
            }
        }
    }
}
