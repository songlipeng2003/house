package utils;

public class StringUtils {
	public static String toUpperCaseName(String name) {
		name = name.replaceAll("\\W", "_");
		name = name.replaceAll("_+", "_");
		name = name.replaceAll("^_+|_+$", "");
		name = name.toLowerCase();
		return name;
	}
}
