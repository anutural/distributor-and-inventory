package com.am.reaprich.reaprichbackend.business.pojo.uermanagement;

import com.am.reaprich.reaprichbackend.data.entities.kyc.KYC;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class KYCResponse {
    private KYC kyc;
    private String error;
}
