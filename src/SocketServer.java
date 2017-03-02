import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

	public static final int SERVER_PORT_80 = 80;
	public static final int SERVER_PORT_81 = 81;
	public static final int SERVER_PORT_82 = 82;
	public static final int[] SERVER_PORTS = {SERVER_PORT_80, SERVER_PORT_81, SERVER_PORT_82};


	public static final String SERVER_ADDRESS = "localhost";

	public static void main(String[] args) {

		String message = "";

		ServerSocket socketServer;

		try {
			for(int port : SERVER_PORTS){
				socketServer = new ServerSocket(port);
				System.out.println("Server läuft auf Port " + port);
				
				Thread thread = new Thread(new PortWorker(socketServer));
				thread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
