package com.am.reaprich.reaprichbackend.data.entities.address.addressprovider;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "ADD_ZONE")
@NoArgsConstructor
public class ADDZone{
    @Id
    @Column(name = "ADD_ZONE_ID")
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
    @JoinColumn(name = "ADD_STATE", referencedColumnName = "ADD_STATE_ID")
    private ADDState state;
}
