import java.util.*;

public class LobbyRoom {
	private int numberOfRooms;
	private ArrayList<ArrayList<String>> clientLobbyRoom;
	
	LobbyRoom(LobbyRoom clientLobbyRoom) {
		this.numberOfRooms = clientLobbyRoom.getNumberOfRooms();
		this.clientLobbyRoom = new ArrayList<ArrayList<String>>();
		
		for (int x = 0; x < clientLobbyRoom.getNumberOfRooms(); ++x) {
			this.clientLobbyRoom.add(new ArrayList<String>());
			
			for (int y = 0; y < clientLobbyRoom.getClientLobbyRoom().get(x).size(); ++y) {
				this.clientLobbyRoom.get(x).add(clientLobbyRoom.getClientLobbyRoom().get(x).get(y));
			}
		}
	}
	
	LobbyRoom() {
		this.clientLobbyRoom = new ArrayList<ArrayList<String>>();
		numberOfRooms = 0;
	}

	public String toString() {
		String output = "";
		
		output += numberOfRooms + "#";
		
		for (int x = 0; x < clientLobbyRoom.size(); ++x) {
			output += x + clientLobbyRoom.get(x).size() + "#";
			for (int y = 0; y < clientLobbyRoom.get(x).size(); ++y) {
				output += clientLobbyRoom.get(x).get(y) + "#";
			}
		}
		
		return output;
	}
	
	public ArrayList<ArrayList<String>> getClientLobbyRoom() {
		return clientLobbyRoom;
	}

	public void setClientLobbyRoom(ArrayList<ArrayList<String>> clientLobbyRoom) {
		this.clientLobbyRoom = clientLobbyRoom;
	}
	
	public void addRoom() {
		if (numberOfRooms > 0) {
			clientLobbyRoom.add(numberOfRooms, new ArrayList<String>());
		}
		else {
			clientLobbyRoom.add(0, new ArrayList<String>());
		}
		
		++numberOfRooms;
	}
	
	public boolean removeRoom() {
		if (numberOfRooms > 0) {
			clientLobbyRoom.remove(numberOfRooms - 1);
			--numberOfRooms;
			return true;
		}
		
		return false;
	}
	
	public boolean addPlayer(int roomNumber, String player) {
		if (clientLobbyRoom.get(roomNumber) != null) {
			clientLobbyRoom.get(roomNumber).add(player);
			return true;
		}
		
		return false;
	}
	
	public boolean removePlayer(int roomNumber, String player) {
		if (clientLobbyRoom.get(roomNumber) != null) {
			
			for (int x = 0; x < clientLobbyRoom.get(roomNumber).size(); ++x) {
				if (clientLobbyRoom.get(roomNumber).get(x).equals("player")) {
					clientLobbyRoom.get(roomNumber).remove(x);
					return true;
				}
			}
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
