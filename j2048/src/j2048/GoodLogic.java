package j2048;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GoodLogic implements TurnPerformer {

	private static final int WINNING_VALUE = 2048;

	private boolean isGameLost(TileGameContext context) {
		for (Direction direction : Direction.values()) {
			if (isMovePossible(direction, context)) {
				return false;
			}
		}
		return true;
	}

	private boolean isGameWon(TileGameContext context) {
		final TileGrid grid = context.getGrid();
		for (BoardLocation loc : grid.getAllOccupiedLocations()) {
			if (grid.at(loc).getValue() >= WINNING_VALUE) {
				return true;
			}
		}
		return false;
	}

	private boolean isMovePossible(Direction direction, TileGameContext context) {
		for (BoardLocation loc : context.getGrid().getAllOccupiedLocations()) {
			Tile here = context.getGrid().at(loc);
			if (loc.hasAdjacentLocation(direction)) {
				BoardLocation adjacent = loc.getAdjacentLocation(direction);
				Tile there = context.getGrid().at(adjacent);
				if (there == null || there.getValue() == here.getValue()) {
					// this tile could be moved or merged
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean turn(Direction direction, TileGameContext context) {
		if (!isMovePossible(direction, context)) {
			return false;
		}

		final TileGrid grid = context.getGrid();

		processTiles(direction, context, grid);

		spawnNewTile(context, grid);

		if (isGameWon(context)) {
			context.winGame();
		} else if (isGameLost(context)) {
			context.loseGame();
		}
		return true;
	}

	private void spawnNewTile(TileGameContext context, TileGrid grid) {
		Set<BoardLocation> open = grid.getAllUnoccupiedLocations();
		if (open.isEmpty()) {
			return;
		}

		int index = (int) (Math.random() * open.size());
		Iterator<BoardLocation> it = open.iterator();

		BoardLocation location = it.next();
		for (int i = 0; i < index && it.hasNext(); i++) {
			location = it.next();
		}

		Tile tile = new Tile();
		tile.setValue(2);
		context.addTile(tile, location);

	}

	private void processTiles(Direction direction, TileGameContext context,
			final TileGrid grid) {
		boolean movingHorizontally = (direction == Direction.EAST || direction == Direction.WEST);
		int movementDirection = (movingHorizontally ? direction.getX()
				: direction.getY());
		int destinationDirection = (movementDirection + 1) / 2;
		int destinationCoordinate = destinationDirection
				* (BoardLocation.BOARD_SIZE - 1);

		// movementDirection will be +1 for WEST/SOUTH, -1 for EAST/NORTH
		// destinationPole will be 1 for WEST/SOUTH, 0 for EAST/NORTH
		// destinationCoordinate will be 1 for WEST/SOUTH, BOARD_SIZE - 1 for
		// EAST/NORTH

		Set<Tile> processed = new HashSet<>();
		for (int i = 0; i < BoardLocation.BOARD_SIZE; i++) {
			// "z" represents (movingHorizontally ? "x" : "y")
			int z = destinationCoordinate - movementDirection * i;
			for (BoardLocation loc : grid.getAllOccupiedLocations()) {
				if ((movingHorizontally ? loc.getX() : loc.getY()) == z) {
					processTileAt(loc, direction, processed, context, grid);
				}
			}
		}
	}

	private void processTileAt(BoardLocation loc, Direction direction,
			Set<Tile> merged, TileGameContext context, final TileGrid grid) {

		final Tile here = grid.at(loc);

		Tile there = null;
		int movementSteps = 0;
		boolean merge = false;
		BoardLocation destination = loc;

		while (destination.hasAdjacentLocation(direction)) {
			final BoardLocation adj = destination
					.getAdjacentLocation(direction);
			there = grid.at(adj);

			if (there == null) {
				// No tile there. Can move.
				movementSteps++;
				destination = adj;
			} else if (there.getValue() == here.getValue()
					&& !merged.contains(there)) {
				merged.add(here);
				merged.add(there);
				// Unprocessed tile of same value there. Can merge.
				movementSteps++;
				merge = true;
				destination = adj;
				break;
			} else {
				// Tile of other value there. Stop.
				break;
			}
		}

		if (movementSteps == 0 && !merge) {
			// Nothing to do.
			return;
		}

		if (!merge) {
			// Just move.
			context.moveTile(here, direction, movementSteps);
		} else {
			// Merge.
			// Add one to movementSteps to eat the other tile.
			final int doubled = here.getValue() * 2;
			context.mergeTiles(there, here, direction, movementSteps, doubled);
			context.incrementScoreBy(doubled);
		}
	}
}
