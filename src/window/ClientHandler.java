package window;

import java.io.ObjectInputStream;
import java.io.Serializable;

public class ClientHandler extends Thread implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private ObjectInputStream inStream;

	public ClientHandler(ObjectInputStream inStream) {
		this.inStream = inStream;
	}

	public void run() {
		try {
			String text = "";

			while((text = (String)inStream.readObject())!=null) {
				System.out.println(text);
			}

			inStream.close();					
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}