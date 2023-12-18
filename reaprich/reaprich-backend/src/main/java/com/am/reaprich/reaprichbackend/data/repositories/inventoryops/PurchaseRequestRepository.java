package com.am.reaprich.reaprichbackend.data.repositories.inventoryops;

import com.am.reaprich.reaprichbackend.data.entities.inventoryops.PurchaseRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRequestRepository extends CrudRepository<PurchaseRequest, String> {
}
