package game;

import graphics.GraphicsHandler;
import graphics.guiGraphics;
import guiTools.SettingsFactory;
import rules.GameRules;
import rules.NormalRules;

public class Main {
    public static void main(String[] args) {
        // Initalize the settings from a file.
        SettingsFactory.createFromFile();
        // (Changeable-Interfaces)
        Board gameBoard = new Board();
        GameRules rules = new NormalRules();
        GraphicsHandler graphics = new guiGraphics(gameBoard);
        Game game = new Game(graphics, rules, gameBoard);
        game.run();
    }
}
