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
        System.out.println("Loader is working");
        if (this.ProcessHasAllResource(this)) {
            int ptr = 1;
            try {
                ptr = getRm().getRegister("PTR").getContentInt();
                ptr = getRm().getData().getBlockInt(getRm().getRegister("PTR").getContentInt());
                System.out.println("------------PTR--------- " + ptr);
                System.out.println("----PTR THE REAL ONE---- " + getRm().getRegister("PTR").getContentInt());
            } catch (WrongAddress wrongAddress) {
                wrongAddress.printStackTrace();
            }
            try {
                System.out.println("EXTERNAL SIZE " + getRm().getExternal().getSize());

                System.out.println("IC VALUE " + getRm().getRegister("IC").getContentInt());
                for (int i = 0; i < getRm().getExternal().getSize(); i++) {
                    try {
//                        getRm().getData().put_block(ptr + i, getRm().getExternal().getBlock(i));
                        getRm().getData().put_block(ptr + i, getRm().getExternal().getBlock(i));
                    } catch (WrongAddress wrongAddress) {
                        wrongAddress.printStackTrace();
                    }
                }
//                new java.util.Scanner(System.in).nextLine();
                System.out.println("IC VALUE " + getRm().getRegister("IC").getContentInt());

            } catch (WrongContentSize wrongContentSize) {
                wrongContentSize.printStackTrace();
            }
            this.createResourcesFromProcess(resourcePlaner, "Loader complete", false);
            resourcePlaner.findResource("Kanalų įrenginys").setFree(true);
//                this.ProcessNeedsResource(resourcePlaner.findResource("Kanalų įrenginys"));
            processPlaner.RemovingProcessesFromList(this);
            processPlaner.AddingProcessesToWaitingList(this);
            this.changeState(3);
        }
    }
}
