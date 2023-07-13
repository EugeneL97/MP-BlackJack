package main;
import java.util.*;

public class CardShoe {
	private final int numOfDecks = 6;
	private final int[] cardRank = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
	private final String[] suits = {"Spade", "Heart", "Club", "Diamond"};
	private final ArrayList<Card> deck;
	
	public CardShoe() {
		deck = new ArrayList<>();
		generateShoe();
	}
	
	public CardShoe(ArrayList<Card> deck) {
		this.deck = new ArrayList<>(deck);
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();

		for (Card card : deck) {
			result.append(card.toString());
		}
		
		return result.toString();
	}
	
	public ArrayList<Card> getDeck() {
		return deck;
	}
	
	public void clearDeck() {
		this.deck.clear();
	}
	public void generateShoe() {
		clearDeck();
		Random random = new Random();

		for (int i = 0; i < numOfDecks; ++i) {
			for (int rank : cardRank) {
				for (String suit : suits) {
					deck.add(new Card(rank, suit));
				}
			}
	    }
		shuffleShoe(random);
		removeCutCards();
	}
	public void shuffleShoe(Random random){
		int shoeCapacity = deck.size() * numOfDecks;
		for (int currentIndex = 0; currentIndex < shoeCapacity; currentIndex++) {
			int randomIndex = random.nextInt(shoeCapacity);
			swapCards(currentIndex, randomIndex);
		}
	}
	public void swapCards(int index1, int index2){
		Card card1 = deck.get(index1);
		Card card2 = deck.get(index2);

		deck.set(index1, card2);
		deck.set(index2, card1);
	}
	public void removeCutCards(){
		int cutPoint = 70;
		deck.subList(0, cutPoint).clear();
	}
	public Card dealCard() {
		if (deck.size() == 0) {
			generateShoe();
		}

		Card cardToDeal = deck.get(0);
		deck.remove(0);

		return cardToDeal;
	}
}
