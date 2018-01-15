import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
//TODO make interface of setting and implement it
public class SettingScreen extends VBox {

    public static final int MAX_BOARD_SIZE = 20;
    public static final int MIN_BOARD_SIZE = 4;
    public static final int BOARD_START_SIZE = 6;
    private final Font defualtFont;

    public SettingScreen() {
        super();
        Font font =  new Font("Arial", 40);
        defualtFont =  new Font("Arial", 25);

        Label title = new Label("Setting:\n");
        title.setFont(font);

        getChildren().add(title);


        makeStarterOption();
        makeColorOptions();
        makeSizeOptions();

    }

    private void makeSizeOptions() {
        Slider slider = new Slider(MIN_BOARD_SIZE,
                MAX_BOARD_SIZE, BOARD_START_SIZE);
        HBox hBox = new HBox();

        slider.setPrefWidth(300);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(1);
        slider.setBlockIncrement(1);
        slider.setMinorTickCount(0);
        slider.snapToTicksProperty().setValue(true);
        Label label = new Label("board size:  ");
        label.setFont(defualtFont);
        hBox.getChildren().addAll(label, slider);

        getChildren().add(hBox);

    }

    private void makeColorOptions() {

        HBox hBox1 = new HBox(), hBox2 = new HBox();

        ColorPicker colorPickerP1 = new ColorPicker();
        ColorPicker colorPickerP2 = new ColorPicker();

        Label l1 = new Label("p1 color: ");
        Label l2 = new Label("p2 color: ");
        l1.setFont(defualtFont);
        l2.setFont(defualtFont);
        hBox1.getChildren().addAll(l1, colorPickerP1);
        hBox2.getChildren().addAll(l2, colorPickerP2);



        getChildren().addAll(hBox1, hBox2);

    }

    private void makeStarterOption() {
        RadioButton p1StartsButton = new RadioButton("p1 start    ");
        RadioButton p2StartsButton = new RadioButton("p2 start    ");
        ToggleGroup startGroup = new ToggleGroup();

        p1StartsButton.setToggleGroup(startGroup);
        p2StartsButton.setToggleGroup(startGroup);
        p1StartsButton.setFont(defualtFont);
        p2StartsButton.setFont(defualtFont);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(p1StartsButton, p2StartsButton);
        getChildren().add(hBox);
    }

}
