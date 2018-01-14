
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.concurrent.CountDownLatch;
import java.util.function.BiConsumer;

public class GuiGraphics extends Application implements UiManager {

    public static final int GRID_LINE_WIDTH = 3;
    public static final int FONT_SIZE = 25;
    private static final Paint BACKGROUND_COLOR = Color.grayRgb(200, 1);
    public static final int TEXT_LENGTH = 300;
    public static final int INITIAL_WIDTH = 800;
    public static final int INITIAL_HEIGHT = 600;
    public static final int MENU_WIDTH = 30;
    private double rows = 6;//is double to prevent unnecessary rounding
    private Canvas canvas;
    private GraphicsContext gc;
    private Label currentPlayerLabel;
    private Label p1ScoreLabel;
    private Label p2ScoreLabel;
    private HBox mainScreen;
    private Cell[][] board;
    private Color p1Color = Color.BLUE;
    private Color p2Color = Color.YELLOWGREEN;
    private BiConsumer<Integer, Integer> boardClickListener = null;
    private VBox root;
    private SettingScreen settingScene;
    private Scene gameScreen;


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

            if (boardClickListener != null) {
                boardClickListener.accept(x, y);
            }
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
        double boardSize = Math.min(root.getWidth() - TEXT_LENGTH, root.getHeight() - MENU_WIDTH);
        canvas.setWidth(boardSize);
        canvas.setHeight(boardSize);
        drawBoard(board);
    }

    private void initUiElements(Stage primaryStage) {
        primaryStage.setTitle("Reversi Game");
        currentPlayerLabel = new Label("Current Player: p1");
        currentPlayerLabel.setFont(new Font("Arial", FONT_SIZE));
        p1ScoreLabel = new Label("p1 scores: 0");
        p1ScoreLabel.setFont(new Font("Arial", FONT_SIZE));
        p2ScoreLabel = new Label("p2 scores: 0");
        p2ScoreLabel.setFont(new Font("Arial", FONT_SIZE));
        mainScreen = new HBox();
        double boardSize = Math.min(INITIAL_HEIGHT, INITIAL_WIDTH- TEXT_LENGTH);
        canvas = new Canvas(boardSize, boardSize);
        gc = canvas.getGraphicsContext2D();
        mainScreen.getChildren().add(canvas);
        VBox vBox = new VBox(currentPlayerLabel, p1ScoreLabel, p2ScoreLabel);
        mainScreen.getChildren().add(vBox);

        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        root = new VBox();
        root.getChildren().add(menuBar);
        root.getChildren().add(mainScreen);

        settingScene = new SettingScreen(new VBox());

        Menu menu = new Menu("screens");

        ToggleGroup toggleGroup = new ToggleGroup();
        RadioMenuItem settingMenu = new RadioMenuItem("setting screen");
        settingMenu.setToggleGroup(toggleGroup);
        settingMenu.setOnAction(actionEvent -> {
            primaryStage.setScene(settingScene);


        });

        menu.getItems().add(settingMenu);
        RadioMenuItem gameMenu = new RadioMenuItem("game screen");
        gameMenu.setToggleGroup(toggleGroup);

        gameMenu.setOnAction(e->{
            primaryStage.setScene(gameScreen);
        });
        menu.getItems().add(gameMenu);

        menuBar.getMenus().add(menu);

        gameScreen = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);
        primaryStage.setScene(gameScreen);


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



    @Override
    public void showAlert(String message) {
        Platform.runLater(() -> {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("reversi");//maybe somthing else?
            alert.setHeaderText(message);

            alert.showAndWait();
        });
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
            currentPlayerLabel.setText("Current Player is: " + (isP1 ? "p1" : "p2"));
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


    public static final CountDownLatch latch = new CountDownLatch(1);
    public static GuiGraphics GuiGraphics = null;

    public static GuiGraphics waitForGuiGraphics() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return GuiGraphics;
    }

    public static void setGuiGraphics(GuiGraphics GuiGraphics0) {
        GuiGraphics = GuiGraphics0;
        latch.countDown();
    }

    public GuiGraphics() {
        setGuiGraphics(this);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    public static GuiGraphics getInstance() {
        new Thread(() -> Application.launch(GuiGraphics.class)).start();
        //UI takes time to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return waitForGuiGraphics();
    }
}
