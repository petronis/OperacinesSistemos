package com.company;

import Exceptions.WrongContentSize;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.lang.reflect.Array;

/**
 * Created by lukas on 2016-03-13.
 */
public class Gui extends JFrame {

    public static RM rm = new RM(1000,100,20);

    Object rowData[][] = new Object[1000][5];
    Object columnNames[] = {"1", "2", "3", "4", "5"};

    JTable table = new JTable(rowData,columnNames);

/* Real machine UI Items */

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

    JButton updateButton, closeButton, inputTextButton, outputTextButton, modeButton, exteriorMemoryButton;

/* End of Real machine UI Items */
/* Virtual machine UI Items */

    JLabel vmRegisterIC;
    JLabel vmRegisterR;
    JLabel vmRegisterC;
    JLabel vmRegisterB;

    String[] vmRegisterNamesArray = {"R","IC","C","B"};
    JLabel[] vmLabelArray = new JLabel[]{vmRegisterR,vmRegisterIC,vmRegisterC,vmRegisterB};

    JTextField vmTextR;
    JTextField vmTextIC;
    JTextField vmTextC;
    JTextField vmTextB;

    JTextField[] vmTextArray = new JTextField[]{vmTextR,vmTextIC,vmTextC,vmTextB};

/* End of Virtual machine UI Items */


