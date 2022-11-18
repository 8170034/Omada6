import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private ServerSocket serverSocket;
	
	public Server(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;		
	}
	
	public void startServer() {
		System.out.println("SERVER HAS STARTED RUNNING\n");		
		try {
			while(!serverSocket.isClosed()) {
				Socket socket = serverSocket.accept();
				ClientManager clientManager= new ClientManager(socket);
				Thread myThread = new Thread(clientManager);
				myThread.start();
			}	
		} catch (IOException e) {
			closeServerSocket();
		}
	}		
	
	public void closeServerSocket() {
		try {
			if(serverSocket != null) {
				serverSocket.close();
			}
		}catch( IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(8080);
			Server server = new Server(serverSocket);
			server.startServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
