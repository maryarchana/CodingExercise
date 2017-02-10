package com.ins.utils;

public class CommonUtils {
	
	public static String getTextAfterLineBreaks(String message) {
		if(message.contains("\n")) {
			String[] removeBreaks = message.split("\n");
			message = removeBreaks[1];
		}
		return message;
	}
	
	public static String removeLineBreaks(String message) {
		String removeBreaks = message.replaceAll("\\r\\n|\\r|\\n", " ");
		return removeBreaks;
	}
	
	
}
