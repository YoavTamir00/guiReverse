package GuiTools;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import utils.Parsing;

import java.util.function.BiConsumer;

import constants.Tile;
import game.Cell;

public class GameScreen extends HBox implements GameUiManager {
    public static final int FONT_SIZE = 25;
    public static final int GRID_LINE_WIDTH = 3;
    private static final Paint BACKGROUND_COLOR = Color.grayRgb(200, 1);
    public static final int TEXT_LENGTH = 300;
    public static final int INITIAL_WIDTH = 800;
    public static final int INITIAL_HEIGHT = 600;
    private double rows;//is double to prevent unnecessary rounding
    private Canvas canvas;
    private GraphicsContext gc;
    private Label currentPlayerLabel;
    private Label p1ScoreLabel;
    private Label p2ScoreLabel;
    
    private VisualSettings settings;
    private Tile[][] board;
    private Color p1Color;
    private Color p2Color;
    private BiConsumer<Integer, Integer> boardClickListener = null;

    public GameScreen(VBox root, VisualSettings settings) {
        super();
        this.settings = settings;
        resetValues();
        this.initEmptyBoard();
        currentPlayerLabel = new Label("Current Player: p1");
        currentPlayerLabel.setFont(Font.font("Arial", FontWeight.BOLD, FONT_SIZE));
        p1ScoreLabel = new Label("p1 scores: 0");
        p1ScoreLabel.setFont(new Font("Arial", FONT_SIZE));
        p2ScoreLabel = new Label("p2 scores: 0");
        p2ScoreLabel.setFont(new Font("Arial", FONT_SIZE));
        double boardSize = Math.min(INITIAL_HEIGHT, INITIAL_WIDTH- TEXT_LENGTH);
        canvas = new Canvas(boardSize, boardSize);
        gc = canvas.getGraphicsContext2D();
        getChildren().add(canvas);
        VBox vBox = new VBox(currentPlayerLabel, p1ScoreLabel, p2ScoreLabel);
        getChildren().add(vBox);

        setResizeHandler(root);
        setClickListener();

        drawBoard(board);
    }
    
    public void resetValues() {
        this.rows = settings.boardDimension();
        p1Color = settings.p1Color();
        p2Color = settings.p2Color();
        if (settings.startingPlayer() == Tile.X) {
        	setCurrentPlayer(true);
        } else {
        	setCurrentPlayer(false);
        }
    }
    private void setClickListener() {
        canvas.setOnMouseClicked(mouseEvent -> {
            gc.setFill(Color.BLUE);
            double size = canvas.getWidth();
            double squareSize = size / (rows);

            int col = (int) (mouseEvent.getX() / squareSize);
            int row = (int) (mouseEvent.getY() / squareSize);
            System.out.println(new Cell(row,col));
            if (boardClickListener != null) {
                boardClickListener.accept(row, col);
            }
        });
    }
    
    private void initEmptyBoard() {
    	board = new Tile[(int) settings.boardDimension()][(int) settings.boardDimension()];
    	for (int i = 0; i < settings.boardDimension();i++) {
    		for (int j= 0; j < settings.boardDimension(); j++) {
    			board[i][j] = Tile.Empty;
    		}
    	}
    }
    private void setResizeHandler(VBox root) {
        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            rszBoard(root);
        });

        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            rszBoard(root);
        });


        ;
    }

    private void rszBoard(VBox root) {
        double boardSize = Math.min(root.getWidth() - TEXT_LENGTH, root.getHeight() - GuiManager.MENU_WIDTH);
        canvas.setWidth(boardSize);
        canvas.setHeight(boardSize);
        drawBoard(board);
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


    @Override
    public void drawBoard(Tile[][] board) {
        this.board = board;
        gc.setFill(BACKGROUND_COLOR);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawGrid();
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                if (board[x][y] != Tile.Empty) {
                    drawCell(y, x, board[x][y] == Tile.O ? p1Color : p2Color);
                }
            }
        }
    }

    @Override
    public void setP1Color(Color color) {
        p1Color = color;
        drawBoard(board);
    }

    @Override
    public void setP2Color(Color color) {
        p2Color = color;
        drawBoard(board);
    }

    @Override
    public void setCurrentPlayer(boolean isP1) {
        Platform.runLater(() -> {
            currentPlayerLabel.setText("Current Player is: " + (isP1 ? "p1": "p2"));
            if (isP1) {
            	currentPlayerLabel.setTextFill(p1Color);
            } else {
            	currentPlayerLabel.setTextFill(p2Color);
            }
        });

    }

    @Override
    public void setP1Score(int score) {
        Platform.runLater(() -> {
            p1ScoreLabel.setText("p1 score: " + score);
        });
    }

    @Override
    public void setP2Score(int score) {
        Platform.runLater(() -> {
            p2ScoreLabel.setText("p2 score: " + score);
        });
    }

    @Override
    public void setBoardClickListener(BiConsumer<Integer, Integer> clickListener) {
        this.boardClickListener = clickListener;
    }


	@Override
    public void showAlert(String message) {
        Platform.runLater(() -> {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Reversi");//maybe somthing else?
            alert.setHeaderText(message);

            alert.showAndWait();
        });
    }
}
