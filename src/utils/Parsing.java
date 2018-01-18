package utils;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;

public class Parsing {
	public static Map<String, String> colorMap = null;
	public static String ColorToName(Color color) {
		if (colorMap == null) {
			colorMap = new HashMap<String, String>();
			for (Field f : Color.class.getFields()) {
			  if (f.getType() == Color.class) {
			    Color c;
				try {
					c = (Color) f.get(null);
				    colorMap.put(c.toString(), f.getName()); 
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
			}
		}
	return colorMap.get(color.toString());
}
}
