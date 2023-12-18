package com.am.reaprich.reaprichbackend.data.repositories.inventoryops;

import com.am.reaprich.reaprichbackend.data.entities.inventoryops.ItemTransfer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemTransferRepository extends CrudRepository<ItemTransfer, String> {
}
