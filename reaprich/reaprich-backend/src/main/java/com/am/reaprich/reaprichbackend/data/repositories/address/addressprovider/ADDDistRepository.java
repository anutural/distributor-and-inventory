package com.am.reaprich.reaprichbackend.data.repositories.address.addressprovider;

import com.am.reaprich.reaprichbackend.data.entities.address.addressprovider.ADDDist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ADDDistRepository extends CrudRepository<ADDDist, String> {
}
