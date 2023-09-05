package com.am.reaprich.reaprichbackend.data.entities.address;

import com.am.reaprich.reaprichbackend.data.entities.actors.actorprovider.ActorType;
import com.am.reaprich.reaprichbackend.data.entities.address.addressprovider.ADDCountry;
import com.am.reaprich.reaprichbackend.data.entities.address.addressprovider.ADDDist;
import com.am.reaprich.reaprichbackend.data.entities.address.addressprovider.ADDState;
import com.am.reaprich.reaprichbackend.data.entities.address.addressprovider.ADDZone;
import lombok.*;
import javax.persistence.*;
import com.am.reaprich.reaprichbackend.data.entities.address.addressprovider.*;

@Entity
@Table(name = "ADDRESS")
@NoArgsConstructor
public class Address {
    @Id
    @Column(name = "ADD_ID")
    @Getter
    @Setter
    private String id;

    @ManyToOne
    @JoinColumn(name = "ACTOR_TYPE", referencedColumnName = "ACTOR_TYPE_ID")
    @Getter
    @Setter
    private ActorType actorType;

    @Column(name = "ADD_NAME")
    @Getter
    @Setter
    private  String name;

    @Column(name = "ADD_LINE1")
    @Getter
    @Setter
    private  String addressLine1;

    @Column(name = "ADD_LINE2")
    @Getter
    @Setter
    private  String addressLine2;

    @Column(name = "ADD_CITY")
    @Getter
    @Setter
    private  String city;

    @Column(name = "ADD_TA")
    @Getter
    @Setter
    private  String taluka;

    @Column(name = "ADD_PIN")
    @Getter
    @Setter
    private  String pinCode;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "ADD_DIST", referencedColumnName = "ADD_DIST_ID")
    private ADDDist dist;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "ADD_ZONE", referencedColumnName = "ADD_ZONE_ID")
    private ADDZone zone;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "ADD_STATE", referencedColumnName = "ADD_STATE_ID")
    private ADDState state;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "ADD_COUNTRY", referencedColumnName = "ADD_COUNTRY_ID")
    private ADDCountry country;

    @Column(name = "STATUS")
    @Getter
    @Setter
    private boolean status;
}
