package com.am.reaprich.reaprichbackend.business.service;

import com.am.reaprich.reaprichbackend.business.pojo.UserManagementProvider;
import com.am.reaprich.reaprichbackend.data.entities.actors.actorprovider.CustomerType;
import com.am.reaprich.reaprichbackend.data.entities.actors.outletprovider.OutletType;
import com.am.reaprich.reaprichbackend.data.entities.address.addressprovider.ADDCountry;
import com.am.reaprich.reaprichbackend.data.entities.address.addressprovider.ADDDist;
import com.am.reaprich.reaprichbackend.data.entities.address.addressprovider.ADDState;
import com.am.reaprich.reaprichbackend.data.entities.address.addressprovider.ADDZone;
import com.am.reaprich.reaprichbackend.data.entities.kyc.kycprovider.KYCAddProofType;
import com.am.reaprich.reaprichbackend.data.entities.kyc.kycprovider.KYCIDType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserManagementProviderService {
    @Autowired ProviderService providerService;
    @Autowired
    private ActorService actorService;
    @Autowired
    private AddressService addressService;
    @Autowired
    protected KYCService kycService;

    public UserManagementProvider GetProviderData() {
        UserManagementProvider providerData = new UserManagementProvider();

        providerData.setActorType(this.providerService.GetActorTypes());

        providerData.setOutletType(actorService.GetOutletTypes());

        providerData.setCustomerType(actorService.GetCustomerTypes());

        providerData.setAddCountryList(addressService.GetCountries());

        providerData.setAddStateList(addressService.GetStates());

        providerData.setAddZoneList(addressService.GetZones());

        providerData.setAddDistList (addressService.GetDistrict());

        providerData.setKycIdTypes(kycService.GetKYCIDTypes());

        providerData.setKycAddProofType(kycService.GetKYCAddProofTypes ());

        return  providerData;
    }
}
