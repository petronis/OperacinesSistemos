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
    }

    public void createStaticResources(){
        resourcePlaner.createResource("Supervizorinės atminties", false, this);
        resourcePlaner.createResource("Užduotis supervizorinėje atminyje", false, this);
        resourcePlaner.createResource("Užduoties duomenys supervizorinėje atmintyje", false, this);
        resourcePlaner.createResource("Užduoties programa supervizorinėje atmintyje", false, this);
        resourcePlaner.createResource("Kanalų įrenginys", false, this);
        resourcePlaner.createResource("Vartotojo atmintis", false, this);
        resourcePlaner.createResource("Pakrovimo paketas", true, this);
        resourcePlaner.createResource("Išorinė atmintis", false, this);
        resourcePlaner.createResource("InputOutput", false, this);
        resourcePlaner.createResource("Pertraukimas", false, this);
        resourcePlaner.createResource("Programa parengta", false, this);
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
