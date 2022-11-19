import java.util.ArrayList;

public class Parser {
	public Parser() {
		
	}
	
	public LobbyRoom parseLobbyRoom(String message) {
		LobbyRoom tmp = null;
		
		return tmp;
	}
	
	
	// Parses the toString() output of the Room class and converts it into an object of Room class
	// with the same state as the one that was sent
	public Room parseRoom(String message) {
		Room tmp = null;
		int roomNumber;
		int readyToStart;
		int numberOfCurrentPlayers;
		ArrayList<Player> currentPlayers = new ArrayList<Player>();
		int numberOfPlayersInRoom;
		int numberOfPlayersInRoomIndex;
		ArrayList<Player> playersInRoom = new ArrayList<Player>();
		Shoe shoe;
		
		String [] room;
		
		room = message.split("%");
		
		roomNumber = Integer.parseInt(room[0]);
		readyToStart = Integer.parseInt(room[1]);
		numberOfCurrentPlayers = Integer.parseInt(room[2]);
		
		for (int x = 3; x < 3 + numberOfCurrentPlayers; ++x) {
			currentPlayers.add(parsePlayer(room[x]));

		}
		
		numberOfPlayersInRoom = Integer.parseInt(room[3 + numberOfCurrentPlayers]);
		numberOfPlayersInRoomIndex = 3 + numberOfCurrentPlayers;
		
		for (int x = 1 + numberOfPlayersInRoomIndex; x < 1 + numberOfPlayersInRoomIndex + numberOfPlayersInRoom; ++x) {
			playersInRoom.add(parsePlayer(room[x]));
		}
		
		shoe = parseShoe(room[room.length - 1]);
		
		tmp = new Room(roomNumber, readyToStart, currentPlayers, playersInRoom, shoe);
		
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
		int isPlayer;
		int isSitting;
		int size;
		ArrayList<ArrayList<Card>> currentHand = new ArrayList<ArrayList<Card>>();
		
		playerInfo = message.split("#");
		
		username = playerInfo[0];
		playerState = Integer.parseInt(playerInfo[1]);
		roomNumber = Integer.parseInt(playerInfo[2]);
		accountBalance = Integer.parseInt(playerInfo[3]);
		currentAction = Integer.parseInt(playerInfo[4]);
		isPlayer = Integer.parseInt(playerInfo[5]);
		isSitting = Integer.parseInt(playerInfo[6]);
		size = Integer.parseInt(playerInfo[7]);
		
		for (int x = 0; x < size; ++x) {
			currentHand.add(new ArrayList<Card>());
		}
		
		for (int x = 8; x < playerInfo.length; x += 3) {
			currentHand.get(Integer.parseInt(playerInfo[x])).add(new Card(Integer.parseInt(playerInfo[x + 1]), playerInfo[x + 2]));
		}
			
		player = new Player(username, playerState, roomNumber, accountBalance, currentAction, isPlayer, isSitting, currentHand);
		
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
