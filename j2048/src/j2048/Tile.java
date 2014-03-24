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

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
