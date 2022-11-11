import java.util.*;

public class Driver {
	public static void main(String[] args) {
		ArrayList<String> text;
		text = new ArrayList<String>();
		text.add("1");
		text.add("2");
		text.add("whatever");
		
		TestClass newMessage;
		
		newMessage = new TestClass(text);
		
		newMessage.print();
	}
}
