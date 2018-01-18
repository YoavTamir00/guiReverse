package graphics;

import constants.Tile;
import game.Cell;
import game.CellCollection;

public interface GraphicsHandler {

	void printCurrentBoard();

	void illegalMoveAlert(Cell cell);

	Cell pickMove(String name);

	void showWinner(String winner);

	void noAvailableMoves(String name);

	void draw();

	void mainMenu();

	void setP1Playing(boolean isP1);

	void setP1Score(int score);

	void setP2Score(int score);
}
