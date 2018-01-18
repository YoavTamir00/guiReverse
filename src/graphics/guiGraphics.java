package graphics;

import game.Board;
import game.Cell;
import guiTools.GuiManager;
import guiTools.ReversiUi;

import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

/**
 * IMPORTANT COMMENT
 * This class is kind of a 'glue' layer between the GUI to the game.
 * It receives signal from the game about what to do, and knows how to transfer those
 * signals appropriately to the gui.
 *
 * @author ohad
 */
public class guiGraphics implements GraphicsHandler {
    private static int row = -1, col;
    private Board board;
    private GuiManager gui;
    private ReversiUi guiController;

    public guiGraphics(Board gameBoard) {
        this.board = gameBoard;
    }

    @Override
    public void mainMenu() {
        gui = GuiManager.getInstance();
        guiController = gui.getGameGuiManager();
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
        // Listen to the user's choice every 200 milliseconds;
        guiController.setBoardClickListener(moveListener);
        while (row == -1) {
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Stop listening(until this function will be called again)
        guiController.setBoardClickListener(null);
        int x = row, y = col;
        row = -1;
        return new Cell(x, y);
    }

    @Override
    public void showWinner(String winner) {
        guiController.showAlertAndExit("The winner is " + winner);
    }

    @Override
    public void noAvailableMoves(String name) {
        guiController.showAlert("Player " + name + " has no moves, So he loses his turn.");
    }

    @Override
    public void draw() {
        guiController.showAlertAndExit("The game ended in a draw.");
    }

    @Override
    public void printCurrentBoard() {
        guiController.drawBoard(board.getBoard());
    }

    public void setP1Playing(boolean isP1) {
        guiController.setCurrentPlayer(isP1);
    }

    public void setP2Score(int score) {
        guiController.setP2Score(score);
    }

    public void setP1Score(int score) {
        guiController.setP1Score(score);
    }
}
