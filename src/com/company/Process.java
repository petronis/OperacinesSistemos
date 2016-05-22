package com.company;

import java.util.ArrayList;

/**
 * Created by lukas on 2016-05-11.
 */
public class Process {
    String name, fatherName;
    int state;
    Resource neededResource;
    ArrayList<Process> chilledProcess;

    public Process(String name, int state, String fatherName) {
        this.name = name;
        this.state = state;
        this.neededResource = null;
        this.fatherName = fatherName;
    }

    public void changeState(int stateToWhichToChange){
        this.state = stateToWhichToChange;
    }

    public String getName(){return name;}

    public int getState(){return state;}

    public void createChilledProcess(String chilledName, int chilledState){
        chilledProcess.add(new Process(chilledName,chilledState,getName()));
    }


}
