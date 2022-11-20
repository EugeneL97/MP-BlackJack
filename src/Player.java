import java.util.*;

public class Player {
	private String username;
	
	// playerState indicates where in the game the player is at
	// State = 0 means player is in the lobby. State = 1 means player is in a room.
	// State = 2 means player has sat down in a game and is actively playing.
	private int playerState;
	
	// roomNumber indicates the player is in which room. It is also used as an index in the server
	// code to easily find the player
	private int roomNumber;
	private int accountBalance;
	
	// currentAction signifies what the player's current action is. This attribute may be useless
	// since we're sending the current action by sending a message to the server. We could repurpose
	// this attribute for something else
	private int currentAction;
	
	// isPlayer indicates whether this is a player or dealer. Player has a value of 1 and dealer has a value of 0
	private int isPlayer;
	
	// isSitting is currently useless. It could be used to indicate which seat the player chose to sit in.
	// There are a total of 5 seats per room.
	private int isSitting;
	
	// currentHand stores the player's cards in 2D array. For example, the first time a player receives cards,
	// the cards will be stored in index 0. If the player decides to split, then we take one card from index
	// 0 and move it to index 1. Then we receive additional cards in index 0 and 1 as cards get dealt.
	private ArrayList<ArrayList<Card>> currentHand;
	
	public Player(Player player) {
		this(player.getUsername(), player.getPlayerState(), player.getRoomNumber(), player.getAccountBalance(),
				player.getCurrentAction(), player.isPlayer(), player.isSitting(), player.getCurrentHand());
	}
	
	public Player(String username, int accountBalance) {
		this(username, 0, -1, accountBalance, -1, 1, -1, null);
	}
	
	public Player(String username, int playerState, int roomNumber, int accountBalance, int currentAction, int isPlayer, int isSitting, ArrayList<ArrayList<Card>> currentHand) {
		super();
		
		this.username = username;
		this.roomNumber = roomNumber;
		this.playerState = playerState;
		this.isPlayer = isPlayer;
		this.isSitting = isSitting;
		this.accountBalance = accountBalance;
		this.currentAction = currentAction;
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
		}
	}

	public String toString() {
		String output = null;
		output = username + "#" + Integer.toString(playerState) + "#" + Integer.toString(roomNumber) + "#" + Integer.toString(accountBalance) + "#"
				+ Integer.toString(currentAction) + "#" + Integer.toString(isPlayer) + "#" + Integer.toString(isSitting) + "#" + Integer.toString(currentHand.size()) + "#";
		
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

	public int isSitting() {
		return isSitting;
	}

	public void setSitting(int isSitting) {
		this.isSitting = isSitting;
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
