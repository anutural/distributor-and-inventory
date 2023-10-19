package com.am.reaprich.reaprichbackend.business.pojo.uermanagement;

import com.am.reaprich.reaprichbackend.data.entities.actors.outletprovider.OutletType;
import com.am.reaprich.reaprichbackend.data.entities.address.Address;
import com.am.reaprich.reaprichbackend.data.entities.bank.BankDetail;
import com.am.reaprich.reaprichbackend.data.entities.kyc.KYC;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UpdateOutletDetailRequest {
    private String firmName;
    private String firmContactNumber;
    private Address firmAddress;
    private BankDetail firmBankDetails;
    private String ownerFirstName;
    private String ownerLastName;
    private String ownerContactNumber;
    private Address ownerAddress;
}
