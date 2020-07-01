package framework;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;

import window.Game;
import window.Game.STATE;
import window.Window;

public class MouseInput implements MouseListener, MouseMotionListener, Serializable {
	private static final long serialVersionUID = 1L;

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {
		if (Game.State == STATE.MENU) {
			
			int mx = e.getX();
			int my = e.getY();
			
			if (mx >= (int) (Game.width / 2) - (int) (Game.width / 20) && mx <= (int) (Game.width / 2) - (int) (Game.width / 20) + (int) (Game.width / 10)) {
				if (my >= (int) (Game.height / 7 * 2) && my <= (int) (Game.height / 7 * 2) + (int) (Game.height / 20)) {
					Game.myGame.createServer();
					
					Game.State = Game.STATE.HOST;  
				}	
			}
			
			if (mx >= (int) (Game.width / 2) - (int) (Game.width / 20) && mx <= (int) (Game.width / 2) - (int) (Game.width / 20) + (int) (Game.width / 10) && Game.connectAvailable) {
				if (my >= (int) (Game.height / 7 * 3) && my <= (int) (Game.height / 7 * 3) + (int) (Game.height / 20)) {
					Game.myGame.connectToServer();

					Game.continueAvailable = true;
					Window.service.submit(Window.player);
					Game.myIndex++;
					System.out.println(Game.myIndex);
					Game.State = Game.STATE.GAME;     
					Game.myGame.setCursor(Game.invisibleCursor);
					
				}
			}
			
			if (mx >= (int) (Game.width / 2) - (int) (Game.width / 20) && mx <= (int) (Game.width / 2) - (int) (Game.width / 20) + (int) (Game.width / 10) && Game.continueAvailable) {
				if (my >= (int) (Game.height / 7 * 4) && my <= (int) (Game.height / 7 * 4) + (int) (Game.height / 20)) {
					Game.connectAvailable = false;
					Game.myIndex++;
					System.out.println("asd");
					Game.State = Game.STATE.GAME;     
					Game.myGame.setCursor(Game.invisibleCursor);
				}
			}
			
			if (mx >= (int) (Game.width / 2) - (int) (Game.width / 20) && mx <= (int) (Game.width / 2) - (int) (Game.width / 20) + (int) (Game.width / 10)) {
				if (my >= (int) (Game.height / 7 * 5) && my <= (int) (Game.height / 7 * 5) + (int) (Game.height / 20)) {	
					System.exit(1);	
				}
			}
		}
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mouseDragged(MouseEvent e) {
		
	}

	public void mouseMoved(MouseEvent e) {
		if (Game.State == STATE.MENU) {
			int mx = e.getX();
			int my = e.getY();
		
			if (mx >= (int) (Game.width / 2) - (int) (Game.width / 20) && mx <= (int) (Game.width / 2) - (int) (Game.width / 20) + (int) (Game.width / 10)) {
				if (my >= (int) (Game.height / 7 * 2) && my <= (int) (Game.height / 7 * 2) + (int) (Game.height / 20)) {

					
					
				}	
			}
		}
	}
}