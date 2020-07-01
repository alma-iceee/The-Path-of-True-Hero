package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.LinkedList;

import framework.GameObject;
import framework.KeyInput;
import framework.ObjectId;
//import framework.Texture;
import framework.KeyInput.DIRECTION;

public class Bullet extends GameObject implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private float width = 64;
	private float height = 64;
	
	//Texture tex;
	
	private DIRECTION Direction;
	
	public Bullet(float x, float y, DIRECTION Direction, ObjectId id) {
		super(x, y, id);
		this.Direction = Direction;
	}

	public void tick(LinkedList<GameObject> object) {
		if (Direction == KeyInput.DIRECTION.NORTH) {
			velY = - 10;
			velX = 0;
		}
		if (Direction == KeyInput.DIRECTION.EAST) {
			velX = - 10;
			velY = 0;
		}
		if (Direction == KeyInput.DIRECTION.WEST) {
			velX = 10;
			velY = 0;
		}
		if (Direction == KeyInput.DIRECTION.SOUTH) {
			velY = 10;
			velX = 0;
		}
		
		x += velX;
		y += velY;

		Collision(object);
	}
	
	private void Collision(LinkedList<GameObject> object) {
		
	}

	public void render(Graphics g) {
		/*
		if (dir == 0) {
			g.drawImage(tex.bullet[0], (int) x, (int) y, (int) width, (int) height, null);
		}		
		if (dir == 1) {
			g.drawImage(tex.bullet[1], (int) x, (int) y, (int) width, (int) height, null);
		}
		if (dir == 2) {
			g.drawImage(tex.bullet[2], (int) x, (int) y, (int) width, (int) height, null);
		}
		if (dir == 3) {
			g.drawImage(tex.bullet[3], (int) x, (int) y, (int) width, (int) height, null);
		}*/
		
		g.setColor(Color.red);
		g.fillRect((int) x, (int) y, (int) width, (int) height);
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}
}