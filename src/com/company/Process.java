package com.company;

import java.util.ArrayList;

/**
 * Created by lukas on 2016-05-11.
 */
public class Process {
    String name;
    Process father;
    static ResourcePlaner resourcePlaner;
    static RM rm;
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

    public static void setResourcePlaner(ResourcePlaner resourcePlaner) {
        Process.resourcePlaner = resourcePlaner;
    }

    public static ResourcePlaner getResourcePlaner() {
        return resourcePlaner;
    }

    public static RM getRm() {
        return rm;
    }

    public static void setRm(RM rm) {
        Process.rm = rm;
    }

    public void ProcessNeedsResource(Resource resource){
        processWantResources.addToWantingList(resource);
        changeState(0);
    }

    public boolean ProcessHasAllResource(){
        return processWantResources.isAvailable();
    }

    public boolean hasItTaken(Resource resource){
        return processWantResources.hasIt(resource);
    }

    public boolean removeResourcesAfterUsingIt(Resource resource){
        return processWantResources.releaseResources(resource);
    }

    public void changeState(int stateToWhichToChange){
        this.state = stateToWhichToChange;
    }

    public String getName(){return this.name;}

    public int getState(){return this.state;}

    public void createChildProcess(String childName, int childState){
        childProcess.add(new Process(childName,childState, this));
    }

    public void createResourcesFromProcess(ResourcePlaner resourcePlaner, String resourcesName, boolean multiuse){
        resourcePlaner.createResource(resourcesName,multiuse,this);
    }

    public void work(ProcessPlaner processPlaner) {
    }
}
