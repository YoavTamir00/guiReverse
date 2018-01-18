package GuiTools;

import javafx.scene.control.Button;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class MenuScreen extends VBox {
	private GameScreen gameScreen;
	private SettingsScreen settingsScreen;
	public MenuScreen(GameScreen game, SettingsScreen settingsScreen) {
		super();
		gameScreen = game;
		this.settingsScreen = settingsScreen;
		initButtons();
	}
	
	private void initButtons() {
		Button openGame = new Button("Start Game");
		openGame.setOnAction(event-> {
			VisualSettings settings = VisualSettingsFromFile.getInstance();
			gameScreen.resetValues();
			settings.setGameReady(true);
			this.setVisible(false);
			gameScreen.setVisible(true);
		});
		Button openSettings = new Button("OpenSettings");
		openSettings.setOnAction(event-> {
			this.setVisible(false);
			settingsScreen.setVisible(true);
		});
		this.getChildren().add(openGame);
		this.getChildren().add(openSettings);

	}
}
