package j2048.jgamegui;

import j2048.BoardLocation;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

import jgame.GContainer;

/**
 * A panel to display the grid of a 2048 game.
 * 
 * @author William Chargin
 * 
 */
public class J2048GridPanel extends GContainer {

	public J2048GridPanel() {
		setSize(500, 500);
	}

	@Override
	public void paint(Graphics2D g) {
		final int gutter = 15;
		final int side = BoardLocation.BOARD_SIZE;
		final double width = (getWidth() - ((side + 1) * gutter)) / side;
		final double height = (getHeight() - ((side + 1) * gutter)) / side;
		final int radius = 3; // rounded corner radius

		g.setColor(new Color(228, 238, 218, 89));

		for (int i = 0; i < side; i++) {
			for (int j = 0; j < side; j++) {
				RoundRectangle2D rr = new RoundRectangle2D.Double(gutter
						+ (gutter + width) * i, gutter + (gutter + height) * j,
						width, height, radius, radius);
				g.fill(rr);
			}
		}
		super.paint(g);
	}

}
