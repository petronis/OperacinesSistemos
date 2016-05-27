package com.company;

import java.util.ArrayList;

/**
 * Created by Vik on 5/10/2016.
 */
public class ProcessWantResources {
    Process process;
    ArrayList<Resource> wants;

    public ProcessWantResources(Process process) {
        this.process = process;
        this.wants = new ArrayList<>();
    }

    public Process getProcess() {
        return process;
    }

    public void addToWantingList(Resource r) {
        wants.add(r);
    }

    public void gotAllResources(){wants.clear();}

    public boolean releaseResources(Resource resource){
        if (wants.contains(resource)){
            wants.remove(resource);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean occupy(){
        if (isAvailable()) {
            int size = wants.size();
            for (int i = 0; i < size ; i++) {
                wants.get(i).setFree(false);
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean isAvailable(){
        int size = wants.size(), check = 0;
        for (int i = 0; i < size ; i++) {
            if(wants.get(i).isFree()) {
                check++;
            }
        }
        if (check == size ) {
            return true;
        }
        return false;
    }

    public boolean hasIt(Resource resource){
        return wants.contains(resource);
    }
}
