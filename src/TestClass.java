import java.util.*;

public class TestClass {
	private ArrayList<String> test;
	
	TestClass(ArrayList<String> test) {
		this.test = test;
	}

	public ArrayList<String> getTest() {
		return test;
	}

	public void setTest(ArrayList<String> test) {
		this.test = test;
	}
	
	public void print() {
		for (int x = 0; x < test.size(); ++x) {
			System.out.println(test.get(x));
		}
	}
}
