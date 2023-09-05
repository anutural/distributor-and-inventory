package com.am.reaprich.reaprichbackend.data.repositories.actors.actorprovider;

import com.am.reaprich.reaprichbackend.data.entities.actors.actorprovider.CustomerType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerTypeRepository extends CrudRepository<CustomerType, String> {
}
