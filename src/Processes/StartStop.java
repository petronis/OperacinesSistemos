package Processes;

import com.company.*;
import com.company.Process;

import java.util.ArrayList;

/**
 * Created by lukas on 2016-05-22.
 */
public class StartStop extends Process {
    ResourcePlaner resourcePlaner;
    ProcessPlaner processPlaner;
    public StartStop(String name, int state, Process father, ResourcePlaner resourcePlaner) {
        super(name, state, father,resourcePlaner);
        this.resourcePlaner = resourcePlaner;
    }


    public void createStaticResources(){
        this.createResourcesFromProcess(resourcePlaner,"Supervizorinės atminties", true);
        this.createResourcesFromProcess(resourcePlaner,"Užduotis supervizorinėje atminyje", true);
        this.createResourcesFromProcess(resourcePlaner,"Užduoties duomenys supervizorinėje atmintyje", true);
        this.createResourcesFromProcess(resourcePlaner,"Užduoties programa supervizorinėje atmintyje", true);
        this.createResourcesFromProcess(resourcePlaner,"Kanalų įrenginys", true);
        this.createResourcesFromProcess(resourcePlaner,"Vartotojo atmintis", true);
        this.createResourcesFromProcess(resourcePlaner,"Pakrovimo paketas", true);
        this.createResourcesFromProcess(resourcePlaner,"Išorinė atmintis", true);
        this.createResourcesFromProcess(resourcePlaner,"InputOutput", true);
        this.createResourcesFromProcess(resourcePlaner,"Pertraukimas", true, false);
        this.createResourcesFromProcess(resourcePlaner,"Iš Interupt", true, false);
        this.createResourcesFromProcess(resourcePlaner,"Interrupt", true, false);
        this.createResourcesFromProcess(resourcePlaner,"Programa parengta", true);
    }

    public void createProcesses(ProcessPlaner processPlaner){
        System.out.println("Create Processes in StartStop");
        ReadFromInterface processToAdd = new ReadFromInterface("ReadFromInterface", 3,this,resourcePlaner);
        processPlaner.addProcessToList(processToAdd);
        /*Cheker processToAdd3 = new Cheker("Cheker", 3,this,resourcePlaner);
        processPlaner.addProcessToList(processToAdd3);*/
        JobToDisk processToAdd1 = new JobToDisk("JobToDisk", 3,this,resourcePlaner);
        processPlaner.addProcessToList(processToAdd1);
        MainProc processToAdd5 = new MainProc("MainProc", 3,this,resourcePlaner);
        processPlaner.addProcessToList(processToAdd5);
        Loader processToAdd2 = new Loader("Loader", 3,this,resourcePlaner);
        processPlaner.addProcessToList(processToAdd2);
        Interrupt interrupt = new Interrupt("Interupt", 3,this,resourcePlaner);
        processPlaner.addProcessToList(interrupt);

    }

    public void work(ProcessPlaner processPlaner){
        this.processPlaner = processPlaner;
        createStaticResources();
        createProcesses(processPlaner);
        Resource startStopEnd = new Resource("OS darbo pabaiga", false, false, this);
        this.ProcessNeedsResource(startStopEnd);
        processPlaner.AddingProcessesToList();
//        processPlaner.PrintWaitingList();
        while(true) {
            if(this.ProcessHasAllResource(this)){
                System.exit(0);
//                break;
            }
            else{
                processPlaner.IsThereAnyReadyProcess();
            }
        }
    }

}
