import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginGUI {
	
	//private JFrame loginFrame;
	private JFrame loginFrame = new JFrame("LoginGUI");
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JFrame frameLoginSystem;
	private Client client;
	
	public JFrame getLoginFrame() {
		return loginFrame;
	}
	
	// Clicking on Login creates a LoginHandler which runs on another thread
	private static class LoginHandler implements Runnable {
		private Client client;
		private JTextField txtUsername;
		private JPasswordField txtPassword;
		private JFrame loginFrame;
		
		// Passing attributes from LoginGUI so that the new thread can control the closing of the frame and the texts from
		// the login and password fields
		public LoginHandler(Client client, JTextField txtUsername, JPasswordField txtPassword, JFrame loginFrame) {
			this.client = client;
			this.txtUsername = txtUsername;
			this.txtPassword = txtPassword;
			this.loginFrame = loginFrame;
		}
		
		@Override
		public void run() {
			// Get user input from LoginGUI
			String password = txtPassword.getText();
			String username = txtUsername.getText();
			
			// Execute login by sending a login message to the server
			client.login(username, password);
			
			// Busy wait until server gets the message, responds, then server adjusts the value of client.login to either 1 or -1
			while(client.getLogin() == 0) {
				System.out.println("client login = " + client.getLogin());
			}	
			
			// Login success, create LobbyGUI and close LoginGUI
			if (client.getLogin() == 1) {
				System.out.println("client login = " + client.getLogin());
				
				new LobbyGUI(client).setupLobbyPanel(); 
				loginFrame.dispose();
				
			}
			// Login failed. Reset client.login = 0 so the busy wait loop will work again.
			else if (client.getLogin() == -1){
				JOptionPane.showMessageDialog(null, "Invalid Username and/or Password", "Login Error", JOptionPane.ERROR_MESSAGE);
				txtPassword.setText(null);
				txtUsername.setText(null);
				client.setLogin(0);
			}
		}
	}
	public static void main (String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String ip = "127.0.1.1";
					int port = 59898;
					Client client = new Client(ip, port);
					LoginGUI window = new LoginGUI(client);
					window.loginFrame.setLocationRelativeTo(null); // center on screen
					window.loginFrame.setVisible(true); // make visible
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public LoginGUI() {
		startUp();
	}
	
	public LoginGUI(Client client) {
		startUp();
		this.client = client;
	}
	
	public void giveFalse(Boolean time) {
		time = false;
	}
	
	public void startUp() {
		loginFrame = new JFrame();
		loginFrame.setBounds(200, 200, 500, 300);
		loginFrame.setSize(900, 650);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.getContentPane().setLayout(null);
		//LayoutManager layout = new FlowLayout();
		loginFrame.setTitle("BlackJack Login Window");
		loginFrame.setResizable(false);
		loginFrame.getContentPane().setBackground(Color.lightGray);
		
		JLabel signInLabel = new JLabel ("Sign In");
		signInLabel.setBounds(375, 110, 150, 140);
		loginFrame.getContentPane().add(signInLabel);
		signInLabel.setFont(new Font("Dialogue", Font.BOLD, 40));
		
		JLabel usernameLabel = new JLabel ("Enter Username:");
		usernameLabel.setBounds(250, 253, 100, 20);
		loginFrame.getContentPane().add(usernameLabel);
		
		JLabel passwordLabel = new JLabel ("Enter Password:");
		passwordLabel.setBounds(250, 303, 100, 20);
		loginFrame.getContentPane().add(passwordLabel);
		
		txtUsername = new JTextField(20);
		txtUsername.setBounds(350, 250, 200, 30); 
		loginFrame.getContentPane().add(txtUsername);
		//txtUsername.setColumns(10);
				
		txtPassword = new JPasswordField();
		txtPassword.setBounds(350, 300, 200, 30);
		loginFrame.getContentPane().add(txtPassword);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener (new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
		
		String password = txtPassword.getText();
		String username = txtUsername.getText();
	
		// I think event handlers work on a separate thread, therefore it immediately executes instructions when
		// event occurs. Therefore, I devised a way in which the event handler could execute immediately but the conditional
		// response message from the server could still be checked before executing LobbyRoomGUI by spawning another thread to handle
		// the condition checking.
		LoginHandler loginHandler = new LoginHandler(client, txtUsername, txtPassword, loginFrame);
		new Thread(loginHandler).start();
		
		}
		
		});
		loginButton.setBounds(275, 375, 150, 45);
		loginFrame.getContentPane().add(loginButton);
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frameLoginSystem = new JFrame("Exit");
				// if (JOptionPane.showConfirmDialog(frameLoginSystem,  "Return to Homepage?", "Exit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
				if (JOptionPane.showConfirmDialog(frameLoginSystem,  "Confirm Exit?", "Exit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		exitButton.setBounds(475, 375, 150, 45);
		loginFrame.getContentPane().add(exitButton);
	}
}
