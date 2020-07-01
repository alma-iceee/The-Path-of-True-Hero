package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.ListIterator;

import framework.GameObject;
import framework.KeyInput;
import framework.ObjectId;
import framework.Texture;
import window.Animation;
import window.Game;
import window.Handler;

public class Player extends GameObject implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private float width = 64;
	private float height = 128;
	
	Texture tex = Game.getInstance();
	
	ListIterator<GameObject> iterator;

	private Animation playerWalkSouth;
	private Animation playerWalkWest;
	private Animation playerWalkEast;
	private Animation playerWalkNorth;
	
	Handler handler;
	
	public Player(float x, float y, int playerIndex, ObjectId id) {
		
		super(x, y, id);
		super.index = playerIndex;
		
		playerWalkSouth = new Animation(5, tex.player[1], tex.player[1], tex.player[2], tex.player[2], tex.player[3], tex.player[3], tex.player[2]);
		playerWalkWest = new Animation(5, tex.player[5], tex.player[5], tex.player[6], tex.player[6], tex.player[7], tex.player[7], tex.player[6]);
		playerWalkEast = new Animation(5, tex.player[9], tex.player[9], tex.player[10], tex.player[10], tex.player[11], tex.player[11], tex.player[10]);
		playerWalkNorth = new Animation(5, tex.player[13], tex.player[13], tex.player[14], tex.player[14], tex.player[15], tex.player[15], tex.player[14]);
		
	}

	public void tick(LinkedList<GameObject> object) {

		x += velX;
		y += velY;
		
		Collision(object);
		
		playerWalkSouth.runAnimation();
		playerWalkWest.runAnimation();
		playerWalkEast.runAnimation();
		playerWalkNorth.runAnimation();
	}
	
	private void Collision(LinkedList<GameObject> object) {
		iterator = object.listIterator();
		GameObject tempObject;
		
		while(iterator.hasNext()){
			tempObject = iterator.next();
			
			if (tempObject.getId() == ObjectId.Block) {
				
				if (getBoundsBottom().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height;

					velY = 0;		
				}
				
				if (getBoundsTop().intersects(tempObject.getBounds())) {
					y = tempObject.getY();
					velY = 0;
				}
				
				if (getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + width - 8;
					velX = 0;
				}
				
				if (getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - width + 12;
					velX = 0;
				}
			}
			else if (tempObject.getId() == ObjectId.Flag) {
				if (getBoundsBottom().intersects(tempObject.getBounds())) {
					handler.clearLevel();
				}
			}
		}
	}

	public void render(Graphics g) {

		if (KeyInput.Direction == KeyInput.DIRECTION.SOUTH) {
			if (velY != 0) {
				playerWalkSouth.drawAnimation(g, (int) x, (int) y, (int) width, (int) height);
			} 
			else {
				g.drawImage(tex.player[0], (int) x, (int) y, (int) width, (int) height, null);
			}
		}
		
		if (KeyInput.Direction == KeyInput.DIRECTION.WEST) {
			if (velX != 0) {
				playerWalkWest.drawAnimation(g, (int) x, (int) y, (int) width, (int) height);
			} 
			else {
				g.drawImage(tex.player[4], (int) x, (int) y, (int) width, (int) height, null);
			}
		}
		
		if (KeyInput.Direction == KeyInput.DIRECTION.EAST) {
			if (velX != 0) {
				playerWalkEast.drawAnimation(g, (int) x, (int) y, (int) width, (int) height);
			} 
			else {
				g.drawImage(tex.player[8], (int) x, (int) y, (int) width, (int) height, null);
			}
		}
		
		if (KeyInput.Direction == KeyInput.DIRECTION.NORTH) {
			if (velY != 0) {
				playerWalkNorth.drawAnimation(g, (int) x, (int) y, (int) width, (int) height);
			} 
			else {
				g.drawImage(tex.player[12], (int) x, (int) y, (int) width, (int) height, null);
			}
		}
		
		if (KeyInput.bounds) {
			Graphics2D g2d = (Graphics2D) g;
			g.setColor(Color.YELLOW);
			g2d.draw(getBounds());
			
			g.setColor(Color.RED);
			g2d.draw(getBoundsRight());
			g2d.draw(getBoundsLeft());
			g2d.draw(getBoundsBottom());
			g2d.draw(getBoundsTop());
		}
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) (x - 1 + (width / 4 - 1) / 2), (int) (y - 1 + height / 4 - 5), (int) (width + 2 - (width / 4 - 1)), (int) (height + 2 - height / 4 + 5));
	}

	public Rectangle getBoundsBottom() {
		return new Rectangle((int) (x + width / 4 + 1), (int) (y + height / 4 + 1 + height / 2), (int) (width / 2 - 2), (int) (height / 4 - 1));
	}

	public Rectangle getBoundsTop() {
		return new Rectangle((int) (x + width / 4 + 1), (int) (y + height / 2), (int) (width / 2 - 2), (int) (height / 4 - 1));
	}

	public Rectangle getBoundsRight() {
		return new Rectangle((int) (x + (width / 4 - 1) / 2), (int) (y + height / 2), (int) ((width / 4 - 1) / 2), (int) height / 2);
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle((int)( x + width * 3 / 4 + 1), (int) (y + height / 2), (int) ((width / 4 - 1) / 2), (int) (height / 2));
	}
}