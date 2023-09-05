package com.am.reaprich.reaprichbackend.data.repositories.kyc.kycprovider;

import com.am.reaprich.reaprichbackend.data.entities.kyc.kycprovider.KYCAddProofType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KYCAddProofTypeRepository extends CrudRepository<KYCAddProofType, String> {
}
