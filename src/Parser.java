import java.util.ArrayList;

public class Parser {
	public Parser() {
		
	}
	
	// Parses the toString() output of the LobbyRoom class and converts it into an object of
	// LobbyRoom class with the same state as the one that was sent
	public LobbyRoom parseLobbyRoom(String message) {
		LobbyRoom tmp = new LobbyRoom();
		String [] lobbyRoom;

		ArrayList<ArrayList<String>> clientLobbyRoom = new ArrayList<ArrayList<String>>();
		
		lobbyRoom = message.split("%");
	
		for (int x = 0; x < tmp.getNumberOfRooms(); ++x) {
			clientLobbyRoom.add(new ArrayList<String>());
		}
		
		
		for (int x = 0; x < lobbyRoom.length; x += 3) {	
			if(Integer.parseInt(lobbyRoom[x + 1]) != 0) {
				String [] names = lobbyRoom[x + 2].split("#");
				
				for (int y = 0; y < names.length; ++y) {
					clientLobbyRoom.get(Integer.parseInt(lobbyRoom[x])).add(names[y]);
				}
			}	
		}
			
		tmp = new LobbyRoom(clientLobbyRoom);
		
		return tmp;
	}
	
	
	// Parses the toString() output of the Room class and converts it into an object of Room class
	// with the same state as the one that was sent
	public Room parseRoom(String message) {
		Room tmp = null;
		int roomNumber;
		int readyToStart;
		int numberOfPlayersInRoom;
		ArrayList<Player> playersInRoom = new ArrayList<Player>();
		Shoe shoe;
		
		String [] room;
		
		room = message.split("%");
		
		roomNumber = Integer.parseInt(room[0]);
		readyToStart = Integer.parseInt(room[1]);
		numberOfPlayersInRoom= Integer.parseInt(room[2]);
		
		for (int x = 3; x < 3 + numberOfPlayersInRoom; ++x) {
			Player player = parsePlayer(room[x]);
			playersInRoom.add(player);
		}
		
		shoe = parseShoe(room[room.length - 1]);
		tmp = new Room(roomNumber, readyToStart, playersInRoom, shoe);
		
		return tmp;
	}
	
	// Parses the toString() output of the Player class and converts it into an object of Player class
	// with the same state as the one that was sent
	public Player parsePlayer(String message) {
		Player player;
		String [] playerInfo;
		String username = null;
		int playerState;
		int roomNumber;
		int accountBalance;
		int currentAction;
		int wager;
		int seatIndex;
		String score = null;
		int size;
		ArrayList<ArrayList<Card>> currentHand = new ArrayList<ArrayList<Card>>();
		
		playerInfo = message.split("#");
		
		username = playerInfo[0];
		playerState = Integer.parseInt(playerInfo[1]);
		roomNumber = Integer.parseInt(playerInfo[2]);
		accountBalance = Integer.parseInt(playerInfo[3]);
		currentAction = Integer.parseInt(playerInfo[4]);
		wager = Integer.parseInt(playerInfo[5]);
		seatIndex = Integer.parseInt(playerInfo[6]);
		score = playerInfo[7];
		size = Integer.parseInt(playerInfo[8]);
		
		
		if (size > 0) {
			for (int x = 0; x < size; ++x) {
				currentHand.add(new ArrayList<Card>());
			}
			
			for (int x = 9; x < playerInfo.length; x += 3) {
				currentHand.get(Integer.parseInt(playerInfo[x])).add(new Card(Integer.parseInt(playerInfo[x + 1]), playerInfo[x + 2]));
			}
		}
		
			
		player = new Player(username, playerState, roomNumber, accountBalance, currentAction, wager, seatIndex, score, currentHand);
		
		return player;
	}
	
	// Helper function for parseRoom function
	public Shoe parseShoe(String message) {
		Shoe tmp = null;
		ArrayList<Card> tmpDeck = parseDeck(message);
		tmp = new Shoe(tmpDeck);
		
		return tmp;
	}
	
	// Helper function for the parseRoom function
	public ArrayList<Card> parseDeck(String message) {
		ArrayList<Card> deck = new ArrayList<Card>();
		String [] cards;
		int value;
		String suit;
		cards = message.split("#");
		
		for (int x = 0; x < cards.length; x += 2) {
	
			Card tmp = new Card(Integer.parseInt(cards[x]), cards[x + 1]);
			
			deck.add(tmp);
		}
		
		return deck;
	}
}
