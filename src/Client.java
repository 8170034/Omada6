import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
	
	private Socket socket;
	private String username;
	public BufferedWriter writer;
	private BufferedReader reader;


	Client(Socket socket, String username) {
		try {
			this.socket = socket;
			this.username = username;
			this.writer = new BufferedWriter( new OutputStreamWriter(socket.getOutputStream()) ) ;
			this.reader = new BufferedReader( new InputStreamReader(socket.getInputStream()) );
		} catch (IOException e) {
			closeClientConnection(socket, reader, writer);
		}
	}
	
	public void sendMessage() {
		Scanner scanner = new Scanner(System.in);;
		try {
			// ginetai mono stin arxi tou kathe client gia na parei to thread tou clientHandler to username prwtaApoOla kai na ekxwrisei sti metavliti username
			writer.write(username);
			writer.newLine();
			writer.flush();
			System.out.println("Type /help if you need help");
					
			while(!socket.isClosed()) {
				System.out.println("Type in the chat....");
				String messageToSend = scanner.nextLine();
			if(messageToSend.startsWith("/")) {
				if (messageToSend.equalsIgnoreCase("/help")) {
					printHelpGuide();
					writeToSocketStream(messageToSend);
				} else if (messageToSend.equalsIgnoreCase("/quit")) {
					writeToSocketStream(messageToSend);
					closeClientConnection(socket, reader, writer);
					System.out.println("QUITED");
				} else if (messageToSend.startsWith("/priv ")) {
					writeToSocketStream(messageToSend);
				} else if (messageToSend.startsWith("/online")) {
					writeToSocketStream(messageToSend);
				}else if (messageToSend.startsWith("/log")) {
					writeToSocketStream(messageToSend);
				} else if (messageToSend.startsWith("/Sign_Up")) { //elegxos gia an thelei na ftiaksei account
					writeToSocketStream(messageToSend);
				} else {
					System.out.println("This is not a valid command");
					writeToSocketStream("/noAction");
				}
			}else{
				writeToSocketStream(username+ ": " + messageToSend);
			}
			}
		}catch(IOException e) {
			closeClientConnection(socket, reader, writer);
		}
			scanner.close();
	}
	
	public void writeToSocketStream(String messageToSend) {
		try {
			writer.write(messageToSend);
			writer.newLine();
			writer.flush();
		} catch (IOException e) {
			closeClientConnection(socket, reader, writer);
		}
	}




	public void printHelpGuide() {
		System.out.println("/help : Print help guide.");
		System.out.println("/quit : Log out.");
		System.out.println("/log  : Print last 10 msgs from group chat.");
		System.out.println("/like : Like last msg");
		System.out.println("/priv UserName 'msg' :Send a private message ");
		System.out.println("/online: Print all online users.");
		System.out.println("/Sign_Up : Create Account "); //to prosthesa gia na mporei o xristis na dimiourgei account otan paei na sundethei
		System.out.println();
		
	}

	public void closeClientConnection(Socket socket, BufferedReader reader, BufferedWriter writer) {		
		try {
				reader.close();
				writer.close();
				socket.close();
			//System.out.println("CONNECTION CLOSED SUCCESSFULLY(CLASS Client)"); //ELEGXW OTI I CLIENT APOSINDEETAI EPITIXWS
		}catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your username");
		String username = scanner.nextLine();
		Socket socket = new Socket("localHost",8080);
		Client client = new Client(socket, username);
		ClientListener clientListener = new ClientListener(socket);
		Thread listenThread = new Thread(clientListener);
		listenThread.start();
		client.sendMessage();
		scanner.close();
		
	}



//// Mi dokimasmena
	public void prinMessages(String username){
		ArrayList<Message> messages=ClientManager.getClientsMessageslist(username);
		for(int i=0;i<messages.size();i++){
			System.out.println("From :"+messages.get(i).getUsername()+"\n "+messages.get(i).getContext()+"\n "+messages.get(i).getTime());
		}
	}
////
}
