package lv.kvd.lu.utils;


public class FunctionUtils {
	
	/**
	 * Get that does not return null instead empty string
	 * @param string
	 * @return
	 */
	public static String nullSafeGet(String string) {
		if(string == null) {
			return "";
		}
		return string;
	}
	
	/**
	 * Get that does not return null instead empty string
	 * @param string
	 * @return
	 */
	public static String nullSafeGet(Long value) {
		if(value == null) {
			return "";
		}
		return value.toString();
	}

}
