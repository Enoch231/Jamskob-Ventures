package com.example.Jamskob_Ventures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class CustomerDetailsService implements UserDetailsService{
	
	@Autowired
	private CustomerRepository repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Customers customer = repo.findByEmail(email);
		if (email == null) {
			throw new UsernameNotFoundException("Customer Not Found");
		}
		
		return new CustomerDetails(customer);
	}

}
