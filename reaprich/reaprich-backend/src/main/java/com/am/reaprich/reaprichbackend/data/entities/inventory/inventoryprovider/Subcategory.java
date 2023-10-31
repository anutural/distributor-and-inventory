package com.am.reaprich.reaprichbackend.data.entities.inventory.inventoryprovider;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ITEM_SUBCATEGORY", uniqueConstraints = @UniqueConstraint(columnNames = {"ID", "CATEGORY"}))
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Subcategory {
    @Id
    @Column(name = "ID")
    private String id;

    @ManyToOne
    @JoinColumn(name = "CATEGORY", referencedColumnName = "ID")
    private Category category;

    @Column (name = "SUBCATEGORY")
    private String subcategory;
}
