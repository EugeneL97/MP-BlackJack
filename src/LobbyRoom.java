import java.util.*;

public class LobbyRoom {
	private ArrayList<String> clientLobbyRoom;
	
	LobbyRoom(ArrayList<String> clientLobbyRoom) {
		this.clientLobbyRoom = clientLobbyRoom;
	}

	public ArrayList<String> getClientLobbyRoom() {
		return clientLobbyRoom;
	}

	public void setClientLobbyRoom(ArrayList<String> clientLobbyRoom) {
		this.clientLobbyRoom = clientLobbyRoom;
	}
	
	public void addPlayer(String player) {
		clientLobbyRoom.add(player);
	}
}
