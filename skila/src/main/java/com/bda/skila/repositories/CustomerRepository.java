package com.bda.skila.repositories;

import com.bda.skila.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c.customerId FROM Customer c ORDER BY c.customerId DESC")
    Long findLastCustomeriD();
}
