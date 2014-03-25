package j2048.jgamegui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

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
	private final ScoreDisplay scoreValue;

	/**
	 * The message representing the user's best score.
	 */
	private final ScoreDisplay bestValue;

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

		addAt(scoreValue = new ScoreDisplay("Score"), 275, 10);
		addAt(bestValue = new ScoreDisplay("Best"), 275, 55);

		grid = new GridPanel();
		grid.setAnchorTopLeft();
		addAt(grid, 0, 100);
	}

	private static class ScoreDisplay extends GContainer {

		private final GMessage score;

		public ScoreDisplay(String title) {
			setAnchorTopLeft();
			setSize(150, 30);
			GMessage label = createLabelMessage(title);
			addAt(label, 0, 0);

			score = createScoreMessage(0);
			addAt(score, 100, 0);
		}

		@Override
		public void paint(Graphics2D g) {
			g.setColor(J2048.MAIN_COLOR);
			g.fillRoundRect(0, 0, getIntWidth(), getIntHeight(), 3, 3);
			super.paint(g);
		}

		public void setScore(int newScore) {
			score.setText(Integer.toString(newScore));
		}

		private static GMessage createScoreMessage(int value) {
			final GMessage score = new GMessage();
			score.setColor(Color.WHITE);
			score.setText(Integer.toString(value));
			score.setSize(100, 30);
			score.setFontSize(24);
			score.setFontStyle(Font.BOLD);
			score.setAlignmentX(0);
			score.setAlignmentY(0.5);
			score.setAnchorTopLeft();
			return score;
		}

		private static GMessage createLabelMessage(String text) {
			final GMessage label = new GMessage();
			label.setColor(J2048.LIGHT_TEXT);
			label.setText(text.toUpperCase());
			label.setSize(90, 30);
			label.setFontSize(18);
			label.setFontStyle(Font.BOLD);
			label.setAlignmentX(1);
			label.setAlignmentY(0.5);
			label.setAnchorTopLeft();
			return label;
		}

	}

}
