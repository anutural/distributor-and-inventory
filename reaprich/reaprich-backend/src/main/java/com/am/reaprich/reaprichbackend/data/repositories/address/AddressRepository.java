package com.am.reaprich.reaprichbackend.data.repositories.address;

import com.am.reaprich.reaprichbackend.data.entities.address.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, String> {
}
