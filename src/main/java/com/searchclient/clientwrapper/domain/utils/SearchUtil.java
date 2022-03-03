package com.searchclient.clientwrapper.domain.utils;

import java.util.ArrayList;
import java.util.List;

public class SearchUtil {
	
	private SearchUtil() {}
	
	public static List<String> getTrimmedListOfStrings(List<String> list) {
		List<String> trimmedList = new ArrayList<>();
		for(String str: list) {
			str = str.trim();
			trimmedList.add(str);
		}
		
		return trimmedList;
	}
}
