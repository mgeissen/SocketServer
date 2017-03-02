import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ConnectionWorker implements Runnable{
	
	Socket verbindung;
	int port;
	
	public ConnectionWorker(Socket verbindung, int port) {
		this.verbindung = verbindung;
		this.port = port;
	}

	@Override
	public void run() {
		String message;

		BufferedReader bufferRead;
		DataOutputStream bufferWrite;

		System.out.println("Client: " + verbindung.getRemoteSocketAddress());

		try {
			bufferRead = new BufferedReader(new InputStreamReader(verbindung.getInputStream()));
			message = bufferRead.readLine();
			System.out.println("Empfangende Nachricht: " + message);
			
			bufferWrite = new DataOutputStream(verbindung.getOutputStream());
			
			switch (port) {
			case SocketServer.SERVER_PORT_80:
				bufferWrite.writeBytes(message + "\n");
				break;
			case SocketServer.SERVER_PORT_81:
				bufferWrite.writeBytes(message.toUpperCase() + "\n");
				break;
			case SocketServer.SERVER_PORT_82:
				bufferWrite.writeBytes("42\n");
				break;
			default:
				break;
			}
			System.out.println("Antwortnachricht: " + message + " (Port: " + port + ")");

			verbindung.close();
			System.out.println("Verbindung zum Client geschlossen!");
			message = "";
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

}
