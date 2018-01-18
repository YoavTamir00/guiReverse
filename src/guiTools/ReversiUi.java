package guiTools;

import constants.Tile;
import javafx.scene.paint.Color;

import java.util.function.BiConsumer;

/**
 * The interface of commands A SCREEN must implement so external classes
 * (such as guiGraphics class), know how to communicate with the gui.
 */
public interface ReversiUi {

    void showAlert(String message);

    void drawBoard(Tile[][] board);

    void setP1Color(Color color);

    void setP2Color(Color color);

    void setCurrentPlayer(boolean isP1);

    void setP1Score(int score);

    void setP2Score(int score);

    void setBoardClickListener(BiConsumer<Integer, Integer> clickListener);

    void showAlertAndExit(String message);
}
