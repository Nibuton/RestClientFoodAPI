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
	
	/*@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}*/
	
	@Bean
	public RestTemplateBuilder restTemplateBuilder() {
		return new RestTemplateBuilder();
	}
	
	@Bean
	CommandLineRunner testGetAll(RecipeRestService recipeRestService, IngredientRestService ingredientRestService) {
		/*Recipe recipe = recipeRestService.getById(1);
		recipe.setDescription("test");
		recipe.setName("test");
		System.out.println(recipe);
		recipeRestService.save(recipe);
		rec.setId(1);
		rec.setName("pipka");
		recipeRestService.save(rec);
		Ingredient ingredient = ingredientRestService.getById(1);
		System.out.println(ingredient);
		ingredient.setRemaining(8.1);
		ingredientRestService.save(ingredient);
		Ingredient ing2 = new Ingredient();
		ing2.setName("salt");
		ing2.setRemaining(1000000.1);
		ingredientRestService.save(ing2); */
		
		Recipe recipe = recipeRestService.getById(1);
		System.out.println(recipe);
		recipe.setDescription("aaa");
		recipe.setName("cccc");
		recipeRestService.save(recipe);
		
		Ingredient ingredient = ingredientRestService.getById(1);
		System.out.println(ingredient);
		ingredient.setRemaining(0.222222);
		String response = ingredientRestService.save(ingredient).getBody();
		System.out.println(response);
		
		recipeRestService.addNewRecipe(recipe.getId(), ingredient.getId(), 56789.1111);
	    return args -> { 
	    };
	}
	  
}
