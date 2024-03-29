package testing;

import main.Card;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

public class CardTesting {

	@Test
	public void ConstructorTest() {
		int rank = 5;
		String suit = "diamond";
		Card card = new Card(rank, suit);
		
		assertTrue(card.getRank() == rank && card.getSuit().equals(suit));
	}
	
	@Test
	public void GetterAndSetterTest() {
		int rank = 10;
		String suit = "spade";
		Card card = new Card(5, "heart");
		
		card.setSuit(suit);
		card.setRank(rank);
		
		assertTrue(card.getSuit().equals(suit) && card.getRank() == rank);
	}
	
	@Test
	public void ToStringTest() {
		int value = 3;
		String suit = "club";
		Card card = new Card(value, suit);
		
		String string1 = card.toString();
		String string2 = value + "#" + suit + "#";

		assertEquals(string1, string2);
	}
	
	@Test
	public void showCardTest() {
		int value = 5;
		String suit = "Club";
		Card card = new Card(value, suit);
		
		String toString = value + " of " + suit;
		assertEquals(card.showCard(), toString);
	}
}
