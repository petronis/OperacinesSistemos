package com.company;

import java.util.ArrayList;

/**
 * Created by Vik on 3/12/2016.
 */
// an array of registers
public class Registers {
    ArrayList<Register> list;

    Registers() {
        list = new ArrayList<>();
    }

    public void addRegister(Register reg) {
        list.add(reg);
    }

    public int get_size() {
        return list.size();
    }

    public Register get_register(int n) {
        return list.get(n);
    }

    public Register get_register(String key) {
        Register temp;
        for (int i = 0; i < list.size(); i++) {
            temp = list.get(i);
            if (temp.getName() == key) {
                return temp;
            }
        }
        return null;
    }

    public void remove_register(int n) {
        list.remove(n);
    }

    public String toString() {
        return list.toString();
    }
}
