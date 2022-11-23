import java.util.*;

public class Room {
	private int roomNumber;
	
	// readyToStart indicates whether the current round is starting. 
	// readyToStart = 0 means game is in idle state and is waiting for players to initiate some action.
	// readyToStart = 1 means game is starting and dealer should receive first two cards. After dealer has been dealt cards set readyToStart = 2
	// readyToStart = 2 means game is waiting for player to decide which moves to make. When all of the players
	// in the room's currentAction = 3 or currentAction = -1, then tally the dealer's total against the players' total.
	// Then set readyToStart = 1 and repeat cycle all over again.
	
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
