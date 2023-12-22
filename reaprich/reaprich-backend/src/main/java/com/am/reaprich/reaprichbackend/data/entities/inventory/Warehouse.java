package com.am.reaprich.reaprichbackend.data.entities.inventory;

import com.am.reaprich.reaprichbackend.data.entities.actors.Outlet;
import com.am.reaprich.reaprichbackend.data.entities.address.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "WAREHOUSE")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Warehouse {
    @Id
    @Column(name = "ID")
    private String id;

    //@ManyToOne
    @OneToOne
    @JoinColumn(name = "OUTLET", referencedColumnName = "OUTLET_ID")
    private Outlet outlet;

    @OneToOne
    @JoinColumn(name = "ADDRESS", referencedColumnName = "ADD_ID")
    private Address address;

    @Column(name = "CONTACT_NUMBER")
    private String contactNumber;

    @Column(name = "IS_COMPANY")
    private boolean isCompany;

    @Column(name = "STATUS")
    private boolean status;
}
