package bg.Layers;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import bg.Keyboard;
import bg.Mouse;
import bg.File.BGFiles;
import bg.Screen.Screen;
import bg.Sprite.Sprite;

public class Container {

	public List<Layer> layers = new ArrayList<Layer>();
	private List<Integer> priorities = new ArrayList<Integer>();
	public List<Layer> layersRender = new ArrayList<Layer>();
	public Layer focusLayer = null;

	private Screen screen;

	private int priority = 0;

	public Container(Screen screen) {
		
		this.screen = screen;
	}
	
	public void addLayer(int layerX, int layerY, int layerWidth, int layerHeight, int color, String name,
			boolean moveable, Sprite sprite) {

		priority = layers.size();
		priorities.add(priority);
		Layer layer = new Layer(layerX, layerY, layerWidth, layerHeight, priority, name, color, screen, moveable);
		layer.createLayerImage(sprite);
		layer.setRenderDefault(true);
		layers.add(layer);	
	}
	public List<Layer> sortLayers(List<Layer> layers, int amountToSort) {
		Layer layer = null;// layer is used as a placement object to switch values and then is switched to
		// the final value at the end layersClicked.get(0)
		int layerCount = 0;
		for (int highlightLayer = 0; highlightLayer < layers.size(); highlightLayer++) {

			for (int compareTo = layerCount; compareTo < layers.size() - 1; compareTo++) {
				if (layers.get(layerCount).getPriority() < layers.get(compareTo + 1).getPriority()) {
					layer = layers.get(layerCount);
					layers.set(layerCount, layers.get(compareTo + 1));
					layers.set(compareTo + 1, layer);
				}
			}
			layerCount++;
		}
		return layers;
	}

//	public void mouseInBounds(int mouseX, int mouseY) {
//		List<Layer> layersInBounds = new ArrayList<Layer>();
//
//		for (int i = 0; i < layers.size(); i++) {
//			if (layers.get(i).mouseInBounds(mouseX, mouseY, screen)) {
//				layersInBounds.add(layers.get(i));
//			}
//		}
//		focusLayer = layersInBounds.get(layersInBounds.size() - 1);	
//	}
	public Layer getLayerOnMouse() {

		List<Layer> layersClicked = new ArrayList<Layer>();

		for (int i = 0; i < layers.size(); i++) {
			if (layers.get(i).mouseInBounds(Mouse.getX(), Mouse.getY(), screen)) {

				layersClicked.add(layers.get(i));
			}
		}
		if (layersClicked.size() > 0) {

			layersClicked = sortLayers(layersClicked, layersClicked.size());
			setPrioLayerToTop(layersClicked.get(layersClicked.size() - 1));
			// focusLayer = layersClicked.get(layersClicked.size() - 1);
			return layersClicked.get(layersClicked.size() - 1);

		} else
			return null;
	}
	public void setPrioLayerToTop(Layer layer) {// layer parameter is the top priority, this method is used to set
												// layer to top priority -- shifts other layers values
		Layer placeLayer = null;

		placeLayer = layers.remove(layer.getPriority());

		layers.add(0, placeLayer);

		for (int l = 0; l < layer.getPriority(); l++) {
			layers.get(l).incrementPriority();
		}
		layer.setTopPriority();
	}

	public Layer getTopPriority() {
		return layers.get(0);
	}

//	public void saveFile() {
//		if (inputKey.k) {
//			files.createDatabase(focusLayer.chernoRender.pixels);
//		}
//	}

	public void layersUpdate() {

		layersRender.clear();
		for (int i = 0; i < layers.size(); i++) {
			layers.get(i).update(screen);
			if (!layers.get(i).isChanged()) {
				layersRender.remove(layers.get(i));
				layers.get(i).setRenderCherno(false);

			} else if (layers.get(i).isChanged()) {

				layersRender.add(layers.get(i));
			}
		}
	}

	public void layersInformation(List<Layer> layers) {
		for (int i = 0; i < layers.size(); i++) {
			System.out.println("Size = " + layers.size() + " Layer Information:");
			layers.get(i).getAllLayerInformation();
		}
	}
}