    public Gui(){
        createView();
        setTitle("Real Machine");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);

    }

    private void createView() {
        JPanel panel = new JPanel();
        JPanel groupLayoutPanel = new JPanel();
        JPanel tablePanel = new JPanel();
        JPanel inputOutputPanel = new JPanel();

        groupLayoutPanel.setLayout(new GridBagLayout());
        getContentPane().add(groupLayoutPanel);

        inputOutputPanel.setLayout(new GridBagLayout());
        getContentPane().add(inputOutputPanel);
//      Naujas GUI GroupLayout

        GroupLayout layout = new GroupLayout(groupLayoutPanel);
        GroupLayout layout1 = new GroupLayout(tablePanel);
        GroupLayout layout2 = new GroupLayout(inputOutputPanel);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout1.setAutoCreateGaps(true);
        layout1.setAutoCreateContainerGaps(true);

        layout2.setAutoCreateGaps(true);
        layout2.setAutoCreateContainerGaps(true);

        for(int i = 0; i < 12; i++) {
            textFieldArray[i] = new JTextField();
            textFieldArray[i].setText("value");
            textFieldArray[i].setEditable(false);

            labelArray[i] = new JLabel();
            labelArray[i].setText("Register " + rm.getRegister(registersNamesArray[i]).getName());
        }
        updateButton = new JButton();
        updateButton.setText("Update");
        updateButton.addActionListener(
                e -> {
                    try {
                        updateTextFields();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
        );
        //groupLayoutPanel.add(updateButton);

        closeButton = new JButton();
        closeButton.setText("Close");
        closeButton.addActionListener(
                e -> {
                    System.exit(0);
                }
        );


        table.setSize(new Dimension(130,100000));
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setPreferredWidth(10);
        table.getColumnModel().getColumn(2).setPreferredWidth(10);
        table.getColumnModel().getColumn(3).setPreferredWidth(10);
        table.getColumnModel().getColumn(4).setPreferredWidth(10);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(122,400));

        TableRowUtilities.addNumberColumn(table, 0, false);

        JTextField inputTextField = new JTextField("");
        JTextArea outputTextArea = new JTextArea("");

        outputTextArea.setEditable(false);
        outputTextArea.setLineWrap(true);
        outputTextArea.setWrapStyleWord(true);

        JScrollPane scrollTextArea = new JScrollPane(outputTextArea);
        scrollTextArea.setPreferredSize(new Dimension(250, 200));

        inputTextField.setPreferredSize(new Dimension(250, 27));


        inputTextButton = new JButton();
        outputTextButton = new JButton();

        modeButton = new JButton();
        modeButton.setText("Mode");
        modeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rm.getRegister(registersNamesArray[11]).getContentStr().equals("S")){
                    VMPanel();
                }

            }
        });
        panel.add(modeButton);
        inputTextButton.setText("Input");
        outputTextButton.setText("Output");

        inputTextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tmp = inputTextField.getText();

                //rm.input = inputTextField.getText();
                try {
                    String tmp1 = tmp.substring(0,7);
                    rm.input = tmp.substring(7);
                    rm.getInstructions().interpreter(tmp1);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
//                System.out.println(rm.input+"after try");
                inputTextField.setText("");
            }
        });
        outputTextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    try {
                        rm.getInstructions().write_to_output(12);
                        outputTextArea.setText(rm.output.toString());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
            }
        });

        exteriorMemoryButton = new JButton("Exterior memory");
        exteriorMemoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exteriorMemory();
            }
        });
        panel.add(exteriorMemoryButton);


        layout1.setHorizontalGroup(layout1.createSequentialGroup()
                        .addComponent(scrollPane)
        );
        layout1.setVerticalGroup(
                layout1.createSequentialGroup()
                .addComponent(scrollPane)
        );

        layout2.setHorizontalGroup(layout2.createSequentialGroup()
                .addGroup(layout2.createSequentialGroup()
                        .addGroup(layout2.createParallelGroup(
                                GroupLayout.Alignment.LEADING)
                                .addComponent(inputTextField)
                                .addComponent(scrollTextArea)
                        )
                )
                .addGroup(layout2.createSequentialGroup()
                        .addGroup(layout2.createParallelGroup(
                                GroupLayout.Alignment.LEADING)
                                .addComponent(inputTextButton)
                                .addComponent(outputTextButton)
                        )

                )
        );
        layout2.setVerticalGroup(
                layout2.createSequentialGroup()
                        .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(inputTextField)
                                .addComponent(inputTextButton)
                        )
                        .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(scrollTextArea)
                                .addComponent(outputTextButton)
                        )

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
        groupLayoutPanel.setLayout(layout);
        tablePanel.setLayout(layout1);
        inputOutputPanel.setLayout(layout2);
        panel.add(groupLayoutPanel);
        panel.add(tablePanel);
        panel.add(inputOutputPanel);
        this.getContentPane().add(panel);
    }

    private void exteriorMemory() {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("Exterior memory");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            try
            {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            JPanel panel = new JPanel();
            JButton updateButton1, closeButton1;

            Object rowData1[][] = new Object[100][5];
            Object columnNames1 [] = {"1","2","3","4","5"};

            JTable exMemoryTable = new JTable(rowData1, columnNames1);
            exMemoryTable.setSize(new Dimension(200,100000));
            exMemoryTable.getColumnModel().getColumn(0).setPreferredWidth(33);
            exMemoryTable.getColumnModel().getColumn(1).setPreferredWidth(33);
            exMemoryTable.getColumnModel().getColumn(2).setPreferredWidth(33);
            exMemoryTable.getColumnModel().getColumn(3).setPreferredWidth(33);
            exMemoryTable.getColumnModel().getColumn(4).setPreferredWidth(33);
            exMemoryTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

            JScrollPane scrollPane = new JScrollPane(exMemoryTable);
            scrollPane.setPreferredSize(new Dimension(212,400));

            TableRowUtilities.addNumberColumn(exMemoryTable, 0, false);
            panel.add(scrollPane);
            try {
                updateVMTable(table, rowData1);
            } catch (Exception e) {
                e.printStackTrace();
            }

            frame.getContentPane().add(BorderLayout.CENTER, panel);
            frame.pack();
            frame.setLocationByPlatform(true);
            frame.setVisible(true);
            frame.setResizable(false);
        });
    }

    private void VMPanel() {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("Virtual Machine");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            try
            {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            JPanel panel = new JPanel();
            JPanel vmRegisterPanel = new JPanel();
            JPanel vmTablePanel = new JPanel();
            JButton updateButton1, closeButton1;


/* TABLE VM */
            Object rowData1[][] = new Object[100][5];
            int counter = 0;
            for(int i = 0; i < 100; i++){
                for(int j = 0; j < 5;j++){
//                        rowData[i][j]= counter;
//                        counter++;
                }
            }
            Object columnNames1[] = {"1", "2", "3", "4", "5"};

            JTable vmTable = new JTable(rowData1, columnNames1);
            vmTable.setSize(new Dimension(200,100000));
            vmTable.getColumnModel().getColumn(0).setPreferredWidth(33);
            vmTable.getColumnModel().getColumn(1).setPreferredWidth(33);
            vmTable.getColumnModel().getColumn(2).setPreferredWidth(33);
            vmTable.getColumnModel().getColumn(3).setPreferredWidth(33);
            vmTable.getColumnModel().getColumn(4).setPreferredWidth(33);
            vmTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

            JScrollPane scrollPane = new JScrollPane(vmTable);
            scrollPane.setPreferredSize(new Dimension(212,400));

            TableRowUtilities.addNumberColumn(vmTable, 0, false);
            vmTablePanel.add(scrollPane);

            updateButton1 = new JButton("Update");
            closeButton1 = new JButton("Close");

            updateButton1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        updateVMTable(vmTable, rowData1);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
            closeButton1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                }
            });
