package com.nibuton.foodclient.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface RestService<T> {
	
	T getById(int id);
	
	List<T> getAll();
	
	ResponseEntity<String> save(T t); 
	
	void deleteById(int id);
}
