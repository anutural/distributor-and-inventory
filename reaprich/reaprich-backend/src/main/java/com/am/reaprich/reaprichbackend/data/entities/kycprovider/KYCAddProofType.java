package com.am.reaprich.reaprichbackend.data.entities.kycprovider;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "KYC_ADD_PROOF_TYPE")
@NoArgsConstructor
public class KYCAddProofType{
    @Id
    @Column(name = "KYC_ADD_PROOF_TYPE")
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    @Column(name = "DESCRIPTION")
    private String idDesc;
}