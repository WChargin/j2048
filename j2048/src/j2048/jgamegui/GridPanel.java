package j2048.jgamegui;

import j2048.BoardLocation;
import j2048.Direction;
import j2048.Tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.util.HashMap;
import java.util.Map;

import jgame.Context;
import jgame.GContainer;
import jgame.GObject;
import jgame.controller.MovementTween;
import jgame.controller.ScaleTween;
import jgame.listener.DelayListener;

/**
 * A panel to display the grid of a 2048 game.
 * 
 * @author William Chargin
 * 
 */
public class GridPanel extends GContainer {

	/**
	 * The number of frames in a turn. This should be an even number.
	 */
	public static final int TURN_DURATION = 10;

	/**
	 * The gutter between grid cells.
	 */
	public static final int GUTTER = 15;

	/**
	 * The map of tiles to their corresponding views.
	 */
	private final Map<Tile, TileView> views = new HashMap<>();

	/**
	 * Creates a grid panel for the 2048 game.
	 */
	public GridPanel() {
		setSize(500, 500);
	}

	/**
	 * Adds a view for the given tile at the given location.
	 * 
	 * @param tile
	 *            the tile to display
	 * @param location
	 *            the location of the tile in the board
	 * @return the newly created {@link TileView}
	 */
	public TileView addTileAt(Tile tile, BoardLocation location) {
		final int side = BoardLocation.BOARD_SIZE;
		final double width = (getWidth() - ((side + 1) * GUTTER)) / side;
		final double height = (getHeight() - ((side + 1) * GUTTER)) / side;

		TileView view = new TileView(tile);
		view.setSize(width, height);

		final double x = GUTTER + (GUTTER + width) * location.getX() + (width)
				/ 2;
		final double y = GUTTER + (GUTTER + height) * location.getY()
				+ (height) / 2;
		addAt(view, x, y);

		view.setScale(0);
		view.addController(new ScaleTween(TURN_DURATION, 0, 1));

		views.put(tile, view);

		return view;
	}

	/**
	 * Performs a tile-move animation on the given tile, setting the tile's
	 * value to the given value when the animation has completed. Note that the
	 * other half of the merge must be removed manually.
	 * 
	 * @param t1
	 *            the tile to be merged
	 * @param t2
	 *            the tile to be deleted
	 * @param newValue
	 *            the new value of the tile
	 */
	public void mergeTile(final Tile t1, final int newValue, final Tile t2) {
		final TileView tileView = views.get(t1);
		final ScaleTween tween = new ScaleTween(TURN_DURATION / 2, 1.0, 1.2);
		tween.chain(new ScaleTween(TURN_DURATION / 2, 1.2, 1.0));
		tileView.addController(tween);
		tileView.addListener(new DelayListener(TURN_DURATION / 2) {
			@Override
			public void invoke(GObject target, Context context) {
				tileView.getTile().setValue(newValue);
				removeTile(t2);
			}
		});
	}

	/**
	 * Moves the given tile view one square in the given direction.
	 * 
	 * @param tile
	 *            the tile to move
	 * @param direction
	 *            the direction in which to move the tile
	 * @param count
	 */
	public void moveTile(Tile tile, Direction direction, int count) {
		final TileView tileView = views.get(tile);
		final double dx = (tileView.getWidth() + GUTTER) * count;
		final double dy = (tileView.getHeight() + GUTTER) * count;

		tileView.addController(new MovementTween(TURN_DURATION, dx
				* direction.getX(), dy * direction.getY()));
	}

	@Override
	public void paint(Graphics2D g) {
		g.setColor(J2048.MAIN_COLOR);
		g.fillRoundRect(0, 0, getIntWidth(), getIntHeight(), 6, 6);

		final int side = BoardLocation.BOARD_SIZE;
		final double width = (getWidth() - ((side + 1) * GUTTER)) / side;
		final double height = (getHeight() - ((side + 1) * GUTTER)) / side;
		g.setColor(new Color(228, 238, 218, 89));

		for (int i = 0; i < side; i++) {
			for (int j = 0; j < side; j++) {
				RoundRectangle2D rr = new RoundRectangle2D.Double(GUTTER
						+ (GUTTER + width) * i, GUTTER + (GUTTER + height) * j,
						width, height, TileView.CORNER_RADIUS,
						TileView.CORNER_RADIUS);
				g.fill(rr);
			}
		}
		super.paint(g);
	}

	/**
	 * Removes the given tile from the grid.
	 * 
	 * @param tile
	 *            the tile to remove
	 */
	public void removeTile(Tile tile) {
		final TileView view = views.remove(tile);
		if (view != null) {
			remove(view);
		}
	}

}
