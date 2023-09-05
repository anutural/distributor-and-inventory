package com.am.reaprich.reaprichbackend.data.repositories.bank;

import com.am.reaprich.reaprichbackend.data.entities.bank.BankDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankDetailRepository extends CrudRepository<BankDetail, String> {
}
