package Processes;

import com.company.Process;
import com.company.ProcessPlaner;
import com.company.ResourcePlaner;
import com.company.VM;

/**
 * Created by Vik on 5/29/2016.
 */
public class VirtualMachine extends Process {
    ResourcePlaner resourcePlaner;
    VM vm;
    boolean firsTime = true;
    public VirtualMachine(String name, int state, Process father, ResourcePlaner resourcePlaner) {
        super(name, state, father, resourcePlaner);
        this.resourcePlaner = resourcePlaner;
    }

    @Override
    public void work(ProcessPlaner processPlaner) {
        System.out.println("VirtualMachine is work");
        try {
            if (getRm().getRegister("MODE").getContentStr().contentEquals("S")) {
                getRm().getInstructions().change_mode();
            }
            if (firsTime) {
                firsTime = false;
                vm = getRm().createVm();
                vm.getInstructions().setVm(vm);
                vm.run();
            } else  {
                vm.getInstructions().setVm(vm);
                vm.rerun();
            }
            getResourcePlaner().freeResource("Pertraukimas");
            String msg = getFather().getProcessName();
            resourcePlaner.findResource("Pertraukimas").setMessage(msg);
            processPlaner.RemovingProcessesFromList(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        processPlaner.RemovingProcessesFromList(this);
    }
}
