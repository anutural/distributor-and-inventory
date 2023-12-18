package com.am.reaprich.reaprichbackend.business.pojo.inventoryops;

import com.am.reaprich.reaprichbackend.data.entities.inventoryops.ItemTransfer;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ItemTransferResponse {
    private ItemTransfer itemTransfer;
    private String error;
}
