package com.company;
import javax.swing.*;
public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Program start point");
        RM rm = new RM(1000,100,50);
        SwingUtilities.invokeLater(() -> new Gui(rm).setVisible(true));
        Memory data = rm.getData();
        try {
            data.put_block(10, "00034");
            data.put_block(11, "00025");
            data.put_block(9, "00020");
            data.put_block(15, "AD011");
            data.put_block(16, "RETRN");
            data.put_block(0, "LR010");
            data.put_block(1, "LB009");
            data.put_block(2, "INVRC");
            data.put_block(3, "RT015");
            data.put_block(4, "DECBL");
            data.put_block(5, "PD011");
            data.put_block(6, "GD012");
            Instructions in = rm.getInstructions();
            in.change_mode();
            rm.run();
            for (int i = 0; i < rm.getRegistersSize(); i++) {
                System.out.println(rm.getRegister(i).getName() + " " + rm.getRegister(i).getContentStr());
            }
            System.out.print(rm.getData().mem());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
