package com.utility.controller;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utility.exception.ExceptionResponse;
import com.utility.service.UtilityService;
import com.utility.utility.Operations;

@RunWith(SpringRunner.class)
@WebMvcTest(value=UtilityController.class,secure=false)
public class UtilityControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UtilityService service;
	
	@MockBean
	private Operations operations;
	
	@Test
	public void testFibonacciNum() throws Exception{
		String uri="/api/Fibonacci?n=10";
		Mockito.when(service.fibonacci(Mockito.anyLong())).thenReturn(55l);
		RequestBuilder requestBuilder=MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		String outputJson=result.getResponse().getContentAsString();
		assertThat(outputJson).isEqualTo(new Long(55).toString());
	}
	
	
	@Test
	public void testFibonacciNumException() throws Exception{
		ExceptionResponse exceptionResponse=new ExceptionResponse();
		exceptionResponse.setMessage("Please enter a positive number");
		exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		
		String expected=mapToJson(exceptionResponse);
		
		String uri="/api/Fibonacci?n=-10";
		Mockito.when(service.fibonacci(Mockito.anyLong())).thenReturn(55l);
		RequestBuilder requestBuilder=MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		String outputJson=result.getResponse().getContentAsString();
		assertThat(outputJson).isEqualTo(expected);
	}
	
	
	@Test
	public void testReverseLetters() throws Exception{
		ExceptionResponse exceptionResponse=new ExceptionResponse();
		exceptionResponse.setMessage("Please enter a positive number");
		exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		
		String expected=mapToJson(exceptionResponse);
		
		String uri="/api/ReverseWords?sentence=how%20are%20you";
		Mockito.when(service.reverseWords(Mockito.anyString())).thenReturn("woh era uoy");
		RequestBuilder requestBuilder=MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		String outputJson=result.getResponse().getContentAsString();
		assertThat(outputJson).isEqualTo("woh era uoy");
	}
	
	
	@Test
	public void testTriangleType() throws Exception{
		String uri="/api/Triangletype?a=1&b=1&c=1";
		Mockito.when(service.triangleType(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt())).thenReturn("Equilateral");
		RequestBuilder requestBuilder=MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		String outputJson=result.getResponse().getContentAsString();
		assertThat(outputJson).isEqualTo("Equilateral");
	}
	
	@Test
	public void testTriangleTypeException() throws Exception{
		ExceptionResponse exceptionResponse=new ExceptionResponse();
		exceptionResponse.setMessage("Please enter a positive value of a,b & c");
		exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		String expected=mapToJson(exceptionResponse);
		
		String uri="/api/Triangletype?a=1&b=1&c=-1";
		Mockito.when(service.triangleType(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt())).thenReturn("Equilateral");
		RequestBuilder requestBuilder=MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		String outputJson=result.getResponse().getContentAsString();
		assertThat(outputJson).isEqualTo(expected);
	}
	
	@Test
	public void testMakeOneArray() throws Exception{
		HashMap<String, List<Integer>> inputHashMap=new HashMap<String, List<Integer>>();
		inputHashMap.put("Array1",new ArrayList<Integer>(Arrays.asList(1,2,3,4)));
		inputHashMap.put("Array2",new ArrayList<Integer>(Arrays.asList(3,4,5,6)));
		inputHashMap.put("Array3",new ArrayList<Integer>(Arrays.asList(6,1,3,11)));
		String inputString=mapToJson(inputHashMap);
		
		HashMap<String, List<Integer>> expectedHashMap=new HashMap<String, List<Integer>>();
		expectedHashMap.put("Array",new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,11)));
		String expectedString=mapToJson(expectedHashMap);
		
		String uri="/api/makeonearray";
		Mockito.when(service.makeOneArray(Mockito.any(HashMap.class))).thenReturn(expectedHashMap);
		RequestBuilder requestBuilder=MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON).content(inputString).contentType(MediaType.APPLICATION_JSON);
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		String outputJson=result.getResponse().getContentAsString();
		assertThat(outputJson).isEqualTo(expectedString);
	}
	
	@Test
	public void testMakeOneArrayException() throws Exception{
		ExceptionResponse responseMsg=new ExceptionResponse();
		responseMsg.setMessage("Please enter atleast a single Array");
		responseMsg.setStatus(HttpStatus.BAD_REQUEST.value());
		
		HashMap<String, List<Integer>> inputHashMap=new HashMap<String, List<Integer>>();
		String inputString=mapToJson(inputHashMap);
		
		HashMap<String, List<Integer>> expectedHashMap=new HashMap<String, List<Integer>>();
		expectedHashMap.put("Array",new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,11)));
		
		String expectedString=mapToJson(responseMsg);
		
		String uri="/api/makeonearray";
		Mockito.when(service.makeOneArray(Mockito.any(HashMap.class))).thenReturn(expectedHashMap);
		RequestBuilder requestBuilder=MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON).content(inputString).contentType(MediaType.APPLICATION_JSON);
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		String outputJson=result.getResponse().getContentAsString();
		assertThat(outputJson).isEqualTo(expectedString);
	}
	
	private String mapToJson(Object object) throws JsonProcessingException{
		ObjectMapper mapper=new ObjectMapper();
		return mapper.writeValueAsString(object);
	}
}
