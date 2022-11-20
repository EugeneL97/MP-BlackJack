import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
	private Socket socket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private Scanner userInput;
	private Player player;
	private LobbyRoom lobbyRoom;
	
	public Client() throws Exception {
		socket = null;
		input = null;
		output = null;
		userInput = null;
	}
	
	public static void main(String [] args) throws Exception {
		// Create a new instance of client
		Client client = new Client();
		
		// check to see if correct arguments were provided
		if (args.length != 2) {
			System.out.println("Please enter IP address followed by port number after java Client.java. i.e. java Client.java 127.0.0.1 7777\n");
			return;
		}

		System.out.println("Attemping to connect to server IP: " + args[0] + ":" + args[1]);
		// connect to provided ip and port
		client.socket = new Socket(args[0], Integer.parseInt(args[1]));
		
		// creating input and output stream for class object
		client.output = new ObjectOutputStream(client.socket.getOutputStream());
		client.input = new ObjectInputStream(client.socket.getInputStream());
		client.userInput = new Scanner(System.in);
		
		Scanner userInput = new Scanner(System.in);
		String line = null;
		int loginAttempts = 0;
		boolean proceedToLobby = false;
		boolean logout = false;
		
		// Login and register loop
		while(!proceedToLobby && loginAttempts < 3) {
			System.out.println("Enter \"1\" to login or \"2\" to register");
			line = userInput.nextLine();
			
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
		
		Parser parser = new Parser();
		// Loop for lobby
		while(!logout) {
			LobbyRoom clientLobbyRoom = new LobbyRoom();
			LobbyRoom newLobbyRoom = null;
			ArrayList<String> username = new ArrayList<String>();
			username.add("md44l");
			username.add("bmagic");
			username.add("ugene");
			username.add("Alex");
			
			for (int x = 0; x < username.size(); ++x) {
				clientLobbyRoom.addPlayer(0, username.get(x));
				clientLobbyRoom.addPlayer(4, username.get(x));
				clientLobbyRoom.addPlayer(2, username.get(x));
			}
			
			Message message = new Message("lobby room", "", clientLobbyRoom.toString());
			client.sendMessage(message);
		}
	
		
		client.closeConnection();
		return;
	}
	
	
	// Login function to log onto server
	public boolean login () {
		boolean login = false;
		
		System.out.printf("Enter username: ");
		String username = userInput.nextLine();
		System.out.printf("Enter password: ");
		String password = userInput.nextLine();
		
		Message message = new Message("login", "", username + "#" + password);
		sendMessage(message);
		
		Message replyMessage = null;
		replyMessage = getMessage(replyMessage);
		
		if (replyMessage.getType().equals("login") && replyMessage.getStatus().equals("success")) {
			System.out.println("Login successful");
			userInput.close();
			return true;
		}
		else if (replyMessage.getType().equals("login") && replyMessage.getStatus().equals("failed")) {
			System.out.println("Login failed");
		}
		
		return login;
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
			e.printStackTrace();
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
			e.printStackTrace();
		}
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
	public void joinRoom(String line) {
		String [] tmpLine = line.split(",");
		int roomNumber = Integer.parseInt(tmpLine[0]);
		Message message = new Message("join room", Integer.toString(roomNumber), "");
		sendMessage(message);
	}
	
	// If player is in a room and is actively playing and requests a hit, use this function to send request to server
	public void hit() {
		Message message = new Message("hit", "", "");
		sendMessage(message);
	}
}