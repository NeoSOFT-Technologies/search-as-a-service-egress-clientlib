package com.searchclient.clientwrapper.domain.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SearchUtil {
	
	private SearchUtil() {}
	
	// VALIDATIONS
	public static boolean checkIfNameIsAlphaNumeric(String name) {
		if (null == name || name.isBlank() || name.isEmpty())
			return false;
		Pattern pattern = Pattern.compile("^[a-zA-Z][a-zA-Z0-9]*$");
		Matcher matcher = pattern.matcher(name);
		return matcher.find();
	}
	
	
	public static boolean isAlphaNumeric(String charSequence) {
	    String pattern= "^[a-zA-Z0-9]*$";
	    return charSequence.matches(pattern);
	}
	
	
	// Utitlity functions
	public static List<String> getTrimmedListOfStrings(List<String> list) {
		List<String> trimmedList = new ArrayList<>();
		for(String str: list) {
			str = str.trim();
			trimmedList.add(str);
		}
		
		return trimmedList;
	}

}
