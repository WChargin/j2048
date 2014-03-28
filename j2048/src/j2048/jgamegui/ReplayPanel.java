package j2048.jgamegui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import jgame.ButtonState;
import jgame.Context;
import jgame.GButton;
import jgame.GContainer;
import jgame.GMessage;
import jgame.GObject;
import jgame.GSprite;
import jgame.controller.AlphaTween;
import jgame.listener.ButtonListener;
import jgame.listener.DelayListener;

public class ReplayPanel extends GContainer {

	private Color tint;

	public ReplayPanel(Color c, String text, final ButtonListener onReplay) {
		setSize(550, 650);
		setAnchorTopLeft();
		tint = new Color(c.getRGB() & ~(0xFF << 24) | (int) (0.25 * 255) << 24,
				true);
		GMessage message = new GMessage(text);
		message.setFontSize(48);
		message.setFontStyle(Font.BOLD);
		message.setColor(J2048.TEXT_COLOR);
		addAtCenter(message);

		message.setWidth(getWidth());
		message.setAlignmentX(0.5);

		final GButton btnPlayAgain = new GButton();

		btnPlayAgain.setStateSprite(ButtonState.NONE,
				createStateSprite(J2048.BUTTON_COLOR));
		btnPlayAgain.setStateSprite(ButtonState.HOVERED,
				createStateSprite(J2048.BUTTON_COLOR.brighter()));
		btnPlayAgain.setStateSprite(ButtonState.PRESSED,
				createStateSprite(J2048.BUTTON_COLOR.darker()));
		btnPlayAgain.setSize(160, 40);

		GMessage again = new GMessage("Play Again");
		again.setColor(J2048.LIGHT_TEXT);
		again.setFontSize(18);
		again.setFontStyle(Font.BOLD);
		again.setSize(160, 40);
		again.setAlignmentX(0.5);
		again.setAlignmentY(0.5);
		btnPlayAgain.addAtCenter(again);

		addAtCenter(btnPlayAgain);
		btnPlayAgain.setY(btnPlayAgain.getY() * 1.25);

		btnPlayAgain.addListener(onReplay);
		btnPlayAgain.addListener(new ButtonListener() {

			@Override
			public void mouseClicked(Context context) {
				btnPlayAgain.removeListener(onReplay);
				btnPlayAgain.removeListener(this);

				addController(new AlphaTween(30, 1, 0));
				addListener(new DelayListener(30) {
					@Override
					public void invoke(GObject target, Context context) {
						removeSelf();
					}
				});
			}

		});
	}

	private static GSprite createStateSprite(Color c) {
		BufferedImage i = new BufferedImage(160, 40,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = (Graphics2D) i.getGraphics();
		g2d.setColor(c);
		antialias(g2d);

		RoundRectangle2D rr = new RoundRectangle2D.Double(0, 0, 160, 40, 3, 3);
		g2d.fill(rr);
		g2d.dispose();

		GSprite s = new GSprite(i);
		s.setPrimitive(s.new ShapePrimitive(rr));
		return s;
	}

	@Override
	public void paint(Graphics2D g) {
		g.setColor(tint);
		g.fillRect(0, 0, getIntWidth(), getIntHeight());
		super.paint(g);
	}

}
