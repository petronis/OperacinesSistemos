package com.company;

import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;

/**
 * Created by Vik on 5/10/2016.
 */
public class ResourcePlaner {
    ArrayList<Resource> resources;
    ArrayList<ProcessWantResources> waiting;

    public ResourcePlaner() {
        this.resources = new ArrayList<>();
    }

    public void createResource(String name, boolean multiuse, Process creator) {
        resources.add(new Resource(name, multiuse, creator));
    }

    private Resource findResource(String key){
        for (int i = 0; i < resources.size(); i++) {
            if (resources.get(i).getName() == key) {
                return resources.get(i);
            }
        }
        return null;
    }

    public boolean destroyResource(String key) {
        Resource temp = findResource(key);
        if (temp != null) {
            resources.remove(temp);
            return false;
        }
        return false;
    }

    public void freeResource(String key) {
        Resource temp = findResource(key);
        if (temp != null) {
            temp.setFree(true);
        }
    }

    public void askForResource(ProcessWantResources needs){
        waiting.add(needs);
    }

    public Process checkWaitingList() {
        for (int i = 0; i < waiting.size(); i++) {
            if (waiting.get(i).isAvailable()) {
                return waiting.get(i).getProcess();
            }
        }
        return null;
    }

}
