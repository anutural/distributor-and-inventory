package com.am.reaprich.reaprichbackend.webservice;

import com.am.reaprich.reaprichbackend.business.pojo.IdResponse;
import com.am.reaprich.reaprichbackend.business.pojo.uermanagement.*;
import com.am.reaprich.reaprichbackend.business.service.*;
import com.am.reaprich.reaprichbackend.business.service.usermanagement.ActorFilterService;
import com.am.reaprich.reaprichbackend.business.service.usermanagement.ActorService;
import com.am.reaprich.reaprichbackend.data.entities.actors.Customer;
import com.am.reaprich.reaprichbackend.data.entities.actors.Outlet;
import com.am.reaprich.reaprichbackend.data.entities.actors.TD;
import com.am.reaprich.reaprichbackend.data.entities.address.Address;
import com.am.reaprich.reaprichbackend.data.entities.bank.BankDetail;
import com.am.reaprich.reaprichbackend.data.entities.kyc.KYC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.POST})
@RequestMapping("/v1/user")
public class UserManagementWebServiceController {
    @Autowired
    private ActorService actorService;
    @Autowired
    private BankService bankService;
    @Autowired
    private KYCService kycService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private ActorFilterService actorFilterService;

    private static final Logger logger = LogManager.getLogger(UserManagementWebServiceController.class);

