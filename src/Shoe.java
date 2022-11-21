import java.util.*;

public class Shoe {
	private final int numOfDecks = 6;
	private final int cutPoint = 70;
	private final int SHOE_SIZE = 312;
	private final int cardValue[];
	private final String suits[];
	private ArrayList<Card> deck;
	
	public Shoe() {
		cardValue = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
		suits = new String[] {"Spade", "Heart", "Club", "Diamond"};
		this.deck = new ArrayList<Card>();
		generateCards();
	}
	
	public Shoe(ArrayList<Card> deck) {
		this();
		this.deck = deck;
	}
	
	public String toString() {
		String output = "";
		
		for (int x = 0; x < deck.size(); ++x) {
			output += deck.get(x).toString();
		}
		
		return output;
	}
	
	public ArrayList<Card> getDeck() {
		return deck;
	}
	
	public void generateCards() {
		Random random = new Random();
		
		for (int i = 0; i < numOfDecks; ++i) {
	        for (int x = 0; x < cardValue.length; ++x ) {
	            for (int y = 0; y < suits.length; ++y) {
	                deck.add(new Card(cardValue[x], suits[y]));
	            }
	        }
	    }
		
		// shuffling
		for (int x = 0; x < SHOE_SIZE; x++) {
			int index = random.nextInt(SHOE_SIZE);
			
			Card tmp = new Card(deck.get(x).getValue(), deck.get(x).getSuit());
			
			deck.set(x, deck.get(index));
			deck.set(index, tmp);
		}
		
		// cutting out the first 70 cards of the deck
		for (int x = 0; x < cutPoint; ++x) {
			deck.remove(x);
		}
	}
	
	public Card dealCard() {
		Card tmp = null;
		
		if (deck.size() > 0) {
			tmp = new Card(deck.get(0).getValue(), deck.get(0).getSuit());
			deck.remove(0);
		}
		else {
			generateCards();
			tmp = new Card(deck.get(0).getValue(), deck.get(0).getSuit());
			deck.remove(0);
		}
		
		return tmp;
	}
}
