package bg.Layers;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import bg.Screen.Screen;

public class ContainerRender extends Canvas {// should only get one layer at a time == no lists

	private static final long serialVersionUID = 1L;

	public Screen screen;

	private Graphics graphics;
	private BufferedImage image;
	private Dimension size = new Dimension();
	private Canvas canvas;
	private int containerX;
	private int containerY;
	
	public ContainerRender(int containerX, int containerY, Screen screen, Frame frame) {
		
		this.screen = screen;
	//	image = new BufferedImage(containerWidth, containerHeight, BufferedImage.TYPE_INT_RGB);
		//size = new Dimension(containerWidth, containerHeight);
		
		canvas = new Canvas();
		//this.containerX = containerWidth;
		//this.containerY = containerHeight;	
		//canvas.setPreferredSize(size);
		frame.addNotify();
		
		frame.add(canvas);
		//render(frame);
	}

	public void renderLayerByLayer(Layer layer) {
		layer.render(graphics);
	}

	public void renderLayerByIndex(List<Layer> layers, int index) {
		if (layers.size() > index) {
			layers.get(index).render(graphics);
		}
	}

	public void layersRender(List<Layer> layers) {

		for (int layer = 0; layer < layers.size(); layer++) {
			layers.get(layer).render(graphics);

		}
	}
	public void render(Frame frame) {
		
		BufferStrategy bs = frame.getBufferStrategy();
		if (bs == null) {
			frame.createBufferStrategy(3);
			return;
		}
		graphics = bs.getDrawGraphics();
		graphics.drawImage(image, containerX, containerY, image.getWidth(), image.getHeight(), null);

		graphics.dispose();
		bs.show();

	}

}
