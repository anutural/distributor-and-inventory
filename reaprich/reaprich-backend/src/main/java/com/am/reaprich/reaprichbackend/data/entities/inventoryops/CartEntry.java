package com.am.reaprich.reaprichbackend.data.entities.inventoryops;

import com.am.reaprich.reaprichbackend.data.entities.actors.Outlet;
import com.am.reaprich.reaprichbackend.data.entities.inventory.Item;
import com.am.reaprich.reaprichbackend.data.entities.inventoryops.transactionsprovider.CartEntryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "CART_ENTRIES")
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class CartEntry {
    @Id
    @Column(name = "ID")
    private String id;

    @ManyToOne
    @JoinColumn(name = "OUTLET", referencedColumnName = "OUTLET_ID")
    private Outlet outlet;

    @ManyToOne
    @JoinColumn(name = "ITEM", referencedColumnName = "ID")
    private Item item;

    @Column(name = "ITEM_QNTY")
    private int itemQuantity;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column (name = "UPDATED_DATE")
    private Date updatedDate;

    @Column (name = "STATE")
    private CartEntryStatus status;
}
