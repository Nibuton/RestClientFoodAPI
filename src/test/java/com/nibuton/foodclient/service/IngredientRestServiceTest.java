package com.nibuton.foodclient.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.nibuton.foodclient.model.Ingredient;
import com.nibuton.foodclient.util.readers.JsonFileToStringReader;

@RestClientTest(IngredientRestService.class)
class IngredientRestServiceTest {
	
	@Autowired
    private MockRestServiceServer server;
	
	@Autowired
	private IngredientRestService client;
	
	@Autowired
	private Environment env;
	
	@Test
	public void whenGetById_thenCorrectResponse() throws IOException {

		String url = env.getProperty("rest.url") + "/ingredients/1";
		File file = new File("/home/nibuton/Рабочий стол/Udemy Spring Course/foodclient/src/main/resources/ingredient.json");
		String respond = JsonFileToStringReader.readJsonFileToString(file);
		
		server.expect(requestTo(url)).
			andExpect(method(HttpMethod.GET)).
				andRespond(withSuccess(respond, MediaType.APPLICATION_JSON));
		
		Ingredient getIngredient = client.getById(1);
		
		assertEquals(getIngredient.getName(),"basilico");
		
	}
	
	@Test
	public void whenGetAll_thenCorrectResponse() {
		
		String url = env.getProperty("rest.url") + "/ingredients";
		File file = new File("/home/nibuton/Рабочий стол/Udemy Spring Course/foodclient/src/main/resources/ingredients.json");
		String str = JsonFileToStringReader.readJsonFileToString(file);
		
		server.expect(requestTo(url)).
		andExpect(method(HttpMethod.GET)).
			andRespond(withSuccess(str, MediaType.APPLICATION_JSON));
		
		List<Ingredient> ingredients = client.getAll();
		
		assertEquals(ingredients.get(1).getName(),"bread");
	}
	
	@Test
	public void whenSaveNew_thenCorrectResponse(){
		
		String url = env.getProperty("rest.url") + "/ingredients";
		File file = new File("/home/nibuton/Рабочий стол/Udemy Spring Course/foodclient/src/main/resources/ingredient.json");
		String str = JsonFileToStringReader.readJsonFileToString(file);
		
		server.expect(requestTo(url)).
		andExpect(method(HttpMethod.POST)).
			andRespond(withSuccess(str, MediaType.APPLICATION_JSON));
		
		String response = client.save(new Ingredient()).getBody();
		
		assertEquals(response,str);
		
	}
	
	@Test
	public void whenSaveExisting_thenCorrectResponse(){
		
		Ingredient ingr = new Ingredient();
		ingr.setId(1);
		
		String url = env.getProperty("rest.url") + "/ingredients/1";
		File file = new File("/home/nibuton/Рабочий стол/Udemy Spring Course/foodclient/src/main/resources/ingredient.json");
		String str = JsonFileToStringReader.readJsonFileToString(file);
		
		server.expect(requestTo(url)).
		andExpect(method(HttpMethod.PUT)).
			andRespond(withSuccess(str, MediaType.APPLICATION_JSON));
		
		String response = client.save(ingr).getBody();
		
		assertEquals(response,str);
		
	}

}
