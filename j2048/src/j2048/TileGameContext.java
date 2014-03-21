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
	 * Ends the game immediately.
	 */
	public void endGame();

}
