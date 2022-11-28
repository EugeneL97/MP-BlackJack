package main;

import java.io.*;
import javax.swing.*;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

public class RegisterOrLoginGUI {
    //private JFrame registerFrame;
    private JFrame registerFrame = new JFrame("RegisterOrLoginGUI");
    private JFrame frameRegisterOrLoginSystem;
    private Client client;

    public static void main (String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Client client = new Client();
                    RegisterOrLoginGUI window = new RegisterOrLoginGUI(client);
                    window.registerFrame.setLocationRelativeTo(null); // center on screen
                    window.registerFrame.setVisible(true); // make visible
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public JFrame getRegisterFrame() {
        return this.registerFrame;
    }
    public RegisterOrLoginGUI() {
        startUp();
    }

    public RegisterOrLoginGUI(Client client) {
        startUp();
        this.client = client;
    }

    private void startUp() {
        // Setting Frame Specifications
        registerFrame = new JFrame();
        registerFrame.setBounds(200, 200, 500, 300);
        registerFrame.setSize(900, 650);
        registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registerFrame.getContentPane().setLayout(null);
        registerFrame.setTitle("BlackJack Register or Login");
        registerFrame.setResizable(false);
        registerFrame.getContentPane().setBackground(Color.lightGray);



        JLabel signUpLabel = new JLabel ("Play BlackJack");
        signUpLabel.setBounds(300, 110, 400, 140);
        registerFrame.getContentPane().add(signUpLabel);
        signUpLabel.setFont(new Font("Dialogue", Font.BOLD, 40));


        JButton goToRegisterButton = new JButton("Register");
        goToRegisterButton.setBounds(375, 250, 150, 45);
        registerFrame.getContentPane().add(goToRegisterButton);

        goToRegisterButton.addActionListener (new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                // link this button to RegisterGUI
                RegisterGUI window = new RegisterGUI(client);
                client.setRegisterGUI(window);
                client.getRegisterGUI().getRegisterFrame().setLocationRelativeTo(null); // center on screen
                client.getRegisterGUI().getRegisterFrame().setVisible(true); // make visible
                registerFrame.setVisible(false);
                registerFrame.dispose();
            }

        });



        JButton goToLoginButton = new JButton("Login");
        goToLoginButton.setBounds(375, 300, 150, 45);
        registerFrame.getContentPane().add(goToLoginButton);

        goToLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginGUI window = new LoginGUI(client);
                client.setLoginGUI(window);
                client.getLoginGUI().getLoginFrame().setLocationRelativeTo(null); // center on screen
                client.getLoginGUI().getLoginFrame().setVisible(true); // make visible
                registerFrame.setVisible(false);
                registerFrame.dispose();

            }

        });


        JButton logOffButon = new JButton("Log Off");
        logOffButon.setBounds(375, 350, 150, 45);
        registerFrame.getContentPane().add(logOffButon);

        logOffButon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frameRegisterOrLoginSystem = new JFrame("Exit");
                if (JOptionPane.showConfirmDialog(frameRegisterOrLoginSystem,  "Confirm exit?", "Exit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                    System.exit(0);
                }
            }
        });
    }
}