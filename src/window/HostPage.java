package window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.Serializable;

public class HostPage implements Serializable {
	private static final long serialVersionUID = 1L;

	public void render(Graphics g) {
		Font fnt0 = new Font("arial", Font.BOLD, (int) (Game.height / 10));
		g.setFont(fnt0);
		g.setColor(Color.WHITE);
		g.drawString("SERVER CREATED", (int) (Game.width / 2) - (int) (Game.width / 10 * 3), (int) (Game.height / 7));
	}
}