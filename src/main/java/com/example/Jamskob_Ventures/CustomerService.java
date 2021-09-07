package com.example.Jamskob_Ventures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository repo;
	
	public void save(Customers customers) {
		repo.save(customers);
	}
	
	public Customers get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete (Long id) {
		repo.deleteById(id);
	}

}
