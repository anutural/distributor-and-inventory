package com.am.reaprich.reaprichbackend.webservice;

import com.am.reaprich.reaprichbackend.business.pojo.uermanagement.UserManagementProvider;
import com.am.reaprich.reaprichbackend.business.service.*;
import com.am.reaprich.reaprichbackend.data.entities.actors.actorprovider.CustomerType;
import com.am.reaprich.reaprichbackend.data.entities.actors.outletprovider.OutletType;
import com.am.reaprich.reaprichbackend.data.entities.address.addressprovider.ADDCountry;
import com.am.reaprich.reaprichbackend.data.entities.address.addressprovider.ADDDist;
import com.am.reaprich.reaprichbackend.data.entities.address.addressprovider.ADDState;
import com.am.reaprich.reaprichbackend.data.entities.address.addressprovider.ADDZone;
import com.am.reaprich.reaprichbackend.data.entities.kyc.kycprovider.KYCAddProofType;
import com.am.reaprich.reaprichbackend.data.entities.kyc.kycprovider.KYCIDType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/v1/provider")
public class ProviderUserManagementWebServiceController {
    @Autowired
    private AddressService addressService;
    @Autowired
    private ProviderService providerService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity<UserManagementProvider> getProvider() {
        return ResponseEntity.ok(this.providerService.GetProviderData());
    }

    @PostMapping("/actor/outlettype")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserManagementProvider> addOutletType(@RequestBody OutletType outletType) throws Exception {
        try {
            this.providerService.AddOutletType(outletType);
        }
        catch (Exception ex) {
            return getUserManagementProviderForInternalServerError(ex);
        }
        return getProvider();
    }
    @PostMapping("/actor/customertype")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserManagementProvider> addCustomerType(@RequestBody CustomerType customerType) throws Exception {
        try {
            this.providerService.AddCustomerType(customerType);
        }
        catch (Exception ex) {
            return getUserManagementProviderForInternalServerError(ex);
        }
        return getProvider();
    }

    @PostMapping("/address/country")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserManagementProvider> addCountry(@RequestBody ADDCountry country) throws Exception {
        try {
            this.addressService.AddCountry(country);
        }
        catch (Exception ex) {
            return getUserManagementProviderForInternalServerError(ex);
        }
        return getProvider();
    }
    @PostMapping("/address/state")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserManagementProvider> addState(@RequestBody ADDState state) throws Exception {
        try{
            this.addressService.AddState(state);
        }
        catch (Exception ex) {
            return getUserManagementProviderForInternalServerError(ex);
        }
        return getProvider();
    }
    @PostMapping("/address/zone")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserManagementProvider> addZone(@RequestBody ADDZone zone) throws Exception {
        try {
            this.addressService.AddZone(zone);
        }
        catch (Exception ex) {
            return getUserManagementProviderForInternalServerError(ex);
        }
        return getProvider();
    }
    @PostMapping("/address/dist")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserManagementProvider> addDist(@RequestBody ADDDist dist) throws Exception {
        try {
            this.addressService.AddDist(dist);
        }
        catch (Exception ex) {
            return getUserManagementProviderForInternalServerError(ex);
        }
        return getProvider();
    }

    @PostMapping("/kyc/idtype")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserManagementProvider> addKYCIdType(@RequestBody KYCIDType kycidType) throws Exception {
        try {
            this.providerService.AddKYCIDType(kycidType);
        }
        catch (Exception ex) {
            return getUserManagementProviderForInternalServerError(ex);
        }
        return getProvider();
    }
    @PostMapping("/kyc/addprooftype")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserManagementProvider> addKYCAddProofType(@RequestBody KYCAddProofType kycAddProofType) throws Exception {
        try{
            this.providerService.AddKYCAddProofType(kycAddProofType);
        }
        catch (Exception ex) {
            return getUserManagementProviderForInternalServerError(ex);
        }
        return getProvider();
    }

    private ResponseEntity<UserManagementProvider> getUserManagementProviderForInternalServerError(Exception ex) {
        return ResponseEntity.internalServerError()
                .body(UserManagementProvider.builder()
                        .error(ex.getMessage())
                        .build());
    }
}
