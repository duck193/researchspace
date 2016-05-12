package com.sentence;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SentenceWordCountTest {

	SentenceWordCount swc;
	String[] arrayOfStrings = {"This test how you test for problems and not just to be doing test."};
	
	@Before
	public void setUp() {
		swc = SentenceWordCount.getSentenceWordCount();
	}
	
	@Test
	public void testSentenceWordCountCreated() {
		assertNotNull("Sentence word count was created.", swc);
	}
	
	@Test
	public void testMainMethodWorks() {
		assertSame(swc, SentenceWordCount.getSentenceWordCount());
	}

}
