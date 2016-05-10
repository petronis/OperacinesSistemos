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

    public boolean isAvailable(){
        int size = wants.size(), check = 0;
        for (int i = 0; i < size ; i++) {
            if(wants.get(i).isFree()) {
                check++;
            }
        }
        if (check == size - 1) {
            return true;
        }
        return false;
    }
}
