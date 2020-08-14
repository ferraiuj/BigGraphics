package bg.Layers;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import bg.Mouse;
import bg.Screen.Screen;
import bg.Screen.RenderMethods.ChernoRender;
import bg.Screen.RenderMethods.WriteableRasters;
import bg.Sprite.Sprite;

public class Layer {
	// VERY SENSITIVE CLASS
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Layer only (STRICTLY) gives information to LayerManagement to protect Layer
	// integrity
	private int layerX;
	private int layerY;
	private int layerWidth;
	private int layerHeight;
	private int priority;
	private int color;
	private int defaultColor;
	private boolean moveable;
	private boolean renderCherno = false;
	private boolean renderDefault = false;
	private String name;
	public ChernoRender chernoRender;
	private Sprite sprite;
	private BufferedImage image;
	private Screen screen;
	private int rightXBound;
	private int bottomYBound;
	private int xOrigin;
	private int yOrigin;
	private boolean changed = false;
	private boolean dragging = false;
	private WriteableRasters raster;

	public Layer(int layerX, int layerY, int layerWidth, int layerHeight, int priority, String name, int color,
			Screen screen, boolean moveable) {

		this.screen = screen;
		this.color = color;
		this.defaultColor = color;
		this.layerX = layerX;
		this.layerY = layerY;
		this.layerWidth = layerWidth;
		this.layerHeight = layerHeight;
		this.priority = priority;
		this.name = name;
		this.raster = new WriteableRasters();
		setMoveable(moveable);
		setBounds();
		setOrigin();

		// layerActions = new LayerActions();
	}

	public void createLayerImage(Sprite sprite) {
		this.sprite = sprite;
		image = new BufferedImage(layerWidth, layerHeight, BufferedImage.TYPE_INT_RGB);
		chernoRender = new ChernoRender(layerWidth, layerHeight, screen, image, sprite);
		chernoRender.renderSprite(getLayerX(), getLayerY(), false); // cache this data

		changed = true;
	}

	public int mouseSpeed = 1;
	public int xOffset;
	public int yOffset;

	public void setLayerX(int mouseX) {
		this.layerX = mouseX;
		changed = true;
	}

	public void setLayerY(int mouseY) {
		this.layerY = mouseY;
		changed = true;
	}

	public void setBounds() {

		rightXBound = screen.getWidth() - (screen.getWidth() - (getLayerX() + getLayerWidth()));
		bottomYBound = screen.getHeight() - (screen.getHeight() - (getLayerY() + getLayerHeight()));
		changed = true;
	}

	public void setOrigin() {
		xOrigin = getLayerX();
		yOrigin = getLayerY();
		changed = true;
	}

	public void incrementPriority() {
		priority++;
		changed = true;
	}

	public void unchanged() {
		changed = false;
	}

	public void setColor(int color, Screen screen) {
		this.color = color;
		changed = true;
	}

	public void setPriority(int prio) {
		priority = prio;
		changed = true;
	}

	public void setRenderCherno(boolean active) {
		renderCherno = active;
		changed = true;
	}

	public void setRenderDefault(boolean active) {
		renderDefault = active;
		changed = true;
	}

	public void setDragging(boolean drag) {
		changed = true;
		dragging = drag;
	}

	public void moveLeft(int moveSpeed) {
		layerX -= moveSpeed;
		changed = true;
	}

	public void moveRight(int moveSpeed) {
		layerX += moveSpeed;
		changed = true;
	}

	public void moveUp(int moveSpeed) {
		layerY -= moveSpeed;
		changed = true;
	}

	public void moveDown(int moveSpeed) {
		layerY += moveSpeed;
		changed = true;
	}

	public void setTopPriority() {
		priority = 0;
		changed = true;
	}

	public void setMoveable(boolean moveable) {
		this.moveable = moveable;
		changed = true;
	}

	// =======================================================Getters=============================================//
	public boolean isChanged() {
		return changed;
	}

	public int getLayerX() {
		return layerX;
	}

	public int getLayerY() {
		return layerY;
	}

