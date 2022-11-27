package main;

public class Start {
	 /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {
    	//String ip = "127.0.1.1";
    	//int port = 59898;
        Client client = new Client();
        new ConnectGUI(client).setupConnectPanel();
    }
}
