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
    boolean firstTime = true;
    public StartStop(String name, int state, Process father, ResourcePlaner resourcePlaner) {
        super(name, state, father,resourcePlaner);
        this.resourcePlaner = resourcePlaner;
    }


    public void createStaticResources(){
        this.createResourcesFromProcess(resourcePlaner,"Supervizorinės atminties", true);
        this.createResourcesFromProcess(resourcePlaner,"Užduoties duomenys supervizorinėje atmintyje", true, false);
        this.createResourcesFromProcess(resourcePlaner,"Kanalų įrenginys", true);
        this.createResourcesFromProcess(resourcePlaner,"Vartotojo atmintis", true);
        this.createResourcesFromProcess(resourcePlaner,"Pakrovimo paketas", true, false);
        this.createResourcesFromProcess(resourcePlaner,"Išorinė atmintis", true);
        this.createResourcesFromProcess(resourcePlaner,"InputOutput", true);
        this.createResourcesFromProcess(resourcePlaner,"Pertraukimas", true, false);
        this.createResourcesFromProcess(resourcePlaner,"Iš Interupt", true, false);
        this.createResourcesFromProcess(resourcePlaner,"Interrupt", true, false);
        this.createResourcesFromProcess(resourcePlaner,"Programa parengta", true, false);
    }

    public void createProcesses(ProcessPlaner processPlaner){
        System.out.println("Create Processes in StartStop");
        ReadFromInterface processToAdd = new ReadFromInterface("ReadFromInterface", 3,this,resourcePlaner);
        processPlaner.addProcessToList(processToAdd);
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
        if(firstTime) {
            firstTime = false;
            createStaticResources();
            createProcesses(processPlaner);
            this.createResourcesFromProcess(resourcePlaner, "OS darbo pabaiga", true, false);
            this.ProcessNeedsResource(resourcePlaner.findResource("OS darbo pabaiga"));
            processPlaner.AddingProcessesToList();
        }
        while(true) {
            if(this.ProcessHasAllResource(this)){
                System.out.println("END OF OPERATING SYSTEM");
                System.exit(0);
            }
            else{
                processPlaner.IsThereAnyReadyProcess();
            }
        }
    }

}
