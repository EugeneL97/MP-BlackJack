import java.util.*;

public class LobbyRoom {
	private int numberOfRooms;
	private ArrayList<ArrayList<String>> clientLobbyRoom;
	
	public LobbyRoom() {
		numberOfRooms = 5;
		
		this.clientLobbyRoom = new ArrayList<ArrayList<String>>();
		for (int x = 0; x < getNumberOfRooms(); ++x) {
			this.clientLobbyRoom.add(new ArrayList<String>());
		}
	}
	
	public LobbyRoom(ArrayList<ArrayList<String>> clientLobbyRoom) {
		this.numberOfRooms = 5;
		this.clientLobbyRoom = clientLobbyRoom;
	}
	
	public String toString() {
		String output = "";
		
		for (int x = 0; x < clientLobbyRoom.size(); ++x) {
			output += x + "%" + clientLobbyRoom.get(x).size() + "%";
			for (int y = 0; y < clientLobbyRoom.get(x).size(); ++y) {
				output += clientLobbyRoom.get(x).get(y) + "#";
			}
			output += "%";
		}
		
		return output;
	}
	
	public ArrayList<ArrayList<String>> getClientLobbyRoom() {
		return clientLobbyRoom;
	}

	public void setClientLobbyRoom(ArrayList<ArrayList<String>> clientLobbyRoom) {
		this.clientLobbyRoom = clientLobbyRoom;
	}
	
	public boolean addPlayer(int roomNumber, String player) {
		if (roomNumber >= 0 && roomNumber < numberOfRooms) {
			clientLobbyRoom.get(roomNumber).add(player);
			return true;
		}
		
		
		return false;
	}
	
	public boolean removePlayer(int roomNumber, String player) {
		if (roomNumber >= 0 && roomNumber < numberOfRooms) {
			clientLobbyRoom.get(roomNumber).remove(player);
		}
		
		return false;
	}
	
	public String playersInRoom(int roomNumber) {
		String output = null;
		
		if (roomNumber <= this.numberOfRooms) {
			output = "";
			
			for (int x = 0; x < clientLobbyRoom.get(roomNumber).size(); ++x) {
				output += clientLobbyRoom.get(roomNumber).get(x) + ", ";
			}
		}
		
		return output;
	}
	
	public int getNumberOfRooms() {
		return numberOfRooms;
	}
}
