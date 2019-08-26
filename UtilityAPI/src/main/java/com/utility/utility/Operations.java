package com.utility.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
//Utility Class
public class Operations {
	public HttpHeaders getHeaders() {
		HttpHeaders headers=new HttpHeaders();
		headers.setCacheControl(CacheControl.noCache());
		headers.setPragma("no-cache");
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return headers;
	}
	
	public long calNthFibonacci(long number) {
		int a=0,b=1,c;
		if (number==0) {
			return a;
		}
		for (int i = 2; i <= number; i++) {
			c=a+b;
			a=b;
			b=c;
		}
		return b;
	}
	
	public String type(int a,int b,int c) {
		if ((a<(b+c)) && (c<(b+a)) && (b<(a+c))) {
			if ((a==b) && (b==c)) 
				return "Equilateral";
			else if ((a==b) || (c==a) || (c==b)) 
				return "Isosceles";
			else if ((b!=c) && (c!=a)) 
				return "Scalene";
		}else {
			return "Not a triangle";
		}
		return null;
	}
	
	public String reverseWordByWord(String str) {
		String[] spliteData=str.split(" ");
		StringBuilder reverseString=new StringBuilder();
		for (int i = 0; i < spliteData.length; i++) {
			String singleData=spliteData[i];
			StringBuilder reverseWord=new StringBuilder();
			for (int j = singleData.length()-1; j >=0 ; j--) {
				reverseWord.append(singleData.charAt(j));
			}
			reverseString.append(reverseWord.toString()+" ");
		}
		return reverseString.toString().trim();
	}
	
	public HashMap<String, List<Integer>> makeOneArray(HashMap<String, List<Integer>> array){
		TreeSet<Integer> set=new TreeSet<>();
		HashMap<String, List<Integer>> resultHashMap=new HashMap<>();
		List<Integer> combinedArray=new ArrayList<>();
		for (int i = 1; i <= array.size(); i++) {
			combinedArray.addAll(array.get("Array"+i));
		}
		System.out.println(combinedArray);
		combinedArray.forEach(item-> set.add(item));
		List<Integer> sortedFinalList=new ArrayList<Integer>(set);
		resultHashMap.put("Array", sortedFinalList);
		return resultHashMap;
	}
}
