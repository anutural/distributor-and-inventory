package com.am.reaprich.reaprichbackend.business.pojo.inventoryops;

import com.am.reaprich.reaprichbackend.data.entities.inventoryops.transactionsprovider.PurchaseRequestStatus;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ItemTransferUpdateStatusRequest {
    private String itemTransferID;
    private PurchaseRequestStatus purchaseRequestStatus;
}
