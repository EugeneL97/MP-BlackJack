
public enum PlayerState {
	Lobby("Lobby"), Observe("Observe"), Playing("Playing");
	
	private final String playerState;
	
	PlayerState(String playerState) {
		this.playerState = playerState;
	}
	
	public String getPlayerState() {
		return playerState;
	}
}
