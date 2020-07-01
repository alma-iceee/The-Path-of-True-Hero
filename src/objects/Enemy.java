package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JOptionPane;

import framework.GameObject;
import framework.KeyInput;
import framework.ObjectId;
import framework.Texture;
import window.Game;
import window.Handler;

public class Enemy extends GameObject {
	private static final long serialVersionUID = 1L;
	
	private float width = 64;
	private float height = 64;
	
	private Handler handler;
	
	Texture tex = Game.getInstance();
	
	private Random rand = new Random();
	private int range = 0;
	private int attack = 0;
	private int dir = 3;
	
	public static boolean dead = false;
	private int delay = 0;
	
	public Enemy(float x, float y, Handler handler, ObjectId id) {	
		super(x, y, id);
		this.handler = handler;
	}

	public void tick(LinkedList<GameObject> object) {
		attack++;
		
		if (attack >= 30) {
			//handler.addObject(new Bullet(getX(), getY(), handler, dir,  ObjectId.Bullet));
			
			attack = 0;
		}
		
		if (range == 200) {
			int dir1 = dir;
			dir = rand.nextInt(5);
			
			
		
			if (dir == 4) {
				dir = dir1;
				setVelX(0);
				setVelY(0);
			}
			
			if (dir == 1) {
				setVelX(0);
				setVelY(5);
			}
			
			if (dir == 2) {
				setVelX(5);
				setVelY(0);
			}
			
			if (dir == 0) {
				setVelX(0);
				setVelY(-5);
			}
			
			if (dir == 3) {
				setVelX(-5);
				setVelY(0);
			}
		
			range = 0;
		}
		x += velX;
		y += velY;
		
		range += 5;
		
		Collision(object);
		
		if (dead)
			delay++;
	}
	
	private void Collision(LinkedList<GameObject> object) {
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ObjectId.Block) {
				
				if (getBoundsBottom().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height;
					velY = 5;		
				}
				
				if (getBoundsTop().intersects(tempObject.getBounds())) {
					y = tempObject.getY() + height;
					velY = -5;
				}
				
				if (getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + width;
					velX = 5;
				}
				
				if (getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - width;
					velX = -5;
				}
				
			}
		}
		
	}

	public void render(Graphics g) {

		if (delay >= 1) {
			
		//	g.drawImage(tex.block[3], (int) x, (int) y, (int) width, (int) height, null);
			
			JOptionPane.showMessageDialog(null, "THANK YOU FOR GAME","YOU WON!", JOptionPane.INFORMATION_MESSAGE);
			
			System.exit(0);
		}
		
		/*if (dir == 0) {
			g.drawImage(tex.enemy[0], (int) x, (int) y, (int) width, (int) height, null);
		}
		if (dir == 1) {
			g.drawImage(tex.enemy[10], (int) x, (int) y, (int) width, (int) height, null);
		}
		if (dir == 2) {
			g.drawImage(tex.enemy[20], (int) x, (int) y, (int) width, (int) height, null);
		}
		if (dir == 3) {
			g.drawImage(tex.enemy[30], (int) x, (int) y, (int) width, (int) height, null);
		}
*/
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
		return new Rectangle((int) x - 1, (int) y - 1, (int) width + 2, (int) height + 2);
	}

	public Rectangle getBoundsBottom() {
		return new Rectangle((int) (x + width / 4 + 1), (int) (y + height / 2 + 1), (int) (width / 2 - 2), (int) height / 2 - 1);
	}

	public Rectangle getBoundsTop() {
		return new Rectangle((int) (x + width / 4 + 1), (int) (y), (int) (width / 2 - 2), (int) height / 2 - 1);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle((int) x, (int) y, (int) width / 4 - 1, (int) height);
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x + (int) width * 3 / 4 + 1, (int) y, (int) width / 4 - 1, (int) height);
	}
	
}