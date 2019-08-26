package com.utility.service;

import java.util.HashMap;
import java.util.List;

public interface UtilityService {
	public long fibonacci(long number);
	public String reverseWords(String word);
	public String triangleType(int a,int b,int c);
	public HashMap<String, List<Integer>> makeOneArray(HashMap<String, List<Integer>> array);
}
