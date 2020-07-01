package window;

import java.awt.Frame;
import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;

import audio.MusicPlayer;

public class Window implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static ExecutorService service;
	public static MusicPlayer player;
	public Window(Game game) {
		service = Executors.newFixedThreadPool(10);
		
		JFrame frame = new JFrame();
		frame.add(game);
		frame.setUndecorated(true);
		frame.pack();
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		player = new MusicPlayer();
		
		service.submit(game);
	}
}