package main;
public class Card {
	private int rank;
	private String suit;
	
	public Card(int rank, String suit) {
		this.rank = rank;
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
		return rank + " of " + suit;
	}
	@Override
	public String toString() {
		return showCard();
	}
}
