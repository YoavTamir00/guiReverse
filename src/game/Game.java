package game;

import java.util.concurrent.TimeUnit;

import constants.Tile;
import graphics.GraphicsHandler;
import guiTools.VisualSettings;
import guiTools.VisualSettingsFromFile;
import players.GamePlayer;
import players.HumanPlayer;
import rules.GameRules;

public class Game {
	private GraphicsHandler graphics;
	private GameRules rules;
	private Board gameBoard;
	private GamePlayer p1, p2;

	public Game(GraphicsHandler graphics, GameRules rules, Board gameBoard) {
		this.graphics = graphics;
		this.rules = rules;
		this.gameBoard = gameBoard;
	}

	private void runMainWindow() {
		graphics.mainMenu();
		VisualSettings settings = VisualSettingsFromFile.getInstance();
		// Wait until the user has configured all the settings.
		while (!settings.getGameReady()) {
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (settings.startingPlayer() == Tile.X) {
			p1 = new HumanPlayer(Tile.X, graphics, "p1");
			p2 = new HumanPlayer(Tile.O, graphics, "p2");
		} else {
			p1 = new HumanPlayer(Tile.O, graphics, "p1");
			p2 = new HumanPlayer(Tile.X, graphics, "p2");
		}
		gameBoard.initializeBoard((int) settings.boardDimension());

		GameFlow g = new GameFlow(p1, p2, rules, graphics);
		g.runGame(gameBoard);
	}

	public void run() {
		runMainWindow();
	}
}