/* END OF TABLE VM */

            vmRegisterPanel.setLayout(new GridBagLayout());
            getContentPane().add(vmRegisterPanel);

            GroupLayout layout = new GroupLayout(vmRegisterPanel);
            layout.setAutoCreateGaps(true);
            layout.setAutoCreateContainerGaps(true);

            for(int i = 0; i < 4; i++){
                vmLabelArray[i] = new JLabel();
                vmLabelArray[i].setText("Register " + rm.vm.getRegister(vmRegisterNamesArray[i]).getName());

                vmTextArray[i] = new JTextField(10);
                vmTextArray[i].setText(String.valueOf(rm.vm.getRegister(vmRegisterNamesArray[i]).getContentStr()));
                vmTextArray[i].setEditable(false);
            }
            layout.setHorizontalGroup(layout.createSequentialGroup()
                    .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(vmLabelArray[0])
                                    .addComponent(vmLabelArray[1])
                                    .addComponent(vmLabelArray[2])
                                    .addComponent(vmLabelArray[3])
                                    .addComponent(updateButton1)
                            )
                    )
                    .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(vmTextArray[0])
                                    .addComponent(vmTextArray[1])
                                    .addComponent(vmTextArray[2])
                                    .addComponent(vmTextArray[3])
                                    .addComponent(closeButton1)
                            )
                    )
            );
            layout.setVerticalGroup(
                    layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(vmLabelArray[0])
                                    .addComponent(vmTextArray[0])
                            )
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(vmLabelArray[1])
                                    .addComponent(vmTextArray[1])
                            )
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(vmLabelArray[2])
                                    .addComponent(vmTextArray[2])
                            )
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(vmLabelArray[3])
                                    .addComponent(vmTextArray[3])
                            )
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(updateButton1)
                                    .addComponent(closeButton1)
                            )
            );
            vmRegisterPanel.setLayout(layout);
            panel.add(vmRegisterPanel);
            panel.add(vmTablePanel);


            frame.getContentPane().add(BorderLayout.CENTER, panel);
            frame.pack();
            frame.setLocationByPlatform(true);
            frame.setVisible(true);
            frame.setResizable(false);

        });
    }

    private void updateVMTable(JTable vmTable, Object[][] row) throws Exception {
        int tmpInt = 0;
        Memory data = rm.vm.getData();
        data.put_block(10, "00034");
        data.put_block(11, "00025");
        String tmp = rm.vm.getData().mem();
        //rm.run();
        for(int i = 0; i < 100; i++){
            for(int j = 0; j < 5; j++){
                row[i][j] = tmp.charAt(tmpInt);
                tmpInt++;
                vmTable.repaint();
            }
        }
    }

    private void updateTextFields() throws Exception {
        for(int i = 0; i < 12; i++){
            textFieldArray[i].setText(String.valueOf(rm.getRegister(registersNamesArray[i]).getContentStr()));
        }
        int tmpInt = 0;
        Memory data = rm.getData();
        data.put_block(10, "00034");
        data.put_block(11, "00025");
        String tmp = rm.getData().mem();
        //rm.run();
        for(int i = 0; i < 1000; i++){
            for(int j = 0; j < 5; j++){
                rowData[i][j] = tmp.charAt(tmpInt);
                tmpInt++;
                table.repaint();
            }
        }
    }

    public static void main(String [] args){
        SwingUtilities.invokeLater(() -> new Gui().setVisible(true));
    }
}
