package rules;

import constants.GameState;
import constants.Tile;
import game.Board;
import game.Cell;
import game.CellCollection;

public class NormalRules implements GameRules {

	@Override
	public boolean validateMove(Board board, Cell cell, Tile player) {
		if (!cell.isValid())
			return false;
		if (board.getValue(cell) != Tile.Empty)
			return false;
		CellCollection possibilites = cellsToFlip(board, cell, player);
		// Special Rule- if no flips occur, the move fails.
		if (possibilites.size() == 0) {
			return false;
		}
		return true;
	}

	@Override
	public CellCollection cellsToFlip(Board b, Cell cell, Tile player) {
		// Space would be free later
		CellCollection ans = new CellCollection();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				cellsToFlipInOneDirection(ans, b, cell, player, i, j);
			}
		}
		return ans;
	}

	@Override
	public GameState gameOver(Board board) {
		int dimension = board.getDimension();
		CellCollection movesX = allPossibleMoves(board, Tile.X);
		CellCollection movesO = allPossibleMoves(board, Tile.O);
		if (board.getNumTaken() != dimension * dimension && (movesX.size() != 0 || movesO.size() != 0)) {
			// The game is on.
			return GameState.playing;
		}
		// Else the game is over. check whose the winner,
		Tile currentValue;
		int countX = 0, countO = 0;
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				currentValue = board.getValue(new Cell(i, j));
				if (currentValue == Tile.X) {
					countX++;
				} else if (currentValue == Tile.O) {
					countO++;
				}
			}
		}
		if (countX > countO) {
			return GameState.xWon;
		} else if (countO > countX) {
			return GameState.oWon;
		}
		return GameState.draw;
	}

	@Override
	public CellCollection allPossibleMoves(Board b, Tile player) {
		Tile[][] board = b.getBoard();
		int dimension = b.getDimension();
		// Space will be freed later on
		CellCollection possibilites = new CellCollection();
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				if (board[i][j] != Tile.Empty) {
					continue;
				}
				if (validateMove(b, new Cell(i, j), player)) {
					possibilites.add(new Cell(i, j));
				}
			}
		}
		return possibilites;
	}

	private void cellsToFlipInOneDirection(CellCollection ans, Board board, Cell cell, Tile player, int deltaRow,
			int deltaColumn) {
		int i, j, a, c;
		int row = cell.getRow(), column = cell.getColumn();
		int dimension = board.getDimension();
		Tile current;
		Tile[][] b = board.getBoard();
		for (i = row + deltaRow, j = column + deltaColumn; i >= 0 && j >= 0 && i < dimension
				&& j < dimension; i += deltaRow, j += deltaColumn) {
			current = b[i][j];
			if (current == Tile.Empty) {
				break;
			} else if (current == player) {
				for (a = i - deltaRow, c = j - deltaColumn; a != row || c != column; a -= deltaRow, c -= deltaColumn) {
					ans.add(new Cell(a, c));
				}
				break;
			}
		}
	}
}
