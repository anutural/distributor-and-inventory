package com.am.reaprich.reaprichbackend.data.entities.inventoryops;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Map;

@Entity
@Data
@Table(name = "ITEM_TRANSFER")
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ItemTransfer {
    @Id
    @Column(name = "ID")
    private String id;

    @OneToOne
    @JoinColumn(name = "PURCHASE_REQUEST", referencedColumnName = "ID")
    private PurchaseRequest purchaseRequest;

    @ElementCollection
    @MapKeyColumn(name="ITEM")
    @Column(name="DELIVERED_QUANTITY")
    @CollectionTable(name="PURCHASE_REQUEST_DELIVERED_ITEMS", joinColumns=@JoinColumn(name="TRANSFER_ID"))
    Map<String, Integer> deliveredItems;

    @Column(name = "IS_UNKNOWN")
    private boolean isUnknown;
}
