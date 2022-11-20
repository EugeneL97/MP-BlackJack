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
		
		/*
		for (int x = 0; x < clientLobbyRoom.getNumberOfRooms(); ++x) {
			this.clientLobbyRoom.add(new ArrayList<String>());
			
			for (int y = 0; y < clientLobbyRoom.getClientLobbyRoom().get(x).size(); ++y) {
				this.clientLobbyRoom.get(x).add(clientLobbyRoom.getClientLobbyRoom().get(x).get(y));
			}
		}*/
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
	
	public void addPlayer(int roomNumber, String player) {
		clientLobbyRoom.get(roomNumber).add(player);
	}
	
	public boolean removePlayer(int roomNumber, String player) {
		if (roomNumber >= 0 && roomNumber < numberOfRooms) {
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
