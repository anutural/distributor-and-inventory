package com.am.reaprich.reaprichbackend.business.pojo;

import com.am.reaprich.reaprichbackend.data.entities.actors.actorprovider.ActorType;
import com.am.reaprich.reaprichbackend.data.entities.actors.actorprovider.CustomerType;
import com.am.reaprich.reaprichbackend.data.entities.actors.outletprovider.OutletType;
import com.am.reaprich.reaprichbackend.data.entities.address.addressprovider.ADDCountry;
import com.am.reaprich.reaprichbackend.data.entities.address.addressprovider.ADDDist;
import com.am.reaprich.reaprichbackend.data.entities.address.addressprovider.ADDState;
import com.am.reaprich.reaprichbackend.data.entities.address.addressprovider.ADDZone;
import com.am.reaprich.reaprichbackend.data.entities.kyc.kycprovider.KYCAddProofType;
import com.am.reaprich.reaprichbackend.data.entities.kyc.kycprovider.KYCIDType;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserManagementProvider {
    @Getter
    @Setter
    private Iterable<ActorType> actorType;

    @Getter
    @Setter
    private Iterable<OutletType> outletType;

    @Getter
    @Setter
    private Iterable<CustomerType> customerType;

    @Getter
    @Setter
    private Iterable<ADDDist> addDistList;

    @Getter
    @Setter
    private Iterable<ADDZone> addZoneList;

    @Getter
    @Setter
    private Iterable<ADDState> addStateList;

    @Getter
    @Setter
    private Iterable<ADDCountry> addCountryList;

    @Getter
    @Setter
    private Iterable<KYCIDType> kycIdTypes;

    @Getter
    @Setter
    private Iterable<KYCAddProofType> kycAddProofType;

    @Getter
    @Setter
    private String error;

}
