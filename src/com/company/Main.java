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
            Instructions in = rm.getInstructions();
            in.check_machine_mode();
            in.load_R_from_memory(0);
            System.out.print(rm.getRegister("R").getContentStr());
        } catch (Exception wrongAddress) {
            wrongAddress.printStackTrace();
        }
    }
}
