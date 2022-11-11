import java.util.*;

public class Player {
	private String firstName;
	private String lastName;
	private String username;
	private int roomNumber;
	private PlayerState playerState;
	private ArrayList<Card> currentHand;
	private boolean isPlayer;
	private boolean isSitting;
	private int accountBalance;
	private int currentAction[];
	private final int CURRENT_ACTION_SIZE = 6;
	
	public Player(String firstName, String lastName, String username, int roomNumber, PlayerState playerState,
			ArrayList<Card> currentHand, boolean isPlayer, boolean isSitting, int accountBalance, int[] currentAction) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.roomNumber = roomNumber;
		this.playerState = playerState;
		this.currentHand = currentHand;
		this.isPlayer = isPlayer;
		this.isSitting = isSitting;
		this.accountBalance = accountBalance;
		this.currentAction = currentAction;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public PlayerState getPlayerState() {
		return playerState;
	}

	public void setPlayerState(PlayerState playerState) {
		this.playerState = playerState;
	}

	public ArrayList<Card> getCurrentHand() {
		return currentHand;
	}

	public void setCurrentHand(ArrayList<Card> currentHand) {
		this.currentHand = currentHand;
	}

	public boolean isPlayer() {
		return isPlayer;
	}

	public void setPlayer(boolean isPlayer) {
		this.isPlayer = isPlayer;
	}

	public boolean isSitting() {
		return isSitting;
	}

	public void setSitting(boolean isSitting) {
		this.isSitting = isSitting;
	}

	public int getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}

	public int[] getCurrentAction() {
		return currentAction;
	}

	public void setCurrentAction(int[] currentAction) {
		this.currentAction = currentAction;
	}

	public int getCURRENT_ACTION_SIZE() {
		return CURRENT_ACTION_SIZE;
	}
	
	
}
