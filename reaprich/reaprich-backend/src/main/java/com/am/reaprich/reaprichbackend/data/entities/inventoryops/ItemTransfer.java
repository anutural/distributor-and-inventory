package com.am.reaprich.reaprichbackend.data.entities.inventoryops;


import com.am.reaprich.reaprichbackend.data.entities.inventoryops.transactionsprovider.ITQntPriceHolder;
import com.am.reaprich.reaprichbackend.data.entities.inventoryops.transactionsprovider.PRQntPriceHolder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @Column(name = "IS_UNKNOWN")
    private boolean isUnknown;
}
