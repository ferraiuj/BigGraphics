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

public class Stage {

	private Container layerManage;

	private Screen screen;
	private Sprite sprite;
	private ContainerRender layerListen;
	private LayerActions layerActions;

	public Stage(Screen screen, Frame frame) {
		this.screen = screen;
		sprite = Sprite.player;
		layerManage = new Container(screen);
		layerListen = new ContainerRender(0, 0, screen, frame);
		layerActions = new LayerActions(screen, layerManage);
		layerManage.addLayer(0, screen.getHeight() - 601, screen.getWidth() - 1, 300, 0xa8a032, "Stage", false, sprite);
	}

	public void update(Keyboard key) {

	}

	public void render() {

		layerListen.renderLayerByIndex(layerManage.layersRender, 0);
	}
}
