package com.am.reaprich.reaprichbackend.data.entities.actors;

import com.am.reaprich.reaprichbackend.data.entities.address.Address;
import com.am.reaprich.reaprichbackend.data.entities.actors.actorprovider.ActorType;
import com.am.reaprich.reaprichbackend.data.entities.actors.actorprovider.CustomerType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="CUSTOMER")
@NoArgsConstructor
public class Customer {
    @Id
    @Column(name = "CUSTOMER_ID")
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "ACTOR_TYPE", referencedColumnName = "ACTOR_TYPE_ID")
    private ActorType actorType;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_TYPE", referencedColumnName = "CUSTOMER_TYPE_ID")
    private CustomerType customerType;

    @Column(name = "CUSTOMER_FNAME")
    @Getter
    @Setter
    private String firstName;

    @Column(name = "CUSTOMER_LNAME")
    @Getter
    @Setter
    private String lastName;

    @Column(name = "CUSTOMER_CONTACT_NO")
    @Getter
    @Setter
    private String contactNumber;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "CUSTOMER_ADD", referencedColumnName = "ADD_ID")
    private Address address;
}
