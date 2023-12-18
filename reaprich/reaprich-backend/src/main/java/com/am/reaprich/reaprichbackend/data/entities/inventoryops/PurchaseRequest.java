package com.am.reaprich.reaprichbackend.data.entities.inventoryops;

import com.am.reaprich.reaprichbackend.data.entities.inventoryops.transactionsprovider.PurchaseRequestStatus;
import com.am.reaprich.reaprichbackend.data.entities.inventoryops.transactionsprovider.PurchaseRequestType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.Map;

@Entity
@Data
@Table(name = "PURCHASE_REQUEST")
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseRequest {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "DATE")
    private Date date;

    @Column(name = "PURCHASE_REQUEST_TYPE")
    private PurchaseRequestType purchaseRequestType;

    @Column(name = "REQUEST_FROM")
    private String requestFromID;

    @Column(name = "REQUEST_TO")
    private String requestToID;

    @ElementCollection
    @MapKeyColumn(name="ITEM")
    @Column(name="REQUESTED_QUANTITY")
    @CollectionTable(name="PURCHASE_REQUEST_ITEMS", joinColumns=@JoinColumn(name="REQUEST_ID"))
    Map<String, Integer> requestedItems;

    @Column(name = "STATUS")
    private PurchaseRequestStatus status;

    @Column(name = "NOTE")
    private String note;
}
