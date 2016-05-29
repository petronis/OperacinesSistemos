package Processes;

import Exceptions.WrongAddress;
import Exceptions.WrongContentSize;
import com.company.Process;
import com.company.ProcessPlaner;
import com.company.ResourcePlaner;
import com.sun.xml.internal.bind.v2.TODO;

/**
 * Created by lukas on 2016-05-22.
 */
public class Loader extends Process {
    ResourcePlaner resourcePlaner;
    public Loader(String name, int state, Process father, ResourcePlaner resourcePlaner) {
        super(name, state, father,resourcePlaner);
        this.resourcePlaner = resourcePlaner;
        this.ProcessNeedsResource(resourcePlaner.findResource("Pakrovimo paketas"));
        this.ProcessNeedsResource(resourcePlaner.findResource("Kanalų įrenginys"));
    }


    public void work(ProcessPlaner processPlaner){
        //System.out.println("Loader is working");
        while(true) {
            if (this.ProcessHasAllResource(this)) {
                try {
//                    getRm().getRegister("PTR").setContent(0);
                    for (int i = 0; i < getRm().getExternal().getSize(); i++) {
                        try {
                            getRm().getData().put_block(i, getRm().getExternal().getBlock(i));
                        } catch (WrongAddress wrongAddress) {
                            wrongAddress.printStackTrace();
                        }
                    }

                } catch (WrongContentSize wrongContentSize) {
                    wrongContentSize.printStackTrace();
                }
                this.createResourcesFromProcess(resourcePlaner, "Loader complete", false);
                this.releaseAllResource();
                processPlaner.RemovingProcessesFromList(this);
                processPlaner.AddingProcessesToWaitingList(this);
                this.changeState(3);
                processPlaner.IsThereAnyReadyProcess();
                // TODO: 2016-05-29 Ptr nustatyti pagal tai kur yra musu atmintis ir sukurti Loader complete resursa
                // TODO: 2016-05-23 Programa uzloadina i vartotojo atminti
            }
        }
    }
}
