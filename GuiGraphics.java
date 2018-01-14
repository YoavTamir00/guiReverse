
import javafx.application.Application;
import javafx.*;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GuiGraphics extends Application implements UiManager {

    public static final int GRID_LINE_WIDTH = 3;
    private double rows = 6;
    private Canvas canvas;
    private GraphicsContext gc;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("My First App");
        Label lbl = new Label("Hello World!");
        lbl.setFont(new Font("Arial", 30));
        HBox root = new HBox();
        canvas = new Canvas(600, 600);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.YELLOW);
        gc.fillRect(0, 0, 600, 600);
        root.getChildren().add(canvas);
        root.getChildren().add(lbl);
        primaryStage.setScene(new Scene(root, 800, 600));

        canvas.setOnMouseClicked(mouseEvent -> {
            gc.setFill(Color.BLUE);
            double size = canvas.getWidth();
            double squareSize = size / (rows);

//            gc.fillRoundRect(mouseEvent.getX() - mouseEvent.getX() % squareSize,
//                    mouseEvent.getY() - mouseEvent.getY() % squareSize,
//                    squareSize, squareSize, squareSize, squareSize);
            drawGrid();
            int x = (int) (mouseEvent.getX() / squareSize);
            int y = (int) (mouseEvent.getY() / squareSize);
            drawCell(x, y);
        });
        drawGrid();

        primaryStage.show();
    }

    private void drawCell(int x, int y) {
        gc.setFill(Color.BLUE);
        double size = canvas.getWidth();
        double squareSize = size / (rows);

        gc.fillRoundRect(x * squareSize,
                y * squareSize,
                squareSize, squareSize, squareSize, squareSize);
        drawGrid();
    }

    private void drawGrid() {
        int k;
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

//    private void drawShapes(GraphicsContext gc) {
//        gc.setFill(Color.YELLOW);
//        gc.fillRect(0, 0, 600, 600);
//    }


    public static void main(String[] args) {
        launch(args);
    }
}
