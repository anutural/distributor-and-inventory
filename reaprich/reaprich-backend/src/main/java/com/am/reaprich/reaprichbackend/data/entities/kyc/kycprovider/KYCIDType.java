package com.am.reaprich.reaprichbackend.data.entities.kyc.kycprovider;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "KYC_ID_TYPE")
@NoArgsConstructor
public class KYCIDType{
    @Id
    @Column(name = "KYC_ID_TYPE")
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    @Column(name = "DESCRIPTION")
    private String idDesc;
}
