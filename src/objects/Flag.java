package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectId;
//import framework.Texture;

public class Flag extends GameObject implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private float width = 64;
	private float height = 64;
	
	//Texture tex;
	
	
	public Flag(float x, float y, ObjectId id) {
		super(x, y, id);
	}

	public void tick(LinkedList<GameObject> object) {

		Collision(object);
	}
	
	private void Collision(LinkedList<GameObject> object) {
		
	}

	public void render(Graphics g) {
		
		g.setColor(Color.yellow);
		g.fillRect((int) x, (int) y, (int) width, (int) height);
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}
}