package com.am.reaprich.reaprichbackend.data.entities.actors.outletprovider;

import lombok.*;
import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name="OUTLET_TYPE")
public class OutletType {
    @Id
    @Column(name = "OUTLETTYPE_ID")
    @Getter
    @Setter
    private String id;

    @Column(name = "NAME")
    @Getter
    @Setter
    private String name;

    @Column(name = "DISCOUNT")
    @Getter
    @Setter
    private float discount;
}
