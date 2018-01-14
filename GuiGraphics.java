
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GuiGraphics extends Application implements UiManager {

    public static final int GRID_LINE_WIDTH = 3;
    private static final Paint BACKGROUND_COLOR = Color.grayRgb(200, 1);
    public static final int TEXT_LENGTH = 300;
    public static final int INITIAL_WIDTH = 800;
    public static final int INITIAL_HEIGHT = 600;
    private double rows = 6;//is double to prevent unnecessary rounding
    private Canvas canvas;
    private GraphicsContext gc;
    private Label currentPlayerLabel;
    private Label p1ScoreLabel;
    private Label p2ScoreLabel;
    private HBox root;
    private Cell[][] board;
    private Color p1Color = Color.BLUE;
    private Color p2Color = Color.YELLOWGREEN;

    @Override
    public void start(Stage primaryStage) throws Exception{
        initBoard();
        initUiElements(primaryStage);
        setResizeHandler();
        setClickListener();

    }

    private void initBoard() {
        board = new Cell[(int) rows][(int) rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                board[i][j] = Cell.EMPTY;
            }
        }
    }

    private void setClickListener() {
        canvas.setOnMouseClicked(mouseEvent -> {
            gc.setFill(Color.BLUE);
            double size = canvas.getWidth();
            double squareSize = size / (rows);

            int x = (int) (mouseEvent.getX() / squareSize);
            int y = (int) (mouseEvent.getY() / squareSize);
            //todo remove this
            board[x][y] = Cell.O;
            drawBoard(board);
        });
    }

    private void setResizeHandler() {
        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            rszBoard();
        });

        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            rszBoard();
        });


;
    }

    private void rszBoard() {
        double boardSize = Math.min(root.getWidth() - TEXT_LENGTH, root.getHeight());
        canvas.setWidth(boardSize);
        canvas.setHeight(boardSize);
        drawBoard(board);
    }

    private void initUiElements(Stage primaryStage) {
        primaryStage.setTitle("Reversi Game");
        currentPlayerLabel = new Label("Current Player: p1");
        currentPlayerLabel.setFont(new Font("Arial", 30));
        p1ScoreLabel = new Label("p1 scores: 0");
        p1ScoreLabel.setFont(new Font("Arial", 30));
        p2ScoreLabel = new Label("p2 scores: 0");
        p2ScoreLabel.setFont(new Font("Arial", 30));
        root = new HBox();
        double boardSize = Math.min(INITIAL_HEIGHT, INITIAL_WIDTH- TEXT_LENGTH);
        canvas = new Canvas(boardSize, boardSize);
        gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        VBox vBox = new VBox(currentPlayerLabel, p1ScoreLabel, p2ScoreLabel);
        root.getChildren().add(vBox);
        primaryStage.setScene(new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT));


        drawBoard(board);

        primaryStage.show();
    }

    private void drawCell(int x, int y, Color color) {
        gc.setFill(color);
        double size = canvas.getWidth();
        double squareSize = size / (rows);

        gc.fillRoundRect(x * squareSize,
                y * squareSize,
                squareSize, squareSize, squareSize, squareSize);
        drawGrid();
    }

    private void drawGrid() {
        double size = canvas.getWidth();
        double rowSpacing = size / (rows);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(GRID_LINE_WIDTH);
        for (int i = 0; i < rows + 1; i++) {
            gc.strokeLine(0, i * rowSpacing, size, i * rowSpacing);
            gc.strokeLine(i * rowSpacing, 0, i * rowSpacing, size);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void drawBoard(Cell[][] board) {
        this.board = board;
        gc.setFill(BACKGROUND_COLOR);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawGrid();
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                if (board[x][y] != Cell.EMPTY) {
                    drawCell(x, y, board[x][y] == Cell.O ? p1Color : p2Color);
                }
            }
        }
    }

    @Override
    public void setP1Color(Color color) {
        p1Color = color;
    }

    @Override
    public void setP2Color(Color color) {
        p2Color = color;

    }
}
