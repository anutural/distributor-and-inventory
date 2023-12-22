package com.am.reaprich.reaprichbackend.business.pojo.inventory;

import com.am.reaprich.reaprichbackend.data.entities.actors.Outlet;
import com.am.reaprich.reaprichbackend.data.entities.inventory.Item;
import com.am.reaprich.reaprichbackend.data.entities.inventory.inventoryprovider.ItemState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AddWarehouseInventoryItemsRequest {
    private Item item;
    private Outlet outlet;
    private String batchNumber;
    private Date mfgDate;
    private Date expDate;
    private ItemState state;
    private int quantity;
}
