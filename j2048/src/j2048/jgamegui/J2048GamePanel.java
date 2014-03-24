package j2048.jgamegui;

import java.awt.Color;
import java.awt.Font;

import jgame.GContainer;
import jgame.GMessage;

/**
 * The main view for a game of 2048.
 * 
 * @author William Chargin
 * 
 */
public class J2048GamePanel extends GContainer {

	/**
	 * The message representing the user's current score.
	 */
	private final GMessage scoreValue;

	/**
	 * The message representing the user's best score.
	 */
	private final GMessage bestValue;

	/**
	 * The grid used in this game.
	 */
	private final J2048GridPanel grid;

	public J2048GamePanel() {
		setSize(500, 535);

		final GMessage scoreLabel = createLabelMessage("Score");
		addAt(scoreLabel, 25, 0);
		scoreValue = createScoreMessage(0);
		addAt(scoreValue, 125, 0);

		final GMessage bestLabel = createLabelMessage("Best");
		addAt(bestLabel, 275, 0);
		bestValue = createScoreMessage(0);
		addAt(bestValue, 375, 0);

		grid = new J2048GridPanel();
		grid.setAnchorTopLeft();
		addAt(grid, 0, 35);
	}

	private GMessage createScoreMessage(int value) {
		final GMessage score = new GMessage();
		score.setColor(Color.WHITE);
		score.setText(Integer.toString(value));
		score.setSize(100, 50);
		score.setFontSize(24);
		score.setFontStyle(Font.BOLD);
		score.setAlignmentX(0);
		score.setAlignmentY(0.5);
		score.setAnchorTopLeft();
		return score;
	}

	private static GMessage createLabelMessage(String text) {
		final GMessage label = new GMessage();
		label.setColor(J2048.LIGHT_COLOR);
		label.setText(text.toUpperCase());
		label.setSize(90, 50);
		label.setFontSize(18);
		label.setFontStyle(Font.BOLD);
		label.setAlignmentX(1);
		label.setAlignmentY(0.5);
		label.setAnchorTopLeft();
		return label;
	}

}
