package com.nibuton.foodclient.util.readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonFileToStringReader {

	public JsonFileToStringReader() {
		// TODO Auto-generated constructor stub
	}
	
	public static String readJsonFileToString(File file){
		
		String str;
		StringBuilder respond = new StringBuilder();
		
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
			while ((str = bufferedReader.readLine()) != null) {
			  respond.append(str); 
			} 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		  
		return respond.toString();
	}

}
