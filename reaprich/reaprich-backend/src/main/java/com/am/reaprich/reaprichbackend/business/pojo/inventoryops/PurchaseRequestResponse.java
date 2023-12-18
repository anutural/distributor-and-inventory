package com.am.reaprich.reaprichbackend.business.pojo.inventoryops;

import com.am.reaprich.reaprichbackend.data.entities.inventoryops.PurchaseRequest;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PurchaseRequestResponse {
    private PurchaseRequest purchaseRequest;
    private String error;
}
