package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lukas on 2016-03-13.
 */
public class Gui extends JFrame {

    public static RM rm = new RM(20);

    JLabel labelRegisterB;
    JLabel labelRegisterIC;
    JLabel labelRegisterR;

    JTextField textFieldB;
    JTextField textFieldIC;
    JTextField textFieldR;

    JButton updateButton;

    public Gui(){
        createView();
        // Setting title
        setTitle("Real Machine");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // packs everything
//        setSize(new Dimension(500, 300));
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void createView() {
        JPanel panel = new JPanel();
        getContentPane().add(panel);

        labelRegisterB = new JLabel();
//        labelRegisterB.setPreferredSize(new Dimension(75,30));
        labelRegisterB.setText("Register " + rm.getRegister("B").getName());
        panel.add(labelRegisterB);

        textFieldB = new JTextField();
//        textFieldB.setPreferredSize(new Dimension(30,30));
        textFieldB.setText("value");
        textFieldB.setEditable(false);
        panel.add(textFieldB);

        labelRegisterIC = new JLabel();
//        labelRegisterIC.setPreferredSize(new Dimension(200,30));
        labelRegisterIC.setText("Register " + rm.getRegister("IC").getName());
        panel.add(labelRegisterIC);

        textFieldIC = new JTextField();
//        textFieldIC.setPreferredSize(new Dimension(30,30));
        textFieldIC.setText("value");
        textFieldIC.setEditable(false);
        panel.add(textFieldIC);

        labelRegisterR = new JLabel();
//        labelRegisterR.setPreferredSize(new Dimension(200,30));
        labelRegisterR.setText("Register " + rm.getRegister("R").getName());
        panel.add(labelRegisterR);

        textFieldR = new JTextField();
//        textFieldR.setPreferredSize(new Dimension(30,30));
        textFieldR.setText("value");
        textFieldR.setEditable(false);
        panel.add(textFieldR);

        updateButton = new JButton();
        updateButton.setText("Update");
        updateButton.addActionListener(
                e -> {
                    updateTextFields();
                }
        );
        panel.add(updateButton);

    }

    private void updateTextFields() {
        textFieldR.setText(String.valueOf(rm.getRegister("R").getSize()));
        textFieldIC.setText(String.valueOf(rm.getRegister("IC").getSize()));
        textFieldB.setText(String.valueOf(rm.getRegister("B").getSize()));
    }


    public static void main(String [] args){
        Register register;
        register = rm.getRegister("B");
        //System.out.println(register.getName());
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Gui().setVisible(true);
            }
        });
    }

}







    /*JButton buttonCounter, buttonReset;
    JLabel labelCount;

    private int clicks = 0;

    public Gui(){
        createView();
        setTitle("Click me");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // everything will be compact
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void createView() {
        JPanel panel = new JPanel();
        getContentPane().add(panel);

        labelCount = new JLabel();
        labelCount.setPreferredSize(new Dimension(200,30));
        panel.add(labelCount);
        updateCounter();

        buttonCounter = new JButton("Click me");
        buttonCounter.addActionListener(
                new ButtonCounterActionListener()
        );
        panel.add(buttonCounter);

        buttonReset = new JButton("Reset");
        buttonReset.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        clicks = 0;
                        updateCounter();
                    }
                }
        );
        panel.add(buttonReset);

    }

    private void updateCounter() {
        labelCount.setText("Clicked " + clicks + " times");
    }

    public static void main(String [] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Gui().setVisible(true);
            }
        });
    }

    private class ButtonCounterActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            clicks++;
            updateCounter();
        }
    }
}*/
