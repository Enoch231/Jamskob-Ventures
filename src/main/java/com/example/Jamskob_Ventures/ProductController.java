package com.example.Jamskob_Ventures;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class ProductController {
	@Autowired
	private ProductRepository repo;
	
	@GetMapping("/products")
	public List<Products> retrieveAllProducts(){
		return repo.findAll();
	}
	
	@GetMapping("/products/{id}")
	public Products retrieveProduct(@PathVariable long id) {
		Optional<Products> product = repo.findById(id);

		if (!product.isPresent())
			throw new ProductNotFoundException("id-" + id);

		return product.get();
	}
	
	@PostMapping("/products")
	public ResponseEntity<Object> createProduct(@RequestBody Products products) {
		Products product = repo.save(products);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(product.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable(name="id") Long id) {
		repo.deleteById(id);
	}

}
