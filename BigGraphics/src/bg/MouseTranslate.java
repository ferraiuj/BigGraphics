package bg;

import bg.Layers.Container;

public class MouseTranslate {

	private Container layers;

	private static int tempDragX = -1;
	private static int tempDragY = -1;
	private static int mouseX;
	private static int mouseY;

	public MouseTranslate(Container layers) {
		this.layers = layers;
	}

	public void dragMouse() {
		if (Mouse.drag()) {
			//layers.dragLayer();
		}
	}
	public void mousePress() {
		
		if (Mouse.getB() == 3) {		
				layers.getLayerOnMouse();		
		}
	}
	public void mouseEntered() {
		
	//	layers.getLayerOnMouse();	
		
	}
	public void mouseExited() {
		
		//layers.getLayerOnMouse(Mouse.getX(), Mouse.getY());	
		
	}
	public void mouseRelease() {

		if (Mouse.release()) {
			int tempRee;
			int calcX = Mouse.getX() - tempDragX;
			if (calcX < 0) {
				tempRee = tempDragX;
				tempDragX = Mouse.getX();
				mouseX = tempRee;
			}
			int calcY = Mouse.getY() - tempDragY;
			if (calcY < 0) {
				tempRee = tempDragY;
				tempDragY = Mouse.getY();
				mouseY = tempRee;
			}
			System.out.println("WE HAVE CLICKED !");
			//layers.addLayer(tempDragX, tempDragY, Math.abs(calcX), Math.abs(calcY), 0xFFFFFF, "Okay", true);
			if (Mouse.getB() == 1 && calcX != 0 && calcY != 0) {
				layers.addLayer(tempDragX, tempDragY, Math.abs(calcX), Math.abs(calcY), 0xFFFFFF, "Okay", true);
				
			} else if (Mouse.getB() == 3) {
				// layers.dropLayer();
				// layers.getLayerOnMouse(mouseX, mouseY).renderDefault();
				// layers.getLayerOnMouse(mouseX, mouseY).setActive(false);
				// layers.getLayerOnMouse(mouseX, mouseY).changed = false;
			}
		}
	}

	public void update() {	
		
		//mouseEntered();
		//dragMouse();
		//mousePress();
		//mouseRelease();
		
	}
}
