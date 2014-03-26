package j2048;

public class SampleBadLogic implements TurnPerformer {

	@Override
	public boolean turn(Direction direction, TileGameContext context) {
		// This is an example of incorrect game logic.
		//
		// Instead of doing the correct action, this method doubles the value of
		// any tiles that are not on the edge in the direction the user pressed.
		//
		// For example, if the user presses the right arrow, then all tiles
		// except those on the right edge will double in value.
		//
		// This is obviously not the desired action.
		final TileGrid grid = context.getGrid();
		for (BoardLocation loc : grid.getAllOccupiedLocations()) {
			Tile here = grid.at(loc);
			if (loc.hasAdjacentLocation(direction)) {
				here.setValue(here.getValue() * 2);
			}
		}
		return true;
	}

}
