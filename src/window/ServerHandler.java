package window;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class ServerHandler extends Thread implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Socket socket;

	public ServerHandler(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());

			ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

			StreamPackage spck = null;

			while((spck = (StreamPackage)inStream.readObject())!=null) {
				//System.out.println(spck);
				outStream.writeObject("MESSAGE DELIVERED");
			}
			
			inStream.close();					
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}