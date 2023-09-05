package com.am.reaprich.reaprichbackend.data.repositories.actors.actorprovider;

import com.am.reaprich.reaprichbackend.data.entities.actors.actorprovider.ActorType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorTypeRepository extends CrudRepository<ActorType, String> {
}
