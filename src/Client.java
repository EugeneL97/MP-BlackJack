import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
	private Socket socket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private Scanner userInput;

	public Client() throws Exception {
		socket = null;
		input = null;
		output = null;
		userInput = null;
	}
	
	public static void main(String [] args) throws Exception {
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
		
		if (loginAttempts >= 3) {
			client.closeConnection();
			return;
		}
		
		while(!logout) {
			Parser parser = new Parser();
			
			Player player = new Player("jackson", 214554);
			Shoe shoe = new Shoe();
			shoe.generateCards();
			for (int x = 0; x < 3; ++x) {
				player.getCurrentHand().add(new ArrayList<Card>());
				for (int y = 0; y < 10; ++y) {
					player.getCurrentHand().get(x).add(shoe.dealCard());
				}
			}
		
			player.setRoomNumber(3);
			player.setPlayerState(1);
			player.setPlayer(1);
			player.setSitting(1);
			
			Message message = new Message("player", "", player.toString());
			client.sendMessage(message);
			
			ArrayList<Player> currentPlayers = new ArrayList<Player>();
			ArrayList<Player> playersInRoom = new ArrayList<Player>();
			int roomNumber = 23;
			int readyToStart = 1;
			ArrayList<String> username = new ArrayList<String>();
			username.add("harvey");
			username.add("md44l");
			username.add("bmagic");

			int playerState = 2;
			int accountBalance = 50000;
			int currentAction = 0;
			int isPlayer = 1;
			int isSitting = 1;
			ArrayList<ArrayList<Card>> currentHand = new ArrayList<ArrayList<Card>>();
			shoe = new Shoe();
			currentHand.add(new ArrayList<Card>());
			
			for (int x = 0; x < 3; ++x) {
				for (int y = 0; y < 3; ++y) {
					currentHand.get(0).add(shoe.dealCard());
				}
				
				currentPlayers.add(new Player(username.get(x), playerState, roomNumber, accountBalance, currentAction, isPlayer, isSitting, currentHand));
				playersInRoom.add(new Player(username.get(x), playerState, roomNumber, accountBalance, currentAction, isPlayer, isSitting, currentHand));
			}
		
			Room room;
			room = new Room(roomNumber, readyToStart, currentPlayers, playersInRoom, shoe);
			
			message = new Message("room", "", room.toString());
			client.sendMessage(message);
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		client.closeConnection();
		return;
	}
	
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
}