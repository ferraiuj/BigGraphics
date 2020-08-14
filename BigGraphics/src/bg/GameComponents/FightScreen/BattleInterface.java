package bg.GameComponents.FightScreen;

import java.awt.Frame;
import java.awt.Graphics;

import javax.swing.JFrame;

import bg.Keyboard;
import bg.File.BGFiles;
import bg.Layers.LayerActions;
import bg.Layers.Container;
import bg.Layers.ContainerRender;
import bg.Screen.Screen;

public class BattleInterface {

	public CardSlider cardContainer;
	public Hud hud;
	public Background background;
	public Stage stage;

	public BattleInterface(Screen screen, Frame frame) {
		
		background = new Background(screen, frame);
		hud = new Hud(screen, frame);
		cardContainer = new CardSlider(screen, frame);
		stage = new Stage(screen, frame);
	}

	public void update(Keyboard key) {
		
		//background.update(key);
		hud.update(key);
		stage.update(key);
		cardContainer.update(key);
	}

	public void render() {

		background.render();
		hud.render();
		stage.render();
		cardContainer.render();
	}
}
