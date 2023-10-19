package com.am.reaprich.reaprichbackend.business.pojo.uermanagement;

import com.am.reaprich.reaprichbackend.data.entities.actors.Outlet;
import com.am.reaprich.reaprichbackend.data.entities.actors.outletprovider.OutletType;
import com.am.reaprich.reaprichbackend.data.entities.address.Address;
import com.am.reaprich.reaprichbackend.data.entities.bank.BankDetail;
import com.am.reaprich.reaprichbackend.data.entities.kyc.KYC;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class OutletDetailResponse {
    private OutletType outletType;
    private String firmName;
    private String firmContactNumber;
    private String firmGSTNumber;
    private String firmPAN;
    private Address firmAddress;
    private BankDetail firmBankDetails;
    private String ownerFirstName;
    private String ownerLastName;
    private String ownerContactNumber;
    private String ownerPAN;
    private KYC ownerKYC;
    private Address ownerAddress;
    private String email;
    private boolean status;
    private String error;

    public  static OutletDetailResponse getOutletDetailResponseFromOutletEntity(Outlet outlet) {
        return OutletDetailResponse.builder()
                .outletType(outlet.getOutletType())
                .firmName(outlet.getFirmName())
                .firmContactNumber(outlet.getFirmContactNumber())
                .firmGSTNumber(outlet.getFirmGSTNumber())
                .firmPAN(outlet.getFirmPAN())
                .firmAddress(outlet.getFirmAddress())
                .firmBankDetails(outlet.getFirmBankDetails())
                .ownerFirstName(outlet.getOwnerFirstName())
                .ownerLastName(outlet.getOwnerLastName())
                .ownerContactNumber(outlet.getOwnerContactNumber())
                .ownerPAN(outlet.getOwnerPAN())
                .ownerKYC(outlet.getOwnerKYC())
                .ownerAddress(outlet.getOwnerAddress())
                .email(outlet.getEmail())
                .status(outlet.isStatus())
                .build();
    }
}


