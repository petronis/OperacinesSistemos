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
        this.startStop = new StartStop("StartStop",1,null);
        //this.startStop.createStaticResources();
        ArrayList<Process> tmp = startStop.createProcesses();
        this.processesList.add(startStop);
        this.resourcePlaner = new ResourcePlaner();
        for (int i = 0; i < tmp.size(); i++){
            this.processesList.add(tmp.get(i));
        }

    }
    public void StartStopProcess(){
        startStop.createStaticResources();
    }
    /*public ArrayList<Process> StartStopProcessesCreate(Process father){
        Process JobToDisk = new Process("JobToDisk", 0, process);
        Process ReadFromInterface = new Process("ReadFromInterface",0,process);
        Process Loader = new Process("Loader", 0, process);
        Process PrintLn = new Process("PrintLine", 0, process);
        Process MainProc = new Process("MainProcess", 0, process);
        processesList.add(father);
        processesList.add(JobToDisk);
        processesList.add(ReadFromInterface);
        processesList.add(Loader);
        processesList.add(PrintLn);
        processesList.add(MainProc);
        return processesList;
    }*/

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
            System.out.println("Process at " + i + " is " + processesList.get(i).getName());
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
        }else{
            for (int i = 0; i < waitingProcessesList.size(); i++){
                if  (waitingProcessesList.get(i).processWantResources.isAvailable()){
                    ChangeListByState(waitingProcessesList.get(i));
                }
            }
        }
    }

    public void createResource(String processName, String resourceName, boolean multiuse){
        int position = getProcessFromListByName(processName);
        if (position == -1){
            System.out.println("There is no process with this name");
        } else {
            processesList.get(position).createResourcesFromProcess(resourcePlaner, resourceName,multiuse);
        }
    }

}
