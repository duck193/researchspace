package com.sentence;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class CountWordsTest {

	CountWords cw;
		
	@Before
	public void setUp() throws Exception {
		cw = new CountWords("This is the time for all good test. What time is it for this?");
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
		assertTrue(sortedMap.get("this") == 2);
	}

	@Test
	public void sortByKeyIsCorrect() {
		Map<String, Integer> sortedMap = cw.mapSorter(cw.getWords(), "KEY");
		assertTrue(sortedMap.get("all") == 1);
	}
	
	@Test
	public void checkSortOrder() {
		cw = new CountWords("This is that, That is this, thIs tHis this");
		Map<String, Integer> sortedMap = cw.mapSorter(cw.getWords(), "VALUE");
		assertTrue(sortedMap.get("this")==5);
	}
	
}
