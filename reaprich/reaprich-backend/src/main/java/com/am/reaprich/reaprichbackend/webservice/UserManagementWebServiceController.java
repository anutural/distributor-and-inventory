package com.am.reaprich.reaprichbackend.webservice;

import com.am.reaprich.reaprichbackend.business.pojo.UserManagementProvider;
import com.am.reaprich.reaprichbackend.business.service.*;
import com.am.reaprich.reaprichbackend.data.entities.actors.Customer;
import com.am.reaprich.reaprichbackend.data.entities.actors.Outlet;
import com.am.reaprich.reaprichbackend.data.entities.actors.TD;
import com.am.reaprich.reaprichbackend.data.entities.actors.actorprovider.CustomerType;
import com.am.reaprich.reaprichbackend.data.entities.actors.outletprovider.OutletType;
import com.am.reaprich.reaprichbackend.data.entities.address.Address;
import com.am.reaprich.reaprichbackend.data.entities.address.addressprovider.ADDCountry;
import com.am.reaprich.reaprichbackend.data.entities.address.addressprovider.ADDDist;
import com.am.reaprich.reaprichbackend.data.entities.address.addressprovider.ADDState;
import com.am.reaprich.reaprichbackend.data.entities.address.addressprovider.ADDZone;
import com.am.reaprich.reaprichbackend.data.entities.bank.BankDetail;
import com.am.reaprich.reaprichbackend.data.entities.kyc.KYC;
import com.am.reaprich.reaprichbackend.data.entities.kyc.kycprovider.KYCAddProofType;
import com.am.reaprich.reaprichbackend.data.entities.kyc.kycprovider.KYCIDType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
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
    private  ProviderService providerService;

    @RequestMapping(path = "/provider", method = RequestMethod.GET)
    public UserManagementProvider GetProvider() {
        return this.providerService.GetProviderData();
    }


    @PostMapping("/actor/provider/outlettype")
    @ResponseStatus(HttpStatus.CREATED)
    public UserManagementProvider AddOutletType(@RequestBody OutletType outletType) throws Exception {
        this.providerService.AddOutletType(outletType);
        return GetProvider();
    }
    @PostMapping("/actor/provider/customertype")
    @ResponseStatus(HttpStatus.CREATED)
    public UserManagementProvider AddCustomerType(@RequestBody CustomerType customerType) throws Exception {
        this.providerService.AddCustomerType(customerType);
        return GetProvider();
    }

    @PostMapping("/address/provider/country")
    @ResponseStatus(HttpStatus.CREATED)
    public UserManagementProvider AddCountry(@RequestBody ADDCountry country) throws Exception {
        this.addressService.AddCountry(country);
        return GetProvider();
    }
    @PostMapping("/address/provider/state")
    @ResponseStatus(HttpStatus.CREATED)
    public UserManagementProvider AddState(@RequestBody ADDState state) throws Exception {
        this.addressService.AddState(state);
        return GetProvider();
    }
    @PostMapping("/address/provider/zone")
    @ResponseStatus(HttpStatus.CREATED)
    public UserManagementProvider AddZone(@RequestBody ADDZone zone) throws Exception {
        this.addressService.AddZone(zone);
        return GetProvider();
    }
    @PostMapping("/address/provider/dist")
    @ResponseStatus(HttpStatus.CREATED)
    public UserManagementProvider AddDist(@RequestBody ADDDist dist) throws Exception {
        this.addressService.AddDist(dist);
        return GetProvider();
    }

    @PostMapping("/kyc/provider/idtype")
    @ResponseStatus(HttpStatus.CREATED)
    public UserManagementProvider AddKYCIdType(@RequestBody KYCIDType kycidType) throws Exception {
        this.providerService.AddKYCIDType(kycidType);
        return GetProvider();
    }
    @PostMapping("/kyc/provider/addprooftype")
    @ResponseStatus(HttpStatus.CREATED)
    public UserManagementProvider AddKYCAddProofType(@RequestBody KYCAddProofType kycAddProofType) throws Exception {
        this.providerService.AddKYCAddProofType(kycAddProofType);
        return GetProvider();
    }

    @PostMapping("/bank/bankdetail")
    @ResponseStatus(HttpStatus.CREATED)
    public String AddBankDetail(@RequestBody BankDetail bankDetail) throws Exception {
        String id = java.util.UUID.randomUUID().toString();
        bankDetail.setId(id);
        this.bankService.AddBankDetail(bankDetail);
        return id;
    }

    @PostMapping("/kyc/kycdetail")
    @ResponseStatus(HttpStatus.CREATED)
    public String AddKYCDetail(@RequestBody KYC kyc) throws Exception {
        String id = java.util.UUID.randomUUID().toString();
        kyc.setId(id);
        this.kycService.AddKYC(kyc);
        return id;
    }

    @PostMapping("/kycdocupload")
    @ResponseStatus(HttpStatus.OK)
    public void UploadKYCDoc(@RequestParam("file") MultipartFile file,
                                               String actorID, String kycID, String userType, String documentType) throws Exception {
        if (kycID == null ||  kycID.isEmpty()) {
            switch (userType.toLowerCase()) {
                case "outlet":
                    kycID = this.actorService.GetOutletById(actorID).getOwnerKYC().getId();
                    break;
                case "td":
                    kycID = this.actorService.GetTDById(actorID).getKYC().getId();
                default:
                    throw new Exception("Invalid user type submitted while uploading the KYC document");
            }
        }
        kycService.AddKYCDoc(actorID, kycID, userType, documentType, file);
    }

    @PostMapping("/address")
    @ResponseStatus(HttpStatus.CREATED)
    public String AddAddress(@RequestBody Address address) throws Exception {
        String id = java.util.UUID.randomUUID().toString();
        address.setId(id);
        this.addressService.AddAddress(address);
        return id;
    }

    @PostMapping("/actor/outlet")
    @ResponseStatus(HttpStatus.CREATED)
    public void AddOutlet(@RequestBody Outlet outlet) throws Exception {
        this.actorService.AddOutlet(outlet);
    }

    @PostMapping("/actor/td")
    @ResponseStatus(HttpStatus.CREATED)
    public void AddTD(@RequestBody TD td) throws Exception {
        this.actorService.AddTD(td);
    }

    @PostMapping("/actor/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public void AddCustomer(@RequestBody Customer customer) throws Exception {
        this.actorService.AddCustomer(customer);
    }
}
