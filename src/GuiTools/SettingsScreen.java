package GuiTools;

import constants.Tile;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
//TODO make interface of setting and implement it
public class SettingsScreen extends VBox {

    public static final int MAX_BOARD_SIZE = 20;
    public static final int MIN_BOARD_SIZE = 4;
    public static final int BOARD_START_SIZE = 6;
    private final Font defaultFont;
    private VisualSettings settings;
    private MenuScreen parent;
    public SettingsScreen(VisualSettings settings) {
        super();
        this.settings = settings;
        Font font =  new Font("Arial", 40);
        defaultFont =  new Font("Arial", 25);

        Label title = new Label("Setting:\n");
        title.setFont(font);

        getChildren().add(title);

        makeStarterOption();
        makeColorOptions();
        makeSizeOptions();
        initReturnButton();
    }
    
    public void setParent(MenuScreen parent) {
    	this.parent = parent;
    }
    private void makeSizeOptions() {
        Slider slider = new Slider(MIN_BOARD_SIZE,
                MAX_BOARD_SIZE, settings.boardDimension());
        HBox hBox = new HBox();

        slider.setPrefWidth(300);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(1);
        slider.setBlockIncrement(1);
        slider.setMinorTickCount(0);
        slider.snapToTicksProperty().setValue(true);
        Label label = new Label("board size:  ");
        label.setFont(defaultFont);
        
        slider.setOnMouseReleased(event-> {
        	settings.setBoardDimension(slider.getValue());
        });
        hBox.getChildren().addAll(label, slider);

        getChildren().add(hBox);

    }

    private void makeColorOptions() {

        HBox hBox1 = new HBox(), hBox2 = new HBox();

        ColorPicker colorPickerP1 = new ColorPicker(settings.p1Color());
        ColorPicker colorPickerP2 = new ColorPicker(settings.p2Color());

        Label l1 = new Label("p1 color: ");
        Label l2 = new Label("p2 color: ");
        l1.setFont(defaultFont);
        l2.setFont(defaultFont);
        hBox1.getChildren().addAll(l1, colorPickerP1);
        hBox2.getChildren().addAll(l2, colorPickerP2);
        
        colorPickerP1.setOnAction(event -> {
        	settings.setP1Color(colorPickerP1.getValue());
        });
        colorPickerP2.setOnAction(event -> {
        	settings.setP2Color(colorPickerP2.getValue());
        });

        getChildren().addAll(hBox1, hBox2);

    }

    private void makeStarterOption() {
        RadioButton p1StartsButton = new RadioButton("p1 start    ");
        RadioButton p2StartsButton = new RadioButton("p2 start    ");
        ToggleGroup startGroup = new ToggleGroup();
        
        if (settings.startingPlayer() == Tile.X) {
        	p1StartsButton.fire();
        } else {
        	p2StartsButton.fire();
        }
        
        p1StartsButton.setToggleGroup(startGroup);
        p2StartsButton.setToggleGroup(startGroup);
        p1StartsButton.setFont(defaultFont);
        p2StartsButton.setFont(defaultFont);
        p1StartsButton.setOnAction(event->{
        	settings.setStartingPlayer(Tile.X);
        });
        p2StartsButton.setOnAction(event->{
        	settings.setStartingPlayer(Tile.O);
        });
        HBox hBox = new HBox();
        hBox.getChildren().addAll(p1StartsButton, p2StartsButton);
        getChildren().add(hBox);
    }
    
    private void initReturnButton() {
    	HBox hBox = new HBox();
    	Button returnButton = new Button("Save settings and return home");
    	returnButton.setOnAction(event-> {
    		settings.save();
    		this.setVisible(false);
    		parent.setVisible(true);
    	});
    	
    	hBox.getChildren().add(returnButton);
    	this.getChildren().add(hBox);
    }

}
