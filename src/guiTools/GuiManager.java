package guiTools;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import screenControllers.GameScreen;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import javax.print.DocFlavor.URL;

// Similar to singelton design pattern.
public class GuiManager extends Application {
	private static final String MENU_SCREEN_LOCATION = "../xmls/mainScene.fxml";
	private static final String SETTINGS_SCREEN_LOCATION = "../xmls/settingsScene.fxml";

	private Pane menuScreen, settingsScreen, currentlyShown;
	private GameScreen gameScreen;
	private VBox root;
	private Scene mainScene;
	
	public static final CountDownLatch latch = new CountDownLatch(1);
	public static GuiManager GuiManager = null;

	public GuiManager() {
		GuiManager = this;
		latch.countDown();
	}

	public static GuiManager waitForGuiGraphics() {
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return GuiManager;
	}

	public static GuiManager getInstance() {
		if (GuiManager == null) {
			new Thread(() -> Application.launch(GuiManager.class)).start();
			// UI takes time to load
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return waitForGuiGraphics();
		} else {
			return GuiManager;
		}
	}

	public ReversiUi getGameGuiManager() {
		return gameScreen;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		initUiElements(primaryStage);

	}

	private void initUiElements(Stage primaryStage) throws IOException {
		primaryStage.setTitle("Reversi Game");
		root = new VBox();
		StackPane stackPane = new StackPane();

		gameScreen = new GameScreen(root);
		gameScreen.setVisible(false);

		settingsScreen = FXMLLoader.load(getClass().getResource(SETTINGS_SCREEN_LOCATION));
		settingsScreen.setVisible(false);
		menuScreen = FXMLLoader.load(getClass().getResource(MENU_SCREEN_LOCATION));
		stackPane.getChildren().add(menuScreen);
		stackPane.getChildren().add(gameScreen);
		stackPane.getChildren().add(settingsScreen);

		currentlyShown = menuScreen;

		root.getChildren().add(stackPane);

		mainScene = new Scene(root, GameScreen.INITIAL_WIDTH, GameScreen.INITIAL_HEIGHT);
		primaryStage.setScene(mainScene);

		primaryStage.show();
	}

	public void changeShowingScreen(ScreenType screen) {
		currentlyShown.setVisible(false);
		if (screen == ScreenType.game) {
			gameScreen.resetValues();
			currentlyShown = gameScreen;
		} else if (screen == ScreenType.main) {
			currentlyShown = menuScreen;
		} else {
			currentlyShown = settingsScreen;
		}
		currentlyShown.setVisible(true);
	}
}
