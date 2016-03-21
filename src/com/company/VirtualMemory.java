package com.company;

import Exceptions.WrongAddress;

/**
 * Created by Viktor-PC on 2016-03-21.
 */
public class VirtualMemory extends Memory {

    int ptr;

    VirtualMemory(char block[], int size, int ptr) {
        super(block, size);
        this.ptr = ptr;
    }

    public void setPtr(int ptr) {
        this.ptr = ptr;
    }

    public String getBlock(int n) throws WrongAddress {
        if (n >= 0 && n < size) {
            n = n + ptr;
            String temp = new String();
            for (int i = 0; i < getBlockSize(); i++) {
                temp += block[n * getBlockSize() + i];
            }
            return  temp;
        } else {
            throw new WrongAddress("wrong block address");
        }
    }

    public int getBlockInt(int n) throws WrongAddress {
        return Integer.parseInt(getBlock(n));
    }

    public char getCell(int n) throws WrongAddress {
        if (n >= 0 && n < size * getBlockSize()) {
            return block[ptr + n];
        } else {
            throw new WrongAddress("wrong cell address");
        }
    }
}
