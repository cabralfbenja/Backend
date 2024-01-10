package com.oveja.poc.repositories;

import com.oveja.poc.entities.support.CustomerForAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerForAddressRepository extends JpaRepository<CustomerForAddress, Long> {
    List<CustomerForAddress> findCustomerForAddressByCustomerId(Long customerId);
}
