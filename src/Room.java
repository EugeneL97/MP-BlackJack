import java.util.*;

public class Room {
	private int roomNumber;
	private int readyToStart;
	private ArrayList<Player> currentPlayers;
	private ArrayList<Player> playersInRoom;
	private Shoe shoe;
	
	public Room(Room room) {
		this(room.getRoomNumber(), room.isReadyToStart(), room.getCurrentPlayers(), room.getPlayersInRoom(), room.getShoe());
	}
	
	public Room(int roomNumber, int readyToStart,
			ArrayList<Player> currentPlayers, ArrayList<Player> playersInRoom, Shoe shoe) {
		
		super();
		this.roomNumber = roomNumber;
		this.readyToStart = readyToStart;
		this.currentPlayers = new ArrayList<Player>();
		this.currentPlayers = currentPlayers;
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
		
		output = Integer.toString(roomNumber) + "%" + Integer.toString(readyToStart) + "%" + Integer.toString(currentPlayers.size()) + "%";
		
		
		for (int x = 0; x < currentPlayers.size(); ++x) {
			output += currentPlayers.get(x).toString();
			output += "%";
		}
		
		output += Integer.toString(playersInRoom.size()) + "%";
		
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

	public ArrayList<Player> getCurrentPlayers() {
		return currentPlayers;
	}

	public void setCurrentPlayers(ArrayList<Player> currentPlayers) {
		this.currentPlayers = currentPlayers;
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
}
