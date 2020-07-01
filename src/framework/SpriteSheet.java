package framework;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class SpriteSheet implements Serializable {
	private static final long serialVersionUID = 1L;
	
	transient private BufferedImage image;
	
	public SpriteSheet(BufferedImage image) {
		this.image = image;
	}
	
	public BufferedImage grabImage(int col, int row, int width, int height) {
		
		BufferedImage img = image.getSubimage((col * width) - width, (row * height) - height, width, height);
		return img;
		
	}
	
}