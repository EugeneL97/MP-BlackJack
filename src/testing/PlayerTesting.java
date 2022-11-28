package testing;

import main.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

public class PlayerTesting {
	@Test
	public void ConstructorTest() {
		String username = "Jimmy";
		int accountBalance = 5000;
		Player player1 = new Player(username, accountBalance);
		Player player2 = new Player(player1);
		
		assertTrue(player1.getUsername().equals(username));
		assertEquals(player1.getAccountBalance(), accountBalance);
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
		assertEquals(player1.getRoomNumber(), roomNumber);
		assertEquals(player1.getPlayerState(), playerState);
		assertEquals(player1.getWager(), wager);
		assertEquals(player1.getSeatIndex(), seatIndex);
		assertEquals(player1.getAccountBalance(), accountBalance);
		assertEquals(player1.getCurrentAction(), currentAction);
		assertTrue(player1.getScore().equals(score));
	}
	@Test
	public void acceptAndRemoveCardTest() {
		Player player1 = new Player("Jimmy Johns", 42000);
		Card card = new Card(2, "Heart");
		
		player1.acceptCard(0, card);
		assertFalse(player1.getCurrentHand().isEmpty());

		player1.removeCard(0, 0);
		assertTrue(player1.getCurrentHand().isEmpty());
	}
	@Test
	public void clearHandTest() {
		Player player1 = new Player("Jimmy Jangles", 50000);
		player1.clearHand();
		assertTrue(player1.getCurrentHand().isEmpty());
	}
}
