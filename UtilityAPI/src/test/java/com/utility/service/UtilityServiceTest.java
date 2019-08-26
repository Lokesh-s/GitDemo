package com.utility.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.utility.utility.Operations;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilityServiceTest {
	@Autowired
	private UtilityService service;
	
	@MockBean
	private Operations operations;
	
	@Test
	public void testFibonacciNum() throws Exception{
		long expected=55l;
		Mockito.when(operations.calNthFibonacci(Mockito.anyLong())).thenReturn(expected);
		assertThat(service.fibonacci(10l)).isEqualTo(expected);
	}
	
	@Test
	public void testReverseWords() throws Exception{
		String expected="woh era uoy";
		Mockito.when(operations.reverseWordByWord(Mockito.anyString())).thenReturn(expected);
		assertThat(service.reverseWords("how%20are%20you")).isEqualTo(expected);
	}
	
	
	@Test
	public void testTriangleType() throws Exception{
		String expected="Equilateral";
		Mockito.when(operations.type(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt())).thenReturn(expected);
		assertThat(service.triangleType(1,1,1)).isEqualTo(expected);
	}
	
	@Test
	public void testMakeOneArray() throws Exception{
		HashMap<String, List<Integer>> inputHashMap=new HashMap<String, List<Integer>>();
		inputHashMap.put("Array1",new ArrayList<Integer>(Arrays.asList(1,2,3,4)));
		inputHashMap.put("Array2",new ArrayList<Integer>(Arrays.asList(3,4,5,6)));
		inputHashMap.put("Array3",new ArrayList<Integer>(Arrays.asList(6,1,3,11)));
		
		HashMap<String, List<Integer>> expectedHashMap=new HashMap<String, List<Integer>>();
		expectedHashMap.put("Array",new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,11)));
		
		Mockito.when(operations.makeOneArray(Mockito.any(HashMap.class))).thenReturn(expectedHashMap);
		assertThat(service.makeOneArray(inputHashMap)).isEqualTo(expectedHashMap);
	}
	
}
