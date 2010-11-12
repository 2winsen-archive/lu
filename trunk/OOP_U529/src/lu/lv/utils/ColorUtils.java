package lu.lv.utils;

import java.awt.Color;

public class ColorUtils {

	public static Color getChartItemColorByKey(String key) {
		switch (Integer.parseInt(key.trim())) {
		case 4:
			return Color.darkGray;
		case 5:
			return Color.blue;
		case 6:
			return Color.green;
		case 7:
			return Color.red;
		case 8:
			return Color.cyan;
		case 9:
			return Color.yellow;
		case 10:
			return Color.magenta;
		default:
			throw new Error("Unknown key");
		}
	}

}
