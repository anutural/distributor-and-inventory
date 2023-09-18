package com.am.reaprich.reaprichbackend.business.service;

import com.am.reaprich.reaprichbackend.data.entities.actors.actorprovider.ActorType;
import com.am.reaprich.reaprichbackend.data.entities.address.Address;
import com.am.reaprich.reaprichbackend.data.entities.address.addressprovider.ADDCountry;
import com.am.reaprich.reaprichbackend.data.entities.address.addressprovider.ADDDist;
import com.am.reaprich.reaprichbackend.data.entities.address.addressprovider.ADDState;
import com.am.reaprich.reaprichbackend.data.entities.address.addressprovider.ADDZone;
import com.am.reaprich.reaprichbackend.data.repositories.address.AddressRepository;
import com.am.reaprich.reaprichbackend.data.repositories.address.addressprovider.ADDCountryRepository;
import com.am.reaprich.reaprichbackend.data.repositories.address.addressprovider.ADDDistRepository;
import com.am.reaprich.reaprichbackend.data.repositories.address.addressprovider.ADDStateRepository;
import com.am.reaprich.reaprichbackend.data.repositories.address.addressprovider.ADDZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ADDCountryRepository addCountryRepository;
    @Autowired
    private ADDStateRepository addStateRepository;
    @Autowired
    private ADDZoneRepository addZoneRepository;
    @Autowired
    private ADDDistRepository addDistRepository;

    public Iterable<ADDCountry> GetCountries() {  return  this.addCountryRepository.findAll(); }
    public ADDCountry GetContryByID(String id) throws Exception {
        Optional<ADDCountry> optionalCountry = this.addCountryRepository.findById(id);
        if (optionalCountry.isEmpty()) {
            throw new java.lang.NullPointerException("Country not found");
        }
        return optionalCountry.get();
    }
    public Iterable<ADDState> GetStates() { return this.addStateRepository.findAll(); }
    public ADDState GetStateByID(String id) throws Exception {
        Optional<ADDState> optionalState = this.addStateRepository.findById(id);
        if (optionalState.isEmpty()) {
            throw new java.lang.NullPointerException("State not found");
        }
        return optionalState.get();
    }
    public Iterable<ADDZone> GetZones() { return this.addZoneRepository.findAll(); }
    public ADDZone GetZoneByID(String id) throws Exception {
        Optional<ADDZone> optionalZone = this.addZoneRepository.findById(id);
        if (optionalZone.isEmpty()) {
            throw new java.lang.NullPointerException("Zone not found");
        }
        return optionalZone.get();
    }
    public Iterable<ADDDist> GetDistrict() { return  this.addDistRepository.findAll(); }
    public ADDDist GetDistByID(String id) throws Exception {
        Optional<ADDDist> optionalDist = this.addDistRepository.findById(id);
        if (optionalDist.isEmpty()) {
            throw new java.lang.NullPointerException("District not found");
        }
        return optionalDist.get();
    }

    public Address GetAddressByID(String id) throws Exception {
        Optional<Address> optionalAddress = this.addressRepository.findById(id);
        if (optionalAddress.isEmpty()) {
            throw new java.lang.NullPointerException("Address not found");
        }
        return optionalAddress.get();
    }
    public void SetAddressStatus(String id, boolean status) throws Exception {
        Optional<Address> optionalAddress = this.addressRepository.findById(id);
        if (optionalAddress.isEmpty()) {
            throw new java.lang.NullPointerException("Address not found");
        }
        Address address = optionalAddress.get();
        address.setStatus(status);
        this.addressRepository.save(address);
    }


    public boolean AddCountry(ADDCountry Country) throws Exception {
        if (this.addCountryRepository.existsById(Country.getId())) {
            throw new Exception("Country with the same ID already exist.");
        }
        this.addCountryRepository.save(Country);
        return true;
    }
    public boolean AddState(ADDState State) throws Exception {
        if (this.addStateRepository.existsById(State.getId())) {
            throw new Exception("State with the same ID already exist.");
        }
        State.setCountry(GetContryByID(State.getCountry().getId()));
        this.addStateRepository.save(State);
        return true;
    }
    public boolean AddZone(ADDZone zone) throws Exception {
        if (this.addZoneRepository.existsById(zone.getId())) {
            throw new Exception("Zone with the same ID already exist.");
        }
        zone.setState(GetStateByID(zone.getState().getId()));
        this.addZoneRepository.save(zone);
        return true;
    }
    public boolean AddDist(ADDDist dist) throws Exception {
        if (this.addDistRepository.existsById(dist.getId())) {
            throw new Exception("District with the same ID already exist.");
        }
        dist.setZone(GetZoneByID(dist.getZone().getId()));
        this.addDistRepository.save(dist);
        return true;
    }

    public boolean AddAddress(Address address) throws Exception {
        if (this.addressRepository.existsById(address.getId())) {
            throw new Exception("Address with the same ID already exist.");
        }
        address.setCountry(GetContryByID(address.getCountry().getId()));
        address.setState(GetStateByID(address.getState().getId()));
        address.setZone(GetZoneByID(address.getZone().getId()));
        address.setDist(GetDistByID(address.getDist().getId()));

        address.setStatus(false);

        this.addressRepository.save(address);
        return true;
    }
}
