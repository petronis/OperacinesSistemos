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
            data.put_block(0, "00034");
            Instructions in = rm.getInstructions();
            in.check_machine_mode();
            in.load_R_from_memory(0);
            rm.getExternal().put_block(6, "12535");
            in.compare(0);
            System.out.println(rm.getRegister("R").getContentStr().substring(2,4));
            System.out.print(rm.getExternal().mem());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
