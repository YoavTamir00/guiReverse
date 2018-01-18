package guiTools;

import constants.Tile;
import javafx.scene.paint.Color;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

// Singleton design pattern.
public class VisualSettingsFromFile extends VisualSettings {
    private static final long serialVersionUID = 1L;
    private static VisualSettingsFromFile INSTANCE = null;
    private String fileName;

    // Contructor of default values
    private VisualSettingsFromFile(String fileName) {
        initCleanSettings(fileName);
    }

    // Singleton's 'getObject' function.
    public static VisualSettingsFromFile getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new VisualSettingsFromFile("");
        }

        return INSTANCE;
    }

    // copy function from a different VisualSettings(except for the fileName).
    public void loadFromCopy(VisualSettings copy, String fileName) {
        this.fileName = fileName;
        setP1Color(copy.p1Color());
        setP2Color(copy.p2Color());
        boardDimension = copy.boardDimension();
        startingPlayer = copy.startingPlayer();
        this.gameReady = false;
    }

    // Default values accord to the exercise.
    public void initCleanSettings(String fileName) {
        p1Color = Color.BLACK.toString();
        p2Color = Color.WHITE.toString();
        boardDimension = 8;
        startingPlayer = Tile.X;
        this.gameReady = false;
        this.fileName = fileName;
    }

    // Method that saves the serializeable object.
    public void save() {
        try {
            ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(fileName));
            objOutput.writeObject(this);
            objOutput.close();
        } catch (Exception e) {
            System.out.println("Caught a problem trying to save the settings.");
        }
    }
}
