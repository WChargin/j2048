package j2048.jgamegui;

import j2048.BoardLocation;
import j2048.Direction;
import j2048.Logic;
import j2048.Tile;
import j2048.TileGameContext;
import j2048.TileGrid;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Set;

import jgame.Context;
import jgame.GContainer;
import jgame.GMessage;
import jgame.GObject;
import jgame.controller.AlphaTween;
import jgame.listener.ButtonListener;
import jgame.listener.FrameListener;

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
	 * The tile grid used in this game.
	 */
	private final TileGrid gridData = new TileGrid();

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

	private TileGameContext tgc;

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

		final Runnable spawnTwo = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 2; i++) {
					Tile t = new Tile();
					t.setValue(2);
					final Set<BoardLocation> free = gridData
							.getAllUnoccupiedLocations();
					int index = (int) (Math.random() * free.size());
					Iterator<BoardLocation> it = free.iterator();
					for (int j = 0; j < index - 1; j++) {
						it.next();
					}
					tgc.addTile(t, it.next());
				}
			}
		};
		tgc = new TileGameContext() {

			private void doReplay(Color color, String message) {
				final AlphaTween in = new AlphaTween(30, 0, 1);
				final ReplayPanel panel = new ReplayPanel(color, message,
						new ButtonListener() {
							@Override
							public void mouseClicked(Context context) {
								for (BoardLocation loc : gridData
										.getAllOccupiedLocations()) {
									Tile t = gridData.at(loc);
									grid.removeTile(t);
									gridData.remove(loc);
								}
								spawnTwo.run();
							}
						});
				panel.setAlpha(0);
				panel.addController(in);
				addSibling(panel);
			}

			@Override
			public void addTile(Tile tile, BoardLocation location)
					throws IllegalArgumentException {
				if (tile == null) {
					throw new IllegalArgumentException("tile must not be null");
				}
				if (location == null) {
					throw new IllegalArgumentException(
							"location must not be null");
				}
				grid.addTileAt(tile, location);
				gridData.put(location, tile);
			}

			@Override
			public TileGrid getGrid() {
				return gridData;
			}

			@Override
			public int getScore() {
				return scoreValue.getScore();
			}

			@Override
			public int incrementScoreBy(int value) {
				int newScore = getScore() + value;
				setScore(newScore);
				return newScore;
			}

			@Override
			public void loseGame() {
				doReplay(Color.RED, "Game over!");
			}

			@Override
			public void mergeTiles(Tile target, Tile mover,
					Direction direction, int movementSteps, int newValue)
					throws IllegalArgumentException {
				if (target == null) {
					throw new IllegalArgumentException("tile must not be null");
				}
				if (mover == null) {
					throw new IllegalArgumentException("tile must not be null");
				}
				if (direction == null) {
					throw new IllegalArgumentException(
							"direction must not be null");
				}
				mover.setValue(newValue);
				grid.mergeTile(target, mover, direction, movementSteps,
						newValue);
				moveTile(mover, direction, movementSteps);
			}

			@Override
			public void moveTile(Tile tile, Direction direction, int count)
					throws IllegalArgumentException {
				if (tile == null) {
					throw new IllegalArgumentException("tile must not be null");
				}
				if (direction == null) {
					throw new IllegalArgumentException(
							"direction must not be null");
				}
				BoardLocation loc = gridData.find(tile);
				if (loc == null) {
					throw new IllegalArgumentException("tile must be in grid");
				}
				grid.moveTile(tile, direction, count);
				gridData.remove(loc);
				for (int i = 0; i < count; i++) {
					loc = loc.getAdjacentLocation(direction);
					if (loc == null) {
						throw new IllegalArgumentException(
								"Trying to move too far: " + count);
					}
				}
				gridData.put(loc, tile);
			}

			@Override
			public void setScore(int score) throws IllegalArgumentException {
				GamePanel.this.setScore(score);
			}

			@Override
			public void winGame() {
				doReplay(Color.GREEN, "You win!");
			}
		};
		spawnTwo.run();
		addListener(new FrameListener() {

			private int tick = 0;
			private Direction lastDir = null;

			@Override
			public void invoke(GObject target, Context context) {
				Direction dir = null;
				for (int key : context.getKeyCodesPressed()) {
					switch (key) {
					case KeyEvent.VK_LEFT:
						dir = Direction.WEST;
						break;
					case KeyEvent.VK_RIGHT:
						dir = Direction.EAST;
						break;
					case KeyEvent.VK_UP:
						dir = Direction.NORTH;
						break;
					case KeyEvent.VK_DOWN:
						dir = Direction.SOUTH;
						break;
					default:
						continue;
					}
				}
				if (dir == null) {
					lastDir = dir;
				} else if (dir == lastDir) {
					return;
				}
				lastDir = dir;
				if (dir != null && tick == 0) {
					new Logic().turn(dir, tgc);
					tick = GridPanel.TURN_DURATION;
				} else if (tick > 0) {
					tick--;
				}
			}
		});

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
