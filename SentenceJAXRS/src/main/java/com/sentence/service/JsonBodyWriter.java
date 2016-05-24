package com.sentence.service;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.ibm.json.java.JSONObject;

@Provider
@Produces("application/json")
public class JsonBodyWriter implements MessageBodyWriter<JSONObject> {

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return type==JSONObject.class;
	}

	@Override
	public long getSize(JSONObject t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return t.size();
	}

	@Override
	public void writeTo(JSONObject t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {
		DataOutputStream os = new DataOutputStream(entityStream);
		os.writeUTF(t.toString());
	}


}
