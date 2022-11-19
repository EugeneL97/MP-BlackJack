import java.util.*;

public class Driver {
	public static void main(String[] args) {
		Parser parser = new Parser();
		/*
		// Test parsing Player
		Player player = new Player("jackson", 214554);
		Shoe shoe = new Shoe();

		for (int x = 0; x < 3; ++x) {
			player.getCurrentHand().add(new ArrayList<Card>());
			for (int y = 0; y < 10; ++y) {
				player.getCurrentHand().get(x).add(shoe.dealCard());
			}
		}
	
		player.setRoomNumber(3);
		player.setPlayerState(1);
		player.setPlayer(1);
		player.setSitting(1);
		
		Message message = new Message("player", "", player.toString());
		System.out.println(player.toString());
		Player tmp = parser.parsePlayer(player.toString());
		System.out.println(tmp.toString() + "\n");
		
		
		// Test parsing Shoe
		shoe = new Shoe();
		shoe.generateCards();
		message = new Message("shoe", "", shoe.toString());
		
		System.out.println(shoe.toString());
		Shoe newShoe = parser.parseShoe(message.getText());
		System.out.println(newShoe.toString() + "\n");
		
		// Test parsing Room
		ArrayList<Player> currentPlayers = new ArrayList<Player>();
		ArrayList<Player> playersInRoom = new ArrayList<Player>();
		int roomNumber = 23;
		int readyToStart = 1;
		ArrayList<String> username = new ArrayList<String>();
		username.add("harvey");
		username.add("md44l");
		username.add("bmagic");

		int playerState = 2;
		int accountBalance = 50000;
		int currentAction = 0;
		int isPlayer = 1;
		int isSitting = 1;
		ArrayList<ArrayList<Card>> currentHand = new ArrayList<ArrayList<Card>>();
		shoe = new Shoe();
		currentHand.add(new ArrayList<Card>());
		
		for (int x = 0; x < 3; ++x) {
			for (int y = 0; y < 3; ++y) {
				currentHand.get(0).add(shoe.dealCard());
			}
			
			currentPlayers.add(new Player(username.get(x), playerState, roomNumber, accountBalance, currentAction, isPlayer, isSitting, currentHand));
			playersInRoom.add(new Player(username.get(x), playerState, roomNumber, accountBalance, currentAction, isPlayer, isSitting, currentHand));
		}
	
		Room room;
		Room newRoom;
		room = new Room(roomNumber, readyToStart, currentPlayers, playersInRoom, shoe);
		System.out.println(room.toString());
		message = new Message("room", "", room.toString());
		newRoom = parser.parseRoom(message.getText());
		System.out.println(newRoom.toString());
		*/
		
		// Test parsing LobbyRoom
		LobbyRoom clientLobbyRoom = null;
		
		
	}
}
