package com.am.reaprich.reaprichbackend.business.service.usermanagement;

import com.am.reaprich.reaprichbackend.business.pojo.uermanagement.UserManagementProvider;
import com.am.reaprich.reaprichbackend.business.service.AddressService;
import com.am.reaprich.reaprichbackend.data.entities.actors.actorprovider.ActorType;
import com.am.reaprich.reaprichbackend.data.entities.actors.actorprovider.CustomerType;
import com.am.reaprich.reaprichbackend.data.entities.actors.outletprovider.OutletType;
import com.am.reaprich.reaprichbackend.data.entities.kyc.kycprovider.KYCAddProofType;
import com.am.reaprich.reaprichbackend.data.entities.kyc.kycprovider.KYCIDType;
import com.am.reaprich.reaprichbackend.data.repositories.actors.actorprovider.ActorTypeRepository;
import com.am.reaprich.reaprichbackend.data.repositories.actors.actorprovider.CustomerTypeRepository;
import com.am.reaprich.reaprichbackend.data.repositories.actors.outletprovider.OutletTypeRepository;
import com.am.reaprich.reaprichbackend.data.repositories.kyc.kycprovider.KYCAddProofTypeRepository;
import com.am.reaprich.reaprichbackend.data.repositories.kyc.kycprovider.KYCIDTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProviderService {

    @Autowired
    private ActorTypeRepository actorTypeRepository;
    @Autowired
    private CustomerTypeRepository customerTypeRepository;
    @Autowired
    protected OutletTypeRepository outletTypeRepository;
    @Autowired
    private KYCIDTypeRepository kycidTypeRepository;
    @Autowired
    private KYCAddProofTypeRepository kycAddProofTypeRepository;
    @Autowired
    private AddressService addressService;

    public Iterable<OutletType> GetOutletTypes() { return this.outletTypeRepository.findAll(); }
    public OutletType GetOutletTypesByID(String id) throws Exception {
        Optional<OutletType> optionalOutletType = this.outletTypeRepository.findById(id);
        if (optionalOutletType.isEmpty()) {
            throw new java.lang.NullPointerException("Outlet type  not found");
        }
        return optionalOutletType.get();
    }

    public Iterable<CustomerType> GetCustomerTypes() { return  this.customerTypeRepository.findAll(); }
    public CustomerType GetCustomerTypesByID(String id) throws Exception {
        Optional<CustomerType> optionalCustomerType = this.customerTypeRepository.findById(id);
        if (optionalCustomerType.isEmpty()) {
            throw new java.lang.NullPointerException("Customer type not found");
        }
        return optionalCustomerType.get();
    }

    public Iterable<ActorType> GetActorTypes() { return this.actorTypeRepository.findAll();    }
    public ActorType GetActorTypesByID(String id) throws Exception {
        Optional<ActorType> optionalActorType = this.actorTypeRepository.findById(id);
        if (optionalActorType.isEmpty()) {
            throw new java.lang.NullPointerException("Actor type  not found");
        }
        return optionalActorType.get();
    }

    public Iterable<KYCIDType> GetKYCIDTypes() { return this.kycidTypeRepository.findAll(); }
    public KYCIDType GetKYCIDTypeByID(String id) {
        Optional<KYCIDType> optionalKYCIDType = this.kycidTypeRepository.findById(id);
        if (optionalKYCIDType.isEmpty()) {
            throw new java.lang.NullPointerException("KYC ID type not found");
        }
        return optionalKYCIDType.get();
    }
    public Iterable<KYCAddProofType> GetKYCAddProofTypes() { return this.kycAddProofTypeRepository.findAll(); }
    public KYCAddProofType GetKYCAddProofTypesByID(String id) {
        Optional<KYCAddProofType> optionalKYCAddProofType = this.kycAddProofTypeRepository.findById(id);
        if (optionalKYCAddProofType.isEmpty()) {
            throw new java.lang.NullPointerException("KYC Address proof type not found");
        }
        return optionalKYCAddProofType.get();
    }

    public boolean AddOutletType(OutletType outletType) throws Exception {
        if (this.outletTypeRepository.existsById(outletType.getId())) {
            throw new Exception("Outlet Type with same ID is already present");
        }
        this.outletTypeRepository.save(outletType);
        return  true;
    }
    public boolean AddCustomerType(CustomerType customerType) throws Exception {
        if (this.customerTypeRepository.existsById(customerType.getId())) {
            throw new Exception("CustomerType with same ID is already present");
        }
        this.customerTypeRepository.save(customerType);
        return  true;
    }

    public boolean AddKYCIDType(KYCIDType kycidType) throws Exception {
        if (this.kycidTypeRepository.existsById(kycidType.getId())) {
            throw new Exception("kyc id type entry with the same ID already exist");
        }
        this.kycidTypeRepository.save(kycidType);
        return true;
    }
    public boolean AddKYCAddProofType(KYCAddProofType kycAddProofType) throws Exception {
        if (this.kycAddProofTypeRepository.existsById(kycAddProofType.getId())) {
            throw new Exception("KYC address proof type entry with the same ID already exist");
        }
        this.kycAddProofTypeRepository.save(kycAddProofType);
        return true;
    }


    public UserManagementProvider GetProviderData() {
        UserManagementProvider providerData = new UserManagementProvider();

        providerData.setActorType(GetActorTypes());

        providerData.setOutletType(GetOutletTypes());

        providerData.setCustomerType(GetCustomerTypes());

        providerData.setAddCountryList(addressService.GetCountries());

        providerData.setAddStateList(addressService.GetStates());

        providerData.setAddZoneList(addressService.GetZones());

        providerData.setAddDistList (addressService.GetDistrict());

        providerData.setKycIdTypes(GetKYCIDTypes());

        providerData.setKycAddProofType(GetKYCAddProofTypes ());

        return  providerData;
    }
}
