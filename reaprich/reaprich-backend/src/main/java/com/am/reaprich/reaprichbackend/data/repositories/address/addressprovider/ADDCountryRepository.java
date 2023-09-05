package com.am.reaprich.reaprichbackend.data.repositories.address.addressprovider;

import com.am.reaprich.reaprichbackend.data.entities.address.addressprovider.ADDCountry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ADDCountryRepository extends CrudRepository<ADDCountry, String> {
}
