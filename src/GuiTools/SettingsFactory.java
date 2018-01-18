package GuiTools;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class SettingsFactory {
	public static String DEFAULT_FILE = "settings.ser";

	public static VisualSettingsFromFile fromFile() {
		return SettingsFactory.fromFile(DEFAULT_FILE);
	}
	
	public static VisualSettingsFromFile fromFile(String fileName) {
		try {
		ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(fileName));
		VisualSettings settings = (VisualSettingsFromFile)(objInput.readObject());
		VisualSettingsFromFile ans = new VisualSettingsFromFile(settings,fileName);
		objInput.close();
		return ans;
		} catch (Exception e) {
			System.out.println("Caught an exception, reading default settings");
			e.printStackTrace();
			return new VisualSettingsFromFile(fileName);
		}
	}
}
