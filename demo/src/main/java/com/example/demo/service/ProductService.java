package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	public List<Product> listAllProducts(){
		return repository.findAll();
	}
	
	public void saveProduct(Product product) {
		repository.save(product);
	}
	
	public Product getProduct(Long id) {
		return repository.findById(id).get();
	}
	
	public void deleteProduct(Long id) {
		repository.deleteById(id);
	}
}
