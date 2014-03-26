package j2048;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

public class TileGridTest {

	@Test(expected = IllegalArgumentException.class)
	public void testAtNullLocation() {
		new TileGrid().at(null);
	}

	@Test
	public void testFind() {
		final TileGrid grid = new TileGrid();
		final Map<Tile, BoardLocation> correct = new HashMap<>();
		final Random r = new Random();
		for (int i = 0; i < 10000; i++) {
			Tile tile = new Tile();
			tile.setValue(4);
			BoardLocation location = new BoardLocation(
					r.nextInt(BoardLocation.BOARD_SIZE),
					r.nextInt(BoardLocation.BOARD_SIZE));
			Tile previous = grid.at(location);
			if (previous != null) {
				correct.remove(previous);
			}
			grid.put(location, tile);
			correct.put(tile, location);
			for (Map.Entry<Tile, BoardLocation> entry : correct.entrySet()) {
				assertEquals(entry.getValue(), grid.find(entry.getKey()));
			}
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFindNull() {
		new TileGrid().find(null);
	}

	@Test
	public void testGetAllOccupiedLocations() {
		TileGrid grid = new TileGrid();
		assertEquals(0, grid.getAllOccupiedLocations().size());

		Set<BoardLocation> occupied = new HashSet<>();
		for (int i = 0; i < BoardLocation.BOARD_SIZE; i++) {
			for (int j = 0; j < BoardLocation.BOARD_SIZE; j++) {
				if (Math.random() > 0.5) {
					BoardLocation loc = new BoardLocation(i, j);
					occupied.add(loc);
					grid.put(loc, new Tile());
				}
			}
		}
		for (int i = 0; i < BoardLocation.BOARD_SIZE; i++) {
			for (int j = 0; j < BoardLocation.BOARD_SIZE; j++) {
				if (Math.random() > 0.5) {
					BoardLocation loc = new BoardLocation(i, j);
					occupied.remove(loc);
					grid.remove(loc);
				}
			}
		}

		assertEquals(occupied, new HashSet<>(grid.getAllOccupiedLocations()));
	}

	@Test
	public void testGetAllUnoccupiedLocations() {
		TileGrid grid = new TileGrid();
		assertEquals(BoardLocation.BOARD_SIZE * BoardLocation.BOARD_SIZE, grid
				.getAllUnoccupiedLocations().size());

		Set<BoardLocation> unoccupied = new HashSet<>();
		for (int i = 0; i < BoardLocation.BOARD_SIZE; i++) {
			for (int j = 0; j < BoardLocation.BOARD_SIZE; j++) {
				BoardLocation loc = new BoardLocation(i, j);
				if (Math.random() > 0.5) {
					unoccupied.add(loc);
				} else {
					grid.put(loc, new Tile());
				}
			}
		}
		for (int i = 0; i < BoardLocation.BOARD_SIZE; i++) {
			for (int j = 0; j < BoardLocation.BOARD_SIZE; j++) {
				BoardLocation loc = new BoardLocation(i, j);
				if (Math.random() > 0.5) {
					unoccupied.remove(loc);
					grid.put(loc, new Tile());
				}
			}
		}

		assertEquals(unoccupied,
				new HashSet<>(grid.getAllUnoccupiedLocations()));
	}

	@Test
	public void testPutAndAt() {
		final TileGrid tg = new TileGrid();

		// repeat to test collisions, etc.
		for (int n = 0; n < 5; n++) {
			final Map<BoardLocation, Tile> correct = new HashMap<>();
			for (int i = 0; i < BoardLocation.BOARD_SIZE; i++) {
				for (int j = 0; j < BoardLocation.BOARD_SIZE; j++) {
					final BoardLocation loc = new BoardLocation(i, j);
					final Tile tile = new Tile();
					correct.put(loc, tile);
					tg.put(loc, tile);
				}
			}
			for (Map.Entry<BoardLocation, Tile> entry : correct.entrySet()) {
				assertEquals(entry.getValue(), tg.at(entry.getKey()));
			}
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPutNullLocation() {
		new TileGrid().put(null, new Tile());
	}

	@Test
	public void testPutNullTile() {
		final TileGrid tg = new TileGrid();
		for (int i = 0; i < BoardLocation.BOARD_SIZE; i++) {
			for (int j = 0; j < BoardLocation.BOARD_SIZE; j++) {
				try {
					final BoardLocation loc = new BoardLocation(i, j);
					tg.put(loc, null);
					fail("Putting a value at " + loc
							+ " did not throw an exception!");
				} catch (Exception e) {
					// good
				}
			}
		}
	}

	@Test
	public void testRemove() {
		final TileGrid tg = new TileGrid();
		final Map<BoardLocation, Tile> correct = new HashMap<>();
		for (int i = 0; i < BoardLocation.BOARD_SIZE; i++) {
			for (int j = 0; j < BoardLocation.BOARD_SIZE; j++) {
				final BoardLocation loc = new BoardLocation(i, j);
				final Tile tile = new Tile();
				correct.put(loc, tile);
				tg.put(loc, tile);
			}
		}
		for (Map.Entry<BoardLocation, Tile> entry : correct.entrySet()) {
			assertEquals(entry.getValue(), tg.remove(entry.getKey()));
			assertEquals(null, tg.remove(entry.getKey()));
		}
		try {
			tg.remove(null);
			fail("Removal of null did not throw an exception!");
		} catch (Exception e) {
			// good
		}
	}

	@Test
	public void testTileGrid() {
		new TileGrid(); // no exceptions please
	}
}
