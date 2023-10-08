package com.am.reaprich.reaprichbackend.data.repositories.auth;

import com.am.reaprich.reaprichbackend.data.entities.auth.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AppUserRepository extends CrudRepository <AppUser, String> {
    Optional<AppUser> findByEmail(String email);
}
