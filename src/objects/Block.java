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

public class Block extends GameObject implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private float width = 64;
	private float height = 64;
	
	Texture tex = Game.getInstance();

	public Block(float x, float y, ObjectId id) {
		super(x, y, id);
	}

	public void tick(LinkedList<GameObject> object) {
		
	}

	public void render(Graphics g) {
		if (Math.abs(Game.cam.locX) - 3 * Constants.BLOCKSIZE / 2 < x && Math.abs(Game.cam.locX) + Game.width + Constants.BLOCKSIZE > x && Math.abs(Game.cam.locY) - 3 * Constants.BLOCKSIZE / 2 < y && Math.abs(Game.cam.locY) + Game.height + Constants.BLOCKSIZE > y) {
			g.drawImage(tex.terrain[0], (int) x, (int) y, (int) width, (int) height, null);
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x + 5, (int) y + 5, (int) width - 10, (int) height - 10);
	}

}