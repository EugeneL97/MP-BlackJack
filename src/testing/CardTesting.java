package testing;

import main.Card;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

public class CardTesting {

	@Test
	public void ConstructorTest() {
		int value = 5;
		String suit = "diamond";
		Card card = new Card(value, suit);
		
		assertTrue(card.getValue() == value && card.getSuit().equals(suit));
	}
	
	@Test
	public void GetterAndSetterTest() {
		int value = 10;
		String suit = "spade";
		Card card = new Card(5, "heart");
		
		card.setSuit(suit);
		card.setValue(value);
		
		assertTrue(card.getSuit().equals(suit) && card.getValue() == value);
	}
	
	@Test
	public void ToStringTest() {
		int value = 3;
		String suit = "club";
		Card card = new Card(value, suit);
		
		String string1 = card.toString();
		String string2 = Integer.toString(value) + "#" + suit + "#";
		
		assertTrue(string1.equals(string2));
	}
	
	@Test
	public void showCardTest() {
		int value = 5;
		String suit = "Club";
		Card card = new Card(value, suit);
		
		String toString = value + " of " + suit;
		assertTrue(card.showCard().equals(toString));
	}
}
