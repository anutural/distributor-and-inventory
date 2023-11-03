package com.am.reaprich.reaprichbackend.business.pojo.uermanagement;

import com.am.reaprich.reaprichbackend.data.entities.bank.BankDetail;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BankDetailResponse {
    private BankDetail bankDetail;
    private String error;
}
