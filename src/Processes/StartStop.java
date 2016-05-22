package Processes;

import com.company.*;
import com.company.Process;

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

    public void createProcesses(ProcessPlaner processPlaner){
        System.out.println("Create Processes");
        ReadFromInterface processToAdd = new ReadFromInterface("ReadFromInterface", 3,this,resourcePlaner);
        processPlaner.addProcessToList(processToAdd);
        JobToDisk processToAdd1 = new JobToDisk("JobToDisk", 3,this,resourcePlaner);
        processPlaner.addProcessToList(processToAdd1);
        Loader processToAdd2 = new Loader("Loader", 3,this,resourcePlaner);
        processPlaner.addProcessToList(processToAdd2);
        Cheker processToAdd3 = new Cheker("Cheker", 3,this,resourcePlaner);
        processPlaner.addProcessToList(processToAdd3);
        PrintLine processToAdd4 = new PrintLine("PrintLine", 3,this,resourcePlaner);
        processPlaner.addProcessToList(processToAdd4);
        MainProc processToAdd5 = new MainProc("MainProc", 3,this,resourcePlaner);
        processPlaner.addProcessToList(processToAdd5);
    }

    public void work(ProcessPlaner processPlaner){
        while(true) {
            createStaticResources();
            createProcesses(processPlaner);
            Resource startStopEnd = new Resource("OS darbo pabaiga", false, false, this);
            this.ProcessNeedsResource(startStopEnd);
            if(this.ProcessHasAllResource()){
                break;
            }
        }
    }
}
