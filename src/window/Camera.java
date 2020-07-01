package window;

import java.io.Serializable;

import framework.GameObject;

public class Camera implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private float x;
	private float y;
	public float locX;
	public float locY;
	
	public Camera(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick(GameObject player) {
		if ((player.getX() - (float) (Game.width / 2)) > 0 && (player.getX() + (float) (Game.width / 2) < Game.picWidth * Constants.BLOCKSIZE)) {
			float xTarg = -player.getX() + (float) (Game.width / 2);
			locX = xTarg;
			x += (xTarg - x) * 0.1;
		}
		else if ((player.getX() - (float) (Game.width / 2) <= 0)) {
			float xTarg = 0;
			locX = xTarg;
			x += (xTarg - x) * 0.1;
		}
		else if ((player.getX() + (float) (Game.width / 2) >= Game.picWidth * Constants.BLOCKSIZE)) {
			float xTarg = -(float) (Game.picWidth * Constants.BLOCKSIZE - Game.width);
			locX = xTarg;
			x += (xTarg - x) * 0.1;
		}
		
		if ((player.getY() - (float) (Game.height / 2)) > 0 && (player.getY() + (float) (Game.height / 2) < Game.picHeight * Constants.BLOCKSIZE)) {
			float yTarg = -player.getY() + (float) (Game.height / 2);
			locY = yTarg;
			y += (yTarg - y) * 0.1;
		}
		else if ((player.getX() - (float) (Game.height / 2) <= 0)) {
			float yTarg = 0;
			locY = yTarg;
			y += (yTarg - y) * 0.1;
		}
		else if ((player.getY() + (float) (Game.height / 2) >= Game.picHeight * Constants.BLOCKSIZE)) {
			float yTarg = -(float) (Game.picHeight * Constants.BLOCKSIZE - Game.height);
			locY = yTarg;
			y += (yTarg - y) * 0.1;
		}
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;	
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}	
}