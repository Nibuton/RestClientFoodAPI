package com.nibuton.foodclient.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

import com.nibuton.foodclient.model.Ingredient;
import com.nibuton.foodclient.model.Recipe;
import com.nibuton.foodclient.service.IngredientRestService;
import com.nibuton.foodclient.service.RecipeRestService;
import com.nibuton.foodclient.service.RestService;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan("com.nibuton.foodclient")
public class Config {
	
	@Bean
	public RestTemplateBuilder restTemplateBuilder() {
		return new RestTemplateBuilder();
	}
	
	@Bean
	CommandLineRunner testGetAll(RecipeRestService recipeRestService, IngredientRestService ingredientRestService) {
	    return args -> { 
	    };
	}
	  
}
