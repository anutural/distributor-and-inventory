package com.am.reaprich.reaprichbackend.data.entities.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.am.reaprich.reaprichbackend.data.entities.auth.Permission.*;

@RequiredArgsConstructor
public enum Role {

    USER(Collections.emptySet()),
    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    ADMIN_CREATE,
                    TD_READ,
                    TD_UPDATE,
                    TD_DELETE,
                    TD_CREATE,
                    SP_OUTLET_CREATE,
                    SP_OUTLET_DELETE,
                    SP_OUTLET_READ,
                    SP_OUTLET_UPDATE,
                    OUTLET_CREATE,
                    OUTLET_DELETE,
                    OUTLET_READ,
                    OUTLET_UPDATE
            )
    ),
    TD(
            Set.of(
                    TD_READ,
                    TD_UPDATE,
                    TD_DELETE,
                    TD_CREATE
            )
    ),
    SP_OUTLET(
            Set.of(
                    SP_OUTLET_CREATE,
                    SP_OUTLET_DELETE,
                    SP_OUTLET_READ,
                    SP_OUTLET_UPDATE,
                    OUTLET_CREATE,
                    OUTLET_DELETE,
                    OUTLET_READ,
                    OUTLET_UPDATE
            )
    ),
    OUTLET(
            Set.of(
                    OUTLET_CREATE,
                    OUTLET_DELETE,
                    OUTLET_READ,
                    OUTLET_UPDATE
            )
    )

    ;

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}

