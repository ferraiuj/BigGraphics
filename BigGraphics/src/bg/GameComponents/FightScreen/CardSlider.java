package bg.GameComponents.FightScreen;

import java.awt.Frame;
import java.awt.Graphics;

import javax.swing.JFrame;

import bg.Keyboard;
import bg.MouseTranslate;
import bg.File.BGFiles;
import bg.Layers.LayerActions;
import bg.Layers.Container;
import bg.Layers.ContainerRender;
import bg.Screen.Screen;
import bg.Sprite.Sprite;

//for all intents and purposes this is a container so think of it like that
public class CardSlider {

	private Screen screen;
	private Container layerManage;
	private Sprite sprite;
	private ContainerRender layerListen;
	private LayerActions layerActions;

	public CardSlider(Screen screen, Frame frame) {
		
		this.screen = screen;
		sprite = Sprite.player;
		layerManage = new Container(screen);
		layerListen = new ContainerRender(0, 0, screen, frame);
		layerActions = new LayerActions(screen, layerManage);
		layerManage.addLayer(0, screen.getHeight() - 301, screen.getWidth() - 1, 300, 0x0015FF, "Card Slider", false, sprite);
	}

	public void update(Keyboard key) {
	
		
	}	

	public void render() {
		
		layerListen.renderLayerByIndex(layerManage.layersRender, 0);
		
	}
}
