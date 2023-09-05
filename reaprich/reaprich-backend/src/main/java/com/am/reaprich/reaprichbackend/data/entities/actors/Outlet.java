package com.am.reaprich.reaprichbackend.data.entities.actors;

import com.am.reaprich.reaprichbackend.data.entities.address.Address;
import com.am.reaprich.reaprichbackend.data.entities.bank.BankDetail;
import com.am.reaprich.reaprichbackend.data.entities.kyc.KYC;
import com.am.reaprich.reaprichbackend.data.entities.actors.actorprovider.ActorType;
import com.am.reaprich.reaprichbackend.data.entities.actors.outletprovider.OutletType;
import lombok.*;
import javax.persistence.*;


@Entity
@Table(name="OUTLET")
@NoArgsConstructor
public class Outlet {
    @Id
    @Column(name = "OUTLET_ID")
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
    @JoinColumn(name = "OUTLET_TYPE", referencedColumnName = "OUTLETTYPE_ID")
    private OutletType outletType;

    @Column(name = "FIRM_NAME")
    @Getter
    @Setter
    private String firmName;

    @Column(name = "FIRM_CONTACT_NO")
    @Getter
    @Setter
    private String firmContactNumber;

    @Column(name = "FIRM_GST_NO")
    @Getter
    @Setter
    private String firmGSTNumber;

    @Column(name = "FIRM_PAN")
    @Getter
    @Setter
    private String firmPAN;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "FIRM_ADD", referencedColumnName = "ADD_ID")
    private Address firmAddress;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "FIRM_BANK", referencedColumnName = "BANK_DETAIL_ID")
    private BankDetail firmBankDetails;

    @Column(name = "OWNER_FNAME")
    @Getter
    @Setter
    private String ownerFirstName;

    @Column(name = "OWNER_LNAME")
    @Getter
    @Setter
    private String ownerLastName;

    @Column(name = "OWNER_CONTACT_NO")
    @Getter
    @Setter
    private String ownerContactNumber;

    @Column(name = "OWNER_PAN")
    @Getter
    @Setter
    private String ownerPAN;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "OWNER_KYC", referencedColumnName = "KYC_ID")
    private KYC ownerKYC;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "OWNER_ADD", referencedColumnName = "ADD_ID")
    private Address ownerAddress;

    @Column(name = "EMAIL")
    @Getter
    @Setter
    private String email;

    @Column(name = "PASSWORD")
    @Getter
    @Setter
    private String password;

    @Column(name = "STATUS")
    @Getter
    @Setter
    private boolean status;
}
