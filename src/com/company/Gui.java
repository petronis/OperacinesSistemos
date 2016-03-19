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

    public static RM rm = new RM(20,20,20);

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

    JButton updateButton, closeButton;
    JTextArea textArea;
    JButton clearButton, submitButton;
    JTextField messageField;


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
//      Naujas GUI GroupLayout

        GroupLayout layout = new GroupLayout(panel);
        //panel.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        updateButton = new JButton("Update me");
        clearButton = new JButton("Clear me");
        for(int i = 0; i < 12; i++) {
            textFieldArray[i] = new JTextField(10);
            textFieldArray[i].setText("value");
            textFieldArray[i].setEditable(false);

            labelArray[i] = new JLabel();
            labelArray[i].setText("Register " + rm.getRegister(registersNamesArray[i]).getName());
        }
        updateButton = new JButton();
        updateButton.setText("Update");
        updateButton.addActionListener(
                e -> {
                    updateTextFields();
                }
        );
        //panel.add(updateButton);

        closeButton = new JButton();
        closeButton.setText("Close");
        closeButton.addActionListener(
                e -> {
                    System.exit(0);
                }
        );

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(
                                GroupLayout.Alignment.LEADING)
                                .addComponent(labelArray[0])
                                .addComponent(labelArray[1])
                                .addComponent(labelArray[2])
                                .addComponent(labelArray[3])
                                .addComponent(labelArray[4])
                                .addComponent(labelArray[5])
                                .addComponent(labelArray[6])
                                .addComponent(labelArray[7])
                                .addComponent(labelArray[8])
                                .addComponent(labelArray[9])
                                .addComponent(labelArray[10])
                                .addComponent(labelArray[11])
                                .addComponent(updateButton)
                        )
                )
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(
                                GroupLayout.Alignment.LEADING)
                                .addComponent(textFieldArray[0])
                                .addComponent(textFieldArray[1])
                                .addComponent(textFieldArray[2])
                                .addComponent(textFieldArray[3])
                                .addComponent(textFieldArray[4])
                                .addComponent(textFieldArray[5])
                                .addComponent(textFieldArray[6])
                                .addComponent(textFieldArray[7])
                                .addComponent(textFieldArray[8])
                                .addComponent(textFieldArray[9])
                                .addComponent(textFieldArray[10])
                                .addComponent(textFieldArray[11])
                                .addComponent(closeButton)
                        )
                )
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labelArray[0])
                                .addComponent(textFieldArray[0])
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labelArray[1])
                                .addComponent(textFieldArray[1])
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labelArray[2])
                                .addComponent(textFieldArray[2])
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labelArray[3])
                                .addComponent(textFieldArray[3])
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labelArray[4])
                                .addComponent(textFieldArray[4])
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labelArray[5])
                                .addComponent(textFieldArray[5])
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labelArray[6])
                                .addComponent(textFieldArray[6])
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labelArray[7])
                                .addComponent(textFieldArray[7])
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labelArray[8])
                                .addComponent(textFieldArray[8])
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labelArray[9])
                                .addComponent(textFieldArray[9])
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labelArray[10])
                                .addComponent(textFieldArray[10])
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labelArray[11])
                                .addComponent(textFieldArray[11])
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(updateButton)
                                .addComponent(closeButton)
                        )
        );
        panel.setLayout(layout);
    }

    private void createLabels(int i, JPanel panel, GridBagConstraints gbc) {
        labelArray[i] = new JLabel();
        labelArray[i].setText("Register " + rm.getRegister(registersNamesArray[i]).getName());
//        labelArray[i].setHorizontalAlignment();
        panel.add(labelArray[i],gbc);//,BorderLayout.EAST);
        //labelArray[i].setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    private void createTextFields(int i, JPanel panel, GridBagConstraints gbc){
        textFieldArray[i] = new JTextField(10);
        textFieldArray[i].setText("value");
        textFieldArray[i].setEditable(false);
        panel.add(textFieldArray[i],gbc);//,BorderLayout.EAST);
    }
    private void updateTextFields() {
        for(int i = 0; i < 12; i++){
            textFieldArray[i].setText(String.valueOf(rm.getRegister(registersNamesArray[i]).getContentStr()));
        }
    }

    public static void main(String [] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Gui().setVisible(true);
            }
        });
    }
}
