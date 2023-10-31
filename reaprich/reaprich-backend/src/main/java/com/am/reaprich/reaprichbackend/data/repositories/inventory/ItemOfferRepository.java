package com.am.reaprich.reaprichbackend.data.repositories.inventory;

import com.am.reaprich.reaprichbackend.data.entities.inventory.ItemOffer;
import com.am.reaprich.reaprichbackend.data.entities.inventory.WarehouseInventory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemOfferRepository extends CrudRepository<ItemOffer, String> {
    @Query( "select i from ItemOffer i where i.item = :item" )
    Iterable<ItemOffer> findByItem(String item);
}
