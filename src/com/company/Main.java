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
            data.put_block(0, "1234");
            Instructions in = rm.getInstructions();
            in.check_machine_mode();
            in.load_R_from_memory(0);
            rm.getExternal().put_block(6, "1253");
            in.save_in_external_memory(0);
            System.out.println(rm.getRegister("R").getContent());
            System.out.print(rm.getExternal().mem());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
