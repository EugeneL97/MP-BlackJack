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
		player.setSeatIndex(1);
		
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
		Shoe shoe = new Shoe();
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
			
			playersInRoom.add(new Player(username.get(x), playerState, roomNumber, accountBalance, currentAction, isPlayer, isSitting, currentHand));
		}
	
		Room room;
		Room newRoom;
		room = new Room(roomNumber, readyToStart, playersInRoom, shoe);
		System.out.println(room.toString());
		Message message = new Message("room", "", room.toString());
		newRoom = parser.parseRoom(message.getText());
		System.out.println(newRoom.toString());
		
		
		
		// Test parsing LobbyRoom
		LobbyRoom clientLobbyRoom = new LobbyRoom();
		LobbyRoom newLobbyRoom = null;
		ArrayList<String> username = new ArrayList<String>();
		username.add("md44l");
		username.add("bmagic");
		username.add("ugene");
		username.add("Alex");
		
		for (int x = 0; x < username.size(); ++x) {
			clientLobbyRoom.addPlayer(0, username.get(x));
			clientLobbyRoom.addPlayer(4, username.get(x));
			clientLobbyRoom.addPlayer(2, username.get(x));
		}
		
		System.out.println(clientLobbyRoom.toString());
		
		newLobbyRoom = parser.parseLobbyRoom(clientLobbyRoom.toString());
		System.out.println(newLobbyRoom.toString());
		
		
		// Testing countdown timer
		Room room = new Room(0);
		
		Driver driver = new Driver();
		driver.countDown(room);
		*/
		
		// Testing bust()
		Driver driver = new Driver();
		Server server = new Server();
		Player player = new Player("yang", 5000);
		player.setRoomNumber(0);
		server.getRooms().get(0).addPlayer(player);
		
		Card card = new Card(1, "Spade");
		Card card2 = new Card(1, "Hearts");
		Card card3 = new Card(6, "Hearts");
		Card card4 = new Card(6, "Hearts");
		Card card5 = new Card(3, "Hearts");
		Card card6 = new Card(4, "Hearts");


		
		server.getRooms().get(0).getPlayersInRoom().get(1).acceptCard(0, card);
		server.getRooms().get(0).getPlayersInRoom().get(1).acceptCard(0, card2);
		server.getRooms().get(0).getPlayersInRoom().get(1).acceptCard(0, card3);
		server.getRooms().get(0).getPlayersInRoom().get(1).acceptCard(0, card4);
		server.getRooms().get(0).getPlayersInRoom().get(1).acceptCard(0, card5);
		//server.getRooms().get(0).getPlayersInRoom().get(1).acceptCard(0, card6);
		System.out.println(player.toString());
		//System.out.println(server.getRooms().get(0).toString());
		//server.getRooms().get(0).getPlayersInRoom().get(1).getCurrentHand().get(0).size()
		
		
		String result = driver.bust(0, 1, 0, server);
		System.out.println(result);
		
	}
	
	// The bust function takes the room number where the player is located, the index that indicates the player's position in the playersInRoom array and the 
	// index of the current hand to determine if the total so far is a bust or not bust or is a blackjack.
	public String bust(int roomNumber, int playerIndex, int handIndex, Server server) {
		int total = 0;
		
		ArrayList<ArrayList<Integer>> tmpArray1 = new ArrayList<ArrayList<Integer>>();
		tmpArray1.add(new ArrayList<Integer>());
		
		// Copy all values of cards currentHand at index x into tmpArray1 and convert values of 11, 12, 13 to 10
		for (int y = 0; y < server.getRooms().get(roomNumber).getPlayersInRoom().get(playerIndex).getCurrentHand().get(handIndex).size(); ++y) {
			if (server.getRooms().get(roomNumber).getPlayersInRoom().get(playerIndex).getCurrentHand().get(handIndex).get(y).getValue() == 11
					|| server.getRooms().get(roomNumber).getPlayersInRoom().get(playerIndex).getCurrentHand().get(handIndex).get(y).getValue() == 12
					|| server.getRooms().get(roomNumber).getPlayersInRoom().get(playerIndex).getCurrentHand().get(handIndex).get(y).getValue() == 13) {
				tmpArray1.get(0).add(10);
			}
			else {
				tmpArray1.get(0).add(server.getRooms().get(roomNumber).getPlayersInRoom().get(playerIndex).getCurrentHand().get(handIndex).get(y).getValue());
			}
		}	
		
		// Check if there are any aces or values of 1 in tmpArray1. If an ace is found, make a duplicate of the current hand by copying it into tmpArray 2 
		// and take note of the index value where the ace is found. Replace the value of the ace from 1 to 11 in tmpArray2, then add it to tmpArray1.
		// by repeating this step, we can get all permutation of the values of the current hand where an ace could be either a 1 or an 11
		for (int j = 0; j < tmpArray1.size(); ++j) {
			for (int i = 0; i < tmpArray1.get(j).size(); ++i) {
				if (tmpArray1.get(j).get(i) == 1) {
					int tmpIndex = i;
					ArrayList<Integer> tmpArray2 = new ArrayList<Integer>();
					tmpArray2 = new ArrayList<Integer> (tmpArray1.get(j));
					
					tmpArray2.set(tmpIndex, 11);
					tmpArray1.add(tmpArray2);
				}
			}
		}
		
		int busts = 0;
		
		for (int y = 0; y < tmpArray1.size(); ++y) {
			for (int i = 0; i < tmpArray1.get(y).size(); ++ i) {
				total += tmpArray1.get(y).get(i);
			}
			
			if (total > 21) {
				++busts;
			}
			else if (total == 21) {
				return "blackjack";
			}
			total = 0;
		}
		
		if (busts == tmpArray1.size() ) {
			return "bust";
		}
		else {
			return "not bust";
		}
	}
	
	
	public void countDown(Room room) {
	    TimerTask task = new TimerTask() {
	        public void run() {
	        	room.setReadyToStart(999);
	            System.out.println("Ready to start has been changed to = " + room.getReadyToStart());
	        }
	    };
	    Timer timer = new Timer("Timer");
	    
	    long delay = 10000L;
	    timer.schedule(task, delay);
	}
}
