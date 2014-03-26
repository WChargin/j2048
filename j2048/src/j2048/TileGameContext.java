package j2048;

/**
 * An interface to allow the game logic to interact with the outside world. This
 * interface provides methods to manipulate the current score and to end the
 * game.
 * 
 * @author William Chargin
 * 
 */
public interface TileGameContext {

	/**
	 * Adds the given tile to the game at the given location.
	 * 
	 * @param tile
	 *            the tile to add
	 * @param location
	 *            the location at which to add the tile
	 * @throws IllegalArgumentException
	 *             if either argument is {@code null}
	 */
	public void addTile(Tile tile, BoardLocation location)
			throws IllegalArgumentException;

	/**
	 * Gets the grid for this game. Modifications to the grid are not advised;
	 * instead, use the mutator methods of this object.
	 * 
	 * @return the game grid
	 */
	public TileGrid getGrid();

	/**
	 * Gets the user's score in the game. This can be modified with
	 * {@link #incrementScoreBy(int)} or {@link #setScore(int)}.
	 * 
	 * @return the user's current score
	 */
	public int getScore();

	/**
	 * Adds the given number of points to the user's score. For example, if the
	 * user had a score of {@code 100} and this method were called with a
	 * parameter of {@code 10}, the user's score after this method completes
	 * would be {@code 110}.
	 * 
	 * @param value
	 *            the number of points by which to increase the score
	 * @throws IllegalArgumentException
	 *             if invoking this method would cause the user's score to
	 *             become negative
	 * @return the new score
	 */
	public int incrementScoreBy(int value);

	/**
	 * Causes the user to lose the game.
	 */
	public void loseGame();

	/**
	 * Merges the mover tile onto the target tile.
	 * 
	 * @param target
	 *            the tile that will remain after the merge is completed
	 * @param mover
	 *            the tile that will move onto the target tile
	 * @param direction
	 *            the direction of movement
	 * @param newValue
	 *            the new value of the {@code target} tile
	 * @throws IllegalArgumentException
	 *             if any argument is {@code null}
	 */
	public void mergeTiles(Tile target, Tile mover, Direction direction,
			int newValue) throws IllegalArgumentException;

	/**
	 * Moves the given tile in the given direction. If another tile is in the
	 * destination cell, it will be removed. If the provided movement is
	 * impossible, the method may throw an exception.
	 * 
	 * @param tile
	 *            the tile to move
	 * @param direction
	 *            the direction in which to move the tile
	 * @param count
	 *            the number of spaces to move the tile
	 * @throws IllegalArgumentException
	 *             if the tile cannot be moved in the given direction (or either
	 *             argument is {@code null})
	 */
	public void moveTile(Tile tile, Direction direction, int count)
			throws IllegalArgumentException;

	/**
	 * Sets the user's score to the given value.
	 * 
	 * @param score
	 *            the new score
	 * @throws IllegalArgumentException
	 *             if the provided score is negative
	 * @see #incrementScoreBy(int)
	 */
	public void setScore(int score) throws IllegalArgumentException;

	/**
	 * Causes the user to win the game.
	 */
	public void winGame();

}
