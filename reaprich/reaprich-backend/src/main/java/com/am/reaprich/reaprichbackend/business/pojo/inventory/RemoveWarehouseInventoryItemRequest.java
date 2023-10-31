package com.am.reaprich.reaprichbackend.business.pojo.inventory;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RemoveWarehouseInventoryItemRequest {
    private List<String> warehouseInvetoryItemList;
}
