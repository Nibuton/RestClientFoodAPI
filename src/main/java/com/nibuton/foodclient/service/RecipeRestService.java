package com.nibuton.foodclient.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nibuton.foodclient.model.Recipe;
import com.nibuton.foodclient.util.parsers.JsonToCollectionParser;

@Service
public class RecipeRestService implements RestService<Recipe> {
	
	private RestTemplate restTemplate;
	private String url;
	private String thisEntitiesName;
	
	{
		thisEntitiesName = Recipe.class.getSimpleName().toLowerCase() + "s";
	}

	@Autowired
	public RecipeRestService(RestTemplateBuilder restTemplateBuilder, @Value("${rest.url}") String url) {
		this.restTemplate = restTemplateBuilder.build();
		this.url = url + "/" + thisEntitiesName;
	}
	
	@Override
	public Recipe getById(int id) {
		return restTemplate.getForObject(url + "/" + id, Recipe.class);
	}
	
	@Override
	public List<Recipe> getAll(){
		try {
			ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
			List<Recipe> recipes = JsonToCollectionParser.<Recipe>parse(entity, Recipe.class);
			return recipes;
		}
		catch(Exception exc) {
			throw new RuntimeException(exc);
		}
	}
	
	@Override
	public ResponseEntity<String> save(Recipe recipe) {
		ResponseEntity<String> response;
		if (recipe.getId() == 0) {
			response = restTemplate.postForEntity(url, recipe , String.class);
		} 
		else {
			response = restTemplate.exchange(url + "/" + recipe.getId(), HttpMethod.PUT, new HttpEntity<>(recipe), String.class);
		}
		return response;
	} 

	public void addNewRecipe(int recipeId, int ingredientId, double amount) {
		String request = url + "/ingredient" + "?recipeId=" + recipeId 
				+"&ingredientId=" + ingredientId + "&amount=" + amount;
		restTemplate.getForEntity(request, String.class);
		return;
	}
	
	@Override
	public void deleteById(int id) {
		restTemplate.exchange(url + "/" + id, HttpMethod.DELETE, null, Void.class);
		return;
	}
}
