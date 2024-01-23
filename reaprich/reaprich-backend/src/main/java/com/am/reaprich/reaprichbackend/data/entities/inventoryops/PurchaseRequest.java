package com.am.reaprich.reaprichbackend.data.entities.inventoryops;

import com.am.reaprich.reaprichbackend.business.pojo.inventoryops.PRRequest;
import com.am.reaprich.reaprichbackend.business.service.inventory.InventoryOpsService;
import com.am.reaprich.reaprichbackend.business.service.usermanagement.ActorService;
import com.am.reaprich.reaprichbackend.data.entities.actors.Outlet;
import com.am.reaprich.reaprichbackend.data.entities.inventoryops.transactionsprovider.CartEntryStatus;
import com.am.reaprich.reaprichbackend.data.entities.inventoryops.transactionsprovider.PurchaseRequestStatus;
import com.am.reaprich.reaprichbackend.data.entities.inventoryops.transactionsprovider.PurchaseRequestType;
import com.am.reaprich.reaprichbackend.data.entities.inventoryops.transactionsprovider.PRQntPriceHolder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "PURCHASE_REQUEST")
@RequiredArgsConstructor
@AllArgsConstructor
public class PurchaseRequest {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "DATE")
    private Date date;

    @Column(name = "PURCHASE_REQUEST_TYPE")
    private PurchaseRequestType purchaseRequestType;

    @ManyToOne
    @JoinColumn(name = "REQUEST_FROM")
    private Outlet requestFrom;

    @ManyToOne
    @JoinColumn(name = "REQUEST_TO")
    private Outlet requestTo;

    @OneToMany(mappedBy = "purchaseRequest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PRQntPriceHolder> PRQntPriceHolders;

    public void setPRQntPriceHolders(List<PRQntPriceHolder> PRQntPriceHolders) {
        for (PRQntPriceHolder PRQntPriceHolder : PRQntPriceHolders) {
            PRQntPriceHolder.setPurchaseRequest(this);
        }
        this.PRQntPriceHolders = PRQntPriceHolders;
    }

    @Column(name = "STATUS")
    private PurchaseRequestStatus status;

    @Column(name = "NOTE")
    private String note;

    @Column(name = "IS_UNKNOWN")
    private boolean isUnknown;
}
