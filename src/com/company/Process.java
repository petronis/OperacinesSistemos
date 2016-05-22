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
    Resource neededResource;
    ArrayList<Process> chilledProcess;

    public Process(String name, int state, Process father) {
        this.name = name;
        this.state = state;
        this.neededResource = null;
        this.father = father;
    }

    public void changeState(int stateToWhichToChange){
        this.state = stateToWhichToChange;
    }

    public String getName(){return this.name;}

    public int getState(){return this.state;}

    public void createChildProcess(String childName, int childState){
        chilledProcess.add(new Process(childName,childState, this));
    }


}
