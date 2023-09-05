package com.am.reaprich.reaprichbackend.data.repositories.actors.outletprovider;

import com.am.reaprich.reaprichbackend.data.entities.actors.outletprovider.OutletType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutletTypeRepository extends CrudRepository<OutletType, String> {
}
