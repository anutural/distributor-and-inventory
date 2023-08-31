package com.am.reaprich.reaprichbackend.data.entities;

import com.am.reaprich.reaprichbackend.data.entities.actors.actorprovider.ActorType;
import lombok.*;
import javax.persistence.*;
import com.am.reaprich.reaprichbackend.data.entities.kycprovider.*;

@Entity
@Table(name = "KYC")
@NoArgsConstructor
public class KYC {
    @Id
    @Column(name = "KYC_ID")
    @Getter
    @Setter
    private String id;

    @ManyToOne
    @JoinColumn(name = "ACTOR_TYPE", referencedColumnName = "ACTOR_TYPE_ID")
    @Getter
    @Setter
    private ActorType actorType;

    @Column(name = "KYC_NAME")
    @Getter
    @Setter
    private  String name;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "KYC_ID_TYPE", referencedColumnName = "KYC_ID_TYPE")
    private KYCIDType idType;

    @Column(name = "KYC_ID_NO")
    @Getter
    @Setter
    private String idNumber;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "KYC_ADD_PROOF_TYPE", referencedColumnName = "KYC_ADD_PROOF_TYPE")
    private KYCAddProofType addProofType;

    @Column(name = "KYC_ADD_PROOF_NO")
    @Getter
    @Setter
    private String addProofNumber;

    @Column(name = "DOC_LINK")
    @Getter
    @Setter
    private String documentLinks;
}
