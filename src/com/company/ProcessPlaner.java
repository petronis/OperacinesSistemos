package com.company;

import Processes.StartStop;

import java.util.ArrayList;

/**
 * Created by lukas on 2016-05-22.
 */
public class ProcessPlaner extends Thread {
    public ArrayList<Process> processesList, readyProcessesList, waitingProcessesList;
    StartStop startStop;
    ResourcePlaner resourcePlaner;
    RM rm;
    public String readingFileName = "";
    int timer;

    public ProcessPlaner(RM rm){
        this.rm = rm;
        this.processesList = new ArrayList<>();
        this.readyProcessesList = new ArrayList<>();
        this.waitingProcessesList = new ArrayList<>();
        this.resourcePlaner = new ResourcePlaner();
        this.startStop = new StartStop("StartStop",1,null,resourcePlaner);
        this.startStop.setRm(rm);
        this.processesList.add(startStop);
//            StartStopProcess();
    }

    /*public void StartStopProcess(){
        IsThereAnyReadyProcess();
        //startStop.createStaticResources();
    }*/


    public void setTimer(int timer) {
        this.timer = timer;
    }

    public int getTimer() {
        return timer;
    }

    public void setReadingFileName(String readingFileName) {
        this.readingFileName = readingFileName;
    }

    public void addProcessToList(Process processToAdd){
        processesList.add(processToAdd);
    }

    public int getProcessFromListByName(String processName){
        for (int i = 0; i < processesList.size(); i++){
            if(processesList.get(i).getProcessName().equals(processName)){
                return i; // returns index of process by its name
            }
        }
        return -1;  // returns -1 if there is no process with name processName
    }

    public void PrintList(){
        for(int i = 0; i < processesList.size(); i++){
            System.out.println("Process at " + i + " is " + processesList.get(i).getProcessName() + " state " + processesList.get(i).getProcessState());
        }
    }

    public void PrintReadyList(){
        for (int i = 0; i < this.readyProcessesList.size(); i++){
            System.out.println("Process in ready list at " + i + " is " + readyProcessesList.get(i).getProcessName());
        }
    }

    public void PrintWaitingList(){
        for (int i = 0; i < waitingProcessesList.size(); i++){
            System.out.println("Process in waiting list at " + i + " is " + waitingProcessesList.get(i).getProcessName());
        }
    }

    public void RemovingProcessesFromList(Process process){
        if (waitingProcessesList.contains(process)){
            waitingProcessesList.remove(process);
        } else if (readyProcessesList.contains(process)){
            readyProcessesList.remove(process);
        }
    }


    public void AddingProcessesToWaitingList(Process process){
        this.waitingProcessesList.add(process);
    }

    public void AddingProcessesToWaitingList(Process process, int position){
        this.waitingProcessesList.add(position, process);
    }

    public void AddingProcessesToList(){
        for(int i = 0; i < processesList.size(); i++) {
            if (processesList.get(i).getProcessState() == 0 || processesList.get(i).getProcessState() == 3) {
                waitingProcessesList.add(processesList.get(i));
            } else if (processesList.get(i).getProcessState() == 2) {
                readyProcessesList.add(processesList.get(i));
            }
        }
    }

    public void ChangeListByState(Process process){
        if  (process.state == 0 || process.state == 3) { // Process is blocked need to be changed to ready
            waitingProcessesList.remove(waitingProcessesList.indexOf(process));
            readyProcessesList.add(process);
            process.changeState(2);
        }
    }

    public void IsThereAnyReadyProcess(){
        /*if (this.resourcePlaner.findResource("InputOutput").getMessage() == ">>...") {
            this.resourcePlaner.findResource("InputOutput").setFree(true);
            this.readyProcessesList.clear();
        }*/
        for (int i = 0; i < processesList.size(); i++) {
            if (processesList.get(i).getProcessState() == 1) {
                processesList.get(i).work(this);
            }
        }
        while(true) {
            if (!readyProcessesList.isEmpty()) {
                for (int i = 0; i < readyProcessesList.size(); i++) {
                    Process workingProcess = readyProcessesList.get(i);
                    workingProcess.changeState(1);
                    workingProcess.work(this);
                }
            } else {
                for (int i = 0; i < waitingProcessesList.size(); i++) {
//                    System.out.println("Waiting list size " + waitingProcessesList.size());
                    if (waitingProcessesList.get(i).processWantResources.isAvailable()) {
                        ChangeListByState(waitingProcessesList.get(i));
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void run() {
        while(true){
            if (readingFileName.equals("sut.txt")) {
                int a = 0;
                try {
                    if  (rm.getInstructions().check_MODE()) {
//                        rm.getInstructions().change_mode();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < processesList.size(); i++){
                if(processesList.get(i).getProcessState() == 1){
                    processesList.get(i).work(this);
                }
            }
            if (!readyProcessesList.isEmpty()){
                for (int i = 0; i < readyProcessesList.size();i++){
                    readyProcessesList.get(i).changeState(1);
                    Process newProcess = readyProcessesList.get(i);
                    readyProcessesList.remove(i);
                    newProcess.work(this);
                }
            } else {
                for (int i = 0; i < waitingProcessesList.size(); i++){
//                    System.out.println("Waiting for processes resources...");
                    if (waitingProcessesList.get(i).processWantResources.isAvailable()){
                        ChangeListByState(waitingProcessesList.get(i));
                        break;
                    }
                }
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
