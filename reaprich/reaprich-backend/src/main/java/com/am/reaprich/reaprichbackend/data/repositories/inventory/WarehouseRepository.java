package com.am.reaprich.reaprichbackend.data.repositories.inventory;

import com.am.reaprich.reaprichbackend.data.entities.inventory.Warehouse;
import com.am.reaprich.reaprichbackend.data.entities.inventory.WarehouseInventory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends CrudRepository <Warehouse, String> {
    @Query( "select w from Warehouse w where w.outlet.id = :outlet" )
    Iterable<Warehouse> findByOutlet(String outlet);
}
