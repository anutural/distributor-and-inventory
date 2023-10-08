package com.am.reaprich.reaprichbackend.data.repositories.actors;

import com.am.reaprich.reaprichbackend.data.entities.actors.TD;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TDRepository extends CrudRepository<TD, String> {
    Optional<TD> findByEmail(String email);

    Optional<TD> findByContactNumber(String number);
}
