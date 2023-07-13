package testing;

import main.CardShoe;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;


public class CardShoeTesting {
	
	@Test
	public void getterTest() {
		
		CardShoe cardShoe = new CardShoe();

		assertEquals(312, cardShoe.getDeck().size());
	}
}
