package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CARDS {
    private JPanel panel1;
    private JButton button1;


    public CARDS() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null,"Hallo world!");
            }
        });
    }

    public static void main(String args[]) {
        JFrame frame = new JFrame();
        frame.setContentPane(new CARDS().panel1);
        frame.setDefaultCloseOperation
                (JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);

    }
    private void createUIComponents() {
    }

}
