package guiTools;

import constants.Tile;
import javafx.scene.paint.Color;

import java.io.Serializable;

/**
 * An abstract class which hold all the information the UI might need for the game.
 * we don't know WHERE those settings come from/where to save them,
 * that is up to the classes that inherit this.
 *
 * @author ohad
 */
public abstract class VisualSettings implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String p1Color, p2Color;
    protected double boardDimension;
    protected Tile startingPlayer;
    protected boolean gameReady;

    // Getters
    public Color p1Color() {
        return Color.valueOf(p1Color);
    }

    public Color p2Color() {
        return Color.valueOf(p2Color);
    }

    public double boardDimension() {
        return boardDimension;
    }

    public Tile startingPlayer() {
        return startingPlayer;
    }

    /**
     * Setters
     * Note that the Colors are internally saved as Strings and not as Color Objects.
     * We save them as strings so they would be serializeable.
     */

    public void setP1Color(Color c) {
        p1Color = c.toString();
    }

    public void setP2Color(Color c) {
        p2Color = c.toString();
    }

    public void setBoardDimension(double d) {
        boardDimension = d;
    }

    public void setStartingPlayer(Tile s) {
        startingPlayer = s;
    }

    public abstract void save();

    // The game is ready if the player has configured all the settings.
    public boolean getGameReady() {
        return gameReady;
    }

    public void setGameReady(boolean r) {
        gameReady = r;
    }
}
