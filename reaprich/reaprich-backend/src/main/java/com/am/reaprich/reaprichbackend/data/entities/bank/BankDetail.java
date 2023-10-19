package com.am.reaprich.reaprichbackend.data.entities.bank;

import com.am.reaprich.reaprichbackend.data.entities.actors.actorprovider.ActorType;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "BANK_DETAIL")
@NoArgsConstructor
public class BankDetail {
    @Id
    @Column(name = "BANK_DETAIL_ID")
    @Getter
    @Setter
    private String id;

    @ManyToOne
    @JoinColumn(name = "ACTOR_TYPE", referencedColumnName = "ACTOR_TYPE_ID")
    @Getter
    @Setter
    private ActorType actorType;

    @Column(name = "BANK_DETAIL_NAME")
    @Getter
    @Setter
    private  String name;

    @Column(name = "BANK_DETAIL_AC_NUMBER")
    @Getter
    @Setter
    private  String acNumber;

    @Column(name = "BANK_DETAIL_AC_TYPE")
    @Getter
    @Setter
    private  String acType;

    @Column(name = "BANK_DETAIL_BANK_NAME")
    @Getter
    @Setter
    private  String bankName;

    @Column(name = "BANK_DETAIL_BRANCH_NAME")
    @Getter
    @Setter
    private  String branchName;

    @Column(name = "BANK_DETAIL_IFCS_CODE")
    @Getter
    @Setter
    private  String ifscCode;

    @Column(name = "STATUS")
    @Getter
    @Setter
    private boolean status;

    public void update(BankDetail bankDetail) {
        this.actorType = bankDetail.getActorType();
        this.name = bankDetail.getName();
        this.acNumber = bankDetail.getAcNumber();
        this.acType = bankDetail.getAcType();
        this.bankName = bankDetail.getBankName();
        this.branchName = bankDetail.getBranchName();
        this.ifscCode = bankDetail.getIfscCode();
    }
}
