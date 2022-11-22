import java.util.*;

public class Room {
	private int roomNumber;
	
	// readyToStart indicates whether the current round is starting. If it is, players have 20 seconds
	// to place wagers. The server will send a timer message to clients every 5 seconds with the time remaining
	// until players are locked out of the current round. 
	// readyToStart = 0 means game is in idle state and waiting for a player to sit and choose an action to start the game
	// readyToStart = 1 means game is in waiting for player to decide which moves to make while timer is counting down
	// readyToStart = 2 means game is in proceed state, meaning either all players have finished deciding their move or timer has ran out
	private int readyToStart;
	private ArrayList<Player> playersInRoom;
	private Shoe shoe;
	
	public Room(int roomNumber) {
		this.roomNumber = roomNumber;
		this.readyToStart = 0;
		this.playersInRoom = new ArrayList<Player>();
		
		// Each time a room is created, a dealer is automatically added to the room
		ArrayList<ArrayList<Card>> currentHand = new ArrayList<ArrayList<Card>>();
		this.playersInRoom.add(new Player("Dealer", 2, this.roomNumber, 0, -1, 0, 0, currentHand));
		
		this.shoe = new Shoe();
	}
	
	public Room(Room room) {
		this.roomNumber = room.getRoomNumber();
		this.readyToStart = room.getReadyToStart();
		this.playersInRoom = room.getPlayersInRoom();
		this.shoe = room.getShoe();
		
	}
	
	public Room(int roomNumber, int readyToStart,
			ArrayList<Player> playersInRoom, Shoe shoe) {
		
		super();
		this.roomNumber = roomNumber;
		this.readyToStart = readyToStart;

		/*
		for (int x = 0; x < currentPlayers.size(); ++x) {
			this.currentPlayers.add(currentPlayers.get(x));
		}*/
		
		this.playersInRoom = new ArrayList<Player>();
		
		/*
		for (int x = 0; x < playersInRoom.size(); ++x) {
			this.playersInRoom.add(playersInRoom.get(x));
		}*/
		this.playersInRoom = playersInRoom;
		
		this.shoe = shoe;
	}

	// Countdown timer set to a default of 10000L which is 10 seconds, at which time the readyToStart attribute of the room
	// will be set to the parameter's value
	public void countDown(int readyToStart) {
	    TimerTask task = new TimerTask() {
	        public void run() {
	        	setReadyToStart(readyToStart);
	            System.out.println("Ready to start has been changed to = " + getReadyToStart());
	        }
	    };
	    Timer timer = new Timer("Timer");
	    
	    long delay = 10000L;
	    timer.schedule(task, delay);
	}
	
	public String toString() {
		String output = "";
		
		output = Integer.toString(roomNumber) + "%" + Integer.toString(readyToStart) + "%" + Integer.toString(playersInRoom.size()) + "%";
		
		for (int x = 0; x < playersInRoom.size(); ++x) {
			output += playersInRoom.get(x).toString();
			output += "%";
		}
		
		
		output += shoe.toString();
		
		return output;
	}
	
	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public ArrayList<Player> getPlayersInRoom() {
		return playersInRoom;
	}

	public void setPlayersInRoom(ArrayList<Player> playersInRoom) {
		this.playersInRoom = playersInRoom;
	}

	public int getReadyToStart() {
		return readyToStart;
	}

	public void setReadyToStart(int readyToStart) {
		this.readyToStart = readyToStart;
	}

	public Shoe getShoe() {
		return shoe;
	}

	public void setShoe(Shoe shoe) {
		this.shoe = shoe;
	}
	
	public void addPlayer(Player player) {
		if (this.playersInRoom.size() < 6) {
			this.playersInRoom.add(player);
		}
	}
	
	public void removePlayer(Player player) {
		if (this.playersInRoom.size() > 0) {
			this.playersInRoom.remove(player);
		}
	}
}
