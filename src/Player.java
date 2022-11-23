import java.util.*;

public class Player {
	private String username;
	
	// playerState indicates where in the game the player is at
	// playerState = 0 means player is in the lobby. 
	// playerState = 1 means player is in a room.
	// playerState = 2 means player has chosen a seat and sat down. 
	// playerState = 3 means server will see what player's currentAction to determine what the dealer and game logic should do. After the action has been updated
	// by the server, set playerState = 4 so that server will not perform the same action again.
	// playerState = 4 means player has decided on their action and is waiting response from the server. In the mean time
	// the GUI should not give any buttons for the players to click until tally has been made.
	private int playerState;
	
	// roomNumber indicates the player is in which room. It is also used as an index in the server
	// code to easily find the player
	private int roomNumber;
	private int accountBalance;
	
	// currentAction signifies what the player's current action is.
	// currentAction = -1 means player has not decided on anything
	// currentAction = 0 means player has clicked deal and wants to receive first two cards
	// currentAction = 1 means player wants a hit, so players receives another card
	// currentAction = 2 means player wants to double down on the bet, so double the wager amount and receive one more card
	// currentAction = 3 means player wants to stand, meaning player is satisfied with the cards server should tally the score.
	// After the tally, set currentAction = -1
	// currentAction = 4 means player wants to sit out of this current round. This is the default action when a player is created.
	private int currentAction;
	
	// Indicates player's current wager
	private int wager;
	
	// seatIndex is used to easily find position of player in server's attribute ArrayList<Room> rooms and LobbyRoom lobbyRooms.
	// This would speed up the process of making changes to the player object
	private int seatIndex;
	
	// This attribute should really be an ArrayList<String> to keep track of score of each
	// hand in currentHand. But for the sake of time, we're only doing 1 hand at the moment.
	// Score = "not bust" means cards add up to < 21
	// Score = "bust" means cards add up to > 21
	// Score = "blackjack" means cards add up to 21
	private String score;
	
	// currentHand stores the player's cards in 2D array. For example, the first time a player receives cards,
	// the cards will be stored in index 0. If the player decides to split, then we take one card from index
	// 0 and move it to index 1. Then we receive additional cards in index 0 and 1 as cards get dealt.
	private ArrayList<ArrayList<Card>> currentHand;
	
	public Player(Player player) {
		this(player.getUsername(), player.getPlayerState(), player.getRoomNumber(), player.getAccountBalance(),
				player.getCurrentAction(), player.getWager(), player.getSeatIndex(), player.getScore(), player.getCurrentHand());
	}
	
	public Player(String username, int accountBalance) {
		this(username, 0, -1, accountBalance, -1, 0, -1, "not bust", new ArrayList<ArrayList<Card>> ());
	}
	
	public Player(String username, int playerState, int roomNumber, int accountBalance, int currentAction, int wager, int seatIndex, String score, ArrayList<ArrayList<Card>> currentHand) {
		super();
		
		this.username = username;
		this.roomNumber = roomNumber;
		this.playerState = playerState;
		this.wager = wager;
		this.seatIndex = seatIndex;
		this.accountBalance = accountBalance;
		this.currentAction = currentAction;
		this.score = score;
		this.currentHand = currentHand;
	}

	public String toString() {
		String output = null;
		output = username + "#" + Integer.toString(playerState) + "#" + Integer.toString(roomNumber) + "#" + Integer.toString(accountBalance) + "#"
				+ Integer.toString(currentAction) + "#" + Integer.toString(wager) + "#" + Integer.toString(seatIndex) + "#" + score + "#" + Integer.toString(currentHand.size()) + "#";
		
		for (int x = 0; x < currentHand.size(); ++x) {
			for (int y = 0; y <currentHand.get(x).size(); ++y) {
				output = output + Integer.toString(x) + "#" + currentHand.get(x).get(y).toString();
			}
		}
		
		return output;
	}

	
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
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

	public int getWager() {
		return wager;
	}

	public void setWager(int wager) {
		this.wager = wager;
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
		if (currentHand.size() <= index) {
			currentHand.add(new ArrayList<Card>());
		}
		if (index >= 0 && index < currentHand.size()) {
			this.currentHand.get(index).add(card);
		}
	}
	
	// Removes a specific card at row index, column cardIndex of the 2D array ArrayList<ArrayList<Card>> currentHand.
	public void removeCard(int index, int cardIndex) {
		if (index >= 0 && index < currentHand.size() && cardIndex >= 0 && cardIndex < currentHand.get(index).size()) {
			this.currentHand.get(index).remove(cardIndex);
		}
	}
	
	// Removes all cards
	public void clearHand() {
		currentHand.clear();
	}
}
