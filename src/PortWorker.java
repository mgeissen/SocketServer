import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PortWorker implements Runnable {

	ServerSocket serverSocket;
	
	public PortWorker(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}
	
	@Override
	public void run() {
		Socket verbindung;
		while (true){
			System.out.println("Warten auf Client...");
			try {
				verbindung = this.serverSocket.accept();
				Thread thread = new Thread(new ConnectionWorker(verbindung, serverSocket.getLocalPort()));
				thread.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
