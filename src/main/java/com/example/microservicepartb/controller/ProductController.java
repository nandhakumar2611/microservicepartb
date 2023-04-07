package com.example.microservicepartb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservicepartb.model.Product;
import com.example.microservicepartb.repository.ProductRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;

	@PostMapping("/product")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		return new ResponseEntity<Product>(productRepository.save(product),HttpStatus.OK);
	}
	
	@GetMapping("/product")
	public ResponseEntity<List<Product>> getAllProduct() {
		
		List<Product> product = new ArrayList<Product>();
		productRepository.findAll().forEach(product::add);
		
		if(product.isEmpty()) {
			return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(product,HttpStatus.OK);
	}
	
	@PutMapping("/product/{id}")
	public ResponseEntity<Product> getuserById(@PathVariable Long id,@RequestBody Product productdetails) {
		
		Optional<Product> productDate = productRepository.findById(id);
		
		if(productDate.isPresent()) {
			Product product = productDate.get();
			product.setName(productdetails.getName());
			product.setDesc(productdetails.getDesc());
			return new ResponseEntity<Product>(productRepository.save(product),HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
		productRepository.deleteById(id);
		return new ResponseEntity<Product>(HttpStatus.OK);
	}

}
