package testing;

import main.Message;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;


public class MessageTesting {
	@Test
	public void ConstructorTest() {
		String type = "delivered";
		String status = "undefined";
		String text = "i love chicken strips so much!!!";
		
		Message msg1 = new Message();
		Message msg2 = new Message(msg1);
		Message msg3 = new Message(type, status, text);
		
		Boolean typeCheck = msg1.getType().equals(msg2.getType());
		Boolean statusCheck = msg1.getStatus().equals(msg2.getStatus());
		Boolean textCheck = msg1.getText().equals(msg2.getText());
		
		assertTrue(typeCheck && statusCheck && textCheck);
		
		typeCheck = msg3.getType().equals(type);
		statusCheck = msg3.getStatus().equals(status);
		textCheck = msg3.getText().equals(text);
		
		assertTrue(typeCheck && statusCheck && textCheck);
	}
	
	@Test
	public void GetterAndSetterTest() {
		String type = "delivered";
		String status = "undefined";
		String text = "i love chicken strips so much!!!";
		
		Message msg1 = new Message();
		msg1.setType(type);
		msg1.setStatus(status);
		msg1.setText(text);
		
		Boolean typeCheck = msg1.getType().equals(type);
		Boolean statusCheck = msg1.getStatus().equals(status);
		Boolean textCheck = msg1.getText().equals(text);
		
		assertTrue(typeCheck && statusCheck && textCheck);
	}
}
