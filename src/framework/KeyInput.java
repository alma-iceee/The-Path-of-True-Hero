package framework;

import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.ListIterator;

import objects.Bullet;
import window.Game;
import window.Game.STATE;
import window.Handler;

public class KeyInput extends KeyAdapter implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private ListIterator<GameObject> iterator;
	private GameObject tempObject;
	
	public static enum DIRECTION {
		NORTH,
		SOUTH,
		EAST,
		WEST
	};
	
	public static DIRECTION Direction = DIRECTION.SOUTH;
	public static boolean bounds = false;
	
	Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		if (Game.State == STATE.GAME) {
			int key = e.getKeyCode();
			
			if (key == KeyEvent.VK_ESCAPE) {
				Game.myGame.setCursor(Cursor.getDefaultCursor());
				Game.State = STATE.MENU;
			}
			
			iterator = handler.object.listIterator();
				
			while(iterator.hasNext()) {
				tempObject = iterator.next();

				if (tempObject.getId() == ObjectId.Player) {
					if (key == KeyEvent.VK_D) {
						tempObject.setVelX(3);
						Direction = DIRECTION.EAST;
					}
					if (key == KeyEvent.VK_A) {
						tempObject.setVelX(-3);
						Direction = DIRECTION.WEST;
					}
					if (key == KeyEvent.VK_W) {
						tempObject.setVelY(-3);
						Direction = DIRECTION.NORTH;
					}
					if (key == KeyEvent.VK_S) {
						tempObject.setVelY(3);	
						Direction = DIRECTION.SOUTH;
					}
				}
			}
			if (key == KeyEvent.VK_B) {
				bounds = true;			
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		if (Game.State == STATE.GAME) {
			int key = e.getKeyCode();
			
			iterator = handler.object.listIterator();
			GameObject tempObject;
				
			while(iterator.hasNext()) {
				tempObject = iterator.next();
	
				if (tempObject.getId() == ObjectId.Player) {
					if (key == KeyEvent.VK_D) {
						tempObject.setVelX(0);
					}
					if (key == KeyEvent.VK_A) {
						tempObject.setVelX(0);	
					}
					if (key == KeyEvent.VK_W) {
						tempObject.setVelY(0);
					}
					if (key == KeyEvent.VK_S) {
						tempObject.setVelY(0);	
					}
					/*if (key == KeyEvent.VK_SPACE) {
						handler.addObject(new Bullet(tempObject.getX(), tempObject.getY(), Direction, ObjectId.Bullet));
					}*/
				}
			}
			if (key == KeyEvent.VK_B) {
				bounds = false;			
			}
		}
	}
}