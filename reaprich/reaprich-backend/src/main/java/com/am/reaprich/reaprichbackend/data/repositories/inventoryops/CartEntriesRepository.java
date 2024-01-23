package com.am.reaprich.reaprichbackend.data.repositories.inventoryops;

import com.am.reaprich.reaprichbackend.data.entities.inventoryops.CartEntry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartEntriesRepository extends CrudRepository<CartEntry, String> {
    @Query( "select c from CartEntry c where c.outlet.id = :outlet and c.status = 0" )
    Iterable<CartEntry> findByOutletID(String outlet);

}
