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
	/**
	 * 
	 */
	private static VisualSettingsFromFile INSTANCE = null;
	private String fileName;
	
	private VisualSettingsFromFile(String fileName) {
		super();
		initCleanSettings(fileName);
	}
	
    public static VisualSettingsFromFile getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new VisualSettingsFromFile("");
        }
         
        return INSTANCE;
    }
	public void loadFromCopy(VisualSettings copy, String fileName) {
		this.fileName = fileName;
		setP1Color(copy.p1Color());
		setP2Color(copy.p2Color());
		boardDimension = copy.boardDimension();
		startingPlayer = copy.startingPlayer();
		this.gameReady = false;
	}
	
	public void initCleanSettings(String fileName) {
		p1Color = Color.BLACK.toString();
		p2Color = Color.WHITE.toString();
		boardDimension = 8;
		startingPlayer = Tile.X;
		this.gameReady = false;
		this.fileName = fileName;
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
