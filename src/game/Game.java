package game;

import constants.Tile;
import graphics.GraphicsHandler;
import players.GamePlayer;
import players.HumanPlayer;
import rules.GameRules;

public class Game {
	private GraphicsHandler graphics;
	private GameRules rules;
	private Board gameBoard;
	private GamePlayer p1,p2;
	public Game(GraphicsHandler graphics, GameRules rules, Board gameBoard) {
		this.graphics = graphics;
		this.rules = rules;
		this.gameBoard = gameBoard;		
	}
	
	private void runMainWindow() {
		graphics.mainMenu();
		p1 = new HumanPlayer(Tile.X, graphics);
		p2 = new HumanPlayer(Tile.O, graphics);

		GameFlow g = new GameFlow(p1, p2, rules, graphics);
		g.runGame(gameBoard);
	}
	
	public void run() {
		runMainWindow();
	}
}
