package com.am.reaprich.reaprichbackend.data.entities.inventory;

import com.am.reaprich.reaprichbackend.data.entities.actors.Outlet;
import com.am.reaprich.reaprichbackend.data.entities.inventory.inventoryprovider.ItemState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "WAREHOUSE_INVENTORY")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class WarehouseInventory {
    @Id
    @Column(name = "ID")
    private String id;

    @ManyToOne
    @JoinColumn(name = "WAREHOUSE", referencedColumnName = "ID")
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "ITEM", referencedColumnName = "ID")
    private Item item;

    @Column(name = "BATCH_NUMBER")
    private String batchNumber;

    @Column(name = "MFG_DATE")
    private Date mfgDate;

    @Column(name = "EXP_DATE")
    private Date expDate;

    @Column(name = "STATE")
    private ItemState state;
}
