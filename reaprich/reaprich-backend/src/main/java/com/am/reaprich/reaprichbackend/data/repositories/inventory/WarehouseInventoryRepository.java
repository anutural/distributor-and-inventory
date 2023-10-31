package com.am.reaprich.reaprichbackend.data.repositories.inventory;

import com.am.reaprich.reaprichbackend.data.entities.inventory.WarehouseInventory;
import com.am.reaprich.reaprichbackend.data.entities.inventory.inventoryprovider.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseInventoryRepository extends CrudRepository<WarehouseInventory, String> {
    @Query( "select i from WarehouseInventory i where i.warehouse.id = :warehouse" )
    Iterable<WarehouseInventory> findByWarehouse(String warehouse);
}
