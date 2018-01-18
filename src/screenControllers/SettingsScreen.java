package screenControllers;

import constants.Tile;
import guiTools.GuiManager;
import guiTools.ScreenType;
import guiTools.VisualSettings;
import guiTools.VisualSettingsFromFile;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

//TODO make interface of setting and implement it
public class SettingsScreen extends VBox {

    @FXML
    private RadioButton p1StartsButton, p2StartsButton;
    @FXML
    private ColorPicker p1ColorPicker, p2ColorPicker;
    @FXML
    private Slider slider;
    @FXML
    private Button returnButton;

    private VisualSettings settings;

    @FXML
    private void initialize() {
        // Initialize this window according to the previous known settings.
        settings = VisualSettingsFromFile.getInstance();
        slider.setValue(settings.boardDimension());
        p1ColorPicker.setValue(settings.p1Color());
        p2ColorPicker.setValue(settings.p2Color());
        if (settings.startingPlayer() == Tile.X) {
            p1StartsButton.fire();
        } else {
            p2StartsButton.fire();
        }

    }

    @FXML
    private void p1Starts() {
        settings.setStartingPlayer(Tile.X);
    }

    @FXML
    private void p2Starts() {
        settings.setStartingPlayer(Tile.O);
    }

    @FXML
    private void pickColorP1() {
        settings.setP1Color(p1ColorPicker.getValue());

    }

    @FXML
    private void pickColorP2() {
        settings.setP2Color(p2ColorPicker.getValue());

    }

    @FXML
    private void sliderReleased() {
        settings.setBoardDimension(slider.getValue());
    }

    @FXML
    private void returnHome() {
        settings.save();
        GuiManager manager = GuiManager.getInstance();
        manager.changeShowingScreen(ScreenType.main);
    }
}
