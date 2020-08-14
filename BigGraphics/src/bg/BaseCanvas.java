package bg;

//Project Started 7/22/2020
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import bg.File.BGFiles;
import bg.GameComponents.FightScreen.BattleInterface;
import bg.Layers.Container;
import bg.Layers.ContainerRender;
import bg.Screen.Screen;
import bg.Screen.RenderMethods.WriteableRasters;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;  
import java.awt.event.WindowListener; 
public class BaseCanvas extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	public static String title = "BigGraphics";
	private static int width = 1920;// 300
	private static int height = 1080;// 168
	public Frame frame;
	private Thread thread;
	private Dimension screenRes;
	private boolean running = false;
	private Screen screen;
	public Mouse mouse;
	public BGFiles bgFiles;
	public WriteableRasters raster;
	public Keyboard key;
	public BattleInterface battle;
	// public LayerManagement layerManager;
	// public LayerRenderListener layerListener;
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	
	// A Buffer is:
	// A container for data of a specific primitive type.
	// A buffer is a linear, finite sequence of elements of a specific primitive
	// type. Aside from its content, the essential properties of a buffer are its
	// capacity, limit, and position:
	// A buffer's capacity is the number of elements it contains. The capacity of a
	// buffer is never negative and never changes.
	// A buffer's limit is the index of the first element that should not be read or
	// written. A buffer's limit is never negative and is never greater than its
	// capacity.
	// A buffer's position is the index of the next element to be read or written. A
	// buffer's position is never negative and is never greater than its limit.
	// Buffers are not safe for use by multiple concurrent threads. If a buffer is
	// to be used by more than one thread then access to the buffer should be
	// controlled by appropriate synchronization.

	// private int pixel = ((DataBufferInt)
	// image.getRaster().getDataBuffer()).getElem(1);
	// private BufferedImage img = new BufferedImage(300, 168,
	// BufferedImage.TYPE_INT_RGB);
	// private int[] pix = ((DataBufferInt)
	// img.getRaster().getDataBuffer()).getData();

	public BaseCanvas() {
		 
		screenRes = Toolkit.getDefaultToolkit().getScreenSize();
		width = screenRes.width;
		height = screenRes.height;
		
		frame = new JFrame();

		key = new Keyboard();
		screen = new Screen(width, height, image);
		bgFiles = new BGFiles();
		battle = new BattleInterface(screen, frame);
		raster = new WriteableRasters();
		mouse = new Mouse();

		setPreferredSize(screenRes);
		addKeyListener(key);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() { 
		
		System.out.println("Stop");
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static long timeStamp = 0;

	public static void timeStamp() {
		System.out.println(timeStamp);
	}

	@Override
	public void run() {

		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();

		while (running == true) {

			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				timeStamp++;
				frame.setTitle(title + "  |  " + updates + " ups,  " + frames + "  fps");
				updates = 0;
				frames = 0;
			}
		}
	}

	public void update() {
	
		key.update();
		battle.update(key);
	}

	public void render() {

		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(image, 0, 0, width, height, null);
		
		battle.render();

		g.dispose();
		bs.show();
		
	}

	public static void main(String[] args) {

		BaseCanvas game = new BaseCanvas();
		game.frame.setResizable(true);
		game.frame.setTitle(BaseCanvas.title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                game.frame.dispose();  
            }  
        });  
		game.frame.setLayout(null);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.start();

	}

}
