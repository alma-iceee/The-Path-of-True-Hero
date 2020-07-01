package framework;

import java.awt.image.BufferedImage;
import java.io.Serializable;

import window.BufferedImageLoader;

public class Texture implements Serializable {
	private static final long serialVersionUID = 1L;
	
	SpriteSheet ps;
	SpriteSheet ts;
	transient private BufferedImage playerSheet = null;
	transient private BufferedImage terrainSheet = null;
	
	transient public BufferedImage[] player = new BufferedImage[16];
	transient public BufferedImage[] terrain = new BufferedImage[2];
	
	public Texture() {
		
		BufferedImageLoader loader = new BufferedImageLoader();
		playerSheet = loader.loadImage("/playerSS.png");
		terrainSheet = loader.loadImage("/terrain1.png");
		
		ps = new SpriteSheet(playerSheet);
		ts = new SpriteSheet(terrainSheet);
		
		getTextures();
	}
	
	private void getTextures() {
		
		terrain[0] = ts.grabImage(5, 3, 48, 48);
		terrain[1] = ts.grabImage(11, 2, 48, 48);

		
		//south
		player[0] = ps.grabImage(1, 1, 32, 64);
		player[1] = ps.grabImage(2, 1, 32, 64);
		player[2] = ps.grabImage(3, 1, 32, 64);
		player[3] = ps.grabImage(4, 1, 32, 64);
		
		//west
		player[4] = ps.grabImage(1, 2, 32, 64);
		player[5] = ps.grabImage(2, 2, 32, 64);
		player[6] = ps.grabImage(3, 2, 32, 64);
		player[7] = ps.grabImage(4, 2, 32, 64);
		
		//east
		player[8] = ps.grabImage(1, 3, 32, 64);
		player[9] = ps.grabImage(2, 3, 32, 64);
		player[10] = ps.grabImage(3, 3, 32, 64);
		player[11] = ps.grabImage(4, 3, 32, 64);
		
		//north
		player[12] = ps.grabImage(1, 4, 32, 64);
		player[13] = ps.grabImage(2, 4, 32, 64);
		player[14] = ps.grabImage(3, 4, 32, 64);
		player[15] = ps.grabImage(4, 4, 32, 64);
		
	}

}
