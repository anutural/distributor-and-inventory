package com.am.reaprich.reaprichbackend.data.repositories;

import com.am.reaprich.reaprichbackend.data.entities.Guest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends CrudRepository<Guest, Integer> {
}
