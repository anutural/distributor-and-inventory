package com.am.reaprich.reaprichbackend.business.pojo.inventory;

import com.am.reaprich.reaprichbackend.data.entities.inventory.WarehouseInventory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class WarehouseInventoryCollectionResponse {
    List<WarehouseInventory> inventoryItems;
    String error;
}
