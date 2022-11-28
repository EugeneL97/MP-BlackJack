package main;
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
	private int register;
	private Boolean canDeal;
	private Boolean endOfRound;
	private Boolean waitingToStart;
	

	private ConnectGUI connectGUI;
	private LoginGUI loginGUI;
	private LobbyGUI lobbyGUI;
	private GameRoomGUI gameRoomGUI;
	private AccountInfoGUI accountInfoGUI;
	private RegisterOrLoginGUI registerOrLoginGUI;
	private RegisterGUI registerGUI;
	
	
	public Client() throws Exception {
		this.socket = null;
		this.input = null;
		this.output = null;
		this.userInput = null;
		this.messageQueue = new ArrayList<Message>();
		this.room = new Room(-1);
		this.login = 0;
		this.register = 0;
		this.canDeal = true; 
		this.endOfRound = false;
		this.waitingToStart = true;
		this.lobbyRoom = new LobbyRoom();
		
	}
	
	public Client(String ip, int port) throws Exception {
		this.socket = new Socket(ip, port);
		this.input = new ObjectInputStream(socket.getInputStream());
		this.output = new ObjectOutputStream(socket.getOutputStream());
		this.userInput = new Scanner(System.in);
		this.messageQueue = new ArrayList<Message>();
		this.room = new Room(-1);
		this.login = 0;
		this.register = 0;
		this.canDeal = true;
		this.endOfRound = false;
		this.waitingToStart = true;
		this.lobbyRoom = new LobbyRoom();
		// Create a message handler for the client
		MessageHandler messageHandler = new MessageHandler(this);
		
		// Spawn a new thread for the client
		new Thread(messageHandler).start();
	}
	
	public void checkWaitingToStart() {
		if (player.getSeatIndex() != -1) {
			if (room.getReadyToStart() == 0) {
				setWaitingToStart(true);
			}
			else {
				setWaitingToStart(false);
			}
			
			if (getWaitingToStart()) {
				gameRoomGUI.startRound();
			}
		}
	}
	
	
	public static void main(String [] args) throws Exception {
		//String ip = "127.0.1.1";
		//int port = 59898;
		// Create a new instance of client
		Client client = new Client();
		new ConnectGUI(client).setupConnectPanel();
		
		System.out.println(client.socket);
		
		client.closeConnection();
		
		return;
		
	}
	
	public void checkDeal() {
		
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
			System.out.println("In MessageHandling loop");
			while (true) {
				Message message = null;
				message = client.getMessage(message);
				client.messageQueue.add(message);
				
				if (client.messageQueue.size() > 0 ) {
					switch (client.messageQueue.get(0).getType()) {
						case "lobby room":
							System.out.println("Client received lobby room object");
							
							client.setLobbyRoom(parser.parseLobbyRoom(client.messageQueue.get(0).getText()));
							

							client.getMessageQueue().remove(0);
							//client.lobbyGUI.refreshRooms();
							break;
						case "room":
							System.out.println("Client received room object");
							client.setRoom(parser.parseRoom(client.messageQueue.get(0).getText()));
							
							client.getMessageQueue().remove(0);
							
							if(client.getPlayer().getRoomNumber() != -1) {
								client.gameRoomGUI.refreshGUI();
							}
							
							System.out.println("new room object = " + client.getRoom().showRoom());
							
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
							System.out.println("new player object = " + client.getPlayer().toString());
							break;
							
						case "register":
							System.out.println("Client received register object");

							if(client.messageQueue.get(0).getStatus().equals("success")) {

								client.setRegister(1);
								System.out.println("register success");
								client.getMessageQueue().remove(0);
							}
							else {
								client.setRegister(-1);
								client.getMessageQueue().remove(0);
							}

							break;
						default:
							break;
					}
				}

				client.checkCanDeal();
				client.checkEndOfRound();
				client.checkWaitingToStart();
			}
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
			output.reset();
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

	// Use this for GUI
	// Login function to log onto server
	public void login (String username, String password) {
		Message message = new Message("login", "", username + "#" + password);
		sendMessage(message);

		System.out.println("Sent login message");
	}

	// Register a new user
	public void register(String username, String password) {
		Message message = new Message("register", "", username + "#" + password);
		sendMessage(message);

		System.out.println("Sent register message");
	}
	
	// If player requests join room use this function to send request to server
	public void joinRoom(int roomNumber) {
		//String [] tmpLine = line.split(",");
		//int roomNumber = Integer.parseInt(tmpLine[0]);
		Message message = new Message("join room", Integer.toString(roomNumber), "");
		sendMessage(message);
		System.out.println("sent join room message");
	}
	
	// If a player requests to log out of the server use this function to send request to server
	public void logout() {
		Message message = new Message("logout", "", "");
		sendMessage(message);
		System.out.println("sent log out message");
	}
	
	// If a player wants to sit down at a seat
	public void sit(int seatIndex) {
		Message message = new Message("sit", Integer.toString(seatIndex), "");
		sendMessage(message);
		System.out.println("sent sit message at index " + seatIndex);
	}
	
	// If a player wants to leave the room
	public void leaveRoom() {
		Message message = new Message("leave room", "", "");
		sendMessage(message);
		System.out.println("sent leave room message");
	}
	
	// If a player wants to be deal the first two cards
	public void deal(int wager) {
		Message message = new Message("deal", Integer.toString(wager), "");
		sendMessage(message);
		System.out.println("sent deal message with wager amount = " + wager);
	}
	
	// If a player wants to sit out of the current round and wait for the next round
	public void sitOut() {
		Message message = new Message("sit out", "", "");
		sendMessage(message);
		System.out.println("sent sit out message");
	}
	
	// If a player wants to doubl down on current bet
	public void doubleDown() {
		Message message = new Message("double down", "", "");
		sendMessage(message);
		System.out.println("sent double down message");
	}
	
	// If player is in a room and is actively playing and requests a hit, use this function to send request to server
	public void hit() {
		Message message = new Message("hit", "", "");
		sendMessage(message);
		System.out.println("sent hit message");
	}

	public void stand() {
		Message message = new Message("stand", String.valueOf(player.getWager()), "");
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
	
	public void setMessageQueue(ArrayList<Message> messageQueue) {
		this.messageQueue = messageQueue;
	}
	
	public int getLogin() {
		return this.login;
	}
	
	public void setLogin(int login) {
		this.login = login;
	}

	public int getRegister() {
		return this.register;
	}

	public void setRegister(int register) {
		this.register = register;
	}

	public void setGameRoomGUI(GameRoomGUI gameRoomGUI) {
		this.gameRoomGUI = gameRoomGUI;
	}
	public RegisterGUI getRegisterGUI() {
		return registerGUI;
	}

	public void setRegisterGUI(RegisterGUI registerGUI) {
		this.registerGUI = registerGUI;
	}

	public RegisterOrLoginGUI getRegisterOrLoginGUI() {
		return registerOrLoginGUI;
	}

	public void setRegisterOrLoginGUI(RegisterOrLoginGUI registerOrLoginGUI) {
		this.registerOrLoginGUI = registerOrLoginGUI;
	}

	public AccountInfoGUI getAccountInfoGUI() {
		return accountInfoGUI;
	}

	public ConnectGUI getConnectGUI() {
		return connectGUI;
	}

	public void setConnectGUI(ConnectGUI connectGUI) {
		this.connectGUI = connectGUI;
	}

	public LoginGUI getLoginGUI() {
		return loginGUI;
	}

	public void setLoginGUI(LoginGUI loginGUI) {
		this.loginGUI = loginGUI;
	}

	public GameRoomGUI getGameRoomGUI() {
		return gameRoomGUI;
	}

	public void setAccountInfoGUI(AccountInfoGUI accountInfoGUI) {
		this.accountInfoGUI = accountInfoGUI;
	}
	
	public Boolean getCanDeal() {
		return canDeal;
	}

	public void setCanDeal(Boolean canDeal) {
		this.canDeal = canDeal;
	}
	
	public LobbyGUI getLobbyGUI() {
		return lobbyGUI;
	}
	
	public void setLobbyGUI(LobbyGUI lobbyGUI) {
		this.lobbyGUI = lobbyGUI;
	}
	
	
	public Boolean getWaitingToStart() {
		return waitingToStart;
	}

	public void setWaitingToStart(Boolean waitingToStart) {
		this.waitingToStart = waitingToStart;
	}

	public void checkCanDeal() {
		if (player.getSeatIndex() != -1) {
			if (room.getReadyToStart() > 1) {
				System.out.println("cannot deal");
				setCanDeal(false);
			} else {
				setCanDeal(true);
				System.out.println("can deal");
			}
			
			if (getCanDeal() == true) {
				gameRoomGUI.startRound();
				System.out.println("startRound executed");
			}
				
			else {
				gameRoomGUI.middleOfRound();
				System.out.println("middleOfRound executed");
			}
				
		}
	}
	
	public Boolean getEndOfRound() {
		return endOfRound;
	}

	public void setEndOfRound(Boolean endOfRound) {
		this.endOfRound = endOfRound;
	}
	
	public void checkEndOfRound () {
		if (player.getSeatIndex() != -1) {
			if (room.getReadyToStart() == 3) {
				setEndOfRound(true);
			}
			else {
				setEndOfRound(false);
			}
			
			if (getEndOfRound()) {
				gameRoomGUI.endRound();
			}
			
			if (room.getReadyToStart() == 0) {
				player.clearHand();
				gameRoomGUI.startRound();
			}
		}
		
	}
}