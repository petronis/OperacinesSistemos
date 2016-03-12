package com.company;

/**
 * Created by Vik on 3/12/2016.
 */
public class Register {
    String name;
    int size;
    char[] content;

    Register(String name, int size) {
        this.name = name;
        this.size = size;
        this.content = new char[size];
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public char[] getContent() {
        return content;
    }

    public void setContent(char[] content) {
        if (size == content.toString().length()) {

            for (int i = 0; i < size; i++) {
                this.content[i] = content[i];
            }
        } else {
            // throw exception
        }
    }
}
