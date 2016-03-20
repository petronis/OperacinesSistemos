package com.company;

import Exceptions.WrongAddress;
import Exceptions.WrongContentSize;

/**
 * Created by Vik on 3/12/2016.
 */

// block = 4 chars, cell = 1 char
public class Memory {

    int size;
    private static final int block_size = 5;
    char block[];

    Memory(int blocks) {
        this.size = blocks;
        this.block = new char[blocks * block_size];
        for (int i = 0; i < size * block_size; i++) { // for testing
            this.block[i] = '0';
        }
    }

    public String getBlock(int n) throws WrongAddress {
        if (n >= 0 && n < size) {
            String temp = new String();
            for (int i = 0; i < block_size; i++) {
                temp += block[n * block_size + i];
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
        if (n >= 0 && n < size * block_size) {
            return block[n];
        } else {
            throw new WrongAddress("wrong cell address");
        }
    }

    private boolean copy_block_to_memory(int n, char[] block) {
        if (block_size == block.toString().length()) {

            for (int i = 0; i < block_size; i++) {
                this.block[n * block_size + i] = block[i];
            }
            return true;
        }
        return false;
    }

    public void put_block(int n, char[] block) {
        if (n >= 0 && n < size) {
            copy_block_to_memory(n, block);
        }
    }


    public void put_block(int n, String content) throws WrongContentSize {
        if (n >= 0 && n < size) {
            if (block_size == content.length()) {

                for (int i = 0; i < block_size; i++) {
                    this.block[n * block_size + i] = content.charAt(i);
                }
            } else {
                throw new WrongContentSize("New content size doesn't match with old one.");
            }
        }
    }

    public String mem() throws Exception{
        String tmp = new String();
        for (int i = 0; i < size; i++) {
            tmp += this.getBlock(i);
            //tmp += i + "|" + this.getBlock(i) + "|\n";
        }
        return tmp;
    }
}
