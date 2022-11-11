
public enum MessageType {
	Connected("Connected"), LoginSuccess("Login Success"), LoginFailed("Login Failed"), RegisterSuccess("Register Success"), RegisterFailed("Register Failed"), LobbyRoom("LobbyRoom"), Room("Room"), Player("Player");
	
	private final String messageType;
	
	MessageType(String messageType) {
		this.messageType = messageType;
	}
	
	public String getMessageType() {
		return messageType;
	}
}
