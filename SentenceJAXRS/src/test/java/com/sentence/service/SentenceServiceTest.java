package com.sentence.service;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.Servlet;
import javax.ws.rs.core.Response;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ibm.json.java.JSONObject;

public class SentenceServiceTest {

	private Tomcat server;
	private String webPort;
	private URL endPoint;
	
	@Before
	public void setUp() throws Exception {
		server = new Tomcat();
		server.setBaseDir("C:/apache-tomcat-8.0.35");
		server.setHostname("localhost");
		webPort = System.getenv("TEST_PORT");
		if(webPort == null || webPort.isEmpty()) {
			webPort = "8888";
		}
		
		server.setPort(Integer.valueOf(webPort));
		Context ctx = server.addContext("", new File(System.getProperty("java.io.tmpdir")).getAbsolutePath());
		Tomcat.addServlet(ctx, "SentenceREST", "com.sentence.service.SentenceService");
		ctx.addServletMapping("/sentence/*", "SentenceREST");
		server.start();
		server.getServer().await();
	}

	@After
	public void tearDown() throws Exception {
		server.stop();
		server.destroy();
	}

	@Test
	public void isServerRunning() throws Exception {
		endPoint = new URL("http://localhost:" + webPort + "/sentenceservice/running");
		HttpURLConnection con = (HttpURLConnection) endPoint.openConnection();
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		assertTrue(responseCode==200);
	}
	
	@Test
	public void checkUnsorted() throws Exception {
		endPoint = new URL("http://localhost:" + webPort + "/sentenceservice/unsorted");
		HttpURLConnection con = (HttpURLConnection) endPoint.openConnection();
		con.setRequestMethod("GET");
		JSONObject jsonObject = (JSONObject)con.getContent();
		Integer with = (Integer)jsonObject.get("with");
		assertTrue(with.equals("1"));
	}

}
