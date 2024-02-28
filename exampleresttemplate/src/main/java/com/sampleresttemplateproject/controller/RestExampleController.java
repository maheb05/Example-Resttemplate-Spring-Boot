package com.sampleresttemplateproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.sampleresttemplateproject.exception.ProductNotFoundException;
import com.sampleresttemplateproject.model.Product;
import com.sampleresttemplateproject.service.RestExampleService;

@RestController
@RequestMapping("/api")
public class RestExampleController {
	
	@Autowired
	private RestExampleService restExampleService;
	
	@PostMapping("/products")
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
		return ResponseEntity.ok().body(this.restExampleService.createProduct(product));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Product>> getAllProducts(){
		return ResponseEntity.ok().body(this.restExampleService.getAllProducts());
	}
	
	@GetMapping("/all/{productId}")
	public ResponseEntity<?> getProductById(@PathVariable long productId){
		System.out.println(productId);
		Product product = restExampleService.getProductById(productId);
		if(product == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no record found with this "+productId+" id");
		}
		else {
			return ResponseEntity.ok().body(product);
		}
	}
	
//	@GetMapping("/all/{productId}")
//	public ResponseEntity<?> getProductById(@PathVariable long productId){
//	    try {
//	        Product product = restExampleService.getProductById(productId);
//	        return ResponseEntity.ok().body(product);
//	    } catch (ProductNotFoundException e) {
//	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No record found with this " + productId + " id");
//	    }
//	}

	
	
	@DeleteMapping("/products/{productId}")
    public String deleteProductById(@PathVariable long productId) {
        return restExampleService.deleteProductByID(productId);
    }
	
	@PutMapping("/update")
	public String updateProduct(@RequestBody Product product) {
	 	return restExampleService.updateProduct(product);
	}
}

