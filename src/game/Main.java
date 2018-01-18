package game;

import graphics.GraphicsHandler;
import graphics.guiGraphics;
import guiTools.SettingsFactory;
import rules.GameRules;
import rules.NormalRules;

public class Main {
	public static void main(String[] args) {
		SettingsFactory.createFromFile();
		Board gameBoard = new Board();
		GameRules rules = new NormalRules();
		GraphicsHandler graphics = new guiGraphics(gameBoard);
		Game game = new Game(graphics, rules, gameBoard);
		game.run();
	}
}
