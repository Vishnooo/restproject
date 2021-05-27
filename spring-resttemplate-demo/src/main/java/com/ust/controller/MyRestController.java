package com.ust.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ust.model.Product;

@RestController
@RequestMapping("resttemplate")
public class MyRestController {
	
	URI uri = URI.create("http://localhost:9092/getDataFromCloud");
	URI producturi = URI.create("http://localhost:8082/product");
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping
	public List<Product> pro(){
		ResponseEntity<Product[]> response = restTemplate.getForEntity(producturi, Product[].class);
		return Arrays.asList(response.getBody());
	} 	 	 	
	
	@PostMapping
	public ResponseEntity<String> insert(@RequestBody Product product){
		ResponseEntity<String> response = restTemplate.postForEntity(producturi, product, String.class);
		return response;
	} 
	
	@PutMapping
	public ResponseEntity<String> update(@RequestBody Product product){
//		ResponseEntity<String> response = restTemplate.postForEntity(producturi, product, String.class);
		/*ResponseEntity<String> response = */restTemplate.put(producturi, product);
		return new ResponseEntity<String>("The product with Id :- " + product.getProductId() + " has been updated.", HttpStatus.OK);
	}
	
	@DeleteMapping("/{productId}")
	public ResponseEntity<String> delete(@PathVariable int productId){
		/*ResponseEntity<String> response =*/ restTemplate.delete(producturi + "/" + productId);
		return new ResponseEntity<String>("Product with Id :- " + productId + "has been deleted.", HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/getDataFromCloud")
	public ResponseEntity<String> hi(){
		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
		return response;
	} 	 	
}
