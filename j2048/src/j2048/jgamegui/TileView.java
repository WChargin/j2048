package j2048.jgamegui;

import j2048.Tile;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

import jgame.Context;
import jgame.GMessage;
import jgame.GObject;
import jgame.listener.FrameListener;

/**
 * A GUI component representing a {@link Tile} in a game of 2048.
 * 
 * @author William Chargin
 * 
 */
public class TileView extends GObject {

	/**
	 * The tile represented by this view.
	 */
	private final Tile tile;

	/**
	 * The message with the text of this tile.
	 */
	private final GMessage label;

	/**
	 * The colors for the tile values.
	 */
	private static final Color[] colors = new Color[11];
	static {
		final int[] hexes = { 0xeee4da, 0xede0c8, 0xf2b179, 0xf59563, 0xf67c5f,
				0xf65e3b, 0xedcf72, 0xedcc61, 0xedc850, 0xedc53f, 0xedc22e };
		if (hexes.length != colors.length) {
			throw new AssertionError(
					"hex values don't match colors (length mismatch)");
		}
		for (int i = 0; i < hexes.length; i++) {
			colors[i] = new Color(hexes[i]);
		}
	}

	/**
	 * Creates a view for the given tile.
	 * 
	 * @param tile
	 *            the tile to be represented in this view
	 */
	public TileView(Tile tile) {
		super();
		this.tile = tile;

		label = new GMessage();
		label.setAlignmentX(0.5);
		label.setAlignmentY(0.5);
		label.setFontStyle(Font.BOLD);
		label.setAnchorTopLeft();
		add(label);

		addListener(new FrameListener() {
			@Override
			public void invoke(GObject target, Context context) {
				updateLabel();
			}
		});
	}

	@Override
	public void paint(Graphics2D g) {
		int index = 0, power = 2;
		final int tileValue = tile.getValue();
		while (power < tileValue && index + 1 < colors.length) {
			power *= 2;
			index++;
		}
		g.setColor(colors[index]);

		RoundRectangle2D rr = new RoundRectangle2D.Double(0, 0, getWidth(),
				getHeight(), 3, 3);
		g.fill(rr);

		super.paint(g);
	}

	/**
	 * Updates the contents of the {@link #label} field to reflect the most
	 * recent values.
	 */
	private void updateLabel() {
		label.setSize(getWidth(), getHeight());

		final int tileValue = tile.getValue();
		label.setText(Integer.toString(tileValue));
		if (tileValue >= 8) {
			label.setColor(Color.WHITE);
		} else {
			label.setColor(J2048.MAIN_COLOR);
		}
		label.setFontSize(50);
		if (tileValue >= 100 && tileValue < 1000) {
			label.setFontSize(45);
		} else if (tileValue >= 1000) {
			label.setFontSize(35);
		}

	}
}
