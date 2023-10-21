package com.am.reaprich.reaprichbackend.webservice;

import com.am.reaprich.reaprichbackend.business.service.*;
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

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
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


    @PostMapping("/bankdetail")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addBankDetail(@RequestBody BankDetail bankDetail) throws Exception {
        String id = java.util.UUID.randomUUID().toString();
        bankDetail.setId(id);
        try {
            this.bankService.AddBankDetail(bankDetail);
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        }
        catch (Exception ex) {
            return getStringForInternalServerError(ex);
        }
    }

    @PostMapping("/kycdetail")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addKYCDetail(@RequestBody KYC kyc) throws Exception {
        String id = java.util.UUID.randomUUID().toString();
        kyc.setId(id);
        try {
            this.kycService.AddKYC(kyc);
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        }
        catch (Exception ex) {
            return getStringForInternalServerError(ex);
        }
    }

    @PostMapping("/kycdocupload")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity uploadKYCDoc(@RequestParam("file") MultipartFile file,
                                       String actorID,
                                       String kycID,
                                       String userType,
                                       String documentType) throws Exception {
        if (kycID == null ||  kycID.isEmpty()) {
            switch (userType.toLowerCase()) {
                case "outlet":
                    kycID = this.actorService.GetOutletById(actorID).getOwnerKYC().getId();
                    break;
                case "td":
                    kycID = this.actorService.GetTDById(actorID).getKYC().getId();
                default:
                    return getStringForInternalServerError(
                        new Exception("Invalid user type submitted while uploading the KYC document"));
            }
        }
        try {
            kycService.AddKYCDoc(actorID, kycID, userType, documentType, file);
            return ResponseEntity.ok().build();
        }
        catch (Exception ex) {
            return getStringForInternalServerError(ex);
        }
    }

    @PostMapping("/address")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addAddress(@RequestBody Address address) throws Exception {
        String id = java.util.UUID.randomUUID().toString();
        address.setId(id);
        try {
            this.addressService.AddAddress(address);
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        }
        catch (Exception ex) {
            return getStringForInternalServerError(ex);
        }
    }

    @PostMapping("/actor/outlet")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addOutlet(@RequestBody Outlet outlet) {
        String id = java.util.UUID.randomUUID().toString();
        outlet.setId(id);
        try {
            this.actorService.AddOutlet(outlet);
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        }
        catch (Exception ex) {
            return  getStringForInternalServerError(ex);
        }
    }
    @PutMapping("/actor/outlet")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> updateOutlet(@RequestBody Outlet outlet) {
        try {
            this.actorService.updateOutlet(outlet);
            return ResponseEntity.status(HttpStatus.OK).body(outlet.getId());
        }
        catch (Exception ex) {
            return getStringForInternalServerError(ex);
        }
    }

    @PostMapping("/actor/td")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addTD(@RequestBody TD td) throws Exception {
        String id = java.util.UUID.randomUUID().toString();
        td.setId(id);
        try {
            this.actorService.AddTD(td);
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        }
        catch (Exception ex) {
            return getStringForInternalServerError(ex);
        }
    }
    @PutMapping("/actor/td")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> updateTD(@RequestBody TD td) {
        try {
            this.actorService.updateTD(td);
            return ResponseEntity.status(HttpStatus.OK).body(td.getId());
        }
        catch (Exception ex) {
            return getStringForInternalServerError(ex);
        }
    }

    @PostMapping("/actor/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer) throws Exception {
        String id = java.util.UUID.randomUUID().toString();
        customer.setId(id);
        try {
            this.actorService.AddCustomer(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        }
        catch (Exception ex) {
            return getStringForInternalServerError(ex);
        }
    }
    @PutMapping("/actor/customer")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> updateCustomer(@RequestBody Customer customer) {
        try {
            this.actorService.updateCustomer(customer);
            return ResponseEntity.status(HttpStatus.OK).body(customer.getId());
        }
        catch (Exception ex) {
            return getStringForInternalServerError(ex);
        }
    }


    private ResponseEntity<String> getStringForInternalServerError(Exception ex) {
        if (ex.getClass() == IllegalArgumentException.class)
        {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.internalServerError()
                .body("Something went wrong!");
    }
}
