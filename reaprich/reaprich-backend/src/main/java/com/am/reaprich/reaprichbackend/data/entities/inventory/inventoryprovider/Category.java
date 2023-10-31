package com.am.reaprich.reaprichbackend.data.entities.inventory.inventoryprovider;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ITEM_CATEGORY", uniqueConstraints = @UniqueConstraint(columnNames = {"ID", "CATEGORY"}))
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Category {
    @Id
    @Column(name = "ID")
    private String id;

    @Column (name = "CATEGORY")
    private String category;
}
