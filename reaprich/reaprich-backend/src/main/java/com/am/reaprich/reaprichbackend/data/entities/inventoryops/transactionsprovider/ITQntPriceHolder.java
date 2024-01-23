package com.am.reaprich.reaprichbackend.data.entities.inventoryops.transactionsprovider;

import com.am.reaprich.reaprichbackend.data.entities.inventory.Item;
import com.am.reaprich.reaprichbackend.data.entities.inventoryops.ItemTransfer;
import com.am.reaprich.reaprichbackend.data.entities.inventoryops.PurchaseRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ITQntPriceHolder {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "PRICE")
    private double price;

    @ManyToOne
    @JoinColumn(name = "ITEM")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "item_transfer_id")
    private ItemTransfer itemTransfer;
}
