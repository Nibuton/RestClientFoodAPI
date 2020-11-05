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

import com.nibuton.foodclient.model.Ingredient;
import com.nibuton.foodclient.service.IngredientRestService;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
	
	private IngredientRestService ingredientRestService;

	@Autowired
	public IngredientController(IngredientRestService ingredientRestService) {
		this.ingredientRestService = ingredientRestService;
	} 
	
	@GetMapping("/getingredient")
	public Ingredient getIngredientById(@RequestParam("id") int id) {
		return ingredientRestService.getById(id);
	}
	
	@GetMapping("/getingredients")
	public List<Ingredient> getAllIngredients(){
		return ingredientRestService.getAll();
	}
	
	@PostMapping("/saveingredient")
	public ResponseEntity<String> saveIngredient(Ingredient ingredient) {
		return ingredientRestService.save(ingredient);
	}
	
	@PutMapping("/updateingredient")
	public ResponseEntity<String> updateIngredient(Ingredient ingredient) {
		return ingredientRestService.save(ingredient);
	}
	
	@DeleteMapping("/deleteingredient")
	public void deleteIngredientById(@RequestParam("id") int id){
		ingredientRestService.deleteById(id);
		return;
	}
	

}
