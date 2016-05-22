package com.company;

/**
 * Created by Vik on 5/10/2016.
 */
public class Resource {
    String name; //
    boolean multiuse, free;
    Process creator;

    public Resource(String name, boolean multiuse, Process creator) {
        this.name = name;
        this.multiuse = multiuse;
        this.free = true;
        this.creator = creator;
    }

    public Resource(String name, boolean multiuse, boolean free, Process creator) {
        this.name = name;
        this.multiuse = multiuse;
        this.free = free;
        this.creator = creator;
    }


    public String getName() {
        return name;
    }

    public boolean isMultiuse() {
        return multiuse;
    }

    public boolean isFree() {
        return free;
    }

    public Process getCreator() {
        return creator;
    }

    public void setFree(boolean free) {
        if (!multiuse && free){
//            nope
        } else {
            this.free = free;
        }
    }
}
