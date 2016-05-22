package Processes;

import com.company.Process;
import com.company.ResourcePlaner;

import java.util.ArrayList;

/**
 * Created by lukas on 2016-05-22.
 */
public class StartStop extends Process {
    ResourcePlaner resourcePlaner;
    public StartStop(String name, int state, Process father) {
        super(name, state, father);
        this.resourcePlaner = new ResourcePlaner();
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
        processArrayList.add(new ReadFromInterface("ReadFromInterface", 3,this));
        processArrayList.add(new ReadFromInterface("JobToDisk", 3,this));
        processArrayList.add(new ReadFromInterface("Loader", 3,this));
        processArrayList.add(new ReadFromInterface("Cheker", 3,this));
        processArrayList.add(new ReadFromInterface("PrintLine", 3,this));
        processArrayList.add(new ReadFromInterface("MainProc", 3,this));
        return processArrayList;
    }
}
