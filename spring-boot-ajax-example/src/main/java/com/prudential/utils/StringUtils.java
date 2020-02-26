package com.prudential.utils;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(StringUtils.class);
	
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static String getJsonKeyFormat(String toBeFormated) {
    	String[] tokens = toBeFormated.toLowerCase().split("_");
    	toBeFormated = "";
    	for(int i = 0; i < tokens.length; i++){
    	    char capLetter = Character.toUpperCase(tokens[i].charAt(0));
    	    toBeFormated +=  " " + capLetter + tokens[i].substring(1);
    	}
    	toBeFormated = toBeFormated.trim();
    	toBeFormated = toBeFormated.substring(0,1).toLowerCase()+toBeFormated.substring(1);
    	return toBeFormated.replace(" ", "");
	}
	
	public static String getFormatDate(String date) {
		String formatDate = "";
		try {
			formatDate = df.format(sdf.parse(date));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return formatDate;
	}
	
	public static String toCapital(String toBeFormated) {
    	String[] tokens = toBeFormated.toLowerCase().split("_");
    	toBeFormated = "";
    	for(int i = 0; i < tokens.length; i++){
    	    char capLetter = Character.toUpperCase(tokens[i].charAt(0));
    	    toBeFormated +=  " " + capLetter + tokens[i].substring(1);
    	}
    	toBeFormated = toBeFormated.trim();
    	return toBeFormated.replace(" ", "_");
	} 

}
