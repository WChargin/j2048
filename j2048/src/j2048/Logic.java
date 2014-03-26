package j2048;

public class Logic implements TurnPerformer {

	@Override
	public boolean turn(Direction direction, TileGameContext context) {
		// TODO: Students should fill in the move logic here.
		// Below is an explanation of what the method should do.
		// (You can also get this documentation by hovering over the
		// word "turn" in "public void turn.")

		// Description:
		/**
		 * Completes a movement in the given direction, if possible. Calling
		 * this method should perform all suitable tile movements, merges, and
		 * deletions. It should also increment the score if appropriate. If the
		 * user wins or loses the game, this should be reflected in the method.
		 * This method should also spawn a new tile at the end of each turn.
		 * 
		 * @param direction
		 *            the direction the user wants to move (i.e., the key
		 *            pressed)
		 * @param context
		 *            the context for interaction with the game; this allows
		 *            movement of tiles, merges, score modifications, etc.
		 * @return {@code true} if the move was valid and has been completed, or
		 *         {@code false} if the move was invalid
		 */

		return false;
	}

}
