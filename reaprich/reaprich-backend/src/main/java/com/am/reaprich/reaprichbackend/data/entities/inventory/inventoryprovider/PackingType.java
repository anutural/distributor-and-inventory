package com.am.reaprich.reaprichbackend.data.entities.inventory.inventoryprovider;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ITEM_PACKING_TYPE", uniqueConstraints = @UniqueConstraint(columnNames = {"ID", "TYPE"}))
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class PackingType {
    @Id
    @Column(name = "ID")
    private String id;

    @Column (name = "TYPE")
    private String packingType;

    @Column (name = "QUANTITY_GMS")
    private double quantityInGrams;

    @Column (name = "CONTAINER")
    private String container;
}
