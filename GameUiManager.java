import javafx.scene.paint.Color;

import java.util.function.BiConsumer;

public interface GameUiManager {

    void showAlert(String message);
    void drawBoard(Cell[][] board);
    void setP1Color(Color color);
    void setP2Color(Color color);
    void setCurrentPlayer(boolean isP1);
    void setP1Score(int score);
    void setP2Score(int score);

    void setBoardClickListener(BiConsumer<Integer, Integer> clickListener);
}
