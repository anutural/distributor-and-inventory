package com.am.reaprich.reaprichbackend.business.service;

import com.am.reaprich.reaprichbackend.data.entities.actors.actorprovider.ActorType;
import com.am.reaprich.reaprichbackend.data.entities.bank.BankDetail;
import com.am.reaprich.reaprichbackend.data.entities.kyc.KYC;
import com.am.reaprich.reaprichbackend.data.entities.kyc.kycprovider.KYCAddProofType;
import com.am.reaprich.reaprichbackend.data.entities.kyc.kycprovider.KYCIDType;
import com.am.reaprich.reaprichbackend.data.repositories.kyc.KYCRepository;
import com.am.reaprich.reaprichbackend.data.repositories.kyc.kycprovider.KYCAddProofTypeRepository;
import com.am.reaprich.reaprichbackend.data.repositories.kyc.kycprovider.KYCIDTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KYCService {
    @Autowired
    private KYCRepository kycRepository;
    @Autowired
    private KYCIDTypeRepository kycidTypeRepository;
    @Autowired
    private KYCAddProofTypeRepository kycAddProofTypeRepository;
    @Autowired
    private ProviderService providerService;


    public KYC GetKYCByID(String id) throws Exception {
        Optional<KYC> optionalKYC = this.kycRepository.findById(id);
        if (optionalKYC.isEmpty()) {
            throw new java.lang.NullPointerException("KYC not found");
        }
        return optionalKYC.get();
    }
    public void SetKYCStatus(String id, boolean status) throws Exception {
        Optional<KYC> optionalKYC = this.kycRepository.findById(id);
        if (optionalKYC.isEmpty()) {
            throw new java.lang.NullPointerException("KYC not found");
        }
        KYC kyc = optionalKYC.get();
        kyc.setStatus(status);
        this.kycRepository.save(kyc);
    }




    public boolean AddKYC(KYC kyc) throws Exception {
        if (this.kycRepository.existsById(kyc.getId())) {
            throw new Exception("KYC entry with the same ID already exist");
        }

        kyc.setActorType(this.providerService.GetActorTypesByID(kyc.getActorType().getId()));
        kyc.setIdType(this.providerService.GetKYCIDTypeByID(kyc.getIdType().getId()));
        kyc.setAddProofType(this.providerService.GetKYCAddProofTypesByID(kyc.getAddProofType().getId()));
        kyc.setStatus(false);

        this.kycRepository.save(kyc);
        return true;
    }
}
