package Processes;

import com.company.Process;
import com.company.ResourcePlaner;

import java.util.ArrayList;

/**
 * Created by lukas on 2016-05-22.
 */
public class StartStop extends Process {
    ResourcePlaner resourcePlaner;
    public StartStop(String name, int state, Process father, ResourcePlaner resourcePlaner) {
        super(name, state, father);
        this.resourcePlaner = resourcePlaner;
    }

    public void createStaticResources(){
        this.createResourcesFromProcess(resourcePlaner,"Supervizorinės atminties", false);
        this.createResourcesFromProcess(resourcePlaner,"Užduotis supervizorinėje atminyje", false);
        this.createResourcesFromProcess(resourcePlaner,"Užduoties duomenys supervizorinėje atmintyje", false);
        this.createResourcesFromProcess(resourcePlaner,"Užduoties programa supervizorinėje atmintyje", false);
        this.createResourcesFromProcess(resourcePlaner,"Kanalų įrenginys", false);
        this.createResourcesFromProcess(resourcePlaner,"Vartotojo atmintis", false);
        this.createResourcesFromProcess(resourcePlaner,"Pakrovimo paketas", true);
        this.createResourcesFromProcess(resourcePlaner,"Išorinė atmintis", false);
        this.createResourcesFromProcess(resourcePlaner,"InputOutput", false);
        this.createResourcesFromProcess(resourcePlaner,"Pertraukimas", false);
        this.createResourcesFromProcess(resourcePlaner,"Programa parengta", false);
    }

    public ArrayList<Process> createProcesses(){
        ArrayList<Process> processArrayList = new ArrayList<>();
        processArrayList.add(new ReadFromInterface("ReadFromInterface", 3,this,resourcePlaner));
        processArrayList.add(new JobToDisk("JobToDisk", 3,this,resourcePlaner));
        processArrayList.add(new Loader("Loader", 3,this,resourcePlaner));
        processArrayList.add(new Cheker("Cheker", 3,this,resourcePlaner));
        processArrayList.add(new PrintLine("PrintLine", 3,this,resourcePlaner));
        processArrayList.add(new MainProc("MainProc", 3,this,resourcePlaner));
        return processArrayList;
    }
}
