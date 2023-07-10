package main;
public class Card {
	private int rank;
	private String suit;
	
	public Card(int value, String suit) {
		this.rank = value;
		this.suit = suit;
	}
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getSuit() {
		return suit;
	}
	public void setSuit(String suit) {
		this.suit = suit;
	}
	
	public String showCard() {
		StringBuilder output = new StringBuilder();
		
		output.append(rank).append(" of ").append(suit);
		
		return output.toString();
	}
	@Override
	public String toString() {
		return showCard();
	}
}
