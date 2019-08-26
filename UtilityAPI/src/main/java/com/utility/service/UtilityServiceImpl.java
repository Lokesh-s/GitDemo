package com.utility.service;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utility.utility.Operations;

@Service
public class UtilityServiceImpl implements UtilityService{

	@Autowired
	Operations operations;
	
	private Logger logger=LoggerFactory.getLogger(UtilityServiceImpl.class);
	
	@Override
	public long fibonacci(long number) {
		logger.info("inside fibonacci(long number)");
		return operations.calNthFibonacci(number);
	}

	@Override
	public String reverseWords(String word) {
		logger.info("inside reverseWords(String word)");
		return operations.reverseWordByWord(word);
	}

	@Override
	public String triangleType(int a, int b, int c) {
		logger.info("inside triangleType(int a, int b, int c)");
		return operations.type(a, b, c);
	}

	@Override
	public HashMap<String, List<Integer>> makeOneArray(HashMap<String, List<Integer>> array) {
		logger.info("inside makeOneArray(HashMap<String, List<Integer>> array)");
		return operations.makeOneArray(array);
	}
	
}
