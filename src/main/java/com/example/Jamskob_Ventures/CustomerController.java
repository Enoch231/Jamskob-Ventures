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
public class CustomerController {
	@Autowired
	private CustomerRepository repo;
	
	@GetMapping("/customers")
	public List<Customers> retrieveAllCustomers(){
		return repo.findAll();
	}
	
	@GetMapping("/customers/{id}")
	public Customers retrieveCustomer(@PathVariable long id) {
		Optional<Customers> customer = repo.findById(id);

		if (!customer.isPresent())
			throw new CustomerNotFoundException("id-" + id);

		return customer.get();
	}
	
	@PostMapping("/customers")
	public ResponseEntity<Object> createCustomer(@RequestBody Customers customers) {
		Customers customer = repo.save(customers);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(customer.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/customers/{id}")
	public void deleteCustomer(@PathVariable(name="id") Long id) {
		repo.deleteById(id);
	}
	

}
