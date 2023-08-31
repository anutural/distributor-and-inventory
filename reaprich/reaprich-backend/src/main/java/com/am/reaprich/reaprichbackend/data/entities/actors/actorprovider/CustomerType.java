package com.am.reaprich.reaprichbackend.data.entities.actors.actorprovider;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER_TYPE")
@NoArgsConstructor
public class CustomerType {
    @Id
    @Getter
    @Setter
    @Column(name = "CUSTOMER_TYPE_ID")
    private String id;

    @Getter
    @Setter
    @Column(name = "DISCOUNT")
    private float discount;

    @Getter
    @Setter
    @Column(name = "CUSTOMER_TYPE_DESC")
    private String desc;
}
