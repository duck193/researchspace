package com.sentence.service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

import com.uline.embedded.LibertyEmbedded;

public class SentenceServiceTest {

	LibertyEmbedded le;
	
	@Before
	public void setUp() throws Exception {
		le = new LibertyEmbedded();
		le.startEmbeddedServer();
	}

	@Test
	public void test() throws IOException {
		StringBuilder sb = new StringBuilder();
		byte data;
		InputStream stream;
		try {
			URL reqeust = new URL("http://localhost:9080/SentenceJAXRS-1.0/sentence/running");
			stream = reqeust.openStream();
			while((data = (byte)stream.read()) != -1) {
				sb.append((char)data);
			}
			stream.close();
		}	catch(IOException e) { e.printStackTrace(); } 
		assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <ServiceRunning>The server is up and running for SentenceService</ServiceRunning>", sb.toString());
	}

}
