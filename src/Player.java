import java.util.*;

public class Player {
	private String username;
	private int playerState;
	private int roomNumber;
	private int accountBalance;
	private int currentAction;
	private int isPlayer;
	private int isSitting;
	private ArrayList<ArrayList<Card>> currentHand;
	
	public Player(Player player) {
		this(player.getUsername(), player.getPlayerState(), player.getRoomNumber(), player.getAccountBalance(),
				player.getCurrentAction(), player.isPlayer(), player.isSitting(), player.getCurrentHand());
	}
	
	public Player(String username, int accountBalance) {
		this(username, 0, -1, accountBalance, -1, 1, 0, null);
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
