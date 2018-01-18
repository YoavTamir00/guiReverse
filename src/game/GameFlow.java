package game;

import constants.GameState;
import constants.Tile;
import graphics.GraphicsHandler;
import players.GamePlayer;
import rules.GameRules;

public class GameFlow {
	private GamePlayer p1, p2;
	private GameRules rules;
	private GraphicsHandler gui;

	public GameFlow(GamePlayer p1, GamePlayer p2, GameRules rules, GraphicsHandler gui) {
		this.p1 = p1;
		this.p2 = p2;
		this.rules = rules;
		this.gui = gui;
	}

	public void runGame(Board board) {
		// 'i' will be 1 if p1 is playing and will be -1 if p2 is playing.
		// If we want to switch the turn from X to O, all we have to do now is
		// multiply i by -1.
		int i;
		if (p1.getTile() == Tile.X) {
			i = 1;
		} else {
			i = -1;
		}

		GamePlayer currentPlayer;
		GameState done = rules.gameOver(board);
		// Game loop.
		while (done == GameState.playing) {
			// Figuring out who is playing right now.
			if (i == 1) {
				currentPlayer = p1;
				gui.setP1Playing(true);
			} else {
				currentPlayer = p2;
				gui.setP1Playing(false);
			}
			gui.setP1Score(board.countTile(p1.getTile()));
			gui.setP2Score(board.countTile(p2.getTile()));

			// Calculating the possible moves for this player
			CellCollection possible = rules.allPossibleMoves(board, currentPlayer.getTile());
			// no possible moves for the current player
			if (possible.size() == 0) {
				i *= -1;
				gui.noAvailableMoves(currentPlayer.getName());
				// check if the game is not over.
				done = rules.gameOver(board);
				if (done == GameState.playing) {
					continue;
				}
				// Game is over, break.
				break;
			}
			// Choose a move
			gui.printCurrentBoard();
			Cell move = currentPlayer.decideMove(board, possible);
			// if the move is valid, apply it.
			if (rules.validateMove(board, move, currentPlayer.getTile())) {
				CellCollection toFlip = rules.cellsToFlip(board, move, currentPlayer.getTile());
				board.changeCell(move, currentPlayer.getTile());
				board.flipAll(toFlip);
				i *= -1;
			} else {
				gui.illegalMoveAlert(move);
			}
			done = rules.gameOver(board);
		} // end of while loop
			// Printing winner/draw. done variable should hold that information.
		gui.setP1Score(board.countTile(p1.getTile()));
		gui.setP2Score(board.countTile(p2.getTile()));
		gui.printCurrentBoard();
		Tile winner = Tile.Empty;
		if (done == GameState.xWon) {
			winner = Tile.X;
		} else if (done == GameState.oWon) {
			winner = Tile.O;
		}
		if (winner == p1.getTile()) {
			gui.showWinner(p1.getName());
		} else if (winner == p2.getTile()) {
			gui.showWinner(p2.getName());
		} else {
			gui.draw();
		}
	}

}
