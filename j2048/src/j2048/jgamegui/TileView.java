package j2048.jgamegui;

import j2048.Tile;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

import jgame.GMessage;
import jgame.GObject;

public class TileView extends GObject {

	/**
	 * The tile represented by this view.
	 */
	private final Tile tile;

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

	public TileView(Tile tile) {
		super();
		this.tile = tile;

		GMessage message = new GMessage() {
			@Override
			public double getWidth() {
				return TileView.this.getWidth();
			}

			@Override
			public double getHeight() {
				return TileView.this.getHeight();
			}
		};
		final int tileValue = tile.getValue();
		message.setText(Integer.toString(tileValue));
		if (tileValue >= 8) {
			message.setColor(Color.WHITE);
		} else {
			message.setColor(J2048.MAIN_COLOR);
		}

		message.setFontSize(50);
		if (tileValue >= 100 && tileValue < 1000) {
			message.setFontSize(45);
		} else if (tileValue >= 1000) {
			message.setFontSize(35);
		}

		message.setAlignmentX(0.5);
		message.setAlignmentY(0.5);
		message.setFontStyle(Font.BOLD);
		addAtCenter(message);
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
}
