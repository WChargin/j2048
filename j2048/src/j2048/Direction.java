package j2048;

/**
 * The various directions on a board. This enum can be used for multiple
 * purposes: for example, it can indicate the direction of a move, or the
 * direction of adjacent tiles.
 * <p>
 * Each {@code Direction} is equipped with {@code x}- and {@code y}-multipliers.
 * These indicate the numerical orientation of the direction. For example, the
 * {@link #WEST} direction points in the negative {@code x} direction, so its
 * {@code x}-multiplier is {@code -1} and its {@code y}-multiplier is {@code 0}.
 * 
 * @author William Chargin
 * 
 */
public enum Direction {

	/**
	 * The north (negative-{@code y}) direction.
	 */
	NORTH(0, -1),

	/**
	 * The east (positive-{@code x}) direction.
	 */
	EAST(1, 0),

	/**
	 * The south (positive-{@code y}) direction.
	 */
	SOUTH(0, 1),

	/**
	 * The west (negative-{@code x}) direction.
	 */
	WEST(-1, 0);

	/**
	 * The {@code x}-multiplier for this direction.
	 */
	private final int x;

	/**
	 * The {@code y}-multiplier for this direction.
	 */
	private final int y;

	/**
	 * Creates the {@code Direction} with the given {@code x}- and {@code y}
	 * -multipliers.
	 * 
	 * @param x
	 *            the {@code x}-multiplier
	 * @param y
	 *            the {@code y}-multiplier
	 */
	private Direction(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Gets the {@code x}-multiplier for this direction.
	 * 
	 * @return the {@code x}-multiplier
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gets the {@code y}-multiplier for this direction.
	 * 
	 * @return the {@code y}-multiplier
	 */
	public int getY() {
		return y;
	}

}
