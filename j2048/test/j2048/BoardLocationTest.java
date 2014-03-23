package j2048;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class BoardLocationTest {

	@Test
	public void testBoardLocation() {
		final int size = BoardLocation.BOARD_SIZE;
		final int range = 2 * size;
		for (int i = -range; i < range; i++) {
			for (int j = -range; j < range; j++) {
				if (i >= 0 && j >= 0 && i < size && j < size) {
					new BoardLocation(i, j);
				} else {
					try {
						fail("Constructed out-of-range location at "
								+ new BoardLocation(i, j));
					} catch (Exception e) {
						// BoardLocation constructor threw an exception.
						// Test passed.
					}
				}
			}
		}
	}

	@Test
	public void testEqualsObject() {
		for (int i1 = 0; i1 < BoardLocation.BOARD_SIZE; i1++) {
			for (int j1 = 0; j1 < BoardLocation.BOARD_SIZE; j1++) {
				final BoardLocation loc1 = new BoardLocation(i1, j1);
				assertTrue(loc1.equals(loc1));
				assertFalse(loc1.equals(null));

				// Loop over all other squares to find all combinations.
				// Yes, this is O(n^4), but n = BOARD_SIZE is small.
				for (int i2 = 0; i2 < BoardLocation.BOARD_SIZE; i2++) {
					for (int j2 = 0; j2 < BoardLocation.BOARD_SIZE; j2++) {
						final BoardLocation loc2 = new BoardLocation(i2, j2);

						assertEquals(i1 == i2 && j1 == j2, loc1.equals(loc2));
						assertTrue(loc1.equals(loc2) == loc2.equals(loc1));
					}
				}
			}
		}
	}

	@Test
	public void testGetAdjacentLocation() {
		final Direction[] directions = Direction.values();
		for (int i1 = 0; i1 < BoardLocation.BOARD_SIZE; i1++) {
			for (int j1 = 0; j1 < BoardLocation.BOARD_SIZE; j1++) {
				final BoardLocation loc = new BoardLocation(i1, j1);
				for (Direction dir : directions) {
					// Manually compute it to be sure.
					int i2, j2;
					switch (dir) {
					case NORTH:
						i2 = i1;
						j2 = j1 - 1;
						break;
					case EAST:
						i2 = i1 + 1;
						j2 = j1;
						break;
					case SOUTH:
						i2 = i1;
						j2 = j1 + 1;
						break;
					case WEST:
						i2 = i1 - 1;
						j2 = j1;
						break;
					default:
						throw new AssertionError("Unexpected direction " + dir);
					}

					BoardLocation adjacent = loc.getAdjacentLocation(dir);
					if (adjacent == null) {
						// Make sure construction actually would have failed.
						try {
							fail("The getAdjacentLocation method returned null instead of "
									+ new BoardLocation(i2, j2));
						} catch (Exception e) {
							// Constructor failed.
							// Test passed.
						}
					} else {
						assertEquals(i2, adjacent.getX());
						assertEquals(j2, adjacent.getY());
					}
				}
			}
		}
	}

	@Test
	public void testGetXY() {
		for (int i = 0; i < BoardLocation.BOARD_SIZE; i++) {
			for (int j = 0; j < BoardLocation.BOARD_SIZE; j++) {
				BoardLocation loc = new BoardLocation(i, j);
				assertEquals(i, loc.getX());
				assertEquals(j, loc.getY());
			}
		}
	}

	@Test
	public void testHasAdjacentLocation() {
		final Direction[] directions = Direction.values();
		for (int i1 = 0; i1 < BoardLocation.BOARD_SIZE; i1++) {
			for (int j1 = 0; j1 < BoardLocation.BOARD_SIZE; j1++) {
				final BoardLocation loc = new BoardLocation(i1, j1);
				for (Direction dir : directions) {
					// Manually compute it to be sure.
					int i2, j2;
					switch (dir) {
					case NORTH:
						i2 = i1;
						j2 = j1 - 1;
						break;
					case EAST:
						i2 = i1 + 1;
						j2 = j1;
						break;
					case SOUTH:
						i2 = i1;
						j2 = j1 + 1;
						break;
					case WEST:
						i2 = i1 - 1;
						j2 = j1;
						break;
					default:
						throw new AssertionError("Unexpected direction " + dir);
					}

					final boolean hasAdjacent = loc.hasAdjacentLocation(dir);
					if (hasAdjacent) {
						// Create to make sure construction doesn't fail.
						new BoardLocation(i2, j2);
					} else {
						// Make sure construction actually would have failed.
						try {
							fail("The hasAdjacentLocation method returned false even though "
									+ new BoardLocation(i2, j2)
									+ " is a valid location");
						} catch (Exception e) {
							// Constructor failed.
							// Test passed.
						}
					}
				}
			}
		}
	}

	@Test
	public void testHashCode() {
		for (int i = 0; i < BoardLocation.BOARD_SIZE; i++) {
			for (int j = 0; j < BoardLocation.BOARD_SIZE; j++) {
				final BoardLocation loc1 = new BoardLocation(i, j);
				final int hc1 = loc1.hashCode();

				for (int count = 0; count < 1000; count++) {
					final BoardLocation loc2 = new BoardLocation(i, j);

					assertEquals(hc1, loc1.hashCode());
					assertEquals(loc1.hashCode(), loc2.hashCode());
				}
			}
		}
	}

	@Test
	public void testGetAllAdjacentLocations() {
		final Direction[] directions = Direction.values();
		for (int i = 0; i < BoardLocation.BOARD_SIZE; i++) {
			for (int j = 0; j < BoardLocation.BOARD_SIZE; j++) {
				final BoardLocation loc = new BoardLocation(i, j);
				final List<BoardLocation> adj = loc.getAllAdjacentLocations();

				int count = 0;
				for (Direction dir : directions) {
					final BoardLocation here = loc.getAdjacentLocation(dir);
					if (here == null) {
						assertFalse(adj.contains(here));
					} else {
						assertTrue(adj.contains(here));
						count++;
					}
				}
				assertEquals(count, adj.size());
			}
		}
	}

}
