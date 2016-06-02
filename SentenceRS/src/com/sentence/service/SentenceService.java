package com.sentence.service;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ibm.json.java.JSONObject;
import com.sentence.CountWords;

@ApplicationPath("sentence")
@Path("/service")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class SentenceService extends javax.ws.rs.core.Application {
	
	@Path("/running")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response isServiceRunning() {
		return Response.ok()
				           .entity("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <ServiceRunning>The server is up and running for SentenceService</ServiceRunning>")
				           //.header("Access-Control-Allow-Orgin", "http://swagger.io")
				           //.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept")
									 .header("Access-Control-Allow-Orgin", "*")
								 	 .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")				           
				           .build();

	}
	
	/**
	 * GetUnsortedSentence
	 **
	 * Return unsorted sentence results
	 * @param sentence
	 * @return JSON
	 */
	@Path("/unsorted")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUnsortedSentence(@QueryParam("sentence") String sentence) {
		if (sentence!=null) {
			System.out.println("****** getUnsortedSentence");
			System.out.println("****** --- sentence : " + sentence);
			JSONObject jsonObject = new JSONObject();
			CountWords cw = new CountWords(sentence);
		  cw.getWords().forEach((k,v) -> jsonObject.put(k, v));
		  System.out.println("****** --- JSON : " + jsonObject.toString());
			return Response.ok().entity(jsonObject)
					 .header("Access-Control-Allow-Orgin", "*")
				 	 .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					 					 .build();
		}
		else {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("MissingParameter", "Sentence parameter was not passed");
			return Response.status(400).entity(jsonObject).build();
		}
	}
	
	/**
	 * GetSortedSentence
	 **
	 * Return sorted sentence results
	 * @param sentence
	 * @param sorttype
	 * @return JSON
	 */
	@Path("/sorted")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSortedSentence(@QueryParam("sentence") String sentence,
																		@QueryParam("sorttype") @DefaultValue("KEY") String sortType) {
		if (sentence!=null) {
			JSONObject jsonObject = new JSONObject();
			CountWords cw = new CountWords(sentence);
		  cw.mapSorter(cw.getWords(), sortType).forEach((k,v) -> jsonObject.put(k, v));
			return Response.ok().entity(jsonObject)
          											 .header("Access-Control-Allow-Orgin", "*")
        											 	 .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
        											 	 .build();
		}
		else {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("MissingParameter", "Sentence parameter was not passed");
			return Response.status(400).entity(jsonObject).build();	
		}
	}
	
}