    @GetMapping("/actor/outlet")
    public ResponseEntity<OutletResponse> getOutletById(@RequestParam String outletId) {
        final String PQMN  = "getOutletById";
        logger.info(PQMN + " - Start");
        try {
            Outlet outlet = this.actorService.getOutletById(outletId);
            outlet.setPassword("****");
            return ResponseEntity.status(HttpStatus.OK).body(
                    OutletResponse
                            .builder()
                            .outlet(outlet)
                            .build());
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            logger.info(ex.getStackTrace().toString());
            return getOutletResponseEntityForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }
    @GetMapping("/actor/td")
    public ResponseEntity<TDResponse> getTDById(@RequestParam String tdID) {
        final String PQMN  = "getTDById";
        logger.info(PQMN + " - Start");
        try {
            TD td = this.actorService.getTDById(tdID);
            td.setPassword("****");
            return ResponseEntity.status(HttpStatus.OK).body(
                    TDResponse
                            .builder()
                            .td(td)
                            .build());
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            logger.info(ex.getStackTrace().toString());
            return getTDResponseEntityForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }
    @GetMapping("/actor/customer")
    public ResponseEntity<CustomerResponse> getCustomerById(@RequestParam String customerID) {
        final String PQMN  = "getCustomerById";
        logger.info(PQMN + " - Start");
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    CustomerResponse
                            .builder()
                            .customer(this.actorService.getCustomerById(customerID))
                            .build());
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            logger.info(ex.getStackTrace().toString());
            return getCustomerResponseEntityForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }

    @PostMapping("/actor/alloutlets")
    public ResponseEntity<OutletCollectionResponse> allOutlets(@RequestBody AllActorsRequest getAllOutletsRequest) {
        final String PQMN  = "allOutlets";
        logger.info(PQMN + " - Start");
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.actorFilterService.getOutletsByFilter(getAllOutletsRequest));
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            logger.info(ex.getStackTrace().toString());
            return getOutletCollectionResponseForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }
    @PostMapping("/actor/alltds")
    public ResponseEntity<TDCollectionResponse> allTDs(@RequestBody AllActorsRequest getAllOutletsRequest) {
        final String PQMN  = "allTDs";
        logger.info(PQMN + " - Start");
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.actorFilterService.getTDsByFilter(getAllOutletsRequest));
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            logger.info(ex.getStackTrace().toString());
            return getTDCollectionResponseForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }
    @PostMapping("/actor/allcustomers")
    public ResponseEntity<CustomerCollectionResponse> allCustomers(@RequestBody AllActorsRequest getAllOutletsRequest) {
        final String PQMN  = "allCustomers";
        logger.info(PQMN + " - Start");
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.actorFilterService.getCustomersByFilter(getAllOutletsRequest));
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            logger.info(ex.getStackTrace().toString());
            return getCustomerCollectionResponseForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }

    @GetMapping("/bankdetail")
    public ResponseEntity<BankDetailResponse> getBankDetailById(@RequestParam String bankDetailID) {
        final String PQMN  = "getBankDetailById";
        logger.info(PQMN + " - Start");
        try{
            return ResponseEntity.status(HttpStatus.OK).body(BankDetailResponse
                    .builder()
                    .bankDetail(bankService.GetBankDetailById(bankDetailID))
                    .build());
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            logger.info(ex.getStackTrace().toString());
            return getBankDetailResponseEntityForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }
    @GetMapping("/kycdetail")
    public ResponseEntity<KYCResponse> getKYCByID(@RequestParam String kycID) {
        final String PQMN  = "getKYCByID";
        logger.info(PQMN + " - Start");
        try{
            return ResponseEntity.status(HttpStatus.OK).body(KYCResponse
                    .builder()
                    .kyc(kycService.GetKYCByID(kycID))
                    .build());
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            logger.info(ex.getStackTrace().toString());
            return getKYCResponseEntityForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }
    @GetMapping("/address")
    public ResponseEntity<AddressResponse> getAddressByID(@RequestParam String addressID) {
        final String PQMN  = "getAddressByID";
        logger.info(PQMN + " - Start");
        try{
            return ResponseEntity.status(HttpStatus.OK).body(AddressResponse
                    .builder()
                    .address(addressService.GetAddressByID(addressID))
                    .build());
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            logger.info(ex.getStackTrace().toString());
            return  getAddressResponseEntityForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }


    @PostMapping("/bankdetail")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<IdResponse> addBankDetail(@RequestBody BankDetail bankDetail) throws Exception {
        final String PQMN  = "addBankDetail";
        logger.info(PQMN + " - Start");
        String id = java.util.UUID.randomUUID().toString();
        bankDetail.setId(id);
        try {
            this.bankService.AddBankDetail(bankDetail);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    IdResponse
                            .builder()
                            .id(id)
                            .build());

        }
        catch (Exception ex) {
            logger.error(ex.toString());
            logger.info(ex.getStackTrace().toString());
            return getIDResponseForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }

    @PostMapping("/kycdetail")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<IdResponse> addKYCDetail(@RequestBody KYC kyc) throws Exception {
        final String PQMN  = "addKYCDetail";
        logger.info(PQMN + " - Start");
        String id = java.util.UUID.randomUUID().toString();
        kyc.setId(id);
        try {
            this.kycService.AddKYC(kyc);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    IdResponse
                            .builder()
                            .id(id)
                            .build());
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            logger.info(ex.getStackTrace().toString());
            return getIDResponseForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }

    @PostMapping("/kycdocupload")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity uploadKYCDoc(@RequestParam("file") MultipartFile file,
                                       String actorID,
                                       String kycID,
                                       String userType,
                                       String documentType) throws Exception {
        final String PQMN  = "uploadKYCDoc";
        logger.info(PQMN + " - Start");
        if (kycID == null ||  kycID.isEmpty()) {
            switch (userType.toLowerCase()) {
                case "outlet":
                    kycID = this.actorService.getOutletById(actorID).getOwnerKYC().getId();
                    break;
                case "td":
                    kycID = this.actorService.getTDById(actorID).getTdKYC().getId();
                default:
                    return getIDResponseForInternalServerError(
                        new Exception("Invalid user type submitted while uploading the KYC document"));
            }
        }
        try {
            kycService.AddKYCDoc(actorID, kycID, userType, documentType, file);
            return ResponseEntity.ok().build();
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            logger.info(ex.getStackTrace().toString());
            return getIDResponseForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }

    @PostMapping("/address")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<IdResponse> addAddress(@RequestBody Address address) throws Exception {
        final String PQMN  = "addAddress";
        logger.info(PQMN + " - Start");
        String id = java.util.UUID.randomUUID().toString();
        address.setId(id);
        try {
            this.addressService.AddAddress(address);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    IdResponse
                            .builder()
                            .id(id)
                            .build());
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            logger.info(ex.getStackTrace().toString());
            return getIDResponseForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }

    @PostMapping("/actor/outlet")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<IdResponse> addOutlet(@RequestBody Outlet outlet) {
        final String PQMN  = "addOutlet";
        logger.info(PQMN + " - Start");
        String id = java.util.UUID.randomUUID().toString();
        outlet.setId(id);
        try {
            this.actorService.AddOutlet(outlet);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    IdResponse
                            .builder()
                            .id(id)
                            .build());
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            logger.info(ex.getStackTrace().toString());
            return  getIDResponseForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }
    @PutMapping("/actor/outlet")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<IdResponse> updateOutlet(@RequestBody Outlet outlet) {
        final String PQMN  = "updateOutlet";
        logger.info(PQMN + " - Start");
        try {
            this.actorService.updateOutlet(outlet);
            return ResponseEntity.status(HttpStatus.OK).body(
                    IdResponse
                            .builder()
                            .id(outlet.getId())
                            .build());
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            logger.info(ex.getStackTrace().toString());
            return getIDResponseForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }

    @PostMapping("/actor/td")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<IdResponse> addTD(@RequestBody TD td) throws Exception {
        final String PQMN  = "addTD";
        logger.info(PQMN + " - Start");
        String id = java.util.UUID.randomUUID().toString();
        td.setId(id);
        try {
            this.actorService.AddTD(td);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    IdResponse
                            .builder()
                            .id(id)
                            .build());
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            return getIDResponseForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }
    @PutMapping("/actor/td")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<IdResponse> updateTD(@RequestBody TD td) {
        final String PQMN  = "updateTD";
        logger.info(PQMN + " - Start");
        try {
            this.actorService.updateTD(td);
            return ResponseEntity.status(HttpStatus.OK).body(
                    IdResponse
                            .builder()
                            .id(td.getId())
                            .build());
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            logger.info(ex.getStackTrace().toString());
            return getIDResponseForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }

    @PostMapping("/actor/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<IdResponse> addCustomer(@RequestBody Customer customer) throws Exception {
        final String PQMN  = "addCustomer";
        logger.info(PQMN + " - Start");
        String id = java.util.UUID.randomUUID().toString();
        customer.setId(id);
        try {
            this.actorService.AddCustomer(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    IdResponse
                            .builder()
                            .id(id)
                            .build());
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            logger.info(ex.getStackTrace().toString());
            return getIDResponseForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }
    @PutMapping("/actor/customer")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<IdResponse> updateCustomer(@RequestBody Customer customer) {
        final String PQMN  = "updateCustomer";
        logger.info(PQMN + " - Start");
        try {
            this.actorService.updateCustomer(customer);
            return ResponseEntity.status(HttpStatus.OK).body(
                    IdResponse
                            .builder()
                            .id(customer.getId())
                            .build());
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            logger.info(ex.getStackTrace().toString());
            return getIDResponseForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }

    @PutMapping("/actor/outlet/approve")
    public ResponseEntity<IdResponse> approveOutlet(@RequestParam String outletID) {
        final String PQMN  = "approveOutlet";
        logger.info(PQMN + " - Start");
        try {
            this.actorService.approveOutlet(outletID);
            return ResponseEntity.status(HttpStatus.OK).body(
                    IdResponse
                            .builder()
                            .id("Success")
                            .build());
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            logger.info(ex.getStackTrace().toString());
            return getIDResponseForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }
    @PutMapping("/actor/td/approve")
    public ResponseEntity<IdResponse> approveTD(@RequestParam String tdID) {
        final String PQMN  = "approveTD";
        logger.info(PQMN + " - Start");
        try {
            this.actorService.approveTD(tdID);
            return ResponseEntity.status(HttpStatus.OK).body(
                    IdResponse
                            .builder()
                            .id("Success")
                            .build());
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            logger.info(ex.getStackTrace().toString());
            return getIDResponseForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }


    private String getErrorMessage(Exception ex) {
        String error = "Something went wrong";
        if (ex.getClass() == IllegalArgumentException.class) {
            error = ex.getMessage();
        }
        return error;
    }
    private HttpStatus getHTTPStatus(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (ex.getClass() == IllegalArgumentException.class) {
            status = HttpStatus.BAD_REQUEST;
        }
        return status;
    }

    private ResponseEntity<IdResponse> getIDResponseForInternalServerError(Exception ex) {
        return ResponseEntity.status(getHTTPStatus(ex)).body(
                IdResponse
                        .builder()
                                .error(
                                        getErrorMessage(ex))
                        .build());
    }
    private ResponseEntity<OutletCollectionResponse> getOutletCollectionResponseForInternalServerError(Exception ex) {
        return ResponseEntity.status(getHTTPStatus(ex)).body(
                OutletCollectionResponse
                        .builder()
                        .error(getErrorMessage(ex))
                        .build());
    }
    private ResponseEntity<TDCollectionResponse> getTDCollectionResponseForInternalServerError(Exception ex) {
        return ResponseEntity.status(getHTTPStatus(ex)).body(
                TDCollectionResponse
                        .builder()
                        .error(getErrorMessage(ex))
                        .build());
    }
    private ResponseEntity<CustomerCollectionResponse> getCustomerCollectionResponseForInternalServerError(Exception ex) {
        return ResponseEntity.status(getHTTPStatus(ex)).body(
                CustomerCollectionResponse
                        .builder()
                        .error(getErrorMessage(ex))
                        .build());
    }

    private ResponseEntity<OutletResponse> getOutletResponseEntityForInternalServerError(Exception ex) {
        return ResponseEntity.status(getHTTPStatus(ex)).body(
                OutletResponse
                        .builder()
                        .error(getErrorMessage(ex))
                        .build());
    }
    private ResponseEntity<TDResponse> getTDResponseEntityForInternalServerError(Exception ex) {
        return ResponseEntity.status(getHTTPStatus(ex)).body(
                TDResponse
                        .builder()
                        .error(getErrorMessage(ex))
                        .build());
    }
    private ResponseEntity<CustomerResponse> getCustomerResponseEntityForInternalServerError(Exception ex) {
        return ResponseEntity.status(getHTTPStatus(ex)).body(
                CustomerResponse
                        .builder()
                        .error(getErrorMessage(ex))
                        .build());
    }

    private ResponseEntity<BankDetailResponse> getBankDetailResponseEntityForInternalServerError(Exception ex) {
        return ResponseEntity.status(getHTTPStatus(ex)).body(
                BankDetailResponse
                        .builder()
                        .error(getErrorMessage(ex))
                        .build());
    }
    private ResponseEntity<KYCResponse> getKYCResponseEntityForInternalServerError(Exception ex) {
        return ResponseEntity.status(getHTTPStatus(ex)).body(
                KYCResponse
                        .builder()
                        .error(getErrorMessage(ex))
                        .build());
    }
    private ResponseEntity<AddressResponse> getAddressResponseEntityForInternalServerError(Exception ex) {
        return ResponseEntity.status(getHTTPStatus(ex)).body(
                AddressResponse
                        .builder()
                        .error(getErrorMessage(ex))
                        .build());
    }
}
