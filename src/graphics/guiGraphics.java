package graphics;

import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

import GuiTools.GameUiManager;
import GuiTools.GuiManager;
import GuiTools.VisualSettings;
import constants.Tile;
import game.Board;
import game.Cell;
import game.CellCollection;

public class guiGraphics implements GraphicsHandler {
	private Board board;
	private GuiManager gui;
	private GameUiManager guiController;
	private static int row,col;
	public guiGraphics(Board gameBoard) {
		this.board = gameBoard;
	}

	@Override
	public void mainMenu() {
        gui = GuiManager.getInstance();
        guiController = gui.getGameScreen();
	}
	
	@Override
	public void illegalMoveAlert(Cell cell) {
		guiController.showAlert(cell.toString() + " is an illegal move.");
	}

	@Override
	public Cell pickMove(String name) {
		BiConsumer<Integer, Integer> moveListener = (x, y) -> {
			guiGraphics.row = x;
			guiGraphics.col = y;
		};
		guiController.setBoardClickListener(moveListener);
		while (row == 0) {
			try {
				TimeUnit.MILLISECONDS.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		guiController.setBoardClickListener(null);
		int x = row, y = col;
		row = 0;
		System.out.println(new Cell(x,y));
		return new Cell(x,y);
	}

	@Override
	public void showWinner(String winner) {
		guiController.showAlert("The winner is " + winner);
	}

	@Override
	public void noAvailableMoves(String name) {
		guiController.showAlert("Player " + name + " has no moves, So he loses his turn.");
	}

	@Override
	public void draw() {
		guiController.showAlert("The game ended in a draw.");
	}

	@Override
	public void printGameStarts(Tile startingPlayer) {
		setPlayingPlayer(startingPlayer);
	}


	@Override
	public void printCurrentBoard() {
		guiController.drawBoard(board.getBoard());
	}
	
	public void setPlayingPlayer(Tile player) {
		boolean isP1 = true;
		if (player == Tile.O) {
			isP1 = false;
		}
		guiController.setCurrentPlayer(isP1);
	}

}
