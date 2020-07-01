package objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import window.Constants;
import window.Game;

public class WoodenFloor extends GameObject implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private float width = Constants.BLOCKSIZE;
	private float height = Constants.BLOCKSIZE;
	
	Texture tex = Game.getInstance();
	

	public WoodenFloor(float x, float y, ObjectId id) {
		
		super(x, y, id);
		
	}

	public void tick(LinkedList<GameObject> object) {
		
	}

	public void render(Graphics g) {
		if (Math.abs(Game.cam.locX) - 3 * Constants.BLOCKSIZE / 2 < x && Math.abs(Game.cam.locX) + Game.width + Constants.BLOCKSIZE > x && Math.abs(Game.cam.locY) - 3 * Constants.BLOCKSIZE / 2 < y && Math.abs(Game.cam.locY) + Game.height + Constants.BLOCKSIZE > y) {
			g.drawImage(tex.terrain[1], (int) x, (int) y, (int) width, (int) height, null);
		}
		
	}
	
	public Rectangle getBounds() {
		return null;
	}

}