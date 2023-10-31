package com.am.reaprich.reaprichbackend.data.entities.inventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Coupon", uniqueConstraints = @UniqueConstraint(columnNames = {"ID", "COUPON_CODE"}))
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Coupon {
    @Id
    @Column(name = "ID")
    private String id;

    @ManyToOne
    @JoinColumn(name = "ITEM_OFFER", referencedColumnName = "ID")
    private ItemOffer itemOffer;

    @Column(name = "COUPON_CODE")
    private String couponCode;

    @Column(name = "COUPON_AMT")
    private int couponAmt;

    @Column(name = "VALIDITY_MONTHS")
    private int validityMonths;

    @Column(name = "IS_REDEEMED")
    private boolean isRedeemed;
}
