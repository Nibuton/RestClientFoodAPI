package com.nibuton.foodclient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nibuton.foodclient.model.Recipe;
import com.nibuton.foodclient.service.RecipeRestService;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
	
	private RecipeRestService recipeRestService;
	
	@Autowired
	public RecipeController(RecipeRestService recipeRestService) {
		this.recipeRestService = recipeRestService;
	} 
	
	@GetMapping("/getrecipe")
	public Recipe getRecipeById(@RequestParam("id") int id) {
		return recipeRestService.getById(id);
	}
	
	@GetMapping("/getrecipes")
	public List<Recipe> getAllRecipes(){
		return recipeRestService.getAll();
	}
	
	@PostMapping("/addNewRecipe")
	public void addNewRecipe(@RequestParam("recipeID") int recipeId, @RequestParam("ingredientId") int ingredientId, @RequestParam("amount") double amount) {
		recipeRestService.addNewRecipe(recipeId, ingredientId, amount);
		return;
	}
	
	@PostMapping("/saverecipe")
	public ResponseEntity<String> saveRecipe(Recipe recipe) {
		return recipeRestService.save(recipe);
	}
	
	@PutMapping("/updaterecipe")
	public ResponseEntity<String> updateRecipe(Recipe recipe) {
		return recipeRestService.save(recipe);
	}
	
	@DeleteMapping("/deleterecipe")
	public void deleteRecipeBiId(@RequestParam("id") int id){
		recipeRestService.deleteById(id);
		return;
	}

}
