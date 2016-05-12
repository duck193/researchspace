package com.sentence;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class CountWordsTest {

	CountWords cw;
		
	@Before
	public void setUp() throws Exception {
		cw = new CountWords("This is the time for all good test. What time is it?");
	}

	@Test
	public void wordsMapIsPopulated() {
		assertTrue(cw.getWords().size() > 0);
	}
	
	@Test
	public void wordCountIsCorrect(){
		Integer timeCount = cw.getWords().get("time");
		assertTrue(timeCount == 2);
	}
	
	@Test
	public void sortByValueIsCorrect() {
		Map<String, Integer> sortedMap = cw.mapSorter(cw.getWords(), "VALUE");
		assertTrue(sortedMap.get("is") == 2);
	}

	@Test
	public void sortByKeyIsCorrect() {
		Map<String, Integer> sortedMap = cw.mapSorter(cw.getWords(), "KEY");
		assertTrue(sortedMap.get("all") == 1);
	}
}
