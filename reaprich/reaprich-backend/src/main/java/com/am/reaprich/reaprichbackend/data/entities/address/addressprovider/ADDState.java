package com.am.reaprich.reaprichbackend.data.entities.address.addressprovider;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "ADD_STATE")
@NoArgsConstructor
public class ADDState{
    @Id
    @Column(name = "ADD_STATE_ID")
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
    @JoinColumn(name = "ADD_COUNTRY", referencedColumnName = "ADD_COUNTRY_ID")
    private ADDCountry country;
}
