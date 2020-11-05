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

import com.nibuton.foodclient.model.Ingredient;
import com.nibuton.foodclient.util.parsers.JsonToCollectionParser;

@Service
public class IngredientRestService implements RestService<Ingredient> {

	private RestTemplate restTemplate;
	private String url;
	private String thisEntitiesName;
	
	{
		thisEntitiesName = Ingredient.class.getSimpleName().toLowerCase() + "s";
	}

	@Autowired
	public IngredientRestService(RestTemplateBuilder restTemplateBuilder, @Value("${rest.url}") String url) {
		this.restTemplate = restTemplateBuilder.build();
		this.url = url + thisEntitiesName;
	}
	
	@Override
	public Ingredient getById(int id) {
		return restTemplate.getForObject(url + "/" + id, Ingredient.class);
	}
	
	@Override
	public List<Ingredient> getAll(){
		try {
			ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
			List<Ingredient> ingredients = JsonToCollectionParser.<Ingredient>parse(entity, Ingredient.class);
			return ingredients;
		}
		catch(Exception exc) {
			throw new RuntimeException(exc);
		}
	}
	
	@Override
	public ResponseEntity<String> save(Ingredient ingredient) {
		ResponseEntity<String> response;
		if (ingredient.getId() == 0) {
			response = restTemplate.postForEntity(url, ingredient, String.class);
		} 
		else {
			response = restTemplate.exchange(url + "/" + ingredient.getId(), HttpMethod.PUT, new HttpEntity<>(ingredient), String.class);
		}
		return response;
	}
	
	@Override
	public void deleteById(int id) {
		restTemplate.exchange(url + "/" + id, HttpMethod.DELETE, null, Void.class);
		return;
	}
}
