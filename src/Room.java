import java.util.*;

public class Room {
	private int roomNumber;
	private ArrayList<Player> currentPlayers;
	private Player dealer;
	private ArrayList<Player> playersInRoom;
	private int minWagerAmount;
	private boolean readyToStart;
	private Shoe shoe;
	
	public Room(int roomNumber, ArrayList<Player> currentPlayers, Player dealer, ArrayList<Player> playersInRoom,
			int minWagerAmount, boolean readyToStart, Shoe shoe) {
		super();
		this.roomNumber = roomNumber;
		this.currentPlayers = currentPlayers;
		this.dealer = dealer;
		this.playersInRoom = playersInRoom;
		this.minWagerAmount = minWagerAmount;
		this.readyToStart = readyToStart;
		this.shoe = shoe;
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

	public Player getDealer() {
		return dealer;
	}

	public void setDealer(Player dealer) {
		this.dealer = dealer;
	}

	public ArrayList<Player> getPlayersInRoom() {
		return playersInRoom;
	}

	public void setPlayersInRoom(ArrayList<Player> playersInRoom) {
		this.playersInRoom = playersInRoom;
	}

	public int getMinWagerAmount() {
		return minWagerAmount;
	}

	public void setMinWagerAmount(int minWagerAmount) {
		this.minWagerAmount = minWagerAmount;
	}

	public boolean isReadyToStart() {
		return readyToStart;
	}

	public void setReadyToStart(boolean readyToStart) {
		this.readyToStart = readyToStart;
	}

	public Shoe getShoe() {
		return shoe;
	}

	public void setShoe(Shoe shoe) {
		this.shoe = shoe;
	}
	
	
}
