package com.company;

import Exceptions.WrongAddress;
import Exceptions.WrongContentSize;

/**
 * Created by Viktor-PC on 2016-03-21.
 */
public class VirtualMemory extends Memory {

    Register ptr;
    Memory old;

    VirtualMemory(char block[], int size, Register ptr, Memory old) {
        super(block, size);
        this.ptr = ptr;
        this.old = old;
    }

    public String getBlock(int n) throws WrongAddress {
        if (n >= 0 && n < size) {
            n = n + old.getBlockInt(ptr.getContentInt());
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
            return block[old.getBlockInt(ptr.getContentInt()) + n];
        } else {
            throw new WrongAddress("wrong cell address");
        }
    }
}
