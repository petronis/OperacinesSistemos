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
        this.ProcessNeedsResource(resourcePlaner.findResource("Užduoties programa supervizorinėje atmintyje"));
    }


    public void work(ProcessPlaner processPlaner){
        while(true) {
            this.ProcessNeedsResource(resourcePlaner.findResource("Kanalų įrenginys"));
            if (this.ProcessHasAllResource(this)) {
                System.out.println(getRm().getSupervision().getSize());
                for (int i = 0; i < getRm().getSupervision().getSize();i++){
                    try {
                        getRm().getExternal().put_block(i,getRm().getSupervision().getBlock(i));
                    } catch (WrongContentSize wrongContentSize) {
                        wrongContentSize.printStackTrace();
                    } catch (WrongAddress wrongAddress) {
                        wrongAddress.printStackTrace();
                    }
                }
                this.releaseAllResource();
                // TODO: 2016-05-23 kopijuojame informacija i isorine atminti -> atlaisviname resursus
            }
        }
    }
}
