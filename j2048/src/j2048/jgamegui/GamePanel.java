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
	 * A score display component with a label and a mutable value.
	 * 
	 * @author William Chargin
	 * 
	 */
	private static class ScoreDisplay extends GContainer {

		/**
		 * Creates a message to be used as a label for the score.
		 * 
		 * @param text
		 *            the text of the label
		 * @return the label message
		 */
		private static GMessage createLabelMessage(String text) {
			final GMessage label = new GMessage();
			label.setColor(J2048.LIGHT_TEXT);
			label.setText(text.toUpperCase());
			label.setSize(90, 30);
			label.setFontSize(16);
			label.setFontStyle(Font.BOLD);
			label.setAlignmentX(1);
			label.setAlignmentY(0.5);
			label.setAnchorTopLeft();
			return label;
		}

		/**
		 * Creates a message representing a score.
		 * 
		 * @param value
		 *            the initial value for the score message
		 * @return the score message
		 */
		private static GMessage createScoreMessage(int value) {
			final GMessage score = new GMessage();
			score.setColor(Color.WHITE);
			score.setText(Integer.toString(value));
			score.setSize(100, 30);
			score.setFontSize(22);
			score.setFontStyle(Font.BOLD);
			score.setAlignmentX(0);
			score.setAlignmentY(0.5);
			score.setAnchorTopLeft();
			return score;
		}

		/**
		 * The actual integer value of the score.
		 */
		private int scoreValue;

		/**
		 * The message displaying the score value.
		 */
		private final GMessage scoreMessage;

		/**
		 * Creates a {@code ScoreDisplay} initialized to the given title and a
		 * score of {@code 0}.
		 * 
		 * @param title
		 *            the title of this display
		 */
		public ScoreDisplay(String title) {
			setAnchorTopLeft();
			setSize(200, 30);
			GMessage label = createLabelMessage(title);
			addAt(label, 0, 0);

			scoreMessage = createScoreMessage(0);
			addAt(scoreMessage, 100, 0);
		}

		/**
		 * Gets the current score.
		 * 
		 * @return the current score
		 */
		public int getScore() {
			return scoreValue;
		}

		@Override
		public void paint(Graphics2D g) {
			g.setColor(J2048.MAIN_COLOR);
			g.fillRoundRect(0, 0, getIntWidth(), getIntHeight(), 3, 3);
			super.paint(g);
		}

		/**
		 * Sets the score displayed in this {@code ScoreDisplay}.
		 * 
		 * @param newScore
		 *            the new displayed score
		 */
		public void setScore(int newScore) {
			scoreValue = newScore;
			scoreMessage.setText(Integer.toString(scoreValue));
		}

	}

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

	/**
	 * Updates the "best score" display to reflect the new best score.
	 * 
	 * @param newBestScore
	 *            the new best score
	 */
	public void setBestScore(int newBestScore) {
		bestValue.setScore(newBestScore);
	}

	/**
	 * Updates the "score" display to reflect the new score.
	 * 
	 * @param newScore
	 *            the new score
	 */
	public void setScore(int newScore) {
		scoreValue.setScore(newScore);
		if (newScore > bestValue.getScore()) {
			setBestScore(newScore);
		}
	}

}
