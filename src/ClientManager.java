import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class ClientManager implements Runnable {
	// akouei ta minimata apo olous tous client kai ta kanei broadcast stous upoloipous client
	// ta println emfanizoun stin consola tou Server
	/////
	private Socket socket;
	private BufferedReader reader;
	private BufferedWriter writer;
	private String username;
	//arraylist pou apothikeuei tous xristes kai getters setters tis-------
	private ArrayList<Profile> users = new ArrayList<>();

	public ArrayList<Profile> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<Profile> users) {
		this.users = users;
	}

	//------------------------------------------------------
	public static HashMap<String, ClientManager> clientManagers = new HashMap<>();

	private static String[] chatLogArray = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "}; //kenoArray poy tha apothikeuontai ta 10 teleutaia Group minimata;

	/////// -->kwdikas upo epeksergasia

	// Methodoi gia offline minimata
	public static ArrayList<Message> messageslist=new ArrayList<>();
	//methodos pou stelnei ston client ta minimata pou tou stalthikan (typou inbox)
	public static ArrayList<Message> getClientsMessageslist(String clientUsername) {
		ArrayList<Message> clientsMsg=new ArrayList<>();
		for (Message message : messageslist) {
			if (clientUsername == message.getRecipientID()) {
				clientsMsg.add(message);

			}
		}
		return clientsMsg;
	}
	//se periptwsi pou theloume na emfanizontai ta minimata stin consola tou server
	public static void showMessages(ArrayList<Message> messages){
		for(int i=0;i<messages.size();i++){
			System.out.println();
			System.out.println(messages.get(i).getTime()+" "+messages.get(i).getUsername()+": "+messages.get(i).getContext());
		}
	}


	////////

	//Client Manager Constructor
	public ClientManager(Socket socket)  {
		try {
			this.socket = socket;
			this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.username = reader.readLine(); //gia na ektelestei, prepei na graftei mia entoli me bufferedWriter+/n+flush
			clientManagers.put(username,this);
			System.out.println(username + " has connected to the server!"); //deixnei stin consola tou server
			post("Server: "+username+" just entered the chat!" );
		} catch (IOException e) {
			closeClientConnection(socket, reader, writer);
		}
	}
	
	@Override
	public void run() {
		String messagesFromClient;
		
		while(!socket.isClosed()) {
			try {
				messagesFromClient = reader.readLine();
				//System.out.println("ELEGXOS: Class MyClientHandler listened"); //ELEGXW OTI I CLIENTHANDLER AKOYEI TA MINIMATA 
				if (messagesFromClient.startsWith("/")) {
					if (messagesFromClient.equals("/noAction")) {		//???????
					}else if (messagesFromClient.equalsIgnoreCase("/quit")) {
						closeClientConnection(socket, reader, writer);
					} else if (messagesFromClient.equalsIgnoreCase("/help")) {
					} else if (messagesFromClient.startsWith("/priv ")) {
						String[] messageSplit = messagesFromClient.split(" ", 3);
						privateMsg(messageSplit[2], messageSplit[1]);
					} else if (messagesFromClient.startsWith("/online")) {
						writer.write("Online Users:\n");
						for (String connection : clientManagers.keySet()) {
							showOnlineUsers(connection, username);
						}
					} else if (messagesFromClient.equals("/log")) {
						for (int i = 0; i < 10; i++) { //Stelnw ena ena ta minimata gt i broadcastToOne dexetai ena String, oxi pinaka
							privateMsg(i + 1 + ". " + chatLogArray[i], this.username);
						}
					}else if (messagesFromClient.equals("/inbox")) {

					}
				}else {
					post(messagesFromClient);
				}				
			} catch (IOException e) {
				closeClientConnection(socket, reader, writer);
			}
		}
	}

	public void post(String messagesToOthers) {
		if (!messagesToOthers.startsWith("Server:")) { //???????
			updateChatLogArray(messagesToOthers);
		}

		for(ClientManager clientManager : clientManagers.values()) {
			try {
				if(!clientManager.username.equals(username)) {
					clientManager.writer.write(messagesToOthers);
					clientManager.writer.newLine();
					clientManager.writer.flush();
				}
			}catch( IOException e) {
				e.printStackTrace();
			}
		}						
	}

	public void updateChatLogArray(String newMessage) {
		for(int i = 0; i < chatLogArray.length - 1; i++ ) {
			chatLogArray[i] = chatLogArray[i+1];
		}
		chatLogArray[9] = newMessage;
	}
	
	public void privateMsg(String messagesPrivate, String usernamePriv) {
		for(ClientManager clientManager : clientManagers.values()) {
			if(clientManager.username.equals(usernamePriv)) {
				try { 
					clientManager.writer.write("DirectMessage " + this.username + ": " + messagesPrivate);
					clientManager.writer.newLine();
					clientManager.writer.flush();
				} catch (IOException e) {
					closeClientConnection(socket, reader, writer);
				}
			}	
		}					
	}

	public void showOnlineUsers(String onlineUsername, String client) {

		for(ClientManager clientManager : clientManagers.values()) {
			if(clientManager.username.equals(client)) {
				try {
					clientManager.writer.write(onlineUsername);
					clientManager.writer.newLine();
					clientManager.writer.flush();
				} catch (IOException e) {
					closeClientConnection(socket, reader, writer);
				}
			}
		}
	}
	
	public void closeClientConnection(Socket socket, BufferedReader reader, BufferedWriter writer) {
		clientManagers.remove(username,this);
		post("Server: " + username + " has left the chat");
		System.out.println("Server: " + username + " has disconected"); 
		try {
				reader.close();
				writer.close();
				socket.close();
			//System.out.println("CONNECTION CLOSED SUCCESSFULLY(CLASS ClientHandler)"); //ELEGXW OTI I CLIENTHANDLER APOSINDEETAI EPITIXWS
		}catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
}



