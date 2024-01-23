package com.am.reaprich.reaprichbackend.business.pojo.inventoryops;

import com.am.reaprich.reaprichbackend.data.entities.inventoryops.CartEntry;
import com.am.reaprich.reaprichbackend.data.entities.inventoryops.transactionsprovider.PurchaseRequestType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class PRRequest {
    private String requesterOutlet;
    private String requestedToOutlet;
    PurchaseRequestType purchaseRequestType;
    List<String> cartEntryIDs;
}
