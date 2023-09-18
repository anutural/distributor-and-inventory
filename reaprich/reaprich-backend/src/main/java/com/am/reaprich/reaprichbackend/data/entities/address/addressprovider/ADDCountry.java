package com.am.reaprich.reaprichbackend.data.entities.address.addressprovider;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADD_COUNTRY")
@NoArgsConstructor
public class ADDCountry{
    @Id
    @Column(name = "ADD_COUNTRY_ID")
    @Getter
    @Setter
    private String id;

    @Column(name = "NAME")
    @Getter
    @Setter
    private String name;
}
