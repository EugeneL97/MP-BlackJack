import java.util.*;

public class Player {
	private String username;
	
	// playerState indicates where in the game the player is at
	// State = 0 means player is in the lobby. 
	// State = 1 means player is in a room.
	// State = 2 means player has chosen a seat and sat down. See what player's currentAction to determine what the dealer should do
	private int playerState;
	
	// roomNumber indicates the player is in which room. It is also used as an index in the server
	// code to easily find the player
	private int roomNumber;
	private int accountBalance;
	
	// currentAction signifies what the player's current action is.
	// currentAction = 0 means player has clicked deal and wants to receive first two cards
	// currentAction = 1 means player wants a hit, so players receives another card
	// currentAction = 2 means player wants to double down on the bet, so double the wager amount
	// currentAction = 3 means player wants to stand, meaning player is satisfied with the cards and
	// will wait to see if the hand beats the dealer's.
	// currentAction = 4 means player wants to sit out of this current round. This is the default action when a player is created.
	private int currentAction;
	
	// isPlayer indicates whether this is a player or dealer. Player has a value of 1 and dealer has a value of 0
	private int isPlayer;
	
	// seatIndex is used to easily find position of player in server's attribute ArrayList<Room> rooms and LobbyRoom lobbyRooms.
	// This would speed up the process of making changes to the player object
	private int seatIndex;
	
	// currentHand stores the player's cards in 2D array. For example, the first time a player receives cards,
	// the cards will be stored in index 0. If the player decides to split, then we take one card from index
	// 0 and move it to index 1. Then we receive additional cards in index 0 and 1 as cards get dealt.
	private ArrayList<ArrayList<Card>> currentHand;
	
	public Player(Player player) {
		this(player.getUsername(), player.getPlayerState(), player.getRoomNumber(), player.getAccountBalance(),
				player.getCurrentAction(), player.isPlayer(), player.getSeatIndex(), player.getCurrentHand());
	}
	
	public Player(String username, int accountBalance) {
		this(username, 0, -1, accountBalance, 4, 1, -1, new ArrayList<ArrayList<Card>> ());
	}
	
	public Player(String username, int playerState, int roomNumber, int accountBalance, int currentAction, int isPlayer, int seatIndex, ArrayList<ArrayList<Card>> currentHand) {
		super();
		
		this.username = username;
		this.roomNumber = roomNumber;
		this.playerState = playerState;
		this.isPlayer = isPlayer;
		this.seatIndex = seatIndex;
		this.accountBalance = accountBalance;
		this.currentAction = currentAction;
		this.currentHand = currentHand;
		/*
		this.currentHand = new ArrayList<ArrayList<Card>>();
		
		if (currentHand != null) {
			for (int x = 0; x < currentHand.size(); ++x) {
				this.currentHand.add(new ArrayList<Card> ());
			}
			
			for (int x = 0; x < currentHand.size(); ++x) {
				for (int y = 0; y < currentHand.get(x).size(); ++y) {
					this.currentHand.get(x).add(currentHand.get(x).get(y));
				}
			}
		}*/
	}

	public String toString() {
		String output = null;
		output = username + "#" + Integer.toString(playerState) + "#" + Integer.toString(roomNumber) + "#" + Integer.toString(accountBalance) + "#"
				+ Integer.toString(currentAction) + "#" + Integer.toString(isPlayer) + "#" + Integer.toString(seatIndex) + "#" + Integer.toString(currentHand.size()) + "#";
		
		for (int x = 0; x < currentHand.size(); ++x) {
			for (int y = 0; y <currentHand.get(x).size(); ++y) {
				output = output + Integer.toString(x) + "#" + currentHand.get(x).get(y).toString();
			}
		}
		
		return output;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getPlayerState() {
		return playerState;
	}

	public void setPlayerState(int playerState) {
		this.playerState = playerState;
	}

	public ArrayList<ArrayList<Card>> getCurrentHand() {
		return currentHand;
	}

	public void setCurrentHand(ArrayList<ArrayList<Card>> currentHand) {
		this.currentHand = currentHand;
	}

	public int isPlayer() {
		return isPlayer;
	}

	public void setPlayer(int isPlayer) {
		this.isPlayer = isPlayer;
	}

	public int getSeatIndex() {
		return seatIndex;
	}

	public void setSeatIndex(int seatIndex) {
		this.seatIndex = seatIndex;
	}

	public int getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}

	public int getCurrentAction() {
		return currentAction;
	}

	public void setCurrentAction(int currentAction) {
		this.currentAction = currentAction;
	}
	
	// Inserts a card at position index in the 2D array ArrayList<ArrayList<Card>> currentHand
	public void acceptCard(int index, Card card) {
		if (index >= 0 && index < currentHand.size()) {
			currentHand.get(index).add(card);
		}
	}
	
	// Removes a specific card at row index, column cardIndex of the 2D array ArrayList<ArrayList<Card>> currentHand.
	public void removeCard(int index, int cardIndex) {
		if (index >= 0 && index < currentHand.size() && cardIndex >= 0 && cardIndex < currentHand.get(index).size()) {
			currentHand.get(index).remove(cardIndex);
		}
	}
	
	// Removes all cards
	public void clearHand() {
		currentHand.clear();
	}
}
