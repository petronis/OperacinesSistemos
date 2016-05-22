package com.company;

import Processes.StartStop;

import java.util.ArrayList;

/**
 * Created by lukas on 2016-05-22.
 */
public class ProcessPlaner {
    ArrayList<Process> processesList, readyProcessesList, waitingProcessesList;
    StartStop startStop;
    ResourcePlaner resourcePlaner;

    public ProcessPlaner(){
        this.processesList = new ArrayList<>();
        this.readyProcessesList = new ArrayList<>();
        this.waitingProcessesList = new ArrayList<>();
        this.resourcePlaner = new ResourcePlaner();
        this.startStop = new StartStop("StartStop",1,null,resourcePlaner);
        this.processesList.add(startStop);
        StartStopProcess();
    }

    public void StartStopProcess(){
        IsThereAnyReadyProcess();
        //startStop.createStaticResources();
    }

    public void addProcessToList(Process processToAdd){
        processesList.add(processToAdd);
    }

    public int getProcessFromListByName(String processName){
        for (int i = 0; i < processesList.size(); i++){
            if(processesList.get(i).getName().equals(processName)){
                return i; // returns index of process by its name
            }
        }
        return -1;  // returns -1 if there is no process with name processName
    }

    public void PrintList(){
        for(int i = 0; i < processesList.size(); i++){
            System.out.println("Process at " + i + " is " + processesList.get(i).getName() + " state " + processesList.get(i).getState());
        }
    }

    public void PrintReadyList(){
        for (int i = 0; i < this.readyProcessesList.size(); i++){
            System.out.println("Process in ready list at " + i + " is " + readyProcessesList.get(i).getName());
        }
    }

    public void PrintWaitingList(){
        for (int i = 0; i < waitingProcessesList.size(); i++){
            System.out.println("Process in waiting list at " + i + " is " + waitingProcessesList.get(i).getName());
        }
    }

    public void RemovingProcessesFromList(Process process){
        if (waitingProcessesList.contains(process)){
            waitingProcessesList.remove(process);
            System.out.println("Process "+process+" was removed from waiting list");
        } else if (readyProcessesList.contains(process)){
            readyProcessesList.remove(process);
            System.out.println("Process "+process+" was removed form ready list");
        }
    }
    public void AddingProcessesToList(){
        for(int i = 0; i < processesList.size(); i++) {
            if (processesList.get(i).getState() == 0 || processesList.get(i).getState() == 3) {
                waitingProcessesList.add(processesList.get(i));
            } else if (processesList.get(i).getState() == 2) {
                readyProcessesList.add(processesList.get(i));
            }
        }
    }

    public void ChangeListByState(Process process){
        if  (process.state == 0) { // Process is blocked need to be changed to ready
            waitingProcessesList.remove(waitingProcessesList.indexOf(process));
            readyProcessesList.add(process);
            process.changeState(2);
        }
    }

    public void IsThereAnyReadyProcess(){
        if  (!readyProcessesList.isEmpty()){
            //TODO Vykdyti procesa
//            for (int i = 0; i < readyProcessesList.size(); i++) {
//                Process workingProcess = readyProcessesList.get(i);
//                workingProcess.work(this);
//            }
        }else{
            for (int i = 0; i < waitingProcessesList.size(); i++){
                if  (waitingProcessesList.get(i).processWantResources.isAvailable()){
                    ChangeListByState(waitingProcessesList.get(i));
                }
            }
        }
        for (int i = 0; i < processesList.size(); i++){
            if (processesList.get(i).getState() == 1){
                processesList.get(i).work(this);
            }
        }
    }

    /*public void createResource(String processName, String resourceName, boolean multiuse){
        int position = getProcessFromListByName(processName);
        if (position == -1){
            System.out.println("There is no process with this name");
        } else {
            processesList.get(position).createResourcesFromProcess(resourcePlaner, resourceName,multiuse);
        }
    }*/

}
