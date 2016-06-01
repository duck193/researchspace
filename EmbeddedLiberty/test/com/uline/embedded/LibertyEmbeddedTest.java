package com.uline.embedded;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LibertyEmbeddedTest {

	private LibertyEmbedded le;

	@After
	public void tearDown() {
		le = null;
	}
	
	@Before
	public void setUp() {
		le = new LibertyEmbedded();
	}
		
	@Test
	public void server1StartsUp() throws InterruptedException, ExecutionException {
		assertTrue(le.startEmbeddedServer());
	}
	
	@Test
	public void server2Deploy() {
		assertTrue(le.deployWar("C:/Users/joe.richard/ProjectWorkspaces/researchspace/SentenceJAXRS/target", "SentenceJAXRS-1.0.war"));
	}
	
	@Test
	public void server3Stops() throws InterruptedException, ExecutionException {
		assertTrue(le.stopEmbeddedServer());
	}

}