	public int getLayerWidth() {
		return layerWidth;
	}

	public int getLayerHeight() {
		return layerHeight;
	}

	public int getXRightBound() {
		return rightXBound;
	}

	public int getYBottomBound() {
		return bottomYBound;
	}

	public int getOriginX() {
		return xOrigin;
	}

	public int getOriginY() {
		return yOrigin;
	}

	public int getPriority() {
		return priority;
	}

	public String getName() {
		return name;
	}

	public boolean getRenderCherno() {
		return renderCherno;
	}

	public boolean getRenderDefault() {
		return renderDefault;
	}

	public boolean isDragging() {

		return dragging;
	}

	public BufferedImage getImage() {
		return image;
	}

	public int getColor() {
		return color;
	}

	public int getDefaultColor() {

		return defaultColor;
	}

	public boolean getMoveable() {
		return moveable;
	}

	public void getAllLayerInformation() {
		System.out.println("LayerX: " + layerX + " LayerY: " + layerY + " LayerWidth: " + layerWidth + " LayerHeight: "
				+ layerHeight + " Priority: " + priority + " Name: " + name);
	}

	public void move(int moveSpeed) {

		System.out.println("MouseX " + Mouse.getX() + " XOffset " + xOffset);
		if (this.xOffset > Mouse.getX()) {

			moveLeft(mouseSpeed);
		} else if (this.xOffset < Mouse.getX()) {

			moveRight(mouseSpeed);
		}
		if (this.yOffset > Mouse.getY()) {
			moveUp(mouseSpeed);
		} else if (this.yOffset < Mouse.getY()) {
			moveDown(mouseSpeed);
		}
		this.xOffset = Mouse.getX();
		this.yOffset = Mouse.getY();
	}

	public boolean mouseInBounds(int mouseX, int mouseY, Screen screen) {

		boolean xClick = false;
		boolean yClick = false;

		if (mouseX >= getLayerX() && mouseX <= getXRightBound()) {
			xClick = true;
		}
		if (mouseY >= getLayerY() && mouseY <= getYBottomBound()) {
			yClick = true;
		}

		if (xClick && yClick) {
			changed = true;
			return true;
		} else
			return false;
	}

	public void update(Screen screen) {
		setBounds();
		if (isDragging()) {

		}
	}

	public void render(Graphics graphics) {

		if (getRenderDefault()) {
			screen.createFilledRect(getLayerX(), getLayerY(), getLayerWidth(), getLayerHeight(), getColor());
			System.out.println("WHEN DEFAULT");
			setRenderDefault(false);
			setRenderCherno(false);
		}
		if (getRenderCherno()) {
			chernoRender.drawRect(layerX, layerY, layerWidth, layerHeight, 0xff0000);
			int[] pixelsVert = ((DataBufferInt) getImage().getRaster().getDataBuffer()).getData();
			for (int i = 0; i < chernoRender.pixels.length; i++) {
				pixelsVert[i] = chernoRender.pixels[i];
			}
			graphics.drawImage(getImage(), getLayerX(), getLayerY(), getLayerWidth(), getLayerHeight(), null);
			graphics.dispose();
			System.out.println("WHEN CHERNO111111" + getLayerX());

		}
	}

	public void renderDefault() {

		screen.createFilledRect(getLayerX(), getLayerY(), getLayerWidth(), getLayerHeight(), 0x000000);

		System.out.println("WHEN DEFAULT");
	}

	public void renderCherno(Graphics graphics) {

		//setColor(0xFF0000, screen);
		chernoRender.drawRect(layerX, layerY, layerWidth, layerHeight, 0xff0000);

		int[] pixelsVert = ((DataBufferInt) getImage().getRaster().getDataBuffer()).getData();
		for (int i = 0; i < chernoRender.pixels.length; i++) {
			pixelsVert[i] = chernoRender.pixels[i];
		}
		graphics.drawImage(getImage(), getLayerX(), getLayerY(), getLayerWidth(), getLayerHeight(), null);
		graphics.dispose();
		System.out.println("WHEN CHERNO" + getLayerX());

	}

}
