package j2048;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A grid of tiles in a game of 2048.
 * 
 * @author William Chargin
 * 
 */
public class TileGrid {

	/**
	 * The map of board locations to the tile at a location.
	 */
	private final Map<BoardLocation, Tile> tiles = new HashMap<>();

	/**
	 * Creates a tile grid.
	 */
	public TileGrid() {
	}

	/**
	 * Gets the tile at the given location.
	 * 
	 * @param location
	 *            the location to query
	 * @return the tile at the given location, or {@code null} if no such tile
	 *         exists
	 * @throws IllegalArgumentException
	 *             if {@code location == null}
	 */
	public Tile at(BoardLocation location) throws IllegalArgumentException {
		if (location == null) {
			throw new IllegalArgumentException("location must not be null");
		}
		return tiles.get(location);
	}

	/**
	 * Gets a list of all occupied locations in this tile grid. Modifications to
	 * the returned list will not affect this grid.
	 * 
	 * @return a list of all occupied locations in this tile grid
	 */
	public List<BoardLocation> getAllOccupiedLocations() {
		return new ArrayList<>(tiles.keySet());
	}

	/**
	 * Gets a list of all unoccupied locations in this tile grid. Modifications
	 * to the returned list will not affect this grid.
	 * 
	 * @return a list of all unoccupied locations in this tile grid
	 */
	public List<BoardLocation> getAllUnoccupiedLocations() {
		List<BoardLocation> unoccupied = new ArrayList<>();
		Set<BoardLocation> occupied = tiles.keySet();
		for (int i = 0; i < BoardLocation.BOARD_SIZE; i++) {
			for (int j = 0; j < BoardLocation.BOARD_SIZE; j++) {
				BoardLocation loc = new BoardLocation(i, j);
				if (!occupied.contains(loc)) {
					unoccupied.add(loc);
				}
			}
		}
		return unoccupied;
	}

	/**
	 * Puts the given tile at the given location. Any previous tile at this
	 * location will be removed.
	 * 
	 * @param location
	 *            the location at which to place a tile
	 * @param tile
	 *            the tile to place
	 * @throws IllegalArgumentException
	 *             if {@code location == null} or {@code tile == null}
	 */
	public void put(BoardLocation location, Tile tile) {
		if (location == null) {
			throw new IllegalArgumentException("location must not be null");
		}
		if (tile == null) {
			throw new IllegalArgumentException("tile must not be null");
		}
		tiles.put(location, tile);
	}

	/**
	 * Removes the tile at the given location.
	 * 
	 * @param location
	 *            the location whose tile to remove
	 * @return the tile that was removed, or {@code null} if no tile was at the
	 *         given location
	 * @throws IllegalArgumentException
	 *             if {@code location == null}
	 */
	public Tile remove(BoardLocation location) throws IllegalArgumentException {
		if (location == null) {
			throw new IllegalArgumentException("location must not be null");
		}
		return tiles.remove(location);
	}

}
