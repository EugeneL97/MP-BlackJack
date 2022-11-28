package testing;

import main.Shoe;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import org.junit.jupiter.api.RepeatedTest;


public class ShoeTesting {
	
	@Test
	public void GetterAndSetterTest() {
		
		Shoe shoe = new Shoe();
		
		assertTrue(shoe.getShoeSize() == 312);
		assertTrue(shoe.getCutPoint() == 70);
		assertTrue(shoe.getNumOfDecks() == 6);
	}
}
