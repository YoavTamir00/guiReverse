package graphics;

import game.Cell;

// All the functions the GameFlow needs to do on the gui.
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
