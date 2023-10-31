package com.am.reaprich.reaprichbackend.business.pojo.uermanagement;

import com.am.reaprich.reaprichbackend.data.entities.actors.TD;
import com.am.reaprich.reaprichbackend.data.entities.actors.actorprovider.ActorType;
import com.am.reaprich.reaprichbackend.data.entities.address.Address;
import com.am.reaprich.reaprichbackend.data.entities.bank.BankDetail;
import com.am.reaprich.reaprichbackend.data.entities.kyc.KYC;
import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class TDDetailResponse {
    private String id;
    private ActorType actorType;

    private String firstName;

    private String lastName;

    private String contactNumber;

    private String PAN;

    private Address address;

    private BankDetail bankDetails;

    private KYC KYC;

    private String email;

    private boolean status;

    private String error;

    public static TDDetailResponse getTDDetailResponseFromOutletEntity(TD td) {
        return TDDetailResponse.builder()
                .id(td.getId())
                .actorType(td.getActorType())
                .firstName(td.getFirstName())
                .lastName(td.getLastName())
                .contactNumber(td.getContactNumber())
                .PAN(td.getPAN())
                .address(td.getAddress())
                .bankDetails(td.getBankDetails())
                .KYC(td.getKYC())
                .email(td.getEmail())
                .status(td.isStatus())
                .build();
    }
}
