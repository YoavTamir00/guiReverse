import javafx.scene.paint.Color;

import java.util.function.BiConsumer;

public class Main {
    public static void main(String[] args) {
        UiManager guiGraphics = GuiGraphics.getInstance();
        guiGraphics.setCurrentPlayer(true);
        guiGraphics.setP1Color(Color.BLACK);
//        guiGraphics.showAlert("good");
        Cell[][] board = new Cell[(int) 6][(int) 6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                board[i][j] = Cell.EMPTY;
            }
        }
        final boolean[] p1Turn = {false};
        guiGraphics.drawBoard(board);
        guiGraphics.setP1Score(13);
        guiGraphics.setP2Score(15);


        guiGraphics.setBoardClickListener((integer, integer2) -> {
            board[integer][integer2] = (p1Turn[0] ? Cell.O : Cell.X);
            guiGraphics.drawBoard(board);
            p1Turn[0] = !p1Turn[0];
            guiGraphics.setCurrentPlayer(p1Turn[0]);
        });
    }
}
