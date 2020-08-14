package bg.Screen.RenderMethods;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import bg.Screen.Screen;
import bg.Sprite.Sprite;
import bg.Sprite.Spritesheet;

public class ChernoRender extends Canvas {
	// LayerImage only (STRICTLY) gives information to Layer to protect LayerImage
	// integrity
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	
	private Sprite sprite;
	private int layerWidth, layerHeight;
	public int[] pixels;
	private final int MAP_SIZE = 64; // Ensure map size alteration doesnt crash
	private Random random; // game
	private final int MAP_SIZE_MASK = MAP_SIZE - 1; // Ensure map size alteration
													// doesnt crash game
	private int tiles[] = new int[MAP_SIZE * MAP_SIZE];
	private int xOffset, yOffset;
	private final int ALPHA_COL = 0xffFF00FF;
	private Screen screen;

	public ChernoRender(int layerWidth, int layerHeight, Screen screen, BufferedImage image, Sprite sprite) {

		this.image = image;
		this.sprite = sprite;
		this.layerWidth = layerWidth;
		this.layerHeight = layerHeight;
		this.screen = screen;
		pixels = new int[layerWidth * layerHeight];
	}

	public void renderUniquePixels() {
		image.getRaster().getWidth();

		int pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getElem(1);
	}

	public void renderSheet(int xp, int yp, Spritesheet sheet, boolean fixed) {
		if (fixed) {

			xp -= xOffset;
			yp -= yOffset;
		}
		for (int y = 0; y < sheet.SPRITE_HEIGHT; y++) {
			int ya = y + yp;
			for (int x = 0; x < sheet.SPRITE_WIDTH; x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= layerWidth || ya < 0 || ya >= layerHeight)
					continue;
				pixels[xa + ya * layerWidth] = sheet.pixels[x + y * sheet.SPRITE_WIDTH];
			}
		}
	}

	public void renderSprite(int xp, int yp, boolean fixed) {
		if (fixed) {

			xp -= xOffset;
			yp -= yOffset;
		}
		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= layerWidth || ya < 0 || ya >= layerHeight)
					continue;
				int col = sprite.pixels[x + y * sprite.getWidth()];
				if (col != ALPHA_COL)
					pixels[xa + ya * layerWidth] = col;
				// pixels[xa + ya * width] = sprite.pixels[x + y *
				// sprite.getWidth()];
			}
		}

	}
	public void drawRect(int layerX, int layerY, int layerWidth, int layerHeight, int color) {

		for (int yPixels = 0; yPixels < layerHeight; yPixels++) {

			if (layerX >= screen.getWidth() || yPixels < 0 || yPixels >= screen.getHeight())
				continue;

			if (layerX + layerWidth >= screen.getWidth())
				continue;
			for (int xPixels = 0; xPixels < layerWidth; xPixels++) {

				if (xPixels < 0 | xPixels >= screen.getWidth() || layerY >= screen.getHeight())
					continue;

				if (layerY + layerHeight >= screen.getHeight())
					continue;

				pixels[xPixels + yPixels * layerWidth] = color;

			}
		}
	}
	public void createFilledRect(int layerHeight, int layerWidth, int color) {
		for (int yPixels = 0; yPixels < layerHeight; yPixels++) {
			for (int xPixels = 0; xPixels < layerWidth; xPixels++) {
				pixels[xPixels + yPixels * layerWidth] = color;
			}
		}
	}
	public int[] getPixelColorArray() {
		return pixels;
		
	}
}
