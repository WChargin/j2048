package j2048;

/**
 * The logic unit in a game of 2048. This controls the movement and combination
 * of tiles on the board.
 * 
 * @author William Chargin
 * 
 */
public interface TurnPerformer {

	/**
	 * Completes a movement in the given direction, if possible. Calling this
	 * method should perform all suitable tile movements, merges, and deletions.
	 * It should also increment the score if appropriate. If the user wins or
	 * loses the game, this should be reflected in the method. This method
	 * should also spawn a new tile at the end of each turn.
	 * 
	 * @param direction
	 *            the direction the user wants to move (i.e., the key pressed)
	 * @param context
	 *            the context for interaction with the game; this allows
	 *            movement of tiles, merges, score modifications, etc.
	 * @return {@code true} if the move was valid and has been completed, or
	 *         {@code false} if the move was invalid
	 */
	public boolean turn(Direction direction, TileGameContext context);

}
