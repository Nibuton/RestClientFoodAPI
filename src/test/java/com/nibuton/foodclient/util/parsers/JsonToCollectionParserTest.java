package com.nibuton.foodclient.util.parsers;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.nibuton.foodclient.model.Ingredient;
import com.nibuton.foodclient.util.readers.JsonFileToStringReader;

class JsonToCollectionParserTest {

	@Test
	void testParse() throws JsonMappingException, JsonProcessingException {
		File file = new File("/home/nibuton/Рабочий стол/Udemy Spring Course/foodclient/src/main/resources/ingredients.json");
		ResponseEntity<String> response = new ResponseEntity<String>(JsonFileToStringReader.readJsonFileToString(file), HttpStatus.OK);
		List<Ingredient> ingredients = JsonToCollectionParser.parse(response, Ingredient.class);
		assertEquals(ingredients.get(1).getName(),"bread");
	}
}
