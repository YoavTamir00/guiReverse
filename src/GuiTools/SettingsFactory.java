package GuiTools;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class SettingsFactory {
	public static String DEFAULT_FILE = "settings.ser";

	public static void createFromFile() {
		SettingsFactory.createFromFile(DEFAULT_FILE);
	}
	
	public static void createFromFile(String fileName) {
		VisualSettingsFromFile ans = VisualSettingsFromFile.getInstance();
		try {
		ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(fileName));
		VisualSettings settings = (VisualSettingsFromFile)(objInput.readObject());
		objInput.close();
		ans.loadFromCopy(settings, fileName);
		} catch (Exception e) {
			System.out.println("Caught an exception, reading default settings");
			e.printStackTrace();
			ans.initCleanSettings(fileName);
		}
	}
}
