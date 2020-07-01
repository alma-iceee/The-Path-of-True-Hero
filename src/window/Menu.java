package window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;

public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Rectangle playButton = new Rectangle((int) (Game.width / 2) - (int) (Game.width / 20), (int) (Game.height / 7 * 2), (int) (Game.width / 10), (int) (Game.height / 20));
	public Rectangle recordsButton = new Rectangle((int) (Game.width / 2) - (int) (Game.width / 20), (int) (Game.height / 7 * 3), (int) (Game.width / 10), (int) (Game.height / 20));
	public Rectangle aboutButton = new Rectangle((int) (Game.width / 2) - (int) (Game.width / 20), (int) (Game.height / 7 * 4), (int) (Game.width / 10), (int) (Game.height / 20));
	public Rectangle quitButton = new Rectangle((int) (Game.width / 2) - (int) (Game.width / 20), (int) (Game.height / 7 * 5), (int) (Game.width / 10), (int) (Game.height / 20));
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("arial", Font.BOLD, (int) (Game.height / 10));
		g.setFont(fnt0);
		g.setColor(Color.WHITE);
		g.drawString("MENU", (int) (Game.width / 2) - (int) (Game.height / 20 * 3), (int) (Game.height / 7));
		
		Font fnt1 = new Font("arial", Font.CENTER_BASELINE, 30);
		g.setFont(fnt1);
		g.setColor(Color.WHITE);
		g.drawString("HOST", playButton.x + (int) (Game.width / 30), playButton.y + (int) (Game.height / 30));
		g.drawString("CONNECT", recordsButton.x + (int) (Game.width / 60), recordsButton.y + (int) (Game.height / 30));
		g.drawString("CONTINUE", aboutButton.x + (int) (Game.width / 40) - 30, aboutButton.y + (int) (Game.height / 30));
		g.drawString("QUIT", quitButton.x + (int) (Game.width / 30), quitButton.y + (int) (Game.height / 30));
		
		g.setColor(Color.WHITE);
		g2d.draw(playButton);
		g2d.draw(recordsButton);
		g2d.draw(aboutButton);
		g2d.draw(quitButton);	
	}
}