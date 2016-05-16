package com.josephrichard.redhat;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author XFP3
 */
public class RangeImplemTest {

	RangeImplem range = new RangeImplem();
	
	@Before
	public void setUp() {
		range.newRange(1, 5);
	}
	
	@Test
	public void rangeWasCreated() {
		assertTrue(range!=null);
	}
	
	@Test
	public void rangeNotGreaterThanFive() {
		assertFalse(range.isIn(6));
	}
	
	@Test
	public void increaseRangeToTen() {
		range.add(range.newRange(8, 10));
		assertTrue(range.isIn(9));
	}
	
	@Test
	public void sixShouldHaveBeenSkipped() {
		assertFalse(range.isIn(6));
	}
}
