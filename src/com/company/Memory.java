package com.company;

/**
 * Created by Vik on 3/12/2016.
 */

// block = 4 chars, cell = 1 char
public class Memory {

    int size;
    private static final int block_size = 4;
    char block[] ;

    Memory(int blocks) {
        this.size = blocks;
        this.block = new char[blocks*block_size];
    }

    public String getBlock(int n) {
        if (n < 0 && n < size) {
            String temp = new String();
            for (int i = 0; i < block_size; i++) {
                temp += block[n*block_size+i];
            }
            return  temp;
        } else {
            return "\0"; // later replace with exception
        }
    }

    public char getCell(int n) {
        if (n < 0 && n < size * block_size) {
            return block[n];
        } else {
            return '\0'; // later replace with exception
        }
    }

    private boolean copy_block_to_memory(int n, char[] block) {
        if (block_size == block.toString().length()) {

            for (int i = 0; i < block_size; i++) {
                this.block[n*block_size+i] = block[i];
            }
            return true;
        }
        return false;
    }

    public void put_block(int n, char[] block) {
        if (n < 0 && n < size) {
            copy_block_to_memory(n, block);
        }
    }


}
