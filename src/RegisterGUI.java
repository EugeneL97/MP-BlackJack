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
			
			
			if (client.register(username, password)) {
				// if true, call the registerOrLoginGUI
				
				// if false put 'username already taken' message
				
			}
//				if (password.equals("pass") && username.equals("user")) {
//					txtPassword.setText(null);
//					txtUsername.setText(null);
//				} else {
//					
//					//JOptionPane.showConfirmDialog(null, "Confirm entered username and password", "Confirm New User Credentials", JOptionPane.OK_OPTION());
//					JOptionPane.showMessageDialog(null, "Invalid Username and/or Password", "Login Error", JOptionPane.ERROR_MESSAGE);
//					txtPassword.setText(null);
//					txtUsername.setText(null);
//				}
			}
			
			});
			
			
			JButton exitButton = new JButton("Exit");
			exitButton.setBounds(475, 375, 150, 45);
			registerFrame.getContentPane().add(exitButton);
			
			exitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					frameRegisterSystem = new JFrame("Exit");
					if (JOptionPane.showConfirmDialog(frameRegisterSystem,  "Confirm exit?", "Exit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
						System.exit(0);
					}
				}
			});
			
		}
	}