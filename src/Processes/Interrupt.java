package Processes;

import Interrupts.SupervisorInterrupt;
import com.company.Process;
import com.company.ProcessPlaner;
import com.company.ResourcePlaner;

import java.util.Scanner;

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
        System.out.println("Interrupt");
        int SI, PI, TI;
        if (this.ProcessHasAllResource(this)){
            System.out.println("WE HAVE ALL RESOURCES");
            SI = getRm().getRegister("SI").getContentInt();
            PI = getRm().getRegister("PI").getContentInt();
            TI = getRm().getRegister("TI").getContentInt();
            if (TI == 0){
//                     timer interrupt
                resourcePlaner.findResource("Interrupt").setMessage("TI");
                resourcePlaner.findResource("Interrupt").setFree(true);
            } else if (PI > 0) {
//                    Program interrupt
                resourcePlaner.findResource("Interrupt").setMessage("PI");
                resourcePlaner.findResource("Interrupt").setFree(true);
            } else if (SI > 0) {
//                    SupervisorInterrupt
                resourcePlaner.findResource("Interrupt").setMessage("SI");
                resourcePlaner.findResource("Interrupt").setFree(true);
            } else  {
//                    no interrupt, means VM still working
                resourcePlaner.findResource("Interrupt").setMessage("VM still working");
                resourcePlaner.findResource("Interrupt").setFree(true);
            }
//                free res for jobGov
            resourcePlaner.findResource("Iš Interupt").setMessage(resourcePlaner.findResource("Pertraukimas").getMessage());
            resourcePlaner.findResource("Iš Interupt").setFree(true);
        }
        processPlaner.RemovingProcessesFromList(this);
        this.changeState(3);
        processPlaner.AddingProcessesToWaitingList(this);
        int place = processPlaner.getProcessFromListByName(resourcePlaner.findResource("Pertraukimas").getMessage());
        if (place > -1){
            processPlaner.AddingProcessesToWaitingList(processPlaner.processesList.get(place),1);
        }
        processPlaner.IsThereAnyReadyProcess();
    }
}
