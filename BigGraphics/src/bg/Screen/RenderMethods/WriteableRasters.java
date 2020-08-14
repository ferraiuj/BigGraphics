package bg.Screen.RenderMethods;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class WriteableRasters {

	private BufferedImage image = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_RGB);
	private WritableRaster raster = image.getRaster();
	private int dArray[] = new int[image.getWidth() * image.getHeight()];
	private int color;
	private int angle;
	private double xMove, yMove;
	private int xAxis, yAxis;

	// Band is set of
	public WriteableRasters() {
		// setSampleArray();
		setPixel();
	}

	public void drawPixels() {
		this.yMove += yAxis;
		this.xMove += xAxis;

	}

	private int[] pixel = {0xff00ff, 0xff00ff, 0x0000ff };

	public void setPixel() {
		// raster.setSample(xAxis, yAxis, 1, color);
		for (int width = 0; width < 100; width++) {
			for (int height = 0; height < 100; height++) {
				raster.setPixel(width, height, pixel);
			}
		}
	}

	public void setSampleArray() {
		if (color == 255) {
			color = 0;
		}
		color++;
		for (int i = 0; i < dArray.length; i++) {
			dArray[i] = color;
			raster.setSample(xAxis, yAxis, 1, color);

		}
//		raster = image.getRaster();
//		raster.setPixels(0, 0, raster.getWidth(), raster.getHeight(), pixels);
//		image.setData(raster);
	}

	public void update() {

		// setSampleArray();
		// System.out.println("angle: " + angle);
	
		// raster.setPixels(0, 0, image.getWidth(), image.getHeight(), dArray);
	}

	public void render(Graphics g) {

		// dArray[color] = color;
		// raster.
		setPixel();
		// drawPixels();	
		if (xAxis == raster.getWidth() - 1) {
			xAxis = 0;
		}
		if (yAxis == raster.getHeight() - 1) {
			yAxis = 0;
		}
		xAxis++;
		yAxis++;
		// raster.setPixels(xAxis, yAxis, image.getWidth(), image.getHeight(), dArray);
		// raster.setSample(xAxis, yAxis, 1, color);
		angle++;
		//g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
	}
}
