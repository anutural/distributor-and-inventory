package com.am.reaprich.reaprichbackend.data.repositories.actors;

import com.am.reaprich.reaprichbackend.data.entities.actors.Outlet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OutletRepository extends CrudRepository<Outlet, String> {

    Optional<Outlet> findByOwnerContactNumber(String ownerContactNumber);
    Optional<Outlet> findByEmail(String email);

}
