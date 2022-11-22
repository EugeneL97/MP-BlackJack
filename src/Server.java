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
	
	// newMessage attribute lets the server know if any one of the ClientHandler threads has received a message from
	// their respective clients after they've entered the lobby room loop and beyond. If a ClientHandler thread received a 
	// message from their respective clients, then set this variable to true. At the end of each loop in the lobby room,
	// game room, or playing loop, check if newMessage variable is set to true. If it is true, then each thread will send the
	// current state of the game, which includes an instance of the server's ArrayList<Room> rooms and the LobbyRoom lobbyRooms
	// object to the client, so that the client may update their GUI based on which window they're on. For example, if they're
	// in the lobby GUI, they will only use the LobbyRoom lobbyRooms object to update their GUI. If they're inside the game room
	// GUI they will use the Room room object to update their GUI.
	private boolean newMessage;
	
	// Max number of rooms
	private final int MAX_ROOMS = 5;
	
	public Server() {
		this.rooms = new ArrayList<Room>();
		this.lobbyRooms = new LobbyRoom();
		this.newMessage = false;
		
		for (int x = 0; x < MAX_ROOMS; ++x) {
			this.rooms.add(new Room(x));
		}
	}
	
	public static void main(String [] args) throws Exception {
		// Create a new instance of Server class. We will pass this variable to the ClientHandler object so that each thread for the client
		// can access the server attributes ArrayList<Room< rooms and LobbyRoom lobbyRooms in order to update it with changes based on player
		// input.
		Server server = new Server();
		
		int port = 59898; // Port to use
		ServerSocket serverSocket = new ServerSocket(port); // opening up port to listen for connection
		serverSocket.setReuseAddress(true);
		
		// Get real ip address
		URL url = new URL("http://checkip.amazonaws.com/");
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		
		// Output ip and local host address
		System.out.println("Server is now running on - \nIP address: " + reader.readLine() + ":" + Integer.toString(port) + "\nLocal Host: " + serverSocket.getInetAddress().getLocalHost() + ":" + Integer.toString(port));
		
		GameHandler gameHandler = new GameHandler(server.rooms);
		
		// Spawn new thread to handle games
		new Thread(gameHandler).start();
		
		// Accept connections
		while (true) {
			Socket incomingConnection = serverSocket.accept();
			System.out.println("New client connected " + incomingConnection.getInetAddress().getHostAddress());
			System.out.flush();
			ClientHandler clientSocket = new ClientHandler(incomingConnection, server);
			
			// Spawn new thread for each conneciton accepted
			new Thread(clientSocket).start();
			
		}
	}
	
	
	// This class will handle all the games that are running in each room
	private static class GameHandler implements Runnable {
		ArrayList<Room> rooms;
		public GameHandler (ArrayList<Room> rooms) {
			this.rooms = rooms;
		}
		
		@Override
		public void run() {
			while(true) {
				for (int x = 0; x < rooms.size(); ++x) {
					switch (rooms.get(x).getReadyToStart()) {
						// if room is not ready to start, check to see if any player's state = 3.
						// if a player's state = 3, then that means the game should start, therefore, change the
						// the room's state = 1.
						case 0:
							for (int y = 0; y < rooms.get(x).getPlayersInRoom().size(); ++y) {
								if (rooms.get(x).getPlayersInRoom().get(y).getPlayerState() == 3) {
									rooms.get(x).setReadyToStart(1);
								}
							}
							
							break;
						case 1:
							for (int y = 0; y < rooms.get(x).getPlayersInRoom().size(); ++y) {
								if (rooms.get(x).getPlayersInRoom().get(y).getPlayerState() == 3) {
									switch (rooms.get(x).getPlayersInRoom().get(y).getCurrentAction()) {
										// Deal Card
										case 0:
											break;
											
									}
								}
							}
					}
				}
			}
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
				while(!proceedToLobby) {
					Message message = null;
					message = getMessage(message);
					
					switch (message.getType()) {
						case "login":
							++loginCount;
							proceedToLobby = login(message);
							// If there are 3 failed login attempts close the connection and end the thread.
							if (loginCount >= 3) {
								closeConnection();
								System.out.println("Too many failed login attempts. Closing connection.");
								return;
							}
							
							break;
						
						case "register":
							register(message);
							break;
					}
				}
				
				
				
				// Instantiate a parser object to convert client message that contains a class into an instance of the class.
				Parser parser = new Parser();
				
				
				// Loop for when a player's in the lobby.
				while(!logout) {
					System.out.println("Inside game lobby loop\n");
					
					Message message = null;
					message = getMessage(message);
					
					switch (message.getType()) {
						
						 // This is the case where a player is in the lobby and clicks join room. The player will send a message
						 // of new Message("join room", "roomNumber", ""). The server will send back a room class with the player
						 // in the ArrayList<Player> playersInRoom. Client can make a copy of their player object from the room
						 // into their client side player object. Also, use the all the users on the playersInRoom list to populate
						 // the GUI with cards and what not 
						case "join room":
							System.out.println("Received join room message\n");
							int roomNumber = Integer.parseInt(message.getStatus());
							// Assigning player to the room number provided
							player.setRoomNumber(roomNumber);
							
							// State = 0 means player's in the lobby. State = 1 means player's in a room
							// State = 2 means player's sitting down and playing.
							player.setPlayerState(1);
							
							// Adding player to the server's attribute ArrayList<Room> rooms
							server.getRooms().get(roomNumber).addPlayer(player);
							
							// Adding player's name to the server's attribute LobbyRoom lobbyRoom
							server.lobbyRooms.addPlayer(roomNumber, player.getUsername());
							
							// Setting new message received by client to true
							server.setNewMessage(true);
							
							// Since newMessage was just set to true, send the class objects to the client.
							checkNewMessage();
							
							// Execute inGameRoom() function
							inGameRoom();
							
							break;
							
						case "logout":
							message = new Message("logout", "success", "");
							updatePlayer();
							logout = true;
							
							// Setting new message received by client to true
							server.setNewMessage(true);
							
						default:
							
					}
					checkNewMessage();
				}


				
				
				
				
				
				System.out.println("Thread closing.");
				closeConnection();
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
		
		
		
		public void inGameRoom() {
			System.out.println("Inside game room loop\n");
			
			// Get the player's room number
			int roomNumber = player.getRoomNumber();
			
			// Keep looping while player state = 1, meaning player is in a game room
			while(player.getPlayerState() == 1) {
				Message message = null;
				
				System.out.println("Inside while loop of game room\n");
				
				// wait for player to send a message
				message = getMessage(message);
				
				switch (message.getType()) {
					// Player sits down to start playing game
					case "sit":
						// Player has sat down but not actively playing
						player.setPlayerState(2);
						
						// Setting new message received by client to true
						server.setNewMessage(true);
						
						// Since newMessage was just set to true, send the class objects to the client.
						checkNewMessage();
						
						// Start playing the game
						playGame();
				
						break;
						
					// Player chooses to leave the room
					case "leave room":
						player.setPlayerState(0);
						
						// Removing player to the server's attribute ArrayList<Room> rooms
						server.getRooms().get(roomNumber).removePlayer(player);
						
						// Removing player's name to the server's attribute LobbyRoom lobbyRoom
						server.lobbyRooms.removePlayer(roomNumber, player.getUsername());
						
						// Setting new message received by client to true
						server.setNewMessage(true);
						
						// Return to calling function, the lobby loop.
						return;
						
					default:
				}
				checkNewMessage();
			}
		}
		
		private void playGame() {
			int roomNumber = player.getRoomNumber();
			
			// keep looping while player is sitting at the table
			while (player.getPlayerState() == 2) {
				Message message = null;
				message = getMessage(message);
				
				switch (message.getType()) {
					// Player chooses to participate in the room by clicking deal
					case "deal":
						// Get player's wager amount and deduct it from the player balance
						player.setWager(Integer.parseInt(message.getStatus()));
						
						// Client needs to check that current player's balance equals or exceeds the wager amount.
						player.setAccountBalance(player.getAccountBalance() - player.getWager());
						
						// Deal first two cards to player
						player.setCurrentAction(0);
						
						// Setting new message received by client to true
						server.setNewMessage(true);
						
						break;
						
					// Player sits down to start playing game
					case "sit out":
						// Set player state to skip current round
						player.setCurrentAction(4);
						
						// Setting new message received by client to true
						server.setNewMessage(true);
				
						break;
						
					// Player chooses to leave the room
					case "leave room":
						// Player wants to leave the game room
						player.setPlayerState(0);
						
						// Reset player's current action to default value of 4
						player.setCurrentAction(4);
						
						// Removing player to the server's attribute ArrayList<Room> rooms
						server.getRooms().get(roomNumber).removePlayer(player);
						
						// Removing player's name to the server's attribute LobbyRoom lobbyRoom
						server.lobbyRooms.removePlayer(roomNumber, player.getUsername());
						
						
						// update player account balance in the database
						updatePlayer();
						
						// Setting new message received by client to true
						server.setNewMessage(true);
						
						// Since newMessage was just set to true, send the class objects to the client.
						checkNewMessage();
						
						// Return to the calling function
						return;
						
					// Player wants to double the bet
					case "double down":
						// Doubling the player's wager
						player.setWager(player.getWager() * 2);
						
						// Setting new message received by client to true
						server.setNewMessage(true);
				}
				
				checkNewMessage();
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
					
					try {
						Thread.sleep(100);
					}
					catch (Exception e) {
						
					}
				}

				return message;
			}
			catch (Exception e) {
				// removed e.printStackTrace(); so that it won't print annoying messages when it expects to receive a message
				// but there isn't one in the pipe.
			}
			
			return message;
		}
		
		// Sends a message to the client
		public void sendMessage(Message message) {
			try {
				output.writeObject(message);
				//output.flush();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		// Checks if server's attribute newMessage. If it's set to true, it will send to the client an instance of
		// the server's lobbyRoom and rooms object.
		public void checkNewMessage() {
			if (server.getNewMessage()) {
				Message message = new Message("lobby room", "", server.getLobbyRooms().toString());
				sendMessage(message);
				int playerIndex = -1;
				
				for (int x = 0; x < server.getRooms().get(player.getRoomNumber()).getPlayersInRoom().size(); ++x) {
					if (server.getRooms().get(player.getRoomNumber()).getPlayersInRoom().get(x).getUsername().equals(player.getUsername())) {
						playerIndex = x;
					}
				}
				message = new Message("room", Integer.toString(playerIndex), server.getRooms().get(player.getRoomNumber()).toString());
				sendMessage(message);
				server.setNewMessage(false);
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
		
		// Updates player balance in database.txt before loging off
		public void updatePlayer() {
			try {
				// Open database file
				File file = new File(System.getProperty("user.dir") + "/database.txt");
				
				// Reader for database file
				BufferedReader myReader = new BufferedReader(new FileReader(file));
				
				// String to store the database
				ArrayList<String> database = new ArrayList<String>();

				// read first line from database.txt	
				String fileInput = myReader.readLine();
				
				// To store database changes output
				String output = "";
				
				// To store tokenized strings
				String [] tmp;
				
				player.setAccountBalance(-111);
				
				while (fileInput != null) {
					tmp = fileInput.split("#");
					
					if (tmp[0].equals(player.getUsername())) {
						tmp[2] = Integer.toString(player.getAccountBalance());
						fileInput = tmp[0] + "#" + tmp[1] + "#" + tmp[2];
					}
					
					database.add(fileInput);
					System.out.println(fileInput);
					fileInput = myReader.readLine();
				}
				
				// If there's no match then create the new user
				FileWriter myWriter = new FileWriter(file);
				
				for (int x = 0; x < database.size(); ++x) {
					output += database.get(x);
					
					if (x != database.size() - 1) {
						output += "\n";
					}
				}
				
				// Rewrite database
				System.out.println("Writing to file: " + output);
				myWriter.write(output);
				
				myReader.close();	
				myWriter.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	public ArrayList<Room> getRooms() {
		return this.rooms;
	}
	
	public LobbyRoom getLobbyRooms() {
		return this.lobbyRooms;
	}
	
	public Boolean getNewMessage() {
		return this.newMessage;
	}
	
	public void setNewMessage(Boolean newMessage) {
		this.newMessage = newMessage;
	}
}