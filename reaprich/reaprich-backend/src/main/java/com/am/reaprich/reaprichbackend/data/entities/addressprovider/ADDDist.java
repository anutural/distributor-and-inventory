package com.am.reaprich.reaprichbackend.data.entities.addressprovider;

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
    private String name;
}
