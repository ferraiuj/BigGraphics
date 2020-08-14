package bg.Screen;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import bg.Sprite.Sprite;
import bg.Sprite.Spritesheet;
public class Screen {
	
	private int xOffset, yOffset;
	private int width;
	private int height;
	private int[] pixels;
	private final int ALPHA_COL = 0xffFF00FF;
	private BufferedImage image;

	public Screen(int width, int height, BufferedImage image) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		this.image = image;
		System.out.println("Screen Constructor");

	}
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;

	}
	public int getOffsetX() {
		return xOffset;

	}
	public int getOffsetY() {
		return yOffset;

	}
	public void createFilledRect(int xp, int yp, int width, int height, int color) {

		for (int x = xp; x < xp + width; x++) {
			if (x < 0 || x >= this.width || yp >= this.height)
				continue;
			if (yp + height >= this.height)
				continue;
			for (int y = yp; y < yp + height; y++) {
				if (xp >= this.width || y < 0 || y >= this.height)
					continue;
				if (xp + width >= this.width)
					continue;
			
				image.setRGB(x, y, color);
			}
		}

	}

	public void createHollowRect(int xp, int yp, int width, int height, int color) {

//		for (int x = xp; x < xp + width; x++) {
//
//			if (x < 0 | x >= this.width || yp >= this.height)
//				continue;
//			if (yp > 0)
//				//image.setRGB(x, yp, 0xffffff);
//
//			if (yp + height >= this.height)
//				continue;
//			if (yp + height > 0) {
//				image.setRGB(x + width, yp, 0xffffff);
//
//			}
//			for (int y = yp; y <= yp + height; y++) {
//
//				if (xp >= this.width || y < 0 || y >= this.height)
//					continue;
//				if (xp > 0)
//					//image.setRGB(x, yp, 0xffffff);
//
//				if (xp + width >= this.width)
//					continue;
//				if (xp + width > 0) {
//					image.setRGB(x, yp + height, 0xffffff);
//
//				}
//			}
//		}
		for (int x = xp; x < xp + width; x++) {
			image.setRGB(x, yp + height, 0xffffff);
			image.setRGB(x, yp, 0xffffff);
		}

		for (int y = yp; y < yp + height; y++) {
			image.setRGB(xp, y, 0xffffff);
			image.setRGB(xp + width, y, 0xffffff);
		}
	}

	public void createHollowTriangle(int pointOne, int pointTwo, int pointThree, int color) {
		int xp = 0;
		int yp = 0;
		for (int x = xp; x < width; x++) {
			for (int y = yp; y < height; y++) {
				image.setRGB(x, y, 0xffffff);
			}
		}

	}
}
