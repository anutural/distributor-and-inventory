package com.am.reaprich.reaprichbackend.business.pojo.auth;

import com.am.reaprich.reaprichbackend.data.entities.auth.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    @JsonProperty ("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("role")
    private Role role;

    @JsonProperty("authority")
    private Collection<? extends GrantedAuthority> authorities;

}
