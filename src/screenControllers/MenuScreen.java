package screenControllers;

import javafx.scene.control.Button;
import guiTools.GuiManager;
import guiTools.ScreenType;
import guiTools.VisualSettings;
import guiTools.VisualSettingsFromFile;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class MenuScreen extends VBox {
	@FXML
	private Button startButton, settingsButton;

	/**
	 * public MenuScreen(GameScreen game, SettingsScreen settingsScreen) {
	 * super(); initButtons(); } private void initButtons() { Button openGame =
	 * new Button("Start Game"); openGame.setOnAction(event-> { VisualSettings
	 * settings = VisualSettingsFromFile.getInstance();
	 * gameScreen.resetValues(); settings.setGameReady(true);
	 * this.setVisible(false); gameScreen.setVisible(true); }); Button
	 * openSettings = new Button("OpenSettings");
	 * openSettings.setOnAction(event-> { this.setVisible(false);
	 * settingsScreen.setVisible(true); }); this.getChildren().add(openGame);
	 * this.getChildren().add(openSettings); }
	 **/

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
