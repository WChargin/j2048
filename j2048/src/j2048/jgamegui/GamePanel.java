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
public class GamePanel extends GContainer {

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
	private final GridPanel grid;

	public GamePanel() {
		setSize(500, 600);

		final GMessage title = new GMessage("2048");
		title.setColor(J2048.TEXT_COLOR);
		title.setSize(250, 100);
		title.setAnchorTopLeft();
		title.setAlignmentX(0.5);
		title.setAlignmentY(0.5);
		title.setFontSize(72);
		title.setFontStyle(Font.BOLD);
		add(title);

		final GMessage scoreLabel = createLabelMessage("Score");
		addAt(scoreLabel, 300, 0);
		scoreValue = createScoreMessage(0);
		addAt(scoreValue, 400, 0);

		final GMessage bestLabel = createLabelMessage("Best");
		addAt(bestLabel, 300, 50);
		bestValue = createScoreMessage(0);
		addAt(bestValue, 400, 50);

		grid = new GridPanel();
		grid.setAnchorTopLeft();
		addAt(grid, 0, 100);
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
