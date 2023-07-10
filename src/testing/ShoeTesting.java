package testing;

import main.Shoe;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;


public class ShoeTesting {
	
	@Test
	public void GetterAndSetterTest() {
		
		Shoe shoe = new Shoe();

		assertEquals(312, shoe.getShoeSize());
		assertEquals(70, shoe.getCutPoint());
		assertEquals(6, shoe.getNumOfDecks());
	}
}
