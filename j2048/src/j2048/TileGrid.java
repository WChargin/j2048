package j2048;

import java.util.HashMap;
import java.util.HashSet;
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
	 * Finds the location of the tile in the grid.
	 * 
	 * @param tile
	 *            the tile to find
	 * @return the location of the tile, or {@code null} if the tile is not in
	 *         the grid
	 * @throws IllegalArgumentException
	 *             if {@code tile == null}
	 */
	public BoardLocation find(Tile tile) throws IllegalArgumentException {
		if (tile == null) {
			throw new IllegalArgumentException("tile must not be null");
		}
		for (Map.Entry<BoardLocation, Tile> entry : tiles.entrySet()) {
			if (entry.getValue() == tile) {
				return entry.getKey();
			}
		}
		return null;
	}

	/**
	 * Gets a set of all occupied locations in this tile grid. Modifications to
	 * the returned set will not affect this grid.
	 * 
	 * @return a set of all occupied locations in this tile grid
	 */
	public Set<BoardLocation> getAllOccupiedLocations() {
		return new HashSet<>(tiles.keySet());
	}

	/**
	 * Gets a set of all unoccupied locations in this tile grid. Modifications
	 * to the returned set will not affect this grid.
	 * 
	 * @return a set of all unoccupied locations in this tile grid
	 */
	public Set<BoardLocation> getAllUnoccupiedLocations() {
		Set<BoardLocation> unoccupied = new HashSet<>();
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
