import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
	public Socket socket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private Scanner userInput;
	private Player player;
	private LobbyRoom lobbyRoom;
	private Room room;
	private ArrayList<Message> messageQueue;
	private int login;
	
	public Client() throws Exception {
		this.socket = null;
		this.input = null;
		this.output = null;
		this.userInput = null;
		this.messageQueue = new ArrayList<Message>();
		this.room = new Room(-1);
		this.login = 0;
	}
	
	public Client(String ip, int port) throws Exception {
		this.socket = new Socket(ip, port);
		this.input = new ObjectInputStream(socket.getInputStream());
		this.output = new ObjectOutputStream(socket.getOutputStream());
		this.userInput = new Scanner(System.in);
		this.messageQueue = new ArrayList<Message>();
		this.room = new Room(-1);
		this.login = 0;
		
		// Create a message handler for the client
		MessageHandler messageHandler = new MessageHandler(this);
		
		// Spawn a new thread for the client
		new Thread(messageHandler).start();
	}
	
	public static void main(String [] args) throws Exception {
		//String ip = "127.0.1.1";
		//int port = 59898;
		// Create a new instance of client
		Client client = new Client();
		
		// check to see if correct arguments were provided
		if (args.length != 2) {
			System.out.println("Please enter IP address followed by port number after java Client.java. i.e. java Client.java 127.0.0.1 7777\n");
			return;
		}

		System.out.println("Attemping to connect to server IP: " + args[0] + ":" + args[1]);
		// connect to provided ip and port
		//client.socket = new Socket(args[0], Integer.parseInt(args[1]));
		client.socket = new Socket("127.0.1.1", 59898);
		// creating input and output stream for class object
		client.output = new ObjectOutputStream(client.socket.getOutputStream());
		client.input = new ObjectInputStream(client.socket.getInputStream());
		client.userInput = new Scanner(System.in);
		
	
		
		String line = null;
		int loginAttempts = 0;
		boolean proceedToLobby = false;
		boolean logout = false;
		
		
		/*
		// Login and register loop
		while(!proceedToLobby && loginAttempts < 3) {
			System.out.println("Enter \"1\" to login or \"2\" to register");
			line = client.userInput.nextLine();
			
			switch (line) {
				case "1":
					++loginAttempts;
					proceedToLobby = client.login();
					break;
				case "2":
					client.register();
					break;
			}
		}
		
		
		// If login attempts > 3 close the connection
		if (loginAttempts >= 3) {
			client.closeConnection();
			return;
		}
		*/
		
		Parser parser = new Parser();
		
		// Create a message handler for the client
		MessageHandler messageHandler = new MessageHandler(client);
		
		// Spawn a new thread for the client
		new Thread(messageHandler).start();
		
		int roomNumber = 3;
		
		System.out.println("Room number before sending join room 3 = " + client.getRoom().getRoomNumber());
		Message message = new Message("join room", Integer.toString(roomNumber), "");
		client.sendMessage(message);
		Thread.sleep(1000);
		
		System.out.println("Room number after sending join room 3 = " + client.getRoom().getRoomNumber());
		
		message = new Message("sit", "", "");
		client.sendMessage(message);
		Thread.sleep(1000);
		System.out.println("Player after sending sit message = " + client.player.toString());
		
		message = new Message("deal", "100", "");
		client.sendMessage(message);
		Thread.sleep(1000);
		System.out.println("Player after sending deal message = " + client.player.toString());
		
		message = new Message("hit", "", "");
		client.sendMessage(message);
		Thread.sleep(1000);
		System.out.println("Player after sending hit message = " + client.player.toString());
		
		message = new Message("stand", "100", "");
		client.sendMessage(message);
		Thread.sleep(1000);
		System.out.println("Player after sending stand message = " + client.player.toString());
		
		// Loop for lobby
		while(!logout) {
			
		
		
			
		}
		
		
		client.closeConnection();
		
		return;
		
	}
	
	// MessageHandler will listen to messages and put them in a queue for processing
	private static class MessageHandler implements Runnable {
		private Client client;
		
		public MessageHandler(Client client) {
			this.client = client;
		}
		
		@Override
		public void run() {
			System.out.println("MessageHandler running");
			Parser parser = new Parser();
			
			while (true) {
				System.out.println("In MessageHandling loop");
				Message message = null;
				message = client.getMessage(message);
				System.out.println("Client received an object");
				client.messageQueue.add(message);
				
				if (client.messageQueue.size() > 0 ) {
					switch (client.messageQueue.get(0).getType()) {
						case "lobby room":
							System.out.println("Client received lobby room object");
							client.setLobbyRoom(parser.parseLobbyRoom(client.messageQueue.get(0).getText()));
							

							client.getMessageQueue().remove(0);
							break;
						case "room":
							System.out.println("Client received room object");
							client.setRoom(parser.parseRoom(client.messageQueue.get(0).getText()));
							if (client.messageQueue.size() > 0) {
								for (int x = 0; x < client.getRoom().getPlayersInRoom().size(); ++x) {
									if(client.player.getUsername().equals(client.getRoom().getPlayersInRoom().get(x).getUsername())) {
										client.setPlayer(client.getRoom().getPlayersInRoom().get(x));
									}
								
								}
							}
							
							client.getMessageQueue().remove(0);
							break;
						case "login":
							System.out.println("Client received login object");

							if(client.messageQueue.get(0).getStatus().equals("success")) {
								
								client.setLogin(1);
								System.out.println("login success");
								client.getMessageQueue().remove(0);
							}
							else {
								client.setLogin(-1);
								client.getMessageQueue().remove(0);
							}

							break;
							
						case "player":
							System.out.println("Client received player object");

							client.setPlayer(parser.parsePlayer(client.messageQueue.get(0).getText()));
							client.getMessageQueue().remove(0);

							break;
							
						default:
							break;
					}
				}
				/*
				try {
					Thread.sleep(1000);
				}
				catch (Exception e) {
					
				}
				*/
			}
		}
	}
	
	
	// Use this for GUI
	// Login function to log onto server
	public void login (String username, String password) {
		Message message = new Message("login", "", username + "#" + password);
		sendMessage(message);
		
		System.out.println("Sent message");
	}

	/*
	// This is a duplicate function from above. It is for testing purposes.
	// Login function to log onto server
	public boolean login () {
		boolean login = false;

		System.out.printf("Enter username: ");
		String username = userInput.nextLine();
		System.out.printf("Enter password: ");
		String password = userInput.nextLine();
		Parser parser = new Parser();
		
		Message message = new Message("login", "", username + "#" + password);
		sendMessage(message);
		
		Message replyMessage = null;
		replyMessage = getMessage(replyMessage);
		
		if (replyMessage.getType().equals("login") && replyMessage.getStatus().equals("success")) {
			System.out.println("Login successful");
			Message playerMessage = null;
			playerMessage = getMessage(playerMessage);
			this.setPlayer(parser.parsePlayer(playerMessage.toString()));
			userInput.close();
			return true;
		}
		else if (replyMessage.getType().equals("login") && replyMessage.getStatus().equals("failed")) {
			System.out.println("Login failed");
		}
		
		return login;
	}
	*/
	
	
	// Use this for the GUI
	// Register a new user
	public Boolean register(String username, String password) {
		Message message = new Message("register", "", username + "#" + password);
		sendMessage(message);
		
		Message replyMessage = null;
		replyMessage = getMessage(replyMessage);
		
		if (replyMessage.getType().equals("register") && replyMessage.getStatus().equals("success")) {
			return true;
		}
		
		return false;
	}
	
	
	
	// Register a new user
	public void register() {
		System.out.printf("Enter username: ");
		String username = userInput.nextLine();
		System.out.printf("Enter password: ");
		String password = userInput.nextLine();
		
		Message message = new Message("register", "", username + "#" + password);
		sendMessage(message);
		
		Message replyMessage = null;
		replyMessage = getMessage(replyMessage);
		
		if (replyMessage.getType().equals("register") && replyMessage.getStatus().equals("success")) {
			System.out.println("Registration successful");
		}
		else if (replyMessage.getType().equals("register") && replyMessage.getStatus().equals("failed")) {
			System.out.println(replyMessage.getText());
		}
	}
	
	// Receives message from server
	public Message getMessage(Message message) {
		try {
			while (message == null) {
				message = (Message) input.readObject();
			}
			
			return message;
		}
		catch (Exception e) {
			// removed e.printStackTrace(); so that it won't print annoying messages when it expects to receive a message
			// but there isn't one in the pipe.
		}
		
		return message;
	}
	
	// Sends message to server
	public void sendMessage(Message message) {
		try {
			output.writeObject(message);
			output.flush();
		}
		catch(Exception e) {
			//e.printStackTrace();
		}
	}
	
	// Countdown timer set to a default of 10000L which is 10 seconds. This is the amount of time a player has to make a decision.
	// If a currentAction = -1 and the timer runs out, then do nothing.
	// This timer should start when a player clicks on deal, hit, and double down. If timer runs out after a player has clicked these buttons
	// set player's currentAction = 3 and send the server an updated player object with the room number in the status field.
	public void countDown() {
	    TimerTask task = new TimerTask() {
	        public void run() {
	        	if (player.getPlayerState() == 3) {
	        		switch (player.getCurrentAction()) {
		        		case 0: case 1: case 2:
		        			player.setCurrentAction(3);
		        			break;
		        		default:
		        			break;
	        		}
	        	}
	        }
	    };
	    Timer timer = new Timer("Timer");
	    
	    long delay = 10000L;
	    timer.schedule(task, delay);
	}
	
	
	
	// Closes connection to server.
	public void closeConnection() {
		try {
			if (userInput != null)
				userInput.close();
			
			if (input != null)
				input.close();
			
			if (output != null)
				output.close();
			
			if (socket != null)
				socket.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// If player requests join room use this function to send request to server
	public void joinRoom(int roomNumber) {
		//String [] tmpLine = line.split(",");
		//int roomNumber = Integer.parseInt(tmpLine[0]);
		Message message = new Message("join room", Integer.toString(roomNumber), "");
		sendMessage(message);
	}
	
	// If a player requests to log out of the server use this function to send request to server
	public void logout() {
		Message message = new Message("logout", "", "");
		sendMessage(message);
	}
	
	// If a player wants to sit down at a seat
	public void sit() {
		Message message = new Message("sit", "", "");
		sendMessage(message);
	}
	
	// If a player wants to leave the room
	public void leaveRoom() {
		Message message = new Message("leave room", "", "");
		sendMessage(message);
	}
	
	// If a player wants to be deal the first two cards
	public void deal(int wager) {
		Message message = new Message("deal", Integer.toString(wager), "");
		sendMessage(message);
	}
	
	// If a player wants to sit out of the current round and wait for the next round
	public void sitOut() {
		Message message = new Message("sit out", "", "");
		sendMessage(message);
	}
	
	// If a player wants to doubl down on current bet
	public void doubleDown() {
		Message message = new Message("double down", "", "");
		sendMessage(message);
	}
	
	// If player is in a room and is actively playing and requests a hit, use this function to send request to server
	public void hit() {
		Message message = new Message("hit", "", "");
		sendMessage(message);
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public LobbyRoom getLobbyRoom() {
		return this.lobbyRoom;
	}
	
	public void setLobbyRoom(LobbyRoom lobbyRoom) {
		this.lobbyRoom = lobbyRoom;
	}
	
	public Room getRoom() {
		return this.room;
	}
	
	public void setRoom(Room room) {
		this.room = room;
	}
	
	public ArrayList<Message> getMessageQueue() {
		return this.messageQueue;
	}
	
	public void setMessageQueue (ArrayList<Message> messageQueue) {
		this.messageQueue = messageQueue;
	}
	
	public int getLogin() {
		return this.login;
	}
	
	public void setLogin(int login) {
		this.login = login;
	}
}