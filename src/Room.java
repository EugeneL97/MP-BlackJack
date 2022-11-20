import java.util.*;

public class Room {
	private int roomNumber;
	private int readyToStart;
	private ArrayList<Player> playersInRoom;
	private Shoe shoe;
	
	public Room() {
		this.roomNumber = -1;
		this.readyToStart = 0;
		this.playersInRoom = new ArrayList<Player>();
		this.shoe = new Shoe();
	}
	
	public Room(Room room) {
		this(room.getRoomNumber(), room.isReadyToStart(), room.getPlayersInRoom(), room.getShoe());
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

	public int isReadyToStart() {
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
		this.playersInRoom.add(player);
	}
	
	public void removePlayer(Player player) {
		this.playersInRoom.remove(player);
	}
}
