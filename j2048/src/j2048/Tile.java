package j2048;

/**
 * A tile in a game of 2048.
 * 
 * @author William Chargin
 * 
 */
public class Tile {

	/**
	 * The current value of this tile.
	 */
	private int value;

	/**
	 * Gets the current value of this tile.
	 * 
	 * @return the tile value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Sets the value of this tile.
	 * 
	 * @param value
	 *            the new tile value
	 */
	public void setValue(int value) {
		this.value = value;
	}

}
