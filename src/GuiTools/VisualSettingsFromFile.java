package GuiTools;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import constants.Tile;
import javafx.scene.paint.Color;

public class VisualSettingsFromFile extends VisualSettings{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fileName;
	
	public VisualSettingsFromFile(String fileName) {
		super();
		initCleanSettings();
		this.fileName = fileName;
	}
	
	public VisualSettingsFromFile(VisualSettings copy, String fileName) {
		super();
		this.fileName = fileName;
		p1Color = copy.p1Color().toString();
		p2Color = copy.p2Color().toString();
		boardDimension = copy.boardDimension();
		startingPlayer = copy.startingPlayer();
	}
	
	private void initCleanSettings() {
		p1Color = Color.BLACK.toString();
		p2Color = Color.WHITE.toString();
		boardDimension = 8;
		startingPlayer = Tile.X;
	}
	
	public void save() {
		System.out.println("SAVING");
		try {
			ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(fileName));
			objOutput.writeObject(this);
			objOutput.close();
		} catch (Exception e) {
			System.out.println("Caught a problem trying to save the settings.");
		}
	}
}
