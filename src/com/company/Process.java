package com.company;

import java.util.ArrayList;

/**
 * Created by lukas on 2016-05-11.
 */
public class Process {
    String name, state, fatherName;
    Resource neededResource;
    ArrayList<Process> chilledProcess;

    public Process(String name, String state, String fatherName) {
        this.name = name;
        this.state = state;
        this.neededResource = null;
        this.fatherName = fatherName;
    }

    public void changeState(String stateToWhichToChange){
        this.state = stateToWhichToChange;
    }

    public String getName(){return name;}

    public String getState(){return state;}

    public void createChilledProcess(String chilledName, String chilledState){
        chilledProcess.add(new Process(chilledName,chilledState,getName()));
    }


}
