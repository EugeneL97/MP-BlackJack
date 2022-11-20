import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
	// Every time a player create a room, a new room is added to this array. This is how the server keeps track of which rooms are active.
	// This will also be how the game is updated. If a player selects hit, it sends a message to the server and the server modifies the
	// values of the player within the corresponding room the player is in using this attribute.
	private ArrayList<Room> rooms;
	
	// This class is used for sending to each client the so they have information to populate the lobby GUI and show which rooms are available
	// and which players are in which room.
	private LobbyRoom lobbyRooms;
	
	public Server() {
		this.rooms = new ArrayList<Room>();
		this.lobbyRooms = new LobbyRoom();
	}
	
	public static void main(String [] args) throws Exception {
		// create a new instance of Server class. We will pass this variable to the ClientHandler object so that each thread for the client
		// can access the server attributes ArrayList<Room< rooms and LobbyRoom lobbyRooms in order to update it with changes based on player
		// input.
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
	
	// Each thread use this class and execute the method run()
	private static class ClientHandler implements Runnable {
		private final Socket clientSocket;
		private ObjectInputStream input;
		private ObjectOutputStream output;
		private Server server; // use this attribute to update the server's attributes ArrayList<Room> rooms and LobbyRoom lobbyRooms.
		private Player player; // use this to keep track of what this player is doing.
		
		public ClientHandler(Socket socket, Server server) {
			this.clientSocket = socket;
			this.server = server;
		}
		
		@Override
		public void run() {		
			try {
				output = new ObjectOutputStream(clientSocket.getOutputStream());
				input = new ObjectInputStream(clientSocket.getInputStream());

				boolean proceedToLobby = false; // Used in conjunction with loginCount to continue the login/register loop
				int loginCount = 0; // Count the number of login attempts. Close connection to client if it exceeds 3 attempts 
				boolean logout = false; // Close connection if this value is true. If player sends a logout message, set this to true
				
				
				// The login/register loop. Continue to loop unless login attempt is greater than 3 or proceedToLobby is set to true
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
				
				// If there are 3 failed login attempts close the connection and end the thread.
				if (loginCount >= 3) {
					closeConnection();
					System.out.println("Too many failed login attempts. Closing connection.");
					return;
				}
				
				// Instantiate a parser object to convert client message that contains a class into an instance of the class.
				Parser parser = new Parser();
				
				// Loop for when a player's in the lobby.
				while(!logout) {
					Message message = null;
					message = getMessage(message);
					
					switch (message.getType()) {
						case "create room":
							
							break;
						case "join room":
							Room room = parser.parseRoom(message.getText());
							System.out.println("Room object received = " + room.toString());
							break;
						case "room":
							room = parser.parseRoom(message.getText());
							System.out.println("Room object received = " + room.toString());
						case "lobby room":
							LobbyRoom lobbyRoom = parser.parseLobbyRoom(message.getText());
							System.out.println(lobbyRoom.toString());
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
		
		
		// Closes connection from client
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
		
		
		// Receive a message to the client
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
		
		// Sends a message to the client
		public void sendMessage(Message message) {
			try {
				output.writeObject(message);
				output.flush();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		// Login function
		public boolean login(Message message) {
			boolean login = false;
			
			System.out.println("Received login message from client " + clientSocket.getInetAddress().getHostAddress());
			login = verifyLogin(message.getText());
			
			if(login) {
				System.out.println("Client " + clientSocket.getInetAddress().getHostAddress() + " login successful\nSending successful login reply back to client " + clientSocket.getInetAddress().getHostAddress());
				message = new Message("login", "success", "");
				sendMessage(message);
			}
			else {
				System.out.println("Client " + clientSocket.getInetAddress().getHostAddress() + " login failed\nSending failed login reply back to client " + clientSocket.getInetAddress().getHostAddress());
				message = new Message("login", "failed", "");
				sendMessage(message);
			}
			
			return login;
		}
		
		// Helper function for the login function to see if username and password matches
		// any line from the database.txt
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

		// Register function
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
		
		// Helper function for the register function to check if user already exists in database.txt
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
					
					// Check if username matches any line from database.txt.
					// If there is a match, exit function and return true
					if (tmpUsername.equals(username)) {
						myReader.close();
						return true;
					}
									
					fileInput = myReader.readLine();
				}
				
				// If there's no match then create the new user
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