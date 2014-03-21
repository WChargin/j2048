package j2048;

/**
 * The various directions on a board. This enum can be used for multiple
 * purposes: for example, it can indicate the direction of a move, or the
 * direction of adjacent tiles.
 * 
 * @author William Chargin
 * 
 */
public enum Direction {

	/**
	 * The north (negative-y) direction.
	 */
	NORTH,

	/**
	 * The east (positive-x) direction.
	 */
	EAST,

	/**
	 * The south (positive-y) direction.
	 */
	SOUTH,

	/**
	 * The west (negative-x) direction.
	 */
	WEST;

}
