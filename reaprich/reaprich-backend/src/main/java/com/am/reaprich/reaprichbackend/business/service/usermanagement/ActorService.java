package com.am.reaprich.reaprichbackend.business.service.usermanagement;

import com.am.reaprich.reaprichbackend.business.pojo.auth.AppUserRegisterRequest;
import com.am.reaprich.reaprichbackend.business.pojo.auth.AuthenticationResponse;
import com.am.reaprich.reaprichbackend.business.pojo.uermanagement.*;
import com.am.reaprich.reaprichbackend.business.service.AddressService;
import com.am.reaprich.reaprichbackend.business.service.BankService;
import com.am.reaprich.reaprichbackend.business.service.KYCService;
import com.am.reaprich.reaprichbackend.business.service.auth.AuthenticationService;
import com.am.reaprich.reaprichbackend.business.service.inventory.InventoryService;
import com.am.reaprich.reaprichbackend.data.entities.actors.Customer;
import com.am.reaprich.reaprichbackend.data.entities.actors.Outlet;
import com.am.reaprich.reaprichbackend.data.entities.actors.TD;
import com.am.reaprich.reaprichbackend.data.entities.auth.Role;
import com.am.reaprich.reaprichbackend.data.entities.inventory.Warehouse;
import com.am.reaprich.reaprichbackend.data.repositories.actors.CustomerRepository;
import com.am.reaprich.reaprichbackend.data.repositories.actors.OutletRepository;
import com.am.reaprich.reaprichbackend.data.repositories.actors.TDRepository;
import com.am.reaprich.reaprichbackend.data.repositories.actors.actorprovider.ActorTypeRepository;
import com.am.reaprich.reaprichbackend.data.repositories.actors.actorprovider.CustomerTypeRepository;
import com.am.reaprich.reaprichbackend.data.repositories.actors.outletprovider.OutletTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ActorService {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private ActorTypeRepository actorTypeRepository;
    @Autowired
    private CustomerTypeRepository customerTypeRepository;
    @Autowired
    private OutletTypeRepository outletTypeRepository;
    @Autowired
    private OutletRepository outletRepository;
    @Autowired
    private TDRepository tdRepository;
    @Autowired
    private  CustomerRepository customerRepository;

    @Autowired
    ProviderService providerService;
    @Autowired
    private KYCService kycService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private BankService bankService;

    @Autowired
    private InventoryService inventoryService;

    public Iterable<Outlet> GetOutlets() {
        return  this.outletRepository.findAll();
    }
    public Outlet getOutletById(String id) throws Exception {
        Optional<Outlet> optionalOutlet = this.outletRepository.findById(id);
        if (optionalOutlet.isEmpty()) {
            throw new IllegalArgumentException("Outlet with specified id doesn't exist");
        }
        return optionalOutlet.get();
    }
    public Outlet getOutletByEmail(String email) throws Exception{
        Optional<Outlet> outletOptional = this.outletRepository.findByEmail(email);
        if (outletOptional.isEmpty())
        {
            throw new IllegalArgumentException("No outlet found with the passed email address");
        }
        return outletOptional.get();
    }
    public OutletDetailResponse getOutletDetailByUserEamil(String email) throws Exception{
        return OutletDetailResponse.getOutletDetailResponseFromOutletEntity(getOutletByEmail(email));
    }

    public Iterable<TD> GetTDs() {
        return this.tdRepository.findAll();
    }
    public TD getTDById(String id) throws Exception {
        Optional<TD> optionalTD = this.tdRepository.findById(id);
        if (optionalTD.isEmpty()) {
            throw new IllegalArgumentException("TD not found");
        }
        return optionalTD.get();
    }
    public TD getTDByEmail(String email) throws Exception {
        Optional<TD> optionalTD = this.tdRepository.findByEmail(email);
        if (optionalTD.isEmpty())
        {
            throw new IllegalArgumentException("No TD found with the passed email address");
        }
        return optionalTD.get();
    }
    public TDDetailResponse getTDDetailByUserEamil(String email) throws Exception{
        return TDDetailResponse.getTDDetailResponseFromOutletEntity(getTDByEmail(email));
    }

    public Iterable<Customer> getCustomers() {
        return this.customerRepository.findAll();
    }
    public Customer getCustomerById(String id) throws Exception {
        Optional<Customer> optionalCustomer = this.customerRepository.findById(id);
        if (optionalCustomer.isEmpty()) {
            throw new IllegalArgumentException("Customer not found");
        }
        return optionalCustomer.get();
    }


    public boolean AddOutlet(Outlet outlet) throws Exception {
        if (this.outletRepository.existsById(outlet.getId())) {
            throw new IllegalArgumentException("Outlet with same ID is already present");
        }
        if (!this.outletRepository.findByEmail(outlet.getEmail()).isEmpty()){
            throw new IllegalArgumentException("Outlet with same email address is already present");
        }
        if (!this.outletRepository.findByOwnerContactNumber(outlet.getOwnerContactNumber()).isEmpty()) {
            throw new IllegalArgumentException("Outlet with same owner contact number is already present");
        }

        addUpdateOutlet(outlet);

        Role role = outlet.getOutletType().getId() == "Pl" ? Role.SP_OUTLET : Role.OUTLET;

        AuthenticationResponse authenticationResponse = authenticationService.register(
                AppUserRegisterRequest.builder()
                        .email(outlet.getEmail())
                        .password(outlet.getPassword())
                        .role(role)
                        .build());
        return true;
    }
    public void updateOutlet (Outlet outlet) throws Exception {
        if (this.outletRepository.findById(outlet.getId()).isEmpty()) {
            throw new IllegalArgumentException("Outlet not found");
        }

        //Resetting the status of existing outlet's address, bank details, and kyc
        Outlet existingOutlet = this.outletRepository.findById(outlet.getId()).get();
        this.addressService.SetAddressStatus(existingOutlet.getFirmAddress().getId(), false);
        this.bankService.SetBankDetailStatus(existingOutlet.getFirmBankDetails().getId(), false);
        this.addressService.SetAddressStatus(existingOutlet.getOwnerAddress().getId(), false);
        this.kycService.SetKYCStatus(existingOutlet.getOwnerKYC().getId(), false);
        addUpdateOutlet(outlet);
    }
    public void addUpdateOutlet(Outlet outlet) throws Exception {
        outlet.setActorType(this.providerService.GetActorTypesByID(outlet.getActorType().getId()));
        outlet.setOutletType(this.providerService.GetOutletTypesByID(outlet.getOutletType().getId()));

        outlet.setFirmAddress(this.addressService.GetAddressByID(outlet.getFirmAddress().getId()));
        this.addressService.SetAddressStatus(outlet.getFirmAddress().getId(), true);

        outlet.setFirmBankDetails(this.bankService.GetBankDetailById(outlet.getFirmBankDetails().getId()));
        this.bankService.SetBankDetailStatus(outlet.getFirmBankDetails().getId(), true);

        outlet.setOwnerAddress(this.addressService.GetAddressByID(outlet.getOwnerAddress().getId()));
        this.addressService.SetAddressStatus(outlet.getOwnerAddress().getId(), true);

        outlet.setOwnerKYC(this.kycService.GetKYCByID(outlet.getOwnerKYC().getId()));
        this.kycService.SetKYCStatus(outlet.getOwnerKYC().getId(), true);

        this.outletRepository.save(outlet);

        this.inventoryService.addUpdateWarehouse(
                Warehouse
                        .builder()
                        .id(outlet.getId())
                        .outlet(outlet)
                        .contactNumber(outlet.getFirmContactNumber())
                        .address(outlet.getFirmAddress())
                        .isCompany(false)
                        .status(true)
                        .build());
    }

    public OutletDetailResponse updateOutletDetail(String email, UpdateOutletDetailRequest updateOutletDetailRequest) throws Exception{
        updateOutletDetailRequest.setFirmAddress(this.addressService.GetAddressByID(updateOutletDetailRequest.getFirmAddress().getId()));
        updateOutletDetailRequest.setOwnerAddress(this.addressService.GetAddressByID(updateOutletDetailRequest.getOwnerAddress().getId()));
        updateOutletDetailRequest.setFirmBankDetails(this.bankService.GetBankDetailById(updateOutletDetailRequest.getFirmBankDetails().getId()));

        Outlet outlet = getOutletByEmail(email);
        outlet.update(updateOutletDetailRequest);

        //Status of bank details, address, KYC, and warehouse will be properly updated in "update outlet call"
        updateOutlet(outlet);
        return OutletDetailResponse.getOutletDetailResponseFromOutletEntity(outlet);
    }


    public boolean AddTD(TD td) throws Exception {
        if (this.tdRepository.existsById(td.getId())) {
            throw new IllegalArgumentException("TD with same ID is already present");
        }
        if (!this.tdRepository.findByEmail(td.getEmail()).isEmpty()){
            throw new IllegalArgumentException("TD with same email is already present");
        }
        if (!this.tdRepository.findByContactNumber(td.getContactNumber()).isEmpty()){
            throw new IllegalArgumentException("TD with same contact number is already present");
        }

        addUpdateTD(td);

        Role role = Role.TD;

        AuthenticationResponse authenticationResponse = authenticationService.register(
                AppUserRegisterRequest.builder()
                        .email(td.getEmail())
                        .password(td.getPassword())
                        .role(role)
                        .build());

        return true;
    }
    public void updateTD (TD td) throws Exception {
        if (this.tdRepository.findById(td.getId()).isEmpty()) {
            throw new IllegalArgumentException("TD not found");
        }
        //Resetting the status of existing TD's address, bank details, and kyc
        TD existingTD = this.tdRepository.findById(td.getId()).get();
        this.addressService.SetAddressStatus(existingTD.getAddress().getId(), false);
        this.bankService.SetBankDetailStatus(existingTD.getBankDetails().getId(), false);
        this.kycService.SetKYCStatus(existingTD.getTdKYC().getId(), false);
        addUpdateTD(td);
    }
    public void addUpdateTD(TD td) throws Exception {
        td.setActorType(this.providerService.GetActorTypesByID(td.getActorType().getId()));

        td.setAddress(this.addressService.GetAddressByID(td.getAddress().getId()));
        this.addressService.SetAddressStatus(td.getAddress().getId(), true);

        td.setBankDetails(this.bankService.GetBankDetailById(td.getBankDetails().getId()));
        this.bankService.SetBankDetailStatus(td.getBankDetails().getId(), true);

        td.setTdKYC(this.kycService.GetKYCByID(td.getTdKYC().getId()));
        this.kycService.SetKYCStatus(td.getTdKYC().getId(), true);

        this.tdRepository.save(td);
    }

    public TDDetailResponse updateTDDetail(String email, UpdateTDDetailRequest updateTDDetailRequest) throws  Exception {
        updateTDDetailRequest.setAddress(this.addressService.GetAddressByID(updateTDDetailRequest.getAddress().getId()));
        updateTDDetailRequest.setBankDetails(this.bankService.GetBankDetailById(updateTDDetailRequest.getBankDetails().getId()));

        TD td = getTDByEmail(email);
        td.update(updateTDDetailRequest);
        updateTD(td);

        return TDDetailResponse.getTDDetailResponseFromOutletEntity(td);
    }

    public boolean AddCustomer(Customer customer) throws Exception {
        if (this.customerRepository.existsById(customer.getId())) {
            throw new IllegalArgumentException("Customer with same ID is already present");
        }
        if (!this.customerRepository.findByContactNumber(customer.getContactNumber()).isEmpty()){
            throw new IllegalArgumentException("Customer with same contact number is already present");
        }
        addUpdateCustomer(customer);
        return true;
    }

    public void updateCustomer (Customer customer) throws Exception {
        if (this.tdRepository.findById(customer.getId()).isEmpty()) {
            throw new IllegalArgumentException("Customer not found");
        }
        //Resetting the status of existing Customer's address
        Customer existingCustomer = this.customerRepository.findById(customer.getId()).get();
        this.addressService.SetAddressStatus(existingCustomer.getAddress().getId(), false);
        addUpdateCustomer(customer);
    }
    public void addUpdateCustomer(Customer customer) throws Exception {
        customer.setActorType(this.providerService.GetActorTypesByID(customer.getActorType().getId()));
        customer.setCustomerType(this.providerService.GetCustomerTypesByID(customer.getCustomerType().getId()));

        customer.setAddress(this.addressService.GetAddressByID(customer.getAddress().getId()));
        this.addressService.SetAddressStatus(customer.getAddress().getId(), true);

        this.customerRepository.save(customer);
    }

    public void approveOutlet(String outletID) throws Exception{
        Outlet outlet = getOutletById(outletID);
        outlet.setStatus(true);
        this.outletRepository.save(outlet);
    }

    public void approveTD(String tdID) throws Exception {
        TD td = getTDById(tdID);
        td.setStatus(true);
        this.tdRepository.save(td);
    }
}
