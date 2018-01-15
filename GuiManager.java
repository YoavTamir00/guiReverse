
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.CountDownLatch;

public class GuiManager extends Application {


    public static final int MENU_WIDTH = 30;

    private GameScreen gameScreen;
    private VBox root;
    private SettingScreen settingScreen;
    private Scene mainScene;


    @Override
    public void start(Stage primaryStage) throws Exception{
        initUiElements(primaryStage);
    }


    private void initUiElements(Stage primaryStage) {
        primaryStage.setTitle("Reversi Game");
        root = new VBox();
        StackPane stackPane = new StackPane();

        gameScreen = new GameScreen(root);

        settingScreen = new SettingScreen();
        settingScreen.setVisible(false);

        stackPane.getChildren().add(gameScreen);
        stackPane.getChildren().add(settingScreen);

        initMenu(primaryStage);
        root.getChildren().add(stackPane);

        mainScene = new Scene(root, GameScreen.INITIAL_WIDTH, GameScreen.INITIAL_HEIGHT);
        primaryStage.setScene(mainScene);



        primaryStage.show();
    }

    private void initMenu(Stage primaryStage) {

        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());

        Menu menu = new Menu("screens");

        ToggleGroup toggleGroup = new ToggleGroup();
        RadioMenuItem settingMenu = new RadioMenuItem("setting screen");
        settingMenu.setToggleGroup(toggleGroup);
        settingMenu.setOnAction(actionEvent -> {
            gameScreen.setVisible(false);
            settingScreen.setVisible(true);

        });

        menu.getItems().add(settingMenu);
        RadioMenuItem gameMenu = new RadioMenuItem("game screen");
        gameMenu.setToggleGroup(toggleGroup);
        gameMenu.setSelected(true);

        gameMenu.setOnAction(actionEvent -> {
            gameScreen.setVisible(true);
            settingScreen.setVisible(false);

        });
        menu.getItems().add(gameMenu);

        menuBar.getMenus().add(menu);
        root.getChildren().add(menuBar);

    }




    public static final CountDownLatch latch = new CountDownLatch(1);
    public static GuiManager GuiManager = null;

    public static GuiManager waitForGuiGraphics() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return GuiManager;
    }

    public static void setGuiManager(GuiManager guiManager0) {
        GuiManager = guiManager0;
        latch.countDown();
    }

    public GuiManager() {
        setGuiManager(this);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    public static GuiManager getInstance() {
        new Thread(() -> Application.launch(GuiManager.class)).start();
        //UI takes time to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return waitForGuiGraphics();
    }

    public GameUiManager getGameGuiManager() {
        return gameScreen;
    }
}
