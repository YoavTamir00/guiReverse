import javafx.scene.paint.Color;

public class Main {
    public static void main(String[] args) {
        GuiManager guiManager = GuiManager.getInstance();
        GameUiManager gameUiManager = guiManager.getGameGuiManager();
        gameUiManager.setCurrentPlayer(true);
        gameUiManager.setP1Color(Color.BLACK);
//        guiManager.showAlert("good");
        Cell[][] board = new Cell[(int) 6][(int) 6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                board[i][j] = Cell.EMPTY;
            }
        }
        final boolean[] p1Turn = {false};
        gameUiManager.drawBoard(board);
        gameUiManager.setP1Score(13);
        gameUiManager.setP2Score(15);


        gameUiManager.setBoardClickListener((integer, integer2) -> {
            board[integer][integer2] = (p1Turn[0] ? Cell.O : Cell.X);
            gameUiManager.drawBoard(board);
            p1Turn[0] = !p1Turn[0];
            gameUiManager.setCurrentPlayer(p1Turn[0]);
        });
    }
}
