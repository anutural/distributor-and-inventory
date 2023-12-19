package com.am.reaprich.reaprichbackend.webservice;

import com.am.reaprich.reaprichbackend.business.pojo.uermanagement.UserManagementProvider;
import com.am.reaprich.reaprichbackend.business.service.*;
import com.am.reaprich.reaprichbackend.business.service.usermanagement.ProviderService;
import com.am.reaprich.reaprichbackend.data.entities.actors.actorprovider.CustomerType;
import com.am.reaprich.reaprichbackend.data.entities.actors.outletprovider.OutletType;
import com.am.reaprich.reaprichbackend.data.entities.address.addressprovider.ADDCountry;
import com.am.reaprich.reaprichbackend.data.entities.address.addressprovider.ADDDist;
import com.am.reaprich.reaprichbackend.data.entities.address.addressprovider.ADDState;
import com.am.reaprich.reaprichbackend.data.entities.address.addressprovider.ADDZone;
import com.am.reaprich.reaprichbackend.data.entities.kyc.kycprovider.KYCAddProofType;
import com.am.reaprich.reaprichbackend.data.entities.kyc.kycprovider.KYCIDType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.POST})
@RequestMapping("/v1/provider")
public class ProviderUserManagementWebServiceController {
    private static final Logger logger = LogManager.getLogger(ProviderUserManagementWebServiceController.class);
    @Autowired
    private AddressService addressService;
    @Autowired
    private ProviderService providerService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity<UserManagementProvider> getProvider() {
        final String PQMN  = "getProvider";
        logger.info("Returning the provider information");
        return ResponseEntity.ok(this.providerService.GetProviderData());
    }

    @PostMapping("/actor/outlettype")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserManagementProvider> addOutletType(@RequestBody OutletType outletType) throws Exception {
        final String PQMN  = "addOutletType";
        logger.info(PQMN + " - Start");
        try {
            this.providerService.AddOutletType(outletType);
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            logger.info(ex.getStackTrace());
            return getUserManagementProviderForInternalServerError(ex);
        }finally {
            logger.info(PQMN + " - End");
        }
        return getProvider();
    }
    @PostMapping("/actor/customertype")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserManagementProvider> addCustomerType(@RequestBody CustomerType customerType) throws Exception {
        final String PQMN  = "addCustomerType";
        logger.info(PQMN + " - Start");
        try {
            this.providerService.AddCustomerType(customerType);
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            logger.info(ex.getStackTrace());
            return getUserManagementProviderForInternalServerError(ex);
        }finally {
            logger.info(PQMN + " - End");
        }
        return getProvider();
    }

    @PostMapping("/address/country")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserManagementProvider> addCountry(@RequestBody ADDCountry country) throws Exception {
        final String PQMN  = "addCountry";
        logger.info(PQMN + " - Start");
        try {
            this.addressService.AddCountry(country);
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            logger.info(ex.getStackTrace());
            return getUserManagementProviderForInternalServerError(ex);
        }finally {
            logger.info(PQMN + " - End");
        }
        return getProvider();
    }
    @PostMapping("/address/state")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserManagementProvider> addState(@RequestBody ADDState state) throws Exception {
        final String PQMN  = "addState";
        logger.info(PQMN + " - Start");
        try{
            this.addressService.AddState(state);
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            logger.info(ex.getStackTrace());
            return getUserManagementProviderForInternalServerError(ex);
        }finally {
            logger.info(PQMN + " - End");
        }
        return getProvider();
    }
    @PostMapping("/address/zone")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserManagementProvider> addZone(@RequestBody ADDZone zone) throws Exception {
        final String PQMN  = "addZone";
        logger.info(PQMN + " - Start");
        try {
            this.addressService.AddZone(zone);
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            logger.info(ex.getStackTrace());
            return getUserManagementProviderForInternalServerError(ex);
        }finally {
            logger.info(PQMN + " - End");
        }
        return getProvider();
    }
    @PostMapping("/address/dist")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserManagementProvider> addDist(@RequestBody ADDDist dist) throws Exception {
        final String PQMN  = "addDist";
        logger.info(PQMN + " - Start");
        try {
            this.addressService.AddDist(dist);
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            logger.info(ex.getStackTrace());
            return getUserManagementProviderForInternalServerError(ex);
        }finally {
            logger.info(PQMN + " - End");
        }
        return getProvider();
    }

    @PostMapping("/kyc/idtype")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserManagementProvider> addKYCIdType(@RequestBody KYCIDType kycidType) throws Exception {
        final String PQMN  = "addKYCIdType";
        logger.info(PQMN + " - Start");
        try {
            this.providerService.AddKYCIDType(kycidType);
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            logger.info(ex.getStackTrace());
            return getUserManagementProviderForInternalServerError(ex);
        }finally {
            logger.info(PQMN + " - End");
        }
        return getProvider();
    }
    @PostMapping("/kyc/addprooftype")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserManagementProvider> addKYCAddProofType(@RequestBody KYCAddProofType kycAddProofType) throws Exception {
        final String PQMN  = "addKYCAddProofType";
        logger.info(PQMN + " - Start");
        try{
            this.providerService.AddKYCAddProofType(kycAddProofType);
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            logger.info(ex.getStackTrace());
            return getUserManagementProviderForInternalServerError(ex);
        }finally {
            logger.info(PQMN + " - End");
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
