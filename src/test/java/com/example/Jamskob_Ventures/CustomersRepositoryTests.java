package com.example.Jamskob_Ventures;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE )
@Rollback(false )
public class CustomersRepositoryTests {
	
	@Autowired
	private CustomerRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateEmployee() {
		Customers customer = new Customers();
		customer.setEmail("maverick@gmail.com");
		customer.setPassword("fantastic");
		customer.setFirstName("Curry");
		customer.setLastName("White");
		
		Customers savedCustomer= repo.save(customer);
		
		Customers existCustomer =entityManager.find(Customers.class, savedCustomer.getId());
		
		assertThat(existCustomer.getEmail()).isEqualTo(customer.getEmail());
	}
	
	@Test	
	public void testFindEmployeeByEmail() {
		String email = "koby@gmail.com";
		
		Customers customer = repo.findByEmail(email);
		
		assertThat(customer).isNotNull();
		
		

	}

}
