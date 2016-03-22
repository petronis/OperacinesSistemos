package com.company;

import Exceptions.WrongContentSize;

/**
 * Created by Vik on 3/12/2016.
 */
public class Register {
    String name;
    int size;
    char[] content;

    private void init(String name, int size) {
        this.name = name;
        this.size = size;
        this.content = new char[size];
    }
    Register(){
        super();
    }
    Register(String name, int size) {
        init(name, size);
    }

    Register(String name, int size, String content) {
        init(name, size);
        try {
            setContent(content);
        } catch (WrongContentSize e){
            System.err.println(e.getMessage());
        }
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

    public String getContentStr() {
        return new String(content);
    }

    public int getContentInt() {
        return Integer.parseInt(getContentStr());
    }

    public void inc(int number) throws WrongContentSize{
        this.setContent(this.getContentInt() + number);
    }

    public void inc(int number, int cycle) throws WrongContentSize{
        if ((this.getContentInt() + number) % cycle == cycle-1)
            this.setContent(this.getContentInt() - cycle - 1);
    }

    public void setContent(char[] content) throws WrongContentSize {
        if (size == content.toString().length()) {

            for (int i = 0; i < size; i++) {
                this.content[i] = content[i];
            }
        } else {
            throw new WrongContentSize("New content size doesn't match with old one.");
        }
    }

    public void setContent(String content) throws WrongContentSize {
        if (size == content.length()) {

            for (int i = 0; i < size; i++) {
                this.content[i] = content.charAt(i);
            }
        } else {
            throw new WrongContentSize("New content size doesn't match with old one.");
        }
    }

    public void setContent(int num) throws WrongContentSize {
        String content = Integer.toString(num);
        if (size == content.length()) {

            for (int i = 0; i < size; i++) {
                this.content[i] = content.charAt(i);
            }
        } else if (size > content.length()) {
            for (int i = 0; i < size; i++) {
                if (i < size - content.length()) {
                    this.content[i] = '0';
                } else {
                    this.content[i] = content.charAt(i - (size - content.length() - 1) - 1);
                }
//                System.out.println(getContentStr() + " cont " + i);
            }
        }else {
            throw new WrongContentSize("New content size doesn't match with old one.");
        }
    }

}
