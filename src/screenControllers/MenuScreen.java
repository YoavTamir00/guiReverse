package screenControllers;

import guiTools.GuiManager;
import guiTools.ScreenType;
import guiTools.VisualSettings;
import guiTools.VisualSettingsFromFile;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MenuScreen extends VBox {
    @FXML
    private Button startButton, settingsButton;

    @FXML
    private void openGame() {
        VisualSettings settings = VisualSettingsFromFile.getInstance();
        settings.setGameReady(true);

        GuiManager gui = GuiManager.getInstance();
        gui.changeShowingScreen(ScreenType.game);
    }

    @FXML
    private void openSettings() {
        GuiManager gui = GuiManager.getInstance();
        gui.changeShowingScreen(ScreenType.settings);
    }
}
