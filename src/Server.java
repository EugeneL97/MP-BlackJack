import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
	private ServerSocket serverSock;
	private Socket clientSock;
	
	public Server() {
		serverSock = null;
		clientSock = null;
	}
	
	public static void main(String[] args) {
		
		Server server = new Server();
		
		try {
			//server listening on port 50509
			server.serverSock = new ServerSocket(50509);
			server.serverSock.setReuseAddress(true);
			
			//loop to get client connection requests
			while(true) {
				// creating socket to accept incoming client connection
				server.clientSock = server.serverSock.accept();
				
				// display new client connection
				System.out.println("New client conected " + server.clientSock.getInetAddress().getHostAddress());
				
				// creating a new thread object to handle the new client
				ClientHandler clientSocket = new ClientHandler(server.clientSock);
				
				// spawning thread
				new Thread(clientSocket).start();
				
				// send connection success message
				MessageType connected = MessageType.Connected;
				Message message = new Message(connected);
				server.SendMessage(message);

				
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (server != null) {
				try {
					server.serverSock.close();
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void SendMessage(Message message) {
		
		try {
			OutputStream outputStream = clientSock.getOutputStream();
			
			ObjectOutputStream output = new ObjectOutputStream(outputStream);
			
			output.writeObject(message);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
		

	
	private static class ClientHandler implements Runnable {
		private final Socket clientSocket;
		private ArrayList<Message> messageQueue;
		
		public ClientHandler(Socket clientSocket) {
			this.clientSocket = clientSocket;
		}
		
		public void run() {
			try {
				InputStream inputStream = clientSocket.getInputStream();
				ObjectInputStream input = new ObjectInputStream(inputStream);
				
				while(true) {
					try {
						List<Message> messageQueue = (List<Message>) input.readObject();
						
						for (int x = 0; x < messageQueue.size(); x++) {
							System.out.println("Message = " + messageQueue.get(x).toString());
						}
					}
					catch (ClassNotFoundException c) {
						c.printStackTrace();
					}
					
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void SendMessage(Message message) {
			
			try {
				OutputStream outputStream = clientSocket.getOutputStream();
				
				ObjectOutputStream output = new ObjectOutputStream(outputStream);
				
				output.writeObject(message);
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
