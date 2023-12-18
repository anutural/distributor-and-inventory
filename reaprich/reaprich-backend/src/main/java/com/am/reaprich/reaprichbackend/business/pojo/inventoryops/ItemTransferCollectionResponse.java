package com.am.reaprich.reaprichbackend.business.pojo.inventoryops;

import com.am.reaprich.reaprichbackend.data.entities.inventoryops.ItemTransfer;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ItemTransferCollectionResponse {
    private List<ItemTransfer> itemTransferList;
    private String error;
}
