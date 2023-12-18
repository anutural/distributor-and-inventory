package com.am.reaprich.reaprichbackend.business.pojo.inventoryops;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AllItemTransferRequest {
    private ItemTransferFilterBy itemTransferFilterBy;
    private String filter;
}
