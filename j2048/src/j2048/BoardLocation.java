package j2048;

import java.util.ArrayList;
import java.util.List;

/**
 * A data structure representing a point on the grid. This point may or may not
 * contain a tile. This class is immutable.
 * 
 * @author William Chargin
 * 
 */
public final class BoardLocation {

	/**
	 * The side length of the grid.
	 */
	public static final int BOARD_SIZE = 4;

	/**
	 * The {@code x}-position of this location in the grid. The leftmost cells
	 * in the grid have {@code x}-position {@code 0}.
	 */
	private final int x;

	/**
	 * The {@code y}-position of this location in the grid. The topmost cells in
	 * the grid have {@code y}-position {@code 0}.
	 */
	private final int y;

	/**
	 * Creates a location with the given coordinates. The coordinates must be
	 * non-negative (greater than or equal to zero) and less than the board size
	 * of {@value #BOARD_SIZE}.
	 * 
	 * @param x
	 *            the {@code x}-position for this tile
	 * @param y
	 *            the {@code y}-position for this tile
	 * @throws IllegalArgumentException
	 *             if {@code x} or {@code y} is negative, or greater than or
	 *             equal to {@value #BOARD_SIZE}
	 */
	public BoardLocation(int x, int y) throws IllegalArgumentException {
		if (x < 0) {
			throw new IllegalArgumentException("x is negative: " + x);
		} else if (y < 0) {
			throw new IllegalArgumentException("y is negative: " + y);
		} else if (x >= BOARD_SIZE) {
			throw new IllegalArgumentException("x is too big: " + x);
		} else if (y >= BOARD_SIZE) {
			throw new IllegalArgumentException("y is too big: " + y);
		}
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardLocation other = (BoardLocation) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	/**
	 * Gets the location adjacent to this location in the given direction. If
	 * the location is invalid, this method will return {@code null}.
	 * 
	 * @param direction
	 *            the direction in which to get the adjacent location
	 * @return the adjacent location in the given direction, or {@code null} if
	 *         such a location would be invalid
	 */
	public BoardLocation getAdjacentLocation(Direction direction) {
		if (hasAdjacentLocation(direction)) {
			final int dx = direction.getX(), dy = direction.getY();
			final int x = this.x + dx, y = this.y + dy;
			return new BoardLocation(x, y);
		} else {
			return null;
		}
	}

	/**
	 * Gets a list of all valid locations adjacent to this location. This is the
	 * same as calling {@link #getAdjacentLocation(Direction)} for each
	 * {@link Direction}, and only taking the non-{@code null} values.
	 * 
	 * @return a (possibly empty) list containing all valid board locations
	 *         adjacent to this location
	 */
	public List<BoardLocation> getAllAdjacentLocations() {
		final Direction[] directions = Direction.values();
		final List<BoardLocation> result = new ArrayList<>();
		for (Direction d : directions) {
			BoardLocation adjacent = getAdjacentLocation(d);
			if (adjacent != null) {
				result.add(adjacent);
			}
		}
		return result;
	}

	/**
	 * Gets the {@code x}-coordinate of this location in the grid.
	 * 
	 * @return the {@code x}-coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gets the {@code y}-coordinate of this location in the grid.
	 * 
	 * @return the {@code y}-coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * Determines whether this location has a valid adjacent location in the
	 * given direction.
	 * 
	 * @param direction
	 *            the direction to check
	 * @return {@code true} if the location adjacent to this location in the
	 *         given direction is valid, or {@code false} if it is not
	 */
	public boolean hasAdjacentLocation(Direction direction) {
		final int dx = direction.getX(), dy = direction.getY();
		final int x = this.x + dx, y = this.y + dy;
		return x >= 0 && y >= 0 && x < BOARD_SIZE && y < BOARD_SIZE;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public String toString() {
		return String.format("(%s, %s)", x, y);
	}

}
