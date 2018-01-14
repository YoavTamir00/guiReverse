import javafx.scene.paint.Color;

public interface UiManager {

    void showMessage(String message);
    void drawBoard(Cell[][] board);
    void setP1Color(Color color);
    void setP2Color(Color color);
}
