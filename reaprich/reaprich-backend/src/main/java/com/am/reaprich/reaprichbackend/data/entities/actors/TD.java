package com.am.reaprich.reaprichbackend.data.entities.actors;

import com.am.reaprich.reaprichbackend.business.pojo.uermanagement.UpdateTDDetailRequest;
import com.am.reaprich.reaprichbackend.data.entities.address.Address;
import com.am.reaprich.reaprichbackend.data.entities.bank.BankDetail;
import com.am.reaprich.reaprichbackend.data.entities.kyc.KYC;
import com.am.reaprich.reaprichbackend.data.entities.actors.actorprovider.ActorType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="TD")
@NoArgsConstructor
public class TD {
    @Id
    @Column(name = "TD_ID")
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "ACTOR_TYPE", referencedColumnName = "ACTOR_TYPE_ID")
    private ActorType actorType;

    @Column(name = "TD_FNAME")
    @Getter
    @Setter
    private String firstName;

    @Column(name = "TD_LNAME")
    @Getter
    @Setter
    private String lastName;

    @Column(name = "TD_CONTACT_NO")
    @Getter
    @Setter
    private String contactNumber;

    @Column(name = "TD_PAN")
    @Getter
    @Setter
    private String PAN;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "TD_ADD", referencedColumnName = "ADD_ID")
    private Address address;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "TD_BANK", referencedColumnName = "BANK_DETAIL_ID")
    private BankDetail bankDetails;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "TD_KYC", referencedColumnName = "KYC_ID")
    private KYC tdKYC;

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

    public void update(UpdateTDDetailRequest updateTDDetailRequest) {
        this.setFirstName(updateTDDetailRequest.getFirstName());
        this.setLastName(updateTDDetailRequest.getLastName());
        this.setContactNumber(updateTDDetailRequest.getContactNumber());

        this.getBankDetails().update(updateTDDetailRequest.getBankDetails());

        this.getAddress().update(updateTDDetailRequest.getAddress());

    }
}
