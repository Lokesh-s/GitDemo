package com.utility.controller;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.utility.exception.EmptyArrayException;
import com.utility.exception.ImproperDataException;
import com.utility.exception.NotTriangleException;
import com.utility.service.UtilityService;
import com.utility.service.UtilityServiceImpl;
import com.utility.utility.Operations;

@RestController
@RequestMapping(value="/api")
//Added Comment
//Added Comment
public class UtilityController {
	
	@Autowired
	UtilityService service;
	
	@Autowired
	Operations operations;
	
	private Logger logger=LoggerFactory.getLogger(UtilityController.class);
	
	@GetMapping("/Fibonacci")
	public ResponseEntity<Long> fibonacciNum(@RequestParam("n") long number) throws ImproperDataException{
		logger.info("inside UtilityController::fibonacciNum()");
		if (number<0) {
			throw new ImproperDataException("Please enter a positive number");
		}else if (number>45) {
			throw new ImproperDataException("Please enter a number < 45");
		}
		Long result=service.fibonacci(number);
		HttpHeaders headers=operations.getHeaders();
		return new ResponseEntity<Long>(result,headers,HttpStatus.OK);
	}
	
	@GetMapping("/ReverseWords")
	public ResponseEntity<String> reverseLetters(@RequestParam("sentence") String word) throws ImproperDataException{
		logger.info("inside UtilityController::reverseLetters()");
		if (word==null || word.length()==0) {
			throw new ImproperDataException("Please enter a word to reverse");
		}
		HttpHeaders headers=operations.getHeaders();
		return new ResponseEntity<String>(service.reverseWords(word),headers,HttpStatus.OK);
	}
	
	
	@GetMapping("/Triangletype")
	public ResponseEntity<String> triangletype(@RequestParam("a") int a,@RequestParam("b") int b,@RequestParam("c") int c) throws NotTriangleException{
		logger.info("inside UtilityController::triangletype()");
		if (a<0 || b<0 || c<0) {
			throw new NotTriangleException("Please enter a positive value of a,b & c");
		}else if (a==0 || b==0 || c==0) {
			throw new NotTriangleException("a,b & c value cannot be zero");
		}
		HttpHeaders headers=operations.getHeaders();
		return new ResponseEntity<String>(service.triangleType(a, b, c),headers,HttpStatus.OK);
	}
	
	@PostMapping("/makeonearray")
	public ResponseEntity<HashMap<String, List<Integer>>> makeOneArray(@RequestBody HashMap<String, List<Integer>> arrays) throws EmptyArrayException{
		logger.info("inside UtilityController::makeOneArray()"+arrays);
		HashMap<String, List<Integer>> hashMap=service.makeOneArray(arrays);
		if (arrays==null || arrays.isEmpty()) {
			throw new EmptyArrayException("Please enter atleast a single Array");
		}
		HttpHeaders headers=operations.getHeaders();
		return new ResponseEntity<HashMap<String, List<Integer>>>(hashMap,headers,HttpStatus.OK);
	}
}
