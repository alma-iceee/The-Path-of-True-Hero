package window;

import java.io.Serializable;

public class StreamPackage implements Serializable {
	private static final long serialVersionUID = 1L;
	
	Handler handler;
	String asd;

	public StreamPackage(/*Handler handler*/ String asd) {
		this.asd = asd;
	}

	public String toString(){
		return "THIS IS HANDLER";
	}
}