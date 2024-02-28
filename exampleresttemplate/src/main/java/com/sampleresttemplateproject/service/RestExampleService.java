package com.sampleresttemplateproject.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.sampleresttemplateproject.exception.ProductNotFoundException;
import com.sampleresttemplateproject.model.Product;

@Service
public class RestExampleService {
	
	@Autowired
	RestTemplate restTemplate;
	
	public Product createProduct(Product product) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Product> request = new HttpEntity<>(product,headers);
		ResponseEntity<Product> response = restTemplate.exchange("http://localhost:8081/api/createProduct", HttpMethod.POST, request, Product.class);
		if(response.getStatusCode().is2xxSuccessful()) {
			return response.getBody();
		}else {
			return null;
		}
	}
	
	public List<Product> getAllProducts(){
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Object> request = new HttpEntity<>(headers);
		ResponseEntity<Product[]> response = restTemplate.exchange("http://localhost:8081/api/allProducts",HttpMethod.GET, request,Product[].class);
		if(response.getStatusCode().is2xxSuccessful()) {
			return Arrays.asList(response.getBody());
		}else {
			return Collections.emptyList();
			//throw new ProductNotFoundException("no products found");
		}
	}
	
	public Product getProductById(long productId) {
		String url = "http://localhost:8081/api/allProducts/" + productId;
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Object> request = new HttpEntity<>(headers);
		ResponseEntity<Product> response = restTemplate.exchange(url, HttpMethod.GET, request, Product.class);
		if(response.getStatusCode() == HttpStatusCode.valueOf(404)) {
			//return response.getBody();
			throw new ProductNotFoundException("no products found", "no products found");
		}
		return response.getBody();
	}
	
	public String deleteProductByID(long productId) {
	    String url = "http://localhost:8081/api/allProducts/" + productId;
	    HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity<Object> request = new HttpEntity<>(headers);
	    
	    try {
	        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);
	        
	        if (response.getStatusCode() == HttpStatus.OK) {
	            return "Product with ID " + productId + " deleted successfully";
	        } else {
	            return "Failed to delete product with ID " + productId;
	        }
	    } catch (HttpClientErrorException.NotFound ex) {
	        return "Product with ID " + productId + " not found";
	    } catch (RestClientException ex) {
	        return "Error occurred while deleting product with ID " + productId + ": " + ex.getMessage();
	    }
	}
	
	public String updateProduct(Product product) {
		String url = "http://localhost:8081/api/allProducts";
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Object> request = new HttpEntity<>(product,headers);
		try {
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
			if(response.getStatusCode() == HttpStatus.OK) {
				return "Product Updated Successfully";
			}else {
				return "Something went wrong while updating";
			}
		}
		catch (Exception e) {
			return " Someting Error While Updating please try again";
		}
	}
	
}
