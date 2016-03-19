package com.company;

import Exceptions.WrongAddress;
import Exceptions.WrongContentSize;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Program start point");
        RM rm = new RM(1000,100,100);
        Memory data = rm.getData();
        try {
            data.put_block(0, "1234");
        } catch (WrongContentSize wrongContentSize) {
            wrongContentSize.printStackTrace();
        }
        try {
            System.out.print(data.getBlockInt(0));
        } catch (WrongAddress wrongAddress) {
            wrongAddress.printStackTrace();
        }
    }
}
