package com.sentence.service;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import com.ibm.json.java.JSONObject;
import com.sentence.CountWords;

@Path("/sentenceservice")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class SentenceService {
	
	@Path("{sentence}")
	@POST
	@Produces("application/json")
	public Response getUnsortedSentence(@PathParam("sentence") String sentence) {
		JSONObject jsonObject = new JSONObject();
		CountWords cw = new CountWords(sentence);
	  cw.getWords().forEach((k,v) -> jsonObject.put(k, v));
		return Response.status(200).entity(jsonObject).build();
	}
}
