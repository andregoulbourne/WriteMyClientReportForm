package com.andre.util;

public class StringUtil {
	
	private StringUtil() {
		super();
	}
	
	public static boolean equals(String str1, String str2) {
		boolean result = false;
		
		try {
			result = str1.equals(str2);
		}catch (Exception e) {
			result = false;
		}
		
		return result;
	}
	
	public static boolean equalsIgnoreCase(String str1, String str2) {
		str1 = toUpperCase(str1);
		str2 = toUpperCase(str2);
		
		return equals(str1, str2);
	}
	
	private static String toUpperCase(String in) {
		if(in == null)
			return null;
		
		return in.toUpperCase();
	}
	
	public static boolean isBlank(String in) {
		return in == null || in.isEmpty() || in.isBlank();
	}

}
