import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
	private ArrayList<Room> rooms;
	
	public Server() {
		this.rooms = new ArrayList<Room>();
	}
	
	public static void main(String [] args) throws Exception {
		Server server = new Server();
		
		int port = 59898; // port to use
		ServerSocket serverSocket = new ServerSocket(port); // opening up port to listen for connection
		serverSocket.setReuseAddress(true);
		
		// get real ip address
		URL url = new URL("http://checkip.amazonaws.com/");
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		
		// output ip and local host address
		System.out.println("Server is now running on - \nIP address: " + reader.readLine() + ":" + Integer.toString(port) + "\nLocal Host: " + serverSocket.getInetAddress().getLocalHost() + ":" + Integer.toString(port));
		
		// accept connections
		while (true) {
			Socket incomingConnection = serverSocket.accept();
			System.out.println("New client connected " + incomingConnection.getInetAddress().getHostAddress());
			System.out.flush();
			ClientHandler clientSocket = new ClientHandler(incomingConnection, server);
			
			// spawn new thread for each conneciton accepted
			new Thread(clientSocket).start();
		}
	}
	
	private static class ClientHandler implements Runnable {
		private final Socket clientSocket;
		private ObjectInputStream input;
		private ObjectOutputStream output;
		private Server server;
		private Player player;
		
		public ClientHandler(Socket socket, Server server) {
			this.clientSocket = socket;
			this.server = server;
		}
		
		@Override
		public void run() {		
			try {
				output = new ObjectOutputStream(clientSocket.getOutputStream());
				input = new ObjectInputStream(clientSocket.getInputStream());

				boolean proceedToLobby = false;
				boolean userInDatabase = true;
				boolean logout = false;
				int loginCount = 0;
				
				while(!proceedToLobby && loginCount < 3) {
					Message message = null;
					message = getMessage(message);
					
					switch (message.getType()) {
						case "login":
							++loginCount;
							proceedToLobby = login(message);
							break;
						
						case "register":
							register(message);
							break;
					}
				}
				
				if (loginCount >= 3) {
					closeConnection();
					System.out.println("Too many failed login attempts. Closing connection.");
					return;
				}
				
				Parser parser = new Parser();
				
				while(!logout) {
					Message message = null;
					message = getMessage(message);
					
					switch (message.getType()) {
						case "create room":
							break;
						case "player":
							Player player = parser.parsePlayer(message.getText());
							System.out.println("Player object received = " + player.toString());
							break;
						case "room":
							Room room = parser.parseRoom(message.getText());
							System.out.println("Room object received = " + room.toString());
					}
				}
				
				
				
				
				
				System.out.println("Thread closing.");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					Thread.sleep(2);
					input.close();
					output.close();
					clientSocket.close();
				}
				catch(Exception e) {
					e.printStackTrace();
				}	
			}
		}
		
		public void closeConnection() {
			try {
				input.close();
				output.close();
				clientSocket.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}	
		}
		
		public Message getMessage(Message message) {
			try {
				while (message == null) {
					message = (Message) input.readObject();
				}

				return message;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
			return message;
		}
		
		public void sendMessage(Message message) {
			try {
				output.writeObject(message);
				output.flush();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public boolean login(Message message) {
			boolean login = false;
			
			System.out.println("Received login message from client " + clientSocket.getInetAddress().getHostAddress());
			login = verifyLogin(message.getText());
			
			if(login) {
				System.out.println("Client " + clientSocket.getInetAddress().getHostAddress() + " login successful\nSending successful login reply back to client " + clientSocket.getInetAddress().getHostAddress());
				message = new Message("login", "success", "");
				sendMessage(message);
				System.out.println("Message outside getMessage = " +  message.getType() + " " + message.getStatus() + " " + message.getText());
			}
			else {
				System.out.println("Client " + clientSocket.getInetAddress().getHostAddress() + " login failed\nSending failed login reply back to client " + clientSocket.getInetAddress().getHostAddress());
				message = new Message("login", "failed", "");
				sendMessage(message);
			}
			
			return login;
		}
		
		public boolean verifyLogin(String loginString) {
			String fileInput = null;
			String [] loginInfo;
			String username = null;
			String password = null;
			File file = new File(System.getProperty("user.dir") + "/database.txt");
			System.out.println("loginString = " +  loginString);

			try {
				BufferedReader myReader = new BufferedReader(new FileReader(file));
				
				// tokenize loginString
				loginInfo = loginString.split("#");
				username = loginInfo[0];
				password = loginInfo[1];

				// read first line from database.txt	
				fileInput = myReader.readLine();
				
				while (fileInput != null) {	
					// tokenize line from database.txt
					loginInfo = fileInput.split("#");
					String tmpUsername = loginInfo[0];
					String tmpPassword = loginInfo[1];
					String tmpBalance = loginInfo[2];
					
					// check if username and password match any line from database.txt
					if (tmpUsername.equals(username) && tmpPassword.equals(password)) {
						// if it's a match, create a new player class
						this.player = new Player(tmpUsername, Integer.parseInt(tmpBalance));
						return true;
					}
									
					fileInput = myReader.readLine();
				}
				myReader.close();
				
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			return false;
		}

		public void register(Message message) {
			boolean userInDatabase;
			
			System.out.println("Received register message from client " + clientSocket.getInetAddress().getHostAddress());
			userInDatabase = verifyRegister(message.getText());
			
			if (userInDatabase) {
				System.out.println("Client " + clientSocket.getInetAddress().getHostAddress() + " attempted to register an already existing user.\nSending failed register reply back to client " + clientSocket.getInetAddress().getHostAddress());
				message = new Message("register", "failed", "Player already exists. Please try again.");
				sendMessage(message);
			}
			else {
				System.out.println("Client registered a new user. Sending successful register reply back to client " + clientSocket.getInetAddress().getHostAddress());
				message = new Message("register", "success", "New player registered");
				sendMessage(message);
			}
		}
		
		public boolean verifyRegister(String registerString) {
			String fileInput = null;
			String [] loginInfo;
			String username = null;
			String password = null;
			String output = null;
			File file = new File(System.getProperty("user.dir") + "/database.txt");
			
			try {
				BufferedReader myReader = new BufferedReader(new FileReader(file));
				// tokenize loginString
				loginInfo = registerString.split("#");
				username = loginInfo[0];
				password = loginInfo[1];

				// read first line from database.txt	
				fileInput = myReader.readLine();
				
				while (fileInput != null) {	
					// tokenize line from database.txt
					loginInfo = fileInput.split("#");
					String tmpUsername = loginInfo[0];
					String tmpPassword = loginInfo[1];
					// check if username and password match any line from database.txt
					if (tmpUsername.equals(username)) {
						myReader.close();
						return true;
					}
									
					fileInput = myReader.readLine();
				}
				
				FileWriter myWriter = new FileWriter(file, true);
				
				// build player info string with default $50k
				output = username + "#" + password + "#" + "50000\n";
				
				// append new user to database.txt
				System.out.println("Writing to file: " + output);
				myWriter.write(output);
				
				myReader.close();	
				myWriter.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			
			return false;
		}
	}	
}