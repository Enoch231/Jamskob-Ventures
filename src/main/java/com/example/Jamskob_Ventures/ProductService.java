package com.example.Jamskob_Ventures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductRepository repos;
	
	public void save(Products product) {
		repos.save(product);
	}
	
	public Products get(Long id) {
		return repos.findById(id).get();
	}
	
	public void delete (Long id) {
		repos.deleteById(id);
	}

}
