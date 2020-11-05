package com.nibuton.foodclient.util.parsers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

public class JsonToCollectionParser {

	public JsonToCollectionParser() {
		// TODO Auto-generated constructor stub
	}
	
	public static <T> List<T> parse(ResponseEntity<String> response, Class<T> instance) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String body = response.getBody();
		JsonNode node = mapper.readTree(body).get("_embedded").get(instance.getSimpleName().toLowerCase() + "s");
		String value = mapper.writeValueAsString(node);
		CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, instance);
		List<T> ts = mapper.readValue(value, listType);
		return ts;
	}

}
