package testing;

import main.Player;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

public class PlayerTesting {
	@Test
	public void ConstructorTest() {
		String username = "Jimmy";
		int accountBalance = 5000;
		Player player1 = new Player(username, accountBalance);
		assertTrue(player1.getUsername().equals(username));
		assertTrue(player1.getAccountBalance() == accountBalance);
	}
	
	@Test
	public void GetterAndSetterTest() {
		String username = "Bobby";
		int roomNumber = 1;
		int playerState = 1;
		int wager = 100;
		int seatIndex = 1;
		int accountBalance = 5000;
		int currentAction = 0;
		String score = "1000";
		
		Player player1 = new Player("Jimmy Jangles", 10000);
		player1.setUsername(username);
		player1.setRoomNumber(roomNumber);
		player1.setPlayerState(playerState);
		player1.setWager(wager);
		player1.setSeatIndex(seatIndex);
		player1.setAccountBalance(accountBalance);
		player1.setCurrentAction(currentAction);
		player1.setScore(score);
		
		assertTrue(player1.getUsername().equals(username));
		assertTrue(player1.getRoomNumber() == roomNumber);
		assertTrue(player1.getPlayerState() == playerState);
		assertTrue(player1.getWager() == wager);
		assertTrue(player1.getSeatIndex() == seatIndex);
		assertTrue(player1.getAccountBalance() == accountBalance);
		assertTrue(player1.getCurrentAction() == currentAction);
		assertTrue(player1.getScore().equals(score));
	}
}
