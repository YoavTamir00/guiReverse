package GuiTools;

import javafx.scene.paint.Color;

import java.util.function.BiConsumer;

import constants.Tile;
import game.Cell;

public interface GameUiManager {

    void showAlert(String message);
    void drawBoard(Tile[][] board);
    void setP1Color(Color color);
    void setP2Color(Color color);
    void setCurrentPlayer(boolean isP1);
    void setP1Score(int score);
    void setP2Score(int score);

    void setBoardClickListener(BiConsumer<Integer, Integer> clickListener);
}
