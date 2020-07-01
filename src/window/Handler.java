package window;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.ListIterator;

import framework.GameObject;

public class Handler implements Serializable {
	private static final long serialVersionUID = 1L;

	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	private GameObject tempObject;
	
	public void tick() {
		ListIterator<GameObject> iterator = object.listIterator();
		while(iterator.hasNext()){
			tempObject = iterator.next();
			
			tempObject.tick(object);
		}
	}
	
	public void render(Graphics g) {
		ListIterator<GameObject> iterator = object.listIterator();
		while(iterator.hasNext()){
			tempObject = iterator.next();
			
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	public void clearLevel() {
		object.clear();
		
		Game.myGame.init();
	}
}