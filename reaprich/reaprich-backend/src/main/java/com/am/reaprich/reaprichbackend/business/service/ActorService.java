package com.am.reaprich.reaprichbackend.business.service;

import com.am.reaprich.reaprichbackend.data.entities.actors.Customer;
import com.am.reaprich.reaprichbackend.data.entities.actors.Outlet;
import com.am.reaprich.reaprichbackend.data.entities.actors.TD;
import com.am.reaprich.reaprichbackend.data.entities.actors.actorprovider.ActorType;
import com.am.reaprich.reaprichbackend.data.entities.actors.actorprovider.CustomerType;
import com.am.reaprich.reaprichbackend.data.entities.actors.outletprovider.OutletType;
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


    public Iterable<Outlet> GetAllOutlets() {
        return  this.outletRepository.findAll();
    }
    public Iterable<TD> GetAllTDs() {
        return this.tdRepository.findAll();
    }
    public Iterable<Customer> GetAllCustomers() {
        return this.customerRepository.findAll();
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


    public boolean AddOutlet(Outlet outlet) throws Exception {
        if (this.outletRepository.existsById(outlet.getId())) {
            throw new Exception("Outlet with same ID is already present");
        }
        Iterable<Outlet> allOutlets = this.outletRepository.findAll();
        for(Outlet o : allOutlets) {
            if (o.getFirmContactNumber().equals(outlet.getFirmContactNumber())){
                throw new Exception("Outlet with same Firm contact number is already present");
            }
            if (o.getEmail().equals(outlet.getEmail())){
                throw new Exception("Outlet with same Contact number is already present");
            }
        }

        outlet.setActorType(this.providerService.GetActorTypeByID(outlet.getActorType().getId()));
        outlet.setOutletType(this.GetOutletTypesByID(outlet.getOutletType().getId()));

        outlet.setFirmAddress(this.addressService.GetAddressByID(outlet.getFirmAddress().getId()));
        this.addressService.SetAddressStatus(outlet.getFirmAddress().getId(), true);

        outlet.setFirmBankDetails(this.bankService.GetBankDetailById(outlet.getFirmBankDetails().getId()));
        this.bankService.SetBankDetailStatus(outlet.getFirmBankDetails().getId(), true);

        outlet.setOwnerAddress(this.addressService.GetAddressByID(outlet.getOwnerAddress().getId()));
        this.addressService.SetAddressStatus(outlet.getOwnerAddress().getId(), true);

        outlet.setOwnerKYC(this.kycService.GetKYCByID(outlet.getOwnerKYC().getId()));
        this.kycService.SetKYCStatus(outlet.getOwnerKYC().getId(), true);

        this.outletRepository.save(outlet);
        return true;
    }

    public boolean AddTD(TD td) throws Exception {
        throw new UnsupportedOperationException();
    }

    public boolean AddCustomer(Customer customer) throws Exception {
        throw new UnsupportedOperationException();
    }
}
