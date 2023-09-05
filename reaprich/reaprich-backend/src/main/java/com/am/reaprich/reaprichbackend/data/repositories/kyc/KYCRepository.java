package com.am.reaprich.reaprichbackend.data.repositories.kyc;

import com.am.reaprich.reaprichbackend.data.entities.kyc.KYC;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KYCRepository extends CrudRepository<KYC, String> {
}
