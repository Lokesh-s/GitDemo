package com.utility.utility;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class OperationsTest {
	Operations operations;
	
	@Before
	public void initialize() {
		operations=new Operations();
	}
	
	@Test
	public void testFibonacciNum() {
		assertEquals(55, operations.calNthFibonacci(10));
	}
	
	@Test
	public void testType() {
		assertEquals("Equilateral", operations.type(1, 1, 1));
	}
	
	@Test
	public void testReverseWord() {
		assertEquals("woh era uoy", operations.reverseWordByWord("how are you"));
	}
	
	@Test
	public void testMakeOneArray() {
		HashMap<String, List<Integer>> inputHashMap=new HashMap<String, List<Integer>>();
		inputHashMap.put("Array1",new ArrayList<Integer>(Arrays.asList(1,2,3,4)));
		inputHashMap.put("Array2",new ArrayList<Integer>(Arrays.asList(3,4,5,6)));
		inputHashMap.put("Array3",new ArrayList<Integer>(Arrays.asList(6,1,3,11)));
		
		HashMap<String, List<Integer>> expectedHashMap=new HashMap<String, List<Integer>>();
		expectedHashMap.put("Array",new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,11)));
		
		assertEquals(expectedHashMap, operations.makeOneArray(inputHashMap));
	}
}
