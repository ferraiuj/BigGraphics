package bg.Layers;

import java.awt.event.MouseEvent;

import bg.Keyboard;
import bg.Mouse;
import bg.Screen.Screen;

public class LayerActions {
	private Layer focusLayer;
	private Screen screen;
	private Container layers;

	public LayerActions(Screen screen, Container layers) {

		this.screen = screen;
		this.layers = layers;
	}

	public void setFocusLayer() {
		focusLayer = layers.focusLayer;
	}

	public void hoverMouse() {

		if (focusLayer != null && focusLayer.mouseInBounds(Mouse.getX(), Mouse.getY(), screen)) {

			// focusLayer.setColor(0xFFFFFF, screen);
			focusLayer.setRenderCherno(true);
			System.out.println("HOVER");
		} else if (focusLayer != null && !focusLayer.mouseInBounds(Mouse.getX(), Mouse.getY(), screen)) {
			focusLayer.setColor(focusLayer.getDefaultColor(), screen);
		}
	}

	public void pickUpLayer() {
		if (focusLayer != null && Mouse.getB() == 3) {

			// focusLayer.setColor(0xFF00FF, screen);
			focusLayer.setRenderDefault(true);
		}
	}

	public void move(Keyboard inputKey) {

		if (focusLayer != null) {
			focusLayer.setRenderCherno(true);
			if (inputKey.left) {
				focusLayer.moveLeft(1);
				System.out.println("move left");
			}
			if (inputKey.right) {

				focusLayer.moveRight(1);
			}
			if (inputKey.up) {

				focusLayer.moveUp(1);
			}
			if (inputKey.down) {

				focusLayer.moveDown(1);
			}
		}
	}

	public void dragLayer() {

		if (Mouse.drag()) {
			System.out.println("DRAGGING");
			if (focusLayer.getMoveable()) {
				focusLayer.setRenderCherno(true);

				int differenceX;
				int differenceY;
				differenceX = Mouse.getX() - focusLayer.getLayerWidth() / 2;
				differenceY = Mouse.getY() - focusLayer.getLayerHeight() / 2;

				focusLayer.setLayerX(differenceX); // highlight layers
				focusLayer.setLayerY(differenceY);
			}
		}
		// Layer dragLayer = getLayerOnMouse(Mouse.getX(), Mouse.getY());

	}

	public void dropLayer() {
		if (focusLayer != null && Mouse.getB() == MouseEvent.NOBUTTON) {
			focusLayer.setColor(0xFF0000, screen);
			// focusLayer.setRenderDefault(true);

			focusLayer.setRenderCherno(false);
			// focusLayer = null;
		}
		// focusLayer.setRenderDefault(true);
		//
	}

	public void update(Keyboard inputKey) {
		setFocusLayer();
		if (focusLayer != null) {

			move(inputKey);
			// pickUpLayer();
			dragLayer();
			hoverMouse();
		
		}
		
		// dropLayer();

	}

}
