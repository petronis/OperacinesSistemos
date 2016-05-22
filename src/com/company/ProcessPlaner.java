package com.company;

import java.util.ArrayList;

/**
 * Created by lukas on 2016-05-22.
 */
public class ProcessPlaner {
    ArrayList<Process> processesList, readyProcessesList;
    Process process;

    public ProcessPlaner(Process process){
        this.processesList = new ArrayList<>();
        this.readyProcessesList = new ArrayList<>();
        this.process = process;
        this.processesList.add(this.process);
    }

    public void StartStopProcessesCreate(String name, int state, String father){
        processesList.add(new Process(name, state, father));
    }

    public void PrintList(){
        for(int i = 0; i < processesList.size(); i++){
            System.out.println("Process at " + i + " is " + processesList.get(i));
        }
    }
}
