package com.am.reaprich.reaprichbackend.business.pojo.inventoryops;

import com.am.reaprich.reaprichbackend.data.entities.inventoryops.PurchaseRequest;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class PurchaseRequestCollectionResponse {
    private List<PurchaseRequest> purchaseRequestList;
    private String error;
}
