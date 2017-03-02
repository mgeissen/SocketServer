import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient { 
	
	public static void main(String[] args) {
		String message = "Hallo Welt!";
		
		Socket verbindung;
		BufferedReader bufferedReader;
		DataOutputStream dataOutputStream;
		
		System.out.println("Verbindung mit Server " + SocketServer.SERVER_ADDRESS + " auf Port " + SocketServer.SERVER_PORT);
		
		try {
			verbindung = new Socket(SocketServer.SERVER_ADDRESS, SocketServer.SERVER_PORT);
			
			dataOutputStream = new DataOutputStream(verbindung.getOutputStream());
			dataOutputStream.writeBytes(message + "\n");
			System.out.println("Nachricht gesendet: " + message);
			
			bufferedReader = new BufferedReader(new InputStreamReader(verbindung.getInputStream()));
			message = bufferedReader.readLine();
			System.out.println("Nachricht empfangen: " + message);
			
			verbindung.close();
			System.out.println("Verbindung wurde geschlossen!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
