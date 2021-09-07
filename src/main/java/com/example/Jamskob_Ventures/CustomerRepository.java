package com.example.Jamskob_Ventures;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CustomerRepository extends JpaRepository<Customers, Long> {
	
	@Query("SELECT e FROM Customers e WHERE e.email = ?1")
	Customers findByEmail(String Email);

}
