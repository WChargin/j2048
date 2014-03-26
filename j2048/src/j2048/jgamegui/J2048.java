package j2048.jgamegui;

import java.awt.Color;

import jgame.GContainer;
import jgame.GRootContainer;
import jgame.Game;

/**
 * The driver class for a 2048 game.
 * 
 * @author William Chargin
 * 
 */
public class J2048 extends Game {

	public static final Color MAIN_COLOR = new Color(187, 173, 160);
	public static final Color TEXT_COLOR = new Color(119, 110, 101);
	public static final Color LIGHT_COLOR = new Color(250, 248, 239);
	public static final Color LIGHT_TEXT = new Color(238, 228, 218);

	public J2048() {
		setTargetFPS(60);

		GRootContainer root = new GRootContainer(LIGHT_COLOR);
		setRootContainer(root);

		GContainer container = new GContainer();
		GamePanel game = new GamePanel();
		container.setSize(game.getWidth() + 50, game.getHeight() + 50);
		container.addAtCenter(game);
		root.add(container);
	}

	public static void main(String[] args) {
		new J2048().startGame("2048");
	}

}
