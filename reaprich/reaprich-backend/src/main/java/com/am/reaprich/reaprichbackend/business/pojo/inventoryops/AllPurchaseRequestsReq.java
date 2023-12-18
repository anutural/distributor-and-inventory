package com.am.reaprich.reaprichbackend.business.pojo.inventoryops;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AllPurchaseRequestsReq {
    private PurchaseRequestFilterBy purchaseRequestFilterBy;
    private String filter;
}
