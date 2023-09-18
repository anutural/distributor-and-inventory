package com.am.reaprich.reaprichbackend.data.entities.address.addressprovider;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "ADD_DIST")
@NoArgsConstructor
public class ADDDist{
    @Id
    @Column(name = "ADD_DIST_ID")
    @Getter
    @Setter
    private String id;

    @Column(name = "NAME")
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "ADD_ZONE", referencedColumnName = "ADD_ZONE_ID")
    private ADDZone zone;
}
