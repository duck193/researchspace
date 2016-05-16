package com.sentence.service;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import com.ibm.json.java.JSONObject;
import com.sentence.CountWords;

@Path("/sentenceservice")
@Produces("application/json")
public class SentenceService {
	
	/**
	 * GetUnsortedSentence
	 **
	 * Return unsorted sentence results
	 * @param sentence
	 * @return JSON
	 */
	@Path("/unsorted")
	@GET
	@Produces("application/json")
	public Response getUnsortedSentence(@QueryParam("sentence") String sentence) {
		if (sentence!=null) {
			JSONObject jsonObject = new JSONObject();
			CountWords cw = new CountWords(sentence);
		  cw.getWords().forEach((k,v) -> jsonObject.put(k, v));
			return Response.status(200).entity(jsonObject).build();
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
	@Produces("application/json")
	public Response getSortedSentence(@QueryParam("sentence") String sentence,
																		@QueryParam("sorttype") @DefaultValue("KEY") String sortType) {
		if (sentence!=null) {
			JSONObject jsonObject = new JSONObject();
			CountWords cw = new CountWords(sentence);
		  cw.mapSorter(cw.getWords(), sortType).forEach((k,v) -> jsonObject.put(k, v));
			return Response.status(200).entity(jsonObject).build();
		}
		else {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("MissingParameter", "Sentence parameter was not passed");
			return Response.status(400).entity(jsonObject).build();	
		}
	}
}
