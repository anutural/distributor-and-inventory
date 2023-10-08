package com.am.reaprich.reaprichbackend.business.pojo.auth;

import com.am.reaprich.reaprichbackend.data.entities.auth.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppUserRegisterRequest {
    private String email;
    private String password;
    private Role role;
}
