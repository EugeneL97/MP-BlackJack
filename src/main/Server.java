package main;
import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
	// Every time a player create a room, a new room is added to this array. This is how the server keeps track of which rooms are active.
	// This will also be how the game is updated. If a player selects hit, it sends a message to the server and the server modifies the
	// values of the player within the corresponding room the player is in using this attribute.
	private ArrayList<Room> rooms;
	
	// This class is used for sending to each client the so they have information to populate the lobby GUI and show which rooms are available
	// and which players are in which room.
	private LobbyRoom lobbyRooms;
	
	// newMessage attribute lets the server know if any one of the ClientHandler threads has received a message from
	// their respective clients after they've entered the lobby room loop and beyond. If a ClientHandler thread received a 
	// message from their respective clients, then set this variable to true. At the end of each loop in the lobby room,
	// game room, or playing loop, check if newMessage variable is set to true. If it is true, then each thread will send the
	// current state of the game, which includes an instance of the server's ArrayList<Room> rooms and the LobbyRoom lobbyRooms
	// object to the client, so that the client may update their GUI based on which window they're on. For example, if they're
	// in the lobby GUI, they will only use the LobbyRoom lobbyRooms object to update their GUI. If they're inside the game room
	// GUI they will use the Room room object to update their GUI.
	private boolean newMessage;
	
	// Max number of rooms
	private final int MAX_ROOMS = 5;

	private static final class currentActions {
		static final int NO_DECISION = -1;
		static final int DEAL = 0;
		static final int HIT = 1;
		static final int DOUBLE = 2;
		static final int STAND = 3;
		static final int SIT_OUT = 4;
	}

	private static final class playerStates {
		static final int DONE = 4;
		static final int PLAYING = 3;
		static final int SITTING = 2;
		static final int SPECTATOR = 1;
		static final int IN_LOBBY = 0;
	}

	private static final class roomStates {
		static final int WaitingToSTart = 0;
		static final int CountDown = 1;
		static final int MiddleOfRound = 2;
		static final int EndOfRound = 3;
	}

	public Server() {
		this.rooms = new ArrayList<Room>();
		this.lobbyRooms = new LobbyRoom();
		this.newMessage = false;
		System.out.println(currentActions.DEAL);
		
		for (int x = 0; x < MAX_ROOMS; ++x) {
			this.rooms.add(new Room(x));
		}
	}
	
	public static void main(String [] args) throws Exception {
		// Create a new instance of Server class. We will pass this variable to the ClientHandler object so that each thread for the client
		// can access the server attributes ArrayList<Room< rooms and LobbyRoom lobbyRooms in order to update it with changes based on player
		// input.
		Server server = new Server();
		
		int port = 59898; // Port to use
		ServerSocket serverSocket = new ServerSocket(port); // opening up port to listen for connection
		serverSocket.setReuseAddress(true);
		
		// Get real ip address
		URL url = new URL("http://checkip.amazonaws.com/");
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		
		// Output ip and local host address
		System.out.println("Server is now running on - \nIP address: " + reader.readLine() + ":" + Integer.toString(port) + "\nLocal Host: " + serverSocket.getInetAddress().getLocalHost() + ":" + Integer.toString(port));
		
	
		// Create new instance of gameHandler
		GameHandler room0, room1, room2, room3, room4;
		
		// Spawn one thread for each of the 5 rooms
		room0 = new GameHandler(server, 0);
		room1 = new GameHandler(server, 1);
		room2 = new GameHandler(server, 2);
		room3 = new GameHandler(server, 3);
		room4 = new GameHandler(server, 4);
		System.out.println("Spawning thread for room 0");
		new Thread(room0).start();
		
		System.out.println("Spawning thread for room 1");
		new Thread(room1).start();
		System.out.println("Spawning thread for room 2");
		new Thread(room2).start();
		System.out.println("Spawning thread for room 3");
		new Thread(room3).start();
		System.out.println("Spawning thread for room 4");
		new Thread(room4).start();
		
		
		/*
		// Old implementation
		// Create new instance of gameHandler
		GameHandler gameHandler = new GameHandler(server);
		// Spawn new thread to handle games
		new Thread(gameHandler).start();
		*/
		
		
		
		
		// Accept connections
		while (true) {
			Socket incomingConnection = serverSocket.accept();
			System.out.println("New client connected " + incomingConnection.getInetAddress().getHostAddress());
			System.out.flush();
			ClientHandler clientSocket = new ClientHandler(incomingConnection, server);
			
			// Spawn new thread for each conneciton accepted
			new Thread(clientSocket).start();
			
		}
	}

	// This class will handle all the games that are running in each room
	private static class GameHandler implements Runnable {
		Server server;
		int roomNumber;
		
		public GameHandler (Server server, int roomNumber) {
			this.server = server;
			this.roomNumber = roomNumber;
		}
			
		// The bust function takes the room number where the player is located, the index that indicates the player's position in the playersInRoom array and the 
		// index of the current hand to determine if the total so far is a bust or not bust or is a blackjack.
		public int tally(int roomNumber, int playerIndex, int handIndex) {
			int total = 0;
			
			ArrayList<Integer> tmpArray1 = new ArrayList<Integer>();

			
			// Copy all values of cards currentHand at index x into tmpArray1 and convert values of 11, 12, 13 to 10
			for (int y = 0; y < server.getRooms().get(roomNumber).getPlayersInRoom()[playerIndex].getCurrentHand().get(handIndex).size(); ++y) {
				if (server.getRooms().get(roomNumber).getPlayersInRoom()[playerIndex].getCurrentHand().get(handIndex).get(y).getValue() == 11
						|| server.getRooms().get(roomNumber).getPlayersInRoom()[playerIndex].getCurrentHand().get(handIndex).get(y).getValue() == 12
						|| server.getRooms().get(roomNumber).getPlayersInRoom()[playerIndex].getCurrentHand().get(handIndex).get(y).getValue() == 13) {
					tmpArray1.add(10);
				}
				else {
					tmpArray1.add(server.getRooms().get(roomNumber).getPlayersInRoom()[playerIndex].getCurrentHand().get(handIndex).get(y).getValue());
				}
			}	
			
			// To keep track of the unchanged ace, where the ace is still = 11
			int unchangedAce = -1;
			
			// Tally up the values in the hand
			for (int i = 0; i < tmpArray1.size(); ++i) {
				if (tmpArray1.get(i) == 1) {
					if (total + 11 <= 21) {
						total += 11;
						unchangedAce = i;
					}
					else if (total + 1 <= 21){
						total += 1;
					}
					else if (total + 1 > 21) {
						if (unchangedAce != -1) {
							total += 1;
							total -= 10;
							unchangedAce = -1;
						}
						else {
							total += 1;
						}
					}
				}
				else {
					if (total + tmpArray1.get(i) > 21 && unchangedAce != -1) {
						total += tmpArray1.get(i);
						total -= 10;
					}
					else {
						total += tmpArray1.get(i);
					}
				}
			}

			
			return total;
		}
		
		// The bust function takes the room number where the player is located, the index that indicates the player's position in the playersInRoom array and the 
		// index of the current hand to determine if the total so far is a bust or not bust or is a blackjack.
		public String bust(int roomNumber, int playerIndex, int handIndex) {
			int total = 0;
			
			ArrayList<Integer> tmpArray1 = new ArrayList<Integer>();

			
			// Copy all values of cards currentHand at index x into tmpArray1 and convert values of 11, 12, 13 to 10
			for (int y = 0; y < server.getRooms().get(roomNumber).getPlayersInRoom()[playerIndex].getCurrentHand().get(handIndex).size(); ++y) {
				if (server.getRooms().get(roomNumber).getPlayersInRoom()[playerIndex].getCurrentHand().get(handIndex).get(y).getValue() == 11
						|| server.getRooms().get(roomNumber).getPlayersInRoom()[playerIndex].getCurrentHand().get(handIndex).get(y).getValue() == 12
						|| server.getRooms().get(roomNumber).getPlayersInRoom()[playerIndex].getCurrentHand().get(handIndex).get(y).getValue() == 13) {
					tmpArray1.add(10);
				}
				else {
					tmpArray1.add(server.getRooms().get(roomNumber).getPlayersInRoom()[playerIndex].getCurrentHand().get(handIndex).get(y).getValue());
				}
			}	
			
			// To keep track of the unchanged ace, where the ace is still = 11
			int unchangedAce = -1;
			
			// Tally up the values in the hand
			for (int i = 0; i < tmpArray1.size(); ++i) {
				if (tmpArray1.get(i) == 1) {
					if (total + 11 <= 21) {
						total += 11;
						unchangedAce = i;
					}
					else if (total + 1 <= 21){
						total += 1;
					}
					else if (total + 1 > 21) {
						if (unchangedAce != -1) {
							total += 1;
							total -= 10;
							unchangedAce = -1;
						}
						else {
							total += 1;
						}
					}
				}
				else {
					if (total + tmpArray1.get(i) > 21 && unchangedAce != -1) {
						total += tmpArray1.get(i);
						total -= 10;
					}
					else {
						total += tmpArray1.get(i);
					}
				}
			}
			
			if (total < 21) {
				return "not bust";
			}
			else if (total == 21) {
				return "blackjack";
			}
			else {
				return "bust";
			}
		}
		
		
		
		
		// Countdown timer set to a default of 10000L which is 10 seconds. This is the amount of time a player has to make a decision.
		// If a currentAction = -1 and the timer runs out, then do nothing.
		// This timer should start when a player clicks on deal, hit, and double down. If timer runs out after a player has clicked these buttons
		// set player's currentAction = 3 and send the server an updated player object with the room number in the status field.
		public void tenSecTilReadyToStart2() {
		    TimerTask task = new TimerTask() {
		        public void run() {
		        	server.getRooms().get(roomNumber).setReadyToStart(2);
		        }
		    };
		    Timer timer = new Timer("Timer");
		    
		    long delay = 10000L;
		    timer.schedule(task, delay);
		}
		
		
		public void dealerFunc() {
			if (tally(0, 0, 0) >= 17) {
				return;
			} 
			else {
				server.getRooms().get(0).getPlayersInRoom()[0].acceptCard(0, server.getRooms().get(0).getShoe().dealCard());
				server.getRooms().get(0).getPlayersInRoom()[0].setScore(bust(0, 0, 0));
			} 
			
		}
		
		@Override
		public void run() {
			// For keeping track of player's score
			String score = "";
			//System.out.println("Entering GameHandler Loop. Initial readyToStart = " + server.getRooms().get(roomNumber).getReadyToStart());
			
			while(true) {
				
				switch (server.getRooms().get(roomNumber).getReadyToStart()) {
					case 0:
						
						// If room is not ready to start, check to see if any player's currentAction != -1.
						// If a player's currentAction != -1, then that means the game should start, therefore, change the
						// the room's state = 1.
						for (int y = 0; y < server.getRooms().get(roomNumber).getPlayersInRoom().length; ++y) {
							if (server.getRooms().get(roomNumber).getPlayersInRoom()[y] != null) {
								if (server.getRooms().get(roomNumber).getPlayersInRoom()[y].getCurrentAction() != -1) {
									System.out.println("In GameHandler. readyToStart = 0 detected. Some player's currenAction != -1.\n"
											+ "Setting readyToStart = 1");
									server.getRooms().get(roomNumber).setReadyToStart(1);

									
								}
							}	
						}
						
						break;
					// If readyToStart = 1 then a player has made some action. In this state, if there are more than 1 player in the room a countdown timer
					// for 10 seconds will begin if a player has not placed a bet, the player will not participate in this hand and their status will be
					// set to 2 and currentAction set to -1. Cards dealt are not displayed in this state until readyToStart = 2.
					case 1:
						// If there's only 1 player in the room, proceed immediately to readyToStart = 2
						if (server.getRooms().get(roomNumber).getNumOfPlayers() == 2) {
							// Set room's readyToStart = 2
							server.getRooms().get(roomNumber).setReadyToStart(2);
							
							// currentAction = 0 means player has clicked deal and wants to receive first two cards
							server.getRooms().get(roomNumber).getPlayersInRoom()[0].setCurrentAction(currentActions.DEAL);
							
							
							System.out.println("In GameHandler. readyToStart = 1 detected. There is only 1 player in room.\n"
									+ "Setting readyToStart = 2");
							
							break;
						}
						else {
							// currentAction = 0 means player has clicked deal and wants to receive first two cards
							server.getRooms().get(roomNumber).getPlayersInRoom()[0].setCurrentAction(currentActions.DEAL);
							
							// Start countdown timer for 10 seconds, when timer is up the function will automatically
							// set readyToStart = 2;
							tenSecTilReadyToStart2();
							
							// while timer is counting down, check for the following conditions
							// If everybody has placed their bets, where each player's state = 3, and their currentAction = 0
							// then set readyToStart = 2, then break out of the loop. Otherwise, wait until timer counts down to 0.
							Boolean everyoneIsReady = true;
							
							while (server.getRooms().get(roomNumber).getReadyToStart() == 1) {
								everyoneIsReady = true;
								int numberOfPlayersInRoom = server.getRooms().get(roomNumber).getNumOfPlayers();
								for (int x = 0; x < server.getRooms().get(roomNumber).getPlayersInRoom().length; ++x) {
									// This is to check if someone else has joined or left the room while performing the check
									// If so, then restart the check.
									if (numberOfPlayersInRoom != server.getRooms().get(roomNumber).getNumOfPlayers()) {
										break;
									}
									
									// Skip player 0 because player 0 is always the dealer
									if (x != 0) {
										if (server.getRooms().get(roomNumber).getPlayersInRoom()[x] != null) {
											if (server.getRooms().get(roomNumber).getPlayersInRoom()[x].getCurrentAction() != 0) {
												everyoneIsReady = false;
												break;
											}
										}
									}
								}
								
								if (everyoneIsReady) {
									break;
								}
							}
							
							// Set room's readyToStart = 2
							server.getRooms().get(roomNumber).setReadyToStart(2);
							
							
							
							break;
								
						}
						
					// If game is in readyToStart = 2, we wait for client actions and update them accordingly.
					case 2:	
						//System.out.println("In GameHandler. readyToStart = 2 detected");
						// If game is in readyToStart = 2, that means a player has chosen some action. If so, then
						
						if (server.getRooms().get(roomNumber).getPlayersInRoom()[0].getCurrentAction() == -1) {
							//dealerFunc();
						}
						else if (server.getRooms().get(roomNumber).getPlayersInRoom()[0].getCurrentAction() == 0) {
							// dealer receives first two cards, then set readyToStart = 2
							server.getRooms().get(roomNumber).getPlayersInRoom()[0].acceptCard(0, server.getRooms().get(roomNumber).getShoe().dealCard());
							server.getRooms().get(roomNumber).getPlayersInRoom()[0].acceptCard(0, server.getRooms().get(roomNumber).getShoe().dealCard());
							
							// Set dealer's currentAction = -1
							server.getRooms().get(roomNumber).getPlayersInRoom()[0].setCurrentAction(currentActions.NO_DECISION);
						}
					
						
						// Loop through each player in the room
						for (int y = 0; y < server.getRooms().get(roomNumber).getPlayersInRoom().length; ++y) {
							if (server.getRooms().get(roomNumber).getPlayersInRoom()[y] != null) {
								// Perform the following actions for all players except the dealer
								if (y != 0) {
									
									// For each player, check the playerState
									switch (server.getRooms().get(roomNumber).getPlayersInRoom()[y].getPlayerState()) {
									
										// playerState = 3 means server will see what player's currentAction to determine what the dealer and game logic should do. After the action has been updated
										// by the server, set State = 2 so that server will not perform the same action again.
										case 3:
											switch(server.getRooms().get(roomNumber).getPlayersInRoom()[y].getCurrentAction()) {
												// currentAction = 0 means player has clicked deal and wants to receive first two cards
												case 0:
													System.out.println("Player deal detected\n");
													// Deal two cards to player
													// Deal first card to player
													server.getRooms().get(roomNumber).getPlayersInRoom()[y].acceptCard(0, server.getRooms().get(roomNumber).getShoe().dealCard());
													// Deal second card to player
													server.getRooms().get(roomNumber).getPlayersInRoom()[y].acceptCard(0, server.getRooms().get(roomNumber).getShoe().dealCard());
													
													System.out.println("In GameHandler. readyToStart = " + server.getRooms().get(roomNumber).getReadyToStart() + ".");
													//System.out.println("Player after being dealt 2 cards = " + server.getRooms().get(roomNumber).getPlayersInRoom()[y].toString());
													// Check if first two cards results in blackjack
													// Loop through player's currentHand, since it's a 2D array, it might have multiple hands.
													for (int i = 0; i < server.getRooms().get(roomNumber).getPlayersInRoom()[y].getCurrentHand().size(); ++i) {
														// Get player's current score
														score = bust(roomNumber, y, i);
														
														// Set player's current score
														server.getRooms().get(roomNumber).getPlayersInRoom()[y].setScore(score);
														
														// If player scores blackjack on deal, add player's wager to accountBalance.
														if (score.equals("blackjack")) {
															// Set player's score to blackjack
															server.getRooms().get(roomNumber).getPlayersInRoom()[y].setScore("blackjack");
															
															// Set playerState = 4 until the round is over, that way no further checking of this player will occur
															// until player gives a new command
															server.getRooms().get(roomNumber).getPlayersInRoom()[y].setPlayerState(playerStates.DONE);
															
															// Set currentAction = -1 means player has not decided on anything
															server.getRooms().get(roomNumber).getPlayersInRoom()[y].setCurrentAction(currentActions.NO_DECISION);
														}
														else if (score.equals("not bust")){
															// Set player's score to not bust
															server.getRooms().get(roomNumber).getPlayersInRoom()[y].setScore("not bust");
															
															// set playerState = 4 until the round is over
															server.getRooms().get(roomNumber).getPlayersInRoom()[y].setPlayerState(playerStates.DONE);
															
															// Set currentAction = -1 means player has not decided on anything
															server.getRooms().get(roomNumber).getPlayersInRoom()[y].setCurrentAction(currentActions.NO_DECISION);
														}
														
														score = "";
													}
													
													
													
													break;
												
												// currentAction = 1 means player wants a hit, so players receives another card
												case 1:
													System.out.println("Player hit detected\n");
													// Deal one card to player
													server.getRooms().get(roomNumber).getPlayersInRoom()[y].acceptCard(0, server.getRooms().get(roomNumber).getShoe().dealCard());
												
													// Check score for each hand
													for (int i = 0; i < server.getRooms().get(roomNumber).getPlayersInRoom()[y].getCurrentHand().size(); ++i) {
														// Get player's current score
														score = bust(roomNumber, y, i);
														
														// Set player's current score
														server.getRooms().get(roomNumber).getPlayersInRoom()[y].setScore(score);
														
														// If player scores blackjack on deal, add player's wager to accountBalance.
														if (score.equals("blackjack")) {
															// set playerState = 4 until the round is over
															server.getRooms().get(roomNumber).getPlayersInRoom()[y].setPlayerState(playerStates.DONE);
															
															// Set currentAction = -1 means player has not decided on anything
															server.getRooms().get(roomNumber).getPlayersInRoom()[y].setCurrentAction(currentActions.STAND);
														}
														else if (score.equals("bust")){
															// set playerState = 4 until the round is over
															server.getRooms().get(roomNumber).getPlayersInRoom()[y].setPlayerState(playerStates.DONE);
															
															// Set currentAction = -1 means player has not decided on anything
															server.getRooms().get(roomNumber).getPlayersInRoom()[y].setCurrentAction(currentActions.STAND);
														}
														else {
															// Set playerState = 4 until the round is over, that way no further checking of this player will occur
															// until player gives a new command
															server.getRooms().get(roomNumber).getPlayersInRoom()[y].setPlayerState(playerStates.DONE);
															
															// Set currentAction = -1 means player has not decided on anything
															server.getRooms().get(roomNumber).getPlayersInRoom()[y].setCurrentAction(currentActions.NO_DECISION);
														}
																
														score = "";
													}
													
													
												
													
													break;
													
												// currentAction = 2 means player wants to double down on the bet, so double the wager amount and receive one more card
												case 2:
													System.out.println("Player double down detected\n");
													// Deal one card to player
													server.getRooms().get(roomNumber).getPlayersInRoom()[y].acceptCard(0, server.getRooms().get(roomNumber).getShoe().dealCard());
												
													// Double the player's wager
													server.getRooms().get(roomNumber).getPlayersInRoom()[y].setWager(server.getRooms().get(roomNumber).getPlayersInRoom()[y].getWager() * 2);
													
													
													// Check score for each hand
													for (int i = 0; i < server.getRooms().get(roomNumber).getPlayersInRoom()[y].getCurrentHand().size(); ++i) {
														// Get player's current score
														score = bust(roomNumber, y, i);
														
														// Set player's current score
														server.getRooms().get(roomNumber).getPlayersInRoom()[y].setScore(score);
														
														// If player scores blackjack on deal, add player's wager to accountBalance.
														if (score.equals("blackjack")) {
															// set playerState = 4 until the round is over
															server.getRooms().get(roomNumber).getPlayersInRoom()[y].setPlayerState(playerStates.DONE);
															
															// Set currentAction = 3 means player has not decided on anything
															server.getRooms().get(roomNumber).getPlayersInRoom()[y].setCurrentAction(currentActions.STAND);
														}
														else if (score.equals("bust")){
															// set playerState = 4 until the round is over
															server.getRooms().get(roomNumber).getPlayersInRoom()[y].setPlayerState(playerStates.DONE);
															
															// Set currentAction = 3 means player has not decided on anything
															server.getRooms().get(roomNumber).getPlayersInRoom()[y].setCurrentAction(currentActions.STAND);
														}
														else {
															// Set playerState = 4 until the round is over, that way no further checking of this player will occur
															// until player gives a new command
															server.getRooms().get(roomNumber).getPlayersInRoom()[y].setPlayerState(playerStates.DONE);
															
															// Set currentAction = -1 means player has not decided on anything
															server.getRooms().get(roomNumber).getPlayersInRoom()[y].setCurrentAction(currentActions.NO_DECISION);
														}
																
														score = "";
													}
														
													// Set newMessage = true so the server will update the client with a new instance
													// of server attributes rooms and lobbyRooms
													server.setNewMessage(true);
													
													break;
													
												// currentAction = 3 means player wants to stand, meaning player is satisfied with the cards server should tally the score.
												case 3:
													System.out.println("Player stand detected\n");
													// Set playerState = 4 until the round is over, that way no further checking of this player will occur
													// until player gives a new command
													server.getRooms().get(roomNumber).getPlayersInRoom()[y].setPlayerState(playerStates.DONE);
													
													// Set currentAction = 3 means player has not decided on anything
													server.getRooms().get(roomNumber).getPlayersInRoom()[y].setCurrentAction(currentActions.STAND);
															
													
													break;
												
												// currentAction = 4 means player wants to sit out of this current round. This is the default action when a player is created.
												case 4:
													// Set playerState = 4 until the round is over, that way no further checking of this player will occur
													// until player gives a new command
													server.getRooms().get(roomNumber).getPlayersInRoom()[y].setPlayerState(playerStates.DONE);
													
													// Set currentAction = 4 means player has not decided on anything
													server.getRooms().get(roomNumber).getPlayersInRoom()[y].setCurrentAction(currentActions.SIT_OUT);
															
													
													
												default:
													break;
											}
										default:
											break;
									}
								}
							}
						}
						
						// Set Dealer's score
						server.getRooms().get(roomNumber).getPlayersInRoom()[0].setScore(bust(roomNumber, 0, 0));
						
						// Check if Dealer has lost. If the dealer has lost, anyone who's score = not bust has won their wager
						if (server.getRooms().get(roomNumber).getPlayersInRoom()[0].getScore().equals("bust")) {
							System.out.println("Dealer has lost detected");
							
							for (int x = 0; x < server.getRooms().get(roomNumber).getPlayersInRoom().length; ++x) {
								if (server.getRooms().get(roomNumber).getPlayersInRoom()[x] != null) {
									if (server.getRooms().get(roomNumber).getPlayersInRoom()[x].getScore().equals("not bust")) {
										// Skip dealer
										if (x != 0) {
											// Add player's current wager to accountBalance
											server.getRooms().get(roomNumber).getPlayersInRoom()[x].setAccountBalance(server.getRooms().get(roomNumber).getPlayersInRoom()[x].getAccountBalance() + server.getRooms().get(roomNumber).getPlayersInRoom()[x].getWager());
										}
									}
									
									// Set playerState = 4 until the round is over, that way no further checking of this player will occur
									// until player gives a new command
									server.getRooms().get(roomNumber).getPlayersInRoom()[x].setPlayerState(playerStates.PLAYING);
									
									// Set currentAction = -1 means player has not decided on anything
									server.getRooms().get(roomNumber).getPlayersInRoom()[x].setCurrentAction(currentActions.NO_DECISION);
								}
								
							}
							
							// Reset game by setting readyToStart = 0
							server.getRooms().get(roomNumber).setReadyToStart(0);
							
							// Reshuffle deck
							server.getRooms().get(roomNumber).getShoe().generateCards();
							
						}
						
						Boolean endRound = true;

						// Go through all the players in the room and check if they are still in or not
						// If there are still players not standing or
						for (int x = 0; x < server.getRooms().get(roomNumber).getPlayersInRoom().length; ++x) {
							if (x != 0) {
								if (server.getRooms().get(roomNumber).getPlayersInRoom()[x] != null) {
									if (server.getRooms().get(roomNumber).getPlayersInRoom()[x].getCurrentAction() != 3 || server.getRooms().get(roomNumber).getPlayersInRoom()[x].getCurrentAction() != 4) {
										endRound = false;
									}
								}
							}
						}

						// Go through all the players in the room and adjust their balance according to their bets and score
						if(endRound) {
							for (int x = 0; x < server.getRooms().get(roomNumber).getPlayersInRoom().length; ++x) {
								if (server.getRooms().get(roomNumber).getPlayersInRoom()[x] != null) {
									if (server.getRooms().get(roomNumber).getPlayersInRoom()[x].getScore().equals("blackjack")) {
										if (x != 0) {
											// Add player's current wager to accountBalance
											server.getRooms().get(roomNumber).getPlayersInRoom()[x].setAccountBalance(server.getRooms().get(roomNumber).getPlayersInRoom()[x].getAccountBalance() + server.getRooms().get(roomNumber).getPlayersInRoom()[x].getWager());
										}
									}
									else if (server.getRooms().get(roomNumber).getPlayersInRoom()[x].getScore().equals("bust")) {
										if ( x != 0) {
											// Subtract player's current wager from accountBalance
											server.getRooms().get(roomNumber).getPlayersInRoom()[x].setAccountBalance(server.getRooms().get(roomNumber).getPlayersInRoom()[x].getAccountBalance() - server.getRooms().get(roomNumber).getPlayersInRoom()[x].getWager());
										}
									}
									else {
										int dealerTally = tally(roomNumber, 0, 0);
										if (x != 0) {
											int playerTally = tally(roomNumber, x, 0);
											
											if(playerTally < dealerTally) {
												// Subtract player's current wager from accountBalance
												server.getRooms().get(roomNumber).getPlayersInRoom()[x].setAccountBalance(server.getRooms().get(roomNumber).getPlayersInRoom()[x].getAccountBalance() - server.getRooms().get(roomNumber).getPlayersInRoom()[x].getWager());
											}
											else if (playerTally == dealerTally) {
												server.getRooms().get(roomNumber).getPlayersInRoom()[x].setWager(0);
											}
											else if (playerTally > dealerTally) {
												// Add player's current wager to accountBalance
												server.getRooms().get(roomNumber).getPlayersInRoom()[x].setAccountBalance(server.getRooms().get(roomNumber).getPlayersInRoom()[x].getAccountBalance() + server.getRooms().get(roomNumber).getPlayersInRoom()[x].getWager());
											}
										}
										
									}
									
									server.getRooms().get(roomNumber).getPlayersInRoom()[x].setCurrentAction(currentActions.NO_DECISION);
									server.getRooms().get(roomNumber).getPlayersInRoom()[x].setPlayerState(playerStates.SITTING);
								}
							}
						}
						
						
						
						//System.out.println("readyToStart = " + server.getRooms().get(roomNumber).getReadyToStart());
						
						// Set newMessage = true so the server will update the client with a new instance
						// of server attributes rooms and lobbyRooms
						server.setNewMessage(true);
						
				}
			}
		}
	}
	
	

	// Each thread use this class and execute the method run()
	private static class ClientHandler implements Runnable {
		private final Socket clientSocket;
		private ObjectInputStream input;
		private ObjectOutputStream output;
		private Server server; // use this attribute to update the server's attributes ArrayList<Room> rooms and LobbyRoom lobbyRooms.
		private Player player; // use this to keep track of what this player is doing.
		
		public ClientHandler(Socket socket, Server server) {
			this.clientSocket = socket;
			this.server = server;
		}
		
		
		@Override
		public void run() {		
			try {
				output = new ObjectOutputStream(clientSocket.getOutputStream());
				input = new ObjectInputStream(clientSocket.getInputStream());

				boolean proceedToLobby = false; // Used in conjunction with loginCount to continue the login/register loop
				int loginCount = 0; // Count the number of login attempts. Close connection to client if it exceeds 3 attempts 
				boolean logout = false; // Close connection if this value is true. If player sends a logout message, set this to true
				
				
				// The login/register loop. Continue to loop unless login attempt is greater than 3 or proceedToLobby is set to true
				while(!proceedToLobby) {
					Message message = null;
					message = getMessage(message);
					
					switch (message.getType()) {
						case "login":
							System.out.println("Received login message.");
							++loginCount;
							proceedToLobby = login(message);
							// If there are 3 failed login attempts close the connection and end the thread.
							if (loginCount >= 3) {
								closeConnection();
								System.out.println("Too many failed login attempts. Closing connection.");
								return;
							}
							
							break;
						
						case "register":
							register(message);
							break;
					}
				}
				
				
				// Loop for when a player's in the lobby.
				while(!logout) {
					System.out.println("Inside game lobby loop\n");
					
					Message message = null;
					message = getMessage(message);
					
					switch (message.getType()) {
						
						 // This is the case where a player is in the lobby and clicks join room. The player will send a message
						 // of new Message("join room", "roomNumber", ""). The server will send back a room class with the player
						 // in the ArrayList<Player> playersInRoom. Client can make a copy of their player object from the room
						 // into their client side player object. Also, use the all the users on the playersInRoom list to populate
						 // the GUI with cards and what not 
						case "join room":
							System.out.println("Received join room message\n");
							int roomNumber = Integer.parseInt(message.getStatus());
				
							// Assigning player to the room number provided
							player.setRoomNumber(roomNumber);
							
							// Set playerState = 1 means player is in a room.
							player.setPlayerState(playerStates.SPECTATOR);
							
							// currentAction = -1 means player has not decided on anything
							player.setCurrentAction(currentActions.NO_DECISION);
							
							// Setting new message received by client to true
							server.setNewMessage(true);
							
							// Since newMessage was just set to true, send the class objects to the client.
							checkNewMessage();
							
							// Execute inGameRoom() function
							inGameRoom();
							
							break;
							
						case "logout":
							message = new Message("logout", "success", "");
							updatePlayer();
							logout = true;
							
							
							
						default:
							break;
					}
					
					// Setting new message received by client to true
					server.setNewMessage(true);
					checkNewMessage();
				}
				
				System.out.println("Thread closing.");
				closeConnection();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					Thread.sleep(2);
					input.close();
					output.close();
					clientSocket.close();
				}
				catch(Exception e) {
					e.printStackTrace();
				}	
			}
		}
		
		
		
		public void inGameRoom() {
			System.out.println("Inside game room loop\n");
			
			// Get the player's room number
			int roomNumber = player.getRoomNumber();
			
			// Keep looping while player state = 1, meaning player is in a game room
			while(player.getPlayerState() == 1) {
				Message message = null;
				
				System.out.println("Inside while loop of game room\n");
				int index;
				// wait for player to send a message
				message = getMessage(message);
				
				switch (message.getType()) {
					// Player sits down to start playing game
					case "sit":
						index = Integer.parseInt(message.getStatus());
										
						// Player has sat down but not actively playing
						player.setPlayerState(playerStates.SITTING);
						
						// currentAction = -1 means player has not decided on anything
						player.setCurrentAction(currentActions.NO_DECISION);
						
						// Set player's seat index to index
						player.setSeatIndex(index);
						
						// Adding player's name to the server's attribute LobbyRoom lobbyRoom
						server.lobbyRooms.addPlayer(roomNumber, player.getUsername());
						
						
						
						// Adding player to the server's attribute ArrayList<Room> rooms's playersInRoom;
						server.getRooms().get(roomNumber).addPlayer(index, player);
						
						System.out.println("After receving sit command. Player requested seat index = " + index +
								"\nRoom state now = " + server.getRooms().get(roomNumber).toString());
						
						// Setting new message received by client to true
						server.setNewMessage(true);
						
						// Since newMessage was just set to true, send the class objects to the client.
						checkNewMessage();
						
						// Start playing the game
						playGame();
				
						break;
						
					// Player chooses to leave the room
					case "leave room":
						
						// Set playerState = 0 means player is in the lobby.
						player.setPlayerState(playerStates.IN_LOBBY);
						
						// currentAction = -1 means player has not decided on anything
						player.setCurrentAction(currentActions.NO_DECISION);
						
						
						
						// Return to calling function, the lobby loop.
						return;
						
					default:
				}
				
				// Setting new message received by client to true
				server.setNewMessage(true);
				checkNewMessage();
			}
		}
		
		private void playGame() {
			
			int roomNumber = player.getRoomNumber();
			
			// keep looping while player is sitting at the table
			while (player.getPlayerState() >= 2) {
				System.out.println("Inside playGame() loop");
				Message message = null;
				message = getMessage(message);
				
				switch (message.getType()) {
					// Player chooses to participate in the room by clicking deal
					case "deal":
						System.out.println("Received deal message");
						System.out.println("Player's room number from deal message = " + player.getRoomNumber());
						// Get player's wager amount and deduct it from the player balance
						player.setWager(Integer.parseInt(message.getStatus()));
							
						// playerState = 3 means server will see what player's currentAction to determine what the dealer should do. After the action has been updated
						// by the server, set State = 2 so that server will not perform the same action again.
						player.setPlayerState(playerStates.PLAYING);
						
						
						// Set player action to deal which player receives first two cards
						player.setCurrentAction(currentActions.DEAL);
						
						// Set room's readyToStart = 1
						server.getRooms().get(player.getRoomNumber()).setReadyToStart(1);
						
					

						
						
						break;
						
					case "hit":
						if (server.getRooms().get(roomNumber).getReadyToStart() == 2) {
							// playerState = 3 means server will see what player's currentAction to determine what the dealer should do. After the action has been updated
							// by the server, set State = 2 so that server will not perform the same action again.
							player.setPlayerState(playerStates.PLAYING);
							
							// Set player action to hit which player receives an additional card
							player.setCurrentAction(currentActions.HIT);
						
						}
						
						
						break;
						
					// Player sits down to start playing game
					case "sit out":
						// playerState = 2 means player has chosen a seat and sat down. 
						player.setPlayerState(playerStates.SITTING);
						
						// currentAction = -1 means player has not decided on anything
						player.setCurrentAction(currentActions.SIT_OUT);
						
						
				
						break;
						
					// Player chooses to leave the room
					case "leave room":
						// Player wants to leave the game room
						player.setPlayerState(playerStates.IN_LOBBY);
						
						// Set currentAction = -1 means player has not decided on anything
						player.setCurrentAction(currentActions.NO_DECISION);
						
						// Set roomNumber back to default value of -1
						player.setRoomNumber(-1);
						
						// Reset player's index number back to -1
						player.setSeatIndex(-1);
						
						// Removing player to the server's attribute ArrayList<Room> rooms
						server.getRooms().get(roomNumber).removePlayer(player.getSeatIndex());
						
						// Removing player's name to the server's attribute LobbyRoom lobbyRoom
						server.lobbyRooms.removePlayer(roomNumber, player.getUsername());
						
						
						// update player account balance in the database
						updatePlayer();
						
						
						
						
						// Return to the calling function
						return;
						
					// Player wants to double the bet
					case "double down":
						if (server.getRooms().get(roomNumber).getReadyToStart() == 2) {
							// playerState = 3 means server will see what player's currentAction to determine what the dealer should do. After the action has been updated
							// by the server, set State = 2 so that server will not perform the same action again.
							player.setPlayerState(playerStates.PLAYING);
							
							// Set currentAction = 2 means player wants to double down on the bet, so double the wager amount and receive one more card
							player.setCurrentAction(currentActions.DOUBLE);
							

						}
						
						break;
					
					// Player sits down to start playing game
					case "stand":
						// playerState = 3 means server will see what player's currentAction to determine what the dealer and game logic should do. After the action has been updated
						player.setPlayerState(playerStates.PLAYING);
						
						// currentAction = 3 means player wants to stand, meaning player is satisfied with the cards server should tally the score.
						player.setCurrentAction(currentActions.STAND);
						
						
				
						break;
						
					default:
						break;
						
				}
				
				// Setting new message received by client to true
				server.setNewMessage(true);
				
				// Send player the updated objects
				checkNewMessage();
			}
		}
		
		
		// Closes connection from client
		public void closeConnection() {
			try {
				input.close();
				output.close();
				clientSocket.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}	
		}
		
		
		// Receive a message to the client
		public Message getMessage(Message message) {
			try {
				while (message == null) {
					message = (Message) input.readObject();
					
					try {
						Thread.sleep(100);
					}
					catch (Exception e) {
						
					}
				}

				return message;
			}
			catch (Exception e) {
				// removed e.printStackTrace(); so that it won't print annoying messages when it expects to receive a message
				// but there isn't one in the pipe.
			}
			
			return message;
		}
		
		// Sends a message to the client
		public void sendMessage(Message message) {
			try {
				output.reset();
				output.writeObject(message);
				output.flush();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		// Checks if server's attribute newMessage. If it's set to true, it will send to the client an instance of
		// the server's lobbyRoom and rooms object.
		public void checkNewMessage() {
			if (server.getNewMessage()) {
				Message message = new Message("lobby room", "", server.getLobbyRooms().toString());
				sendMessage(message);
				
				System.out.println("Sending out LobbyRoom object = " + server.getLobbyRooms().toString());
				
				message = new Message("room", Integer.toString(player.getSeatIndex()), server.getRooms().get(player.getRoomNumber()).toString());
				sendMessage(message);
				
				System.out.println("Sending out Room object");
				//System.out.println(" = " + server.getRooms().toString());
				
				message = new Message("player", "", player.toString());
				sendMessage(message);
				
				System.out.println("Sending out Player object = " + player.toString());
				server.setNewMessage(false);
			}
		}
		
		// Login function
		public boolean login(Message message) {
			System.out.println("login method Running");
			boolean login = false;
			
			System.out.println("Received login message from client " + clientSocket.getInetAddress().getHostAddress());
			login = verifyLogin(message.getText());
			
			if(login) {
				System.out.println("Client " + clientSocket.getInetAddress().getHostAddress() + " login successful\nSending successful login reply back to client " + clientSocket.getInetAddress().getHostAddress());
				message = new Message("login", "success", "");
				sendMessage(message);
			}
			else {
				System.out.println("Client " + clientSocket.getInetAddress().getHostAddress() + " login failed\nSending failed login reply back to client " + clientSocket.getInetAddress().getHostAddress());
				message = new Message("login", "failed", "");
				sendMessage(message);
			}
			
			return login;
		}
		
		// Helper function for the login function to see if username and password matches
		// any line from the database.txt
		public boolean verifyLogin(String loginString) {
			System.out.println("verifyLogin Running");
			String fileInput = null;
			String [] loginInfo;
			String username = null;
			String password = null;
			File file = new File(System.getProperty("user.dir") + "/src/database.txt");
			System.out.println("loginString = " +  loginString);

			try {
				BufferedReader myReader = new BufferedReader(new FileReader(file));
				
				// tokenize loginString
				loginInfo = loginString.split("#");
				username = loginInfo[0];
				password = loginInfo[1];

				// read first line from database.txt	
				fileInput = myReader.readLine();
				
				while (fileInput != null) {	
					// tokenize line from database.txt
					loginInfo = fileInput.split("#");
					String tmpUsername = loginInfo[0];
					String tmpPassword = loginInfo[1];
					String tmpBalance = loginInfo[2];
					
					// check if username and password match any line from database.txt
					if (tmpUsername.equals(username) && tmpPassword.equals(password)) {
						// if it's a match, create a new player class
						this.player = new Player(tmpUsername, Integer.parseInt(tmpBalance));
						sendMessage(new Message("player", "", this.player.toString()));
						myReader.close();
						return true;
					}
									
					fileInput = myReader.readLine();
				}
				myReader.close();
				
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			return false;
		}

		// Register function
		public void register(Message message) {
			boolean userInDatabase;
			
			System.out.println("Received register message from client " + clientSocket.getInetAddress().getHostAddress());
			userInDatabase = verifyRegister(message.getText());
			
			if (userInDatabase) {
				System.out.println("Client " + clientSocket.getInetAddress().getHostAddress() + " attempted to register an already existing user.\nSending failed register reply back to client " + clientSocket.getInetAddress().getHostAddress());
				message = new Message("register", "failed", "Player already exists. Please try again.");
				sendMessage(message);
			}
			else {
				System.out.println("Client registered a new user. Sending successful register reply back to client " + clientSocket.getInetAddress().getHostAddress());
				message = new Message("register", "success", "New player registered");
				sendMessage(message);
			}
		}
		
		// Helper function for the register function to check if user already exists in database.txt
		public boolean verifyRegister(String registerString) {
			String fileInput = null;
			String [] loginInfo;
			String username = null;
			String password = null;
			String output = null;
			File file = new File(System.getProperty("user.dir") + "/src/database.txt");
			
			try {
				BufferedReader myReader = new BufferedReader(new FileReader(file));
				// tokenize loginString
				loginInfo = registerString.split("#");
				username = loginInfo[0];
				password = loginInfo[1];

				// read first line from database.txt	
				fileInput = myReader.readLine();
				
				while (fileInput != null) {	
					// tokenize line from database.txt
					loginInfo = fileInput.split("#");
					String tmpUsername = loginInfo[0];
					String tmpPassword = loginInfo[1];
					
					// Check if username matches any line from database.txt.
					// If there is a match, exit function and return true
					if (tmpUsername.equals(username)) {
						myReader.close();
						return true;
					}
									
					fileInput = myReader.readLine();
				}
				
				// If there's no match then create the new user
				FileWriter myWriter = new FileWriter(file, true);
				
				// build player info string with default $50k
				output = username + "#" + password + "#" + "50000\n";
				
				// append new user to database.txt
				System.out.println("Writing to file: " + output);
				myWriter.write(output);
				
				myReader.close();	
				myWriter.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			
			return false;
		}
		
		// Updates player balance in database.txt before loging off
		public void updatePlayer() {
			try {
				// Open database file
				File file = new File(System.getProperty("user.dir") + "/database.txt");
				
				// Reader for database file
				BufferedReader myReader = new BufferedReader(new FileReader(file));
				
				// String to store the database
				ArrayList<String> database = new ArrayList<String>();

				// read first line from database.txt	
				String fileInput = myReader.readLine();
				
				// To store database changes output
				String output = "";
				
				// To store tokenized strings
				String [] tmp;
				
				player.setAccountBalance(-111);
				
				while (fileInput != null) {
					tmp = fileInput.split("#");
					
					if (tmp[0].equals(player.getUsername())) {
						tmp[2] = Integer.toString(player.getAccountBalance());
						fileInput = tmp[0] + "#" + tmp[1] + "#" + tmp[2];
					}
					
					database.add(fileInput);
					System.out.println(fileInput);
					fileInput = myReader.readLine();
				}
				
				// If there's no match then create the new user
				FileWriter myWriter = new FileWriter(file);
				
				for (int x = 0; x < database.size(); ++x) {
					output += database.get(x);
					
					if (x != database.size() - 1) {
						output += "\n";
					}
				}
				
				// Rewrite database
				System.out.println("Writing to file: " + output);
				myWriter.write(output);
				
				myReader.close();	
				myWriter.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	public ArrayList<Room> getRooms() {
		return this.rooms;
	}
	
	public LobbyRoom getLobbyRooms() {
		return this.lobbyRooms;
	}
	
	public Boolean getNewMessage() {
		return this.newMessage;
	}
	
	public void setNewMessage(Boolean newMessage) {
		this.newMessage = newMessage;
	}
}