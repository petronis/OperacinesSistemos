package com.company;

import java.util.ArrayList;

/**
 * Created by lukas on 2016-05-11.
 */
public class Process {
    String name;
    Process father;
    int state;// 1 - vykdomas procesas; 0 - blokuotas; 2 - pasiruoses;
              // 3 - blokuotas sustabdytas; 4 - pasiruoses sustabdytas.
    ProcessWantResources processWantResources;
    ArrayList<Process> childProcess;

    public Process(String name, int state, Process father) {
        this.name = name;
        this.state = state;
        this.processWantResources = new ProcessWantResources(this);
        this.father = father;
    }

    public void ProcessNeedsResource(Resource resource){
        processWantResources.addToWantingList(resource);
        changeState(0);
    }

    public void changeState(int stateToWhichToChange){
        this.state = stateToWhichToChange;
    }

    public String getName(){return this.name;}

    public int getState(){return this.state;}

    public void createChildProcess(String childName, int childState){
        childProcess.add(new Process(childName,childState, this));
    }
}
