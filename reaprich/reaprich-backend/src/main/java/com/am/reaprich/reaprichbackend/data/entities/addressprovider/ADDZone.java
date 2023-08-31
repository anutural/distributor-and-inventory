package com.am.reaprich.reaprichbackend.data.entities.addressprovider;

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
    private String name;
}
