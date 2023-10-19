package com.am.reaprich.reaprichbackend.business.pojo.uermanagement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ResetPasswordRequest {
    private String email;
    private String oldPassword;
    private String newPassword;
}
