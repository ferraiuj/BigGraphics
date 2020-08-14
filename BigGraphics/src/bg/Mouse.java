package bg;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import bg.Layers.Layer;
import bg.Layers.Container;

public class Mouse implements MouseListener, MouseMotionListener {

	private static int mouseX = -1;
	private static int mouseY = -1;
	private static int tempDragX = -1;
	private static int tempDragY = -1;
	private static int mouseB = -1;
	private static boolean drag = false;
	private static boolean press = false;
	private static boolean release = false;
	private static boolean clicked = false;


	public Mouse() {
	
	}

	public static int getX() {
		return mouseX;
	}

	public static int getY() {
		return mouseY;
	}

	public static int getB() {
		return mouseB;
	}

	public static boolean drag() {
		return drag;
	}

	public static boolean press() {
		return press;
	}

	public static boolean release() {
		return release;
	}

	public static boolean clicked() {
		return clicked;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		//mouseB = e.getButton();
		
		release = false;
		if(mouseB == 3) {
			drag = true;		
			//layers.dragLayer();
			//layers.renderLayerByLayer((layers.mouseInLayerBounds(mouseX, mouseY)));
		}	
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		release = false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		//System.out.println("Mouse Pressed" + mouseX + " " + mouseY);
		clicked = true;
		//release = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();

	}

	@Override
	public void mouseExited(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();

	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		tempDragX = e.getX();
		tempDragY = e.getY();
		mouseB = e.getButton();
		press = true;
		release = false;
		if(mouseB == 3) {
			//layers.getLayerOnMouse(mouseX,  mouseY); 
			//layers.pickUpLayer();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		drag = false;
		press = false;
		release = true;
		int tempRee;
		int calcX = mouseX - tempDragX;
		if (calcX < 0) {
			tempRee = tempDragX;
			tempDragX = mouseX;
			mouseX = tempRee;
		}
		int calcY = mouseY - tempDragY;
		if (calcY < 0) {
			tempRee = tempDragY;
			tempDragY = mouseY;
			mouseY = tempRee;
		}
		if(mouseB == 1 && calcX != 0 && calcY != 0) {
			//layers.addLayer(tempDragX, tempDragY, Math.abs(calcX), Math.abs(calcY), 0xFFFFFF, "Okay", true);
		}else if (mouseB == 3) {
			//layers.dropLayer();	
			//layers.getLayerOnMouse(mouseX, mouseY).renderDefault();
			//layers.getLayerOnMouse(mouseX, mouseY).setActive(false);	
			//layers.getLayerOnMouse(mouseX, mouseY).changed = false;	
		}
		
		mouseB = MouseEvent.NOBUTTON;
		tempDragX = -1;
		tempDragY = -1;
	}
}
