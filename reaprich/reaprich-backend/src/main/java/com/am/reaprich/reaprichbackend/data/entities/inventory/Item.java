package com.am.reaprich.reaprichbackend.data.entities.inventory;

import com.am.reaprich.reaprichbackend.data.entities.actors.outletprovider.OutletType;
import com.am.reaprich.reaprichbackend.data.entities.inventory.inventoryprovider.Category;
import com.am.reaprich.reaprichbackend.data.entities.inventory.inventoryprovider.PackingType;
import com.am.reaprich.reaprichbackend.data.entities.inventory.inventoryprovider.Subcategory;
import com.sun.xml.bind.v2.runtime.reflect.Lister;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@Table (name = "ITEM")
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "PACKING_TYPE", referencedColumnName = "ID")
    private PackingType packingType;

    @ManyToOne
    @JoinColumn(name = "CATEGORY", referencedColumnName = "ID")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "SUBCATEGORY", referencedColumnName = "ID")
    private Subcategory subcategory;

    @ElementCollection
    @MapKeyColumn(name="OUTLET_TYPE")
    @Column(name="PRICE")
    @CollectionTable(name="ITEM_PRICES", joinColumns=@JoinColumn(name="ITEM_ID"))
    Map<String, Double> itemPrices;

    @Column(name = "GST_PRICE")
    private double GSTPrice;

    @Column(name = "RETAIL_PRICE")
    private double reatilPrice;

    @Column(name = "PICTURE")
    private String pictureLink;

    @Column(name = "THUMBNAIL")
    private String thumbnailLink;

    public double getPriceForOutletType(OutletType outletType) {
        if (this.getItemPrices().containsKey(outletType.getId())) {
            return this.getItemPrices().get(outletType.getId());
        }
        return this.getReatilPrice();
    }
}
