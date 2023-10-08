package com.am.reaprich.reaprichbackend.data.repositories.auth;

import com.am.reaprich.reaprichbackend.data.entities.auth.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, String> {
    @Query(value = "select t from Token t inner join AppUser u on t.appUser.id = u.id where u.id = :id and (t.expired = false or t.revoked = false)")
    List<Token> findAllValidTokenByUser(String id);

    Optional<Token> findByToken(String token);
}
