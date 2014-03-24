package j2048.jgamegui;

import java.awt.Color;

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
	public static final Color LIGHT_COLOR = new Color(250, 248, 239);

	public J2048() {
		GRootContainer root = new GRootContainer(MAIN_COLOR);
		setRootContainer(root);
		root.add(new J2048GamePanel());
	}

	public static void main(String[] args) {
		new J2048().startGame("2048");
	}

}
