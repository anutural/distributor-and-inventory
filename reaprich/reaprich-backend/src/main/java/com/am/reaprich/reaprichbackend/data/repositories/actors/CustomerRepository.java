package com.am.reaprich.reaprichbackend.data.repositories.actors;

import com.am.reaprich.reaprichbackend.data.entities.actors.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {
    Optional<Customer> findByContactNumber(String email);
}
