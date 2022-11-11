import java.io.Serializable;

public class Message implements Serializable {
	private static int count = 0;
	private MessageType message;
	private Room room;
	private Player player;
	private Login login;
	private Register register;
	private LobbyRoom lobbyRoom;
	
	public Message() {
		this.message = null;
		this.room = null;
		this.player = null;
		this.login = null;
		this.register = null;
		this.lobbyRoom = null;
	}
	
	public Message(MessageType message) {
		this(message, null, null, null, null, null);
	}
	
	public Message(MessageType message, Room room) {
		this(message, room, null, null, null, null);
	}
	
	public Message(MessageType message, Player player) {
		this(message, null, player, null, null, null);
	}
	
	public Message(MessageType message, Login login) {
		this(message, null, null, login, null, null);
	}
	
	public Message(MessageType message, Register register) {
		this(message, null, null, null, register, null);
	}
	
	public Message(MessageType message, LobbyRoom lobbyRoom) {
		this(message, null, null, null, null, lobbyRoom);
	}
	
	public Message(MessageType message, Room room, Player player, Login login, Register register, LobbyRoom lobbyRoom) {
		this.message = message;
		this.room = room;
		this.player = player;
		this.login = login;
		this.register = register;
		this.lobbyRoom = lobbyRoom;
		++count;
	}

	public MessageType getMessage() {
		return message;
	}

	public void setMessage(MessageType message) {
		this.message = message;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public Register getRegister() {
		return register;
	}

	public void setRegister(Register register) {
		this.register = register;
	}

	public LobbyRoom getLobbyRoom() {
		return lobbyRoom;
	}

	public void setLobbyRoom(LobbyRoom lobbyRoom) {
		this.lobbyRoom = lobbyRoom;
	}
	
	public int getCount() {
		return count;
	}
}
