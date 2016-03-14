package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;

/**
 * Created by lukas on 2016-03-13.
 */
public class Gui extends JFrame {

    public static RM rm = new RM(20);

    JLabel labelRegisterPTR;
    JLabel labelRegisterB;
    JLabel labelRegisterIC;
    JLabel labelRegisterC;
    JLabel labelRegisterR;
    JLabel labelRegisterPI;
    JLabel labelRegisterSI;
    JLabel labelRegisterTI;
    JLabel labelRegisterCH1;
    JLabel labelRegisterCH2;
    JLabel labelRegisterCH3;
    JLabel labelRegisterMODE;

    String[] registersNamesArray = {"PTR","B","IC","C","R","PI","SI","TI","CH1","CH2","CH3","MODE"};
    

    JLabel[] labelArray = new JLabel[]{ labelRegisterPTR,labelRegisterB,labelRegisterIC,labelRegisterC,
                                        labelRegisterR,labelRegisterPI,labelRegisterSI,labelRegisterTI,
                                        labelRegisterCH1,labelRegisterCH2,labelRegisterCH3,labelRegisterMODE};

    JTextField textFieldPTR;
    JTextField textFieldB;
    JTextField textFieldIC;
    JTextField textFieldC;
    JTextField textFieldR;
    JTextField textFieldPI;
    JTextField textFieldSI;
    JTextField textFieldTI;
    JTextField textFieldCH1;
    JTextField textFieldCH2;
    JTextField textFieldCH3;
    JTextField textFieldMODE;
    
    JTextField[] textFieldArray = new JTextField[]{ textFieldPTR,textFieldB,textFieldIC,textFieldC,
                                                    textFieldR,textFieldPI,textFieldSI,textFieldTI,
                                                    textFieldCH1,textFieldCH2,textFieldCH3,textFieldMODE};


    

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
        panel.setLayout(new GridBagLayout());
        getContentPane().add(panel);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(2, 2, 2, 2);

        for(int i = 1; i < 12; i++){
            createLabels(i, panel, gbc);
            gbc.gridx++;
            createTextFields(i, panel, gbc);
            gbc.gridx = 0;
            gbc.gridy++;
            gbc.fill = GridBagConstraints.HORIZONTAL;
        }

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 2;
        updateButton = new JButton();
        updateButton.setText("Update");
        updateButton.addActionListener(
                e -> {
                    updateTextFields();
                }
        );
        panel.add(updateButton,gbc);
    }

    private void createLabels(int i, JPanel panel, GridBagConstraints gbc) {
        labelArray[i] = new JLabel();
        labelArray[i].setText("Register " + rm.getRegister(registersNamesArray[i]).getName());
//        labelArray[i].setHorizontalAlignment();
        panel.add(labelArray[i],gbc);//,BorderLayout.EAST);
        //labelArray[i].setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    private void createTextFields(int i, JPanel panel, GridBagConstraints gbc){
        textFieldArray[i] = new JTextField(4);
        textFieldArray[i].setText("value");
        textFieldArray[i].setEditable(false);
        panel.add(textFieldArray[i], gbc);//,BorderLayout.EAST);
    }

    private void updateTextFields() {
        for(int i = 1; i < 12; i++){
            textFieldArray[i].setText(String.valueOf(rm.getRegister(registersNamesArray[i]).getSize()));
        }
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
