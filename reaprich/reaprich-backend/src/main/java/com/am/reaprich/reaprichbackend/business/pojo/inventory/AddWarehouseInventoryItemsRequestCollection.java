package com.am.reaprich.reaprichbackend.business.pojo.inventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class AddWarehouseInventoryItemsRequestCollection {
    private List<AddWarehouseInventoryItemsRequest> addWarehouseInventoryItemsRequests;
}
