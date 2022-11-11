import java.util.*;
import java.io.*;
import java.net.*;

public class Client {
	private ArrayList<Message> messageQueue;
	private Socket socket;
	
	Client() {
		messageQueue = new ArrayList<Message>();
		socket = null;
	}
	
	public static void main(String [] args) throws IOException {
		Client client = new Client();
		
		client.socket = new Socket("localhost", 50509);
		
		OutputStream outputStream = client.socket.getOutputStream();
		ObjectOutputStream output = new ObjectOutputStream(outputStream);
		
		InputStream inputStream = client.socket.getInputStream();
		ObjectInputStream input = new ObjectInputStream(inputStream);
		
	}
	
	public void SendMessage(Message message) {
		
		try {
			OutputStream outputStream = socket.getOutputStream();
			
			ObjectOutputStream output = new ObjectOutputStream(outputStream);
			
			output.writeObject(message);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
