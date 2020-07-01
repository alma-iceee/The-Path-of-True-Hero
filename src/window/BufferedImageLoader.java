package window;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class BufferedImageLoader implements Serializable {
	private static final long serialVersionUID = 1L;
	
	transient private BufferedImage image;
	
	public BufferedImage loadImage(String path) {
		
		try {
			image = ImageIO.read(getClass().getResource(path));
		}
		catch (IOException e) {
			System.out.println("ERROR IN BUFFEREDIMAGELOADER");
			e.printStackTrace();
		}
		
		return image;
		
	}
	
}