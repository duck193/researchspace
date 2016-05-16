package com.josephrichard.redhat;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringNamesTest {

	private String[] itemsToAdd = {"This", "is", "what", "a", "String", "Array", "looks", "like"};
	private StringNames sn = new StringNames();
	
	@Test
	public void stringBufferTest() {
		String bufferOutput = sn.addStringItems(itemsToAdd, false);
		assertTrue(bufferOutput.equals("ThisiswhataStringArraylookslike"));
	}
	
	@Test
	public void stringBufferUpperCaseTest() {
		String bufferOutput = sn.addStringItems(itemsToAdd, true);
		assertTrue(bufferOutput.equals(("ThisiswhataStringArraylookslike").toUpperCase()));		
	}
	
	@Test
	public void arrayListTest() {
		String listOutput = sn.addStringItemsCollectionsFramework(itemsToAdd, false);
		assertTrue(listOutput.equals("ThisiswhataStringArraylookslike"));
	}

	@Test
	public void arrayListUpperCaseTest() {
		String listOutput = sn.addStringItemsCollectionsFramework(itemsToAdd, true);
		assertTrue(listOutput.equals(("ThisiswhataStringArraylookslike").toUpperCase()));
	}

}
