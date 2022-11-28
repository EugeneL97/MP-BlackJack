package testing;

import main.Room;
import main.Player;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

public class RoomTesting {
	@Test
	public void addOrRemovePlayerTest() {
		Room room = new Room(0);
		Player player = new Player("test", 5000);
		for (int i = 0; i < 100; i++) {
			room.addPlayer(i, player);
		}
		
		// Max should be 7, dealer + 6 players
		assertTrue(room.getNumOfPlayers() == 7);
		
		for (int i = room.getNumOfPlayers() - 2; i >= 0; i--) {
			room.removePlayer(i);
		}
		//System.out.println(room.getNumOfPlayers());
		//Dealer should be last one left
		assertTrue(room.getNumOfPlayers() == 1);
		
		
	}
	
	@Test
	public void ConstructorTest() {
		Room room1 = new Room(0);
		Room room2 = new Room(room1);
		Room room3 = new Room(room2.getRoomNumber(), room2.getReadyToStart(), room2.getPlayersInRoom(), room2.getShoe());
		
		Boolean roomNumberComp = room1.getRoomNumber() == room3.getRoomNumber();
		Boolean readyToStartComp = room1.getReadyToStart() == room3.getReadyToStart();
		Boolean playersInRoomComp = room1.getPlayersInRoom() == room3.getPlayersInRoom();
		Boolean shoeComp = room1.getShoe() == room3.getShoe();
		
		assertTrue(roomNumberComp && readyToStartComp && playersInRoomComp && shoeComp);
		
	}
}
