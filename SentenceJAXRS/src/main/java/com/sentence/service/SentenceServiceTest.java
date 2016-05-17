package com.sentence.service;

import org.junit.Rule;
import org.junit.*;
import org.junit.runner.RunWith;
import com.eclipsesource.restfuse.*;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.HttpTest;

import junit.framework.TestCase;

@RunWith( HttpJUnitRunner.class )
public class SentenceServiceTest extends TestCase {

	@Rule
	public Destination destination = new Destination( this, "http//localhost:8080");
	
	@Context
	private Response response;
	
	@HttpTest( method = Method.GET, path="/sentenceservice")
	public void testServiceIsUp() throws Exception {
		assertNotNull( response );
	}
	
}
