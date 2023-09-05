package com.am.reaprich.reaprichbackend.data.repositories.kyc.kycprovider;

import com.am.reaprich.reaprichbackend.data.entities.kyc.kycprovider.KYCIDType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KYCIDTypeRepository extends CrudRepository<KYCIDType, String> {
}
