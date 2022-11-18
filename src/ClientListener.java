import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientListener implements Runnable {
	// akouei ta minimata apo tous allous kai ta ektipwnei stin othoni tou client 
	
	private Socket socket;
	private BufferedReader reader;
	
	public ClientListener(Socket socket) {
		try {
			this.socket = socket;
			this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			closeClientConnection(socket, reader);
		}
	}
	
	public void run() {
		String messageFromOthers;
		
		while(!socket.isClosed()) {
			try {
				messageFromOthers = reader.readLine();
				//System.out.println("ELEGXOS: Class MyClientListener listened"); //ELEGXW OTI I CLIENTHANDLER AKOYEI TA MINIMATA
				System.out.println(messageFromOthers);//deixnei stin konsola tou client
			} catch (IOException e) {
				closeClientConnection(socket, reader);
			}
		}		
	}
	
	public void closeClientConnection(Socket socket, BufferedReader reader) {		
		try {
			reader.close();
			socket.close();
			// System.out.println("CONNECTION CLOSED SUCCESSFULLY(CLASS ClientListener)"); //ELEGXW OTI I CLIENTLISTENER APOSINDEETAI EPITXWS
		}catch (IOException e) {
			e.printStackTrace();
		}		
	}

}
