package com.company;

import Exceptions.WrongAddress;
import Exceptions.WrongContentSize;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Program start point");
        RM rm = new RM(100,10,10);
        Memory data = rm.getData();
        try {
            data.put_block(10, "00034");
            data.put_block(0, "LR010");
            data.put_block(1, "HALTP");
            Instructions in = rm.getInstructions();
            rm.run();
            System.out.println("R " + rm.getRegister("R").getContentStr());
            System.out.println("IC " + rm.getRegister("IC").getContentStr());
            System.out.print(rm.getData().mem());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
