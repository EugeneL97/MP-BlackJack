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

public class RegisterGUI {
    private JFrame registerFrame = new JFrame("RegisterGUI");
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JFrame frameRegisterSystem;
    private Client client;

    public static void main (String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Client client = new Client();
                    RegisterGUI window = new RegisterGUI(client);
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

    public RegisterGUI() {
        startUp();
    }

    public RegisterGUI(Client client) {
        startUp();
        this.client = client;
    }

    public void startUp() {
        registerFrame = new JFrame();
        registerFrame.setBounds(200, 200, 500, 300);
        registerFrame.setSize(900, 650);
        registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registerFrame.getContentPane().setLayout(null);
        //LayoutManager layout = new FlowLayout();
        registerFrame.setTitle("BlackJack Register Window");
        registerFrame.setResizable(false);
        registerFrame.getContentPane().setBackground(Color.lightGray);

        JLabel signUpLabel = new JLabel ("Create New Account");
        signUpLabel.setBounds(250, 110, 400, 140);
        registerFrame.getContentPane().add(signUpLabel);
        signUpLabel.setFont(new Font("Dialogue", Font.BOLD, 40));

        JLabel createUsernameLabel = new JLabel ("Create Username:");
        createUsernameLabel.setBounds(240, 253, 125, 20);
        registerFrame.getContentPane().add(createUsernameLabel);

        JLabel createPasswordLabel = new JLabel ("Create Password:");
        createPasswordLabel.setBounds(240, 303, 125, 20);
        registerFrame.getContentPane().add(createPasswordLabel);

        txtUsername = new JTextField(20);
        txtUsername.setBounds(350, 250, 200, 30);
        registerFrame.getContentPane().add(txtUsername);
        //txtUsername.setColumns(10);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(350, 300, 200, 30);
        registerFrame.getContentPane().add(txtPassword);

        JButton signInButton = new JButton("Sign Up");
        signInButton.setBounds(275, 375, 150, 45);
        registerFrame.getContentPane().add(signInButton);

        signInButton.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String password = txtPassword.getText();
                String username = txtUsername.getText();

                register();

            }

        });


        JButton exitButton = new JButton("Done");
        exitButton.setBounds(475, 375, 150, 45);
        registerFrame.getContentPane().add(exitButton);

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frameRegisterSystem = new JFrame("Done");
                if (JOptionPane.showConfirmDialog(frameRegisterSystem,  "Confirm done?", "Done", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                    registerFrame.setVisible(false);
                    registerFrame.dispose();
                    System.out.println("Client ip = " + client.socket);

                    RegisterOrLoginGUI window = new RegisterOrLoginGUI(client);
                    window.getRegisterFrame().setLocationRelativeTo(null); // center on screen
                    window.getRegisterFrame().setVisible(true); // make visible
                }
            }
        });

    }

    public void register() {
        // Get user input from LoginGUI
        String password = txtPassword.getText();
        String username = txtUsername.getText();

        // Execute login by sending a login message to the server
        client.register(username, password);

        // Busy wait until server gets the message, responds, then server adjusts the value of client.login to either 1 or -1
        while(client.getRegister() == 0) {
            System.out.println("client register = " + client.getRegister());
        }

        // Login success, create LobbyGUI and close LoginGUI
        if (client.getRegister() == 1) {
            System.out.println("client register = " + client.getRegister());

            JOptionPane.showMessageDialog(null, "Registration successful", "Registration", JOptionPane.ERROR_MESSAGE);


        }
        // Login failed. Reset client.login = 0 so the busy wait loop will work again.
        else if (client.getRegister() == -1){
            JOptionPane.showMessageDialog(null, "Registration failed", "Registration", JOptionPane.ERROR_MESSAGE);
            txtPassword.setText(null);
            txtUsername.setText(null);
            client.setRegister(0);
        }
    }
}