package GuiTools;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import constants.Tile;
import javafx.scene.paint.Color;

public abstract class VisualSettings implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String p1Color, p2Color;
	protected double boardDimension;
	protected Tile startingPlayer;
	protected boolean gameReady;
	
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
	
	public boolean getGameReady() {
		return gameReady;
	}
	
	public void setGameReady(boolean r) {
		gameReady = r;
	}
}
