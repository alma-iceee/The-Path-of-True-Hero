package audio;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer extends TimerTask implements Runnable, Serializable {
	private static final long serialVersionUID = 1L;

	private String musicFiles;
	
	Timer myTimer = new Timer();
	
	public MusicPlayer() {
		musicFiles = "./res/audio/theme.wav";
	}
	
	private void playSound(String fileName) {
		try {
			File soundFile = new File(fileName);
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
			AudioFormat format = ais.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(ais);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-20);
			clip.start();
		}
		catch(LineUnavailableException lue) {
			lue.printStackTrace();
		}
		catch(UnsupportedAudioFileException uafe) {
			uafe.printStackTrace();
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public void run() {
		myTimer.scheduleAtFixedRate(new MusicPlayer(), /*LENGTH OF MUSIC*/ 109000, 109000);
		
		playSound(musicFiles);
	}
}