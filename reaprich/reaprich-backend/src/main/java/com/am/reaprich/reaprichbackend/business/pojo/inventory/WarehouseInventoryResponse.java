package com.am.reaprich.reaprichbackend.business.pojo.inventory;

import com.am.reaprich.reaprichbackend.data.entities.inventory.WarehouseInventory;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class WarehouseInventoryResponse {
    private WarehouseInventory warehouseInventoryItem;
    private String error;
}
