package com.am.reaprich.reaprichbackend.business.service;

import com.am.reaprich.reaprichbackend.business.service.usermanagement.ProviderService;
import com.am.reaprich.reaprichbackend.data.entities.kyc.KYC;
import com.am.reaprich.reaprichbackend.data.repositories.kyc.KYCRepository;
import com.am.reaprich.reaprichbackend.data.repositories.kyc.kycprovider.KYCAddProofTypeRepository;
import com.am.reaprich.reaprichbackend.data.repositories.kyc.kycprovider.KYCIDTypeRepository;
import com.am.reaprich.reaprichbackend.util.io.FileUploader;
import com.am.reaprich.reaprichbackend.util.io.PathHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
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

    public void AddKYCDoc (String actorID, String kycID, String userType, String documentType,  MultipartFile file) throws Exception {
        try {
            String originalFileName =  file.getOriginalFilename();
            String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);

            Path directory = PathHelper.GetKYCDataLocationForActor(actorID);

            String docLink = FileUploader.SaveFile(documentType + "." + extension, directory, file);

            KYC kyc = GetKYCByID(kycID);
            switch (documentType.toLowerCase()) {
                case "idtype":
                    kyc.setKycIDLink(docLink);
                    break;
                case "addprooftype":
                    kyc.setKycAddProofLink(docLink);
                    break;
                default:
                    throw new Exception("Invalid document type submitted while uploading the KYC document");
            }
            kycRepository.save(kyc);
        }
        catch (Exception ex) {
            throw ex;
        }
    }

}
