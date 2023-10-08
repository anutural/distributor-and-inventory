package com.am.reaprich.reaprichbackend.business.service;

import com.am.reaprich.reaprichbackend.business.pojo.auth.AppUserRegisterRequest;
import com.am.reaprich.reaprichbackend.business.pojo.auth.AuthenticationResponse;
import com.am.reaprich.reaprichbackend.business.service.auth.AuthenticationService;
import com.am.reaprich.reaprichbackend.data.entities.actors.Customer;
import com.am.reaprich.reaprichbackend.data.entities.actors.Outlet;
import com.am.reaprich.reaprichbackend.data.entities.actors.TD;
import com.am.reaprich.reaprichbackend.data.entities.actors.actorprovider.ActorType;
import com.am.reaprich.reaprichbackend.data.entities.actors.actorprovider.CustomerType;
import com.am.reaprich.reaprichbackend.data.entities.actors.outletprovider.OutletType;
import com.am.reaprich.reaprichbackend.data.entities.auth.Role;
import com.am.reaprich.reaprichbackend.data.repositories.actors.CustomerRepository;
import com.am.reaprich.reaprichbackend.data.repositories.actors.OutletRepository;
import com.am.reaprich.reaprichbackend.data.repositories.actors.TDRepository;
import com.am.reaprich.reaprichbackend.data.repositories.actors.actorprovider.ActorTypeRepository;
import com.am.reaprich.reaprichbackend.data.repositories.actors.actorprovider.CustomerTypeRepository;
import com.am.reaprich.reaprichbackend.data.repositories.actors.outletprovider.OutletTypeRepository;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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

    @Autowired ProviderService providerService;
    @Autowired
    private KYCService kycService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private BankService bankService;

    public Iterable<Outlet> GetOutlets() {
        return  this.outletRepository.findAll();
    }
    public Outlet GetOutletById(String id) throws Exception {
        Optional<Outlet> optionalOutlet = this.outletRepository.findById(id);
        if (optionalOutlet.isEmpty()) {
            throw new java.lang.NullPointerException("Outlet not found");
        }
        return optionalOutlet.get();
    }
    public Iterable<TD> GetTDs() {
        return this.tdRepository.findAll();
    }
    public TD GetTDById(String id) throws Exception {
        Optional<TD> optionalTD = this.tdRepository.findById(id);
        if (optionalTD.isEmpty()) {
            throw new java.lang.NullPointerException("TD not found");
        }
        return optionalTD.get();
    }
    public Iterable<Customer> GetCustomers() {
        return this.customerRepository.findAll();
    }
    public Customer GetCustomerById(String id) throws Exception {
        Optional<Customer> optionalCustomer = this.customerRepository.findById(id);
        if (optionalCustomer.isEmpty()) {
            throw new java.lang.NullPointerException("Customer not found");
        }
        return optionalCustomer.get();
    }


    public boolean AddOutlet(Outlet outlet) throws Exception {
        if (this.outletRepository.existsById(outlet.getId())) {
            throw new Exception("Outlet with same ID is already present");
        }
        if (!this.outletRepository.findByEmail(outlet.getEmail()).isEmpty()){
            throw new Exception("Outlet with same email address is already present");
        }
        if (!this.outletRepository.findByOwnerContactNumber(outlet.getOwnerContactNumber()).isEmpty()) {
            throw new Exception("Outlet with same owner contact number is already present");
        }

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

        Role role = outlet.getOutletType().getId() == "Pl" ? Role.SP_OUTLET : Role.OUTLET;

        AuthenticationResponse authenticationResponse = authenticationService.register(
                AppUserRegisterRequest.builder()
                        .email(outlet.getEmail())
                        .password(outlet.getPassword())
                        .role(role)
                        .build());

        this.outletRepository.save(outlet);
        return true;
    }

    public boolean AddTD(TD td) throws Exception {
        if (this.tdRepository.existsById(td.getId())) {
            throw new Exception("TD with same ID is already present");
        }
        if (!this.tdRepository.findByEmail(td.getEmail()).isEmpty()){
            throw new Exception("TD with same email is already present");
        }
        if (!this.tdRepository.findByContactNumber(td.getContactNumber()).isEmpty()){
            throw new Exception("TD with same contact number is already present");
        }

        td.setActorType(this.providerService.GetActorTypesByID(td.getActorType().getId()));

        td.setAddress(this.addressService.GetAddressByID(td.getAddress().getId()));
        this.addressService.SetAddressStatus(td.getAddress().getId(), true);

        td.setBankDetails(this.bankService.GetBankDetailById(td.getBankDetails().getId()));
        this.bankService.SetBankDetailStatus(td.getBankDetails().getId(), true);

        td.setKYC(this.kycService.GetKYCByID(td.getKYC().getId()));
        this.kycService.SetKYCStatus(td.getKYC().getId(), true);

        Role role = Role.TD;

        AuthenticationResponse authenticationResponse = authenticationService.register(
                AppUserRegisterRequest.builder()
                        .email(td.getEmail())
                        .password(td.getPassword())
                        .role(role)
                        .build());

        this.tdRepository.save(td);
        return true;
    }

    public boolean AddCustomer(Customer customer) throws Exception {
        if (this.customerRepository.existsById(customer.getId())) {
            throw new Exception("Customer with same ID is already present");
        }
        if (!this.customerRepository.findByContactNumber(customer.getContactNumber()).isEmpty()){
            throw new Exception("Customer with same contact number is already present");
        }

        customer.setActorType(this.providerService.GetActorTypesByID(customer.getActorType().getId()));
        customer.setCustomerType(this.providerService.GetCustomerTypesByID(customer.getCustomerType().getId()));

        customer.setAddress(this.addressService.GetAddressByID(customer.getAddress().getId()));
        this.addressService.SetAddressStatus(customer.getAddress().getId(), true);

        this.customerRepository.save(customer);
        return true;
    }
}
