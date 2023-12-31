package com.am.reaprich.reaprichbackend.business.service;

import com.am.reaprich.reaprichbackend.business.service.usermanagement.ProviderService;
import com.am.reaprich.reaprichbackend.data.entities.bank.BankDetail;
import com.am.reaprich.reaprichbackend.data.repositories.bank.BankDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class BankService {
    @Autowired
    private BankDetailRepository bankDetailRepository;
    @Autowired
    private ProviderService providerService;

    public BankDetail GetBankDetailById(String id) throws Exception {
        Optional<BankDetail> optionalBankDetail = this.bankDetailRepository.findById(id);
        if (optionalBankDetail.isEmpty()) {
            throw new java.lang.IllegalArgumentException("Bank detail not found");
        }
        return optionalBankDetail.get();
    }
    public void SetBankDetailStatus(String id, boolean status) throws Exception {
        Optional<BankDetail> optionalBankDetail = this.bankDetailRepository.findById(id);
        if (optionalBankDetail.isEmpty()) {
            throw new java.lang.IllegalArgumentException("Bank detail not found");
        }
        BankDetail bankDetail = optionalBankDetail.get();
        bankDetail.setStatus(status);
        this.bankDetailRepository.save(bankDetail);
    }

    public boolean AddBankDetail(BankDetail bankDetail) throws Exception {
        if (this.bankDetailRepository.existsById(bankDetail.getId())) {
            throw new IllegalArgumentException("Bank Detail entry with the same ID already exist");
        }
        bankDetail.setActorType(this.providerService.GetActorTypesByID(bankDetail.getActorType().getId()));
        bankDetail.setStatus(false);

        this.bankDetailRepository.save(bankDetail);
        return true;
    }
}
