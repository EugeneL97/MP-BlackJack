
public class Card {
	private int value;
	private String suit;
	public Card(int value, String suit) {
		super();
		this.value = value;
		this.suit = suit;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getSuit() {
		return suit;
	}
	public void setSuit(String suit) {
		this.suit = suit;
	}
	
	public String toString() {
		return Integer.toString(value) + " of " + suit;
	}
}
