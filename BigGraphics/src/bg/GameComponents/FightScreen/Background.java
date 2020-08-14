package bg.GameComponents.FightScreen;

import java.awt.Canvas;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import bg.Keyboard;
import bg.Mouse;
import bg.MouseTranslate;
import bg.File.BGFiles;
import bg.Layers.Layer;
import bg.Layers.LayerActions;
import bg.Layers.Container;
import bg.Layers.ContainerRender;
import bg.Screen.Screen;
import bg.Sprite.Sprite;

public class Background extends Canvas {

	private static final long serialVersionUID = 1L;
	private Screen screen;
	private Sprite sprite;
	public final String layerName = "Background";
	public int angle;
	private Container layerManage;
	private BufferedImage image;
	private ContainerRender layerListen;
	private LayerActions layerActions;
	private Canvas canvas;
	private Frame frame;

	public Background(Screen screen, Frame frame) {

		this.screen = screen;
		this.frame = frame;

		layerManage = new Container(screen);
		layerListen = new ContainerRender(0, 0, screen, frame);
		layerActions = new LayerActions(screen, layerManage);
		image = new BufferedImage(screen.getWidth() - 1, 500, BufferedImage.TYPE_INT_RGB);
		sprite = Sprite.player;
		layerManage.addLayer(0, 0, 1000, 1000, 0xd4d6d4, layerName, true, sprite);
		canvas = new Canvas();
		frame.addNotify();
		frame.add(canvas);

	}

	public void update(Keyboard key) {

//		layer.setRenderCherno(true);
//		layer.setLayerX((int) (Math.cos(angle) * 20));
//		layer.setLayerY((int) (Math.sin(angle) * 20));
//		angle++;
		if (layerManage.getLayerOnMouse() != null) {

			if (layerManage.getLayerOnMouse().getName() == layerName) {
				System.out.println("Layer name: " + layerManage.getLayerOnMouse().getName());

			}
			layerManage.layersUpdate();
			System.out.println("Layer name: ");

		}
		layerActions.update(key);

	}

	public void render() {
//now have to determine in battleinterface class when to run this render method
		BufferStrategy bs = frame.getBufferStrategy();
		if (bs == null) {
			frame.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		//g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
		if (layerManage.getLayerOnMouse() != null) {

			if (layerManage.getLayerOnMouse().getName() == layerName) {
				//layerManage.getLayerOnMouse().renderCherno(g);
				//layerManage.getLayerOnMouse().setRenderCherno(true);
				layerListen.renderLayerByIndex(layerManage.layersRender, 0);

			}
		}
		g.dispose();
		bs.show();

	}
}
