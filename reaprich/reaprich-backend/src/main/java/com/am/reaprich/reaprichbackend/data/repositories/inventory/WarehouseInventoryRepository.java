package com.am.reaprich.reaprichbackend.data.repositories.inventory;

import com.am.reaprich.reaprichbackend.data.entities.inventory.WarehouseInventory;
import com.am.reaprich.reaprichbackend.data.entities.inventory.inventoryprovider.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseInventoryRepository extends CrudRepository<WarehouseInventory, String> {
    @Query( "select i from WarehouseInventory i where i.warehouse.id = :warehouse and i.state = 2" )
    Iterable<WarehouseInventory> findByWarehouse(String warehouse);

    @Query( "select i from WarehouseInventory i where i.state = :state" )
    Iterable<WarehouseInventory> findByState(int state);
}
