package Processes;

import Exceptions.WrongAddress;
import Exceptions.WrongContentSize;
import com.company.*;
import com.company.Process;

/**
 * Created by lukas on 2016-05-22.
 */
public class JobToDisk extends Process {
    ResourcePlaner resourcePlaner;
    public JobToDisk(String name, int state, Process father, ResourcePlaner resourcePlaner) {
        super(name, state, father,resourcePlaner);
        this.resourcePlaner = resourcePlaner;
        this.ProcessNeedsResource(resourcePlaner.findResource("Užduoties duomenys supervizorinėje atmintyje"));
    }


    public void work(ProcessPlaner processPlaner){
        System.out.println("Job To Disk is working now");

            this.ProcessNeedsResource(resourcePlaner.findResource("Kanalų įrenginys"));
            if (this.ProcessHasAllResource(this)) {
                for (int i = 0; i < getRm().getSupervision().getSize();i++){
                    try {
                        getRm().getExternal().put_block(i,getRm().getSupervision().getBlock(i));
                    } catch (WrongContentSize wrongContentSize) {
                        wrongContentSize.printStackTrace();
                    } catch (WrongAddress wrongAddress) {
                        wrongAddress.printStackTrace();
                    }
                }
                new java.util.Scanner(System.in).nextLine();
                resourcePlaner.findResource("Kanalų įrenginys").setFree(true);
                resourcePlaner.findResource("Programa parengta").setFree(true);
                processPlaner.setTimer(0);
                this.changeState(3);
                processPlaner.RemovingProcessesFromList(this);
                processPlaner.AddingProcessesToWaitingList(this);
            }

    }
}
