package com.interswitchug.phoenix.simulator.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONDataTransform {
	public static String marshall(Object object) throws Exception {
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
			String str = mapper.writeValueAsString(object);
			return str;
	}
	
	public static <T> List<T> unmarshallList(String json,Class<T> objectClass) throws Exception
	{		
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(ArrayList.class, objectClass));
	}


	public static <T> Object unmarshall(String json, Class<T> theClass) throws Exception {
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return  mapper.readValue(json, theClass);
	}
}
