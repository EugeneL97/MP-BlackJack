package testing;

import main.Shoe;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.Test;


public class ShoeTesting {
	@Test
	public void GetterAndSetterTest() {
		
		Shoe shoe = new Shoe();
		
		assertEquals(shoe.getShoeSize(), 312);
		assertEquals(shoe.getCutPoint(), 70);
		assertEquals(shoe.getNumOfDecks(), 6);
		shoe.getDeck();
	}
	@Test
	public void dealCardTest() {
		
		Shoe shoe1 = new Shoe();
		Shoe shoe2 = new Shoe(shoe1.getDeck());
		shoe2.dealCard();
		
		shoe2.clearDeck();
		shoe2.dealCard();
	}
	@Test
	public void toStringTest() {
		String output = "";
		Shoe shoe1 = new Shoe();
		for (int i = 0; i < shoe1.getDeck().size(); ++i) {
			output += shoe1.getDeck().get(i).toString();
		}
		assertTrue(output.equals(shoe1.toString()));
	}
}
