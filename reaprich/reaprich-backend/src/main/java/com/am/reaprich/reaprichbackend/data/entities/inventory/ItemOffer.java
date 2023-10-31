package com.am.reaprich.reaprichbackend.data.entities.inventory;

import com.am.reaprich.reaprichbackend.data.entities.inventory.inventoryprovider.OfferType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ITEM_OFFER")
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ItemOffer {
    @Id
    @Column(name = "ID")
    private String id;

    @ManyToOne
    @JoinColumn(name = "ITEM", referencedColumnName = "ID")
    private Item item;
    @ManyToOne
    @JoinColumn(name = "OFFERED_ITEM", referencedColumnName = "ID")
    private Item offeredItem;

    @Column(name = "OFFER_TYPE")
    private OfferType offerType;

    @Column(name = "MIN_QTY")
    private int minQty;

    @Column(name = "MIN_AMT")
    private int minAMT;

    @Column(name = "OFFER_ITEM_QTY")
    private int offerItemQty;

    @Column(name = "OFFER_DISCOUNT_PERCENT")
    private double offerDiscountPercent;

    @Column(name = "COUPON_VALIDITY_MONTHS")
    private int couponValidityMonths;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "IS_ACTIVE")
    private boolean isActive;

}
