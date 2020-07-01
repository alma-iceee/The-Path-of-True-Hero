package window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
//import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ListIterator;

import framework.GameObject;
import framework.KeyInput;
import framework.MouseInput;
import framework.ObjectId;
import framework.Texture;
import objects.Block;
import objects.Flag;
import objects.Player;
import objects.WoodenFloor;

public class Game extends Canvas implements Runnable, Serializable {

	private static final long serialVersionUID = 1L;
	public static int myTimer = 0;
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static double width = screenSize.getWidth();
	public static double height = screenSize.getHeight();
	public static double picWidth;
	public static double picHeight;
	public static float pX; 
	public static float pY; 

	public static boolean running = false;
	
	transient private BufferedImage level = null;
	transient private BufferedImage logo = null;
	
	private ListIterator<GameObject> iterator;
	
	public static int updatesInfo;
	public static int framesInfo;
	
	public static Cursor invisibleCursor ;
	
	public static Handler handler;
	public static Camera cam;
	public static Texture tex;
	private Menu menu;
	private HostPage host;

	public static StreamPackage response;
	public static Game myGame;
	public static int myIndex = 0;
	
	public static boolean continueAvailable = false;
	public static boolean connectAvailable = true;

	transient BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
	
	public static enum STATE {
		MENU,
		GAME,
		HOST
	};
	
	public static STATE State = STATE.MENU;
	
	public static int LEVEL = 1;
	
	public void init() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Point hotSpot = new Point(0,0);
		BufferedImage cursorImage = new BufferedImage(1, 1, BufferedImage.TRANSLUCENT); 
		invisibleCursor = toolkit.createCustomCursor(cursorImage, hotSpot, "InvisibleCursor");
		
		menu = new Menu();
		host = new HostPage();
		
		tex = new Texture();
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/level2.png");
		
		logo = loader.loadImage("/logo.png");
		
		handler = new Handler();
		
		cam = new Camera(0, 0);
		
		loadImageLevel(level);
		
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput());
		this.addMouseMotionListener(new MouseInput());
	}
	
	public void run() {
		running = true;
		init();
		this.requestFocus();
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1) {
				
				tick();
				updates++;
				delta--;
				
			}
			
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				updatesInfo = updates;
				framesInfo = frames;
				frames = 0;
				updates = 0;
				myTimer++;
			}
		}
	}
	
	public void tick() {
		if (State == STATE.GAME) {
			if (myTimer >= 5) {
				handler.tick();
				
				iterator = handler.object.listIterator();
				GameObject tempObject;
				while(iterator.hasNext()){
					tempObject = iterator.next();
					if (tempObject.getId() == ObjectId.Player && tempObject.getIndex() == myIndex) {
						cam.tick(tempObject);	
						pX = tempObject.getX();
						pY = tempObject.getY();
					}
				}
			}
		}
		else if (State == STATE.MENU) {
			
		}
		else if (State == STATE.HOST) {
			
			
		}
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		
		//////////////////////
		
		if (State == STATE.GAME) {
			if (myTimer < 5) {
				g.drawImage(logo, 0, 0, getWidth(), getHeight(), null);
			}
			
			if (myTimer >= 5) {
				g2d.translate(cam.getX(), cam.getY());
				
				handler.render(g);
				
				g2d.translate(-cam.getX(), -cam.getY());
			}
		}
		
		else if (State == STATE.MENU) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth(), getHeight());
			
			menu.render(g);
		}
		
		else if (State == STATE.HOST) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth(), getHeight());
			
			host.render(g);
		}
		
		g.setColor(Color.WHITE);
		Font fnt1 = new Font("arial", Font.BOLD, 20);
		g.setFont(fnt1);
		g.drawString("TICKS: " + updatesInfo + " FPS: " + framesInfo, getWidth() - 200, getHeight() - 10);
		
		g.dispose();
		bs.show();
	}
	
	private void loadImageLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		
		picWidth = w;
		picHeight = h;
		
		int locationX = 0;
		int locationY = 0;
		
		for (int xx = 0; xx < h; xx++) {
			for (int yy = 0; yy < w; yy++) {
				int pixel = image.getRGB(xx, yy);
				
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if (red == 0 && green == 0 && blue == 0) {
					handler.addObject(new Block(xx * 64, yy * 64, ObjectId.Block));
				}
				
				if (red == 0 && green == 255 && blue == 0) {
					handler.addObject(new WoodenFloor(xx * 64, yy * 64, ObjectId.WoodenFloor));
				}
				
				if (red == 255 && green == 216 && blue == 0) {
					handler.addObject(new Flag(xx * 64, yy * 64, ObjectId.Flag));
				}
				
				if (red == 0 && green == 0 && blue == 255) {
					locationX = xx;
					locationY = yy;
					handler.addObject(new WoodenFloor(xx * 64, yy * 64, ObjectId.WoodenFloor));
				}
			}
		}	
		handler.addObject(new Player(locationX * 64, locationY * 64, 1, ObjectId.Player));
	}
	
	public static Texture getInstance() {
		return tex;
	}
	
	public void createServer() {	
		try{
			@SuppressWarnings("resource")
			ServerSocket server = new ServerSocket(1996);

			Thread threadServer = new Thread(new Runnable() {
				public void run() {
				while(true) {
					Socket socket = null;
					try {
						socket = server.accept();
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("Connected");
					myIndex++;
					ServerHandler serverHandler = new ServerHandler(socket);
					Window.service.submit(serverHandler);
					}
				}
			});
			
			Window.service.submit(threadServer);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void connectToServer() {
		try {
			@SuppressWarnings("resource")
			Socket socket = new Socket("127.0.0.1", 1996);
			ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

			ClientHandler clientHandler = new ClientHandler(inStream);
			Window.service.submit(clientHandler);
			
			boolean state = true;
			Thread thread = new Thread(new Runnable() {
				public void run() {
					while(state) {
						
						StreamPackage send = new StreamPackage("asd");
						try {
							outStream.writeObject(send);
						}
						catch (IOException e) {
							e.printStackTrace();
						}
						
						/*try {
							response = (StreamPackage)inStream.readObject();
						}
						catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
						catch (IOException e) {
							e.printStackTrace();
						}*/
					}
				}
			});
			thread.start();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		myGame = new Game();
		new Window(myGame);		
	}
}