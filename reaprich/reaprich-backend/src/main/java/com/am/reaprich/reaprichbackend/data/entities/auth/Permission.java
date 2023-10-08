package com.am.reaprich.reaprichbackend.data.entities.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    TD_READ("td:read"),
    TD_UPDATE("td:update"),
    TD_CREATE("td:create"),
    TD_DELETE("td:delete"),
    SP_OUTLET_READ("sp_outlet:read"),
    SP_OUTLET_UPDATE("sp_outlet:update"),
    SP_OUTLET_CREATE("sp_outlet:create"),
    SP_OUTLET_DELETE("sp_outlet:delete"),
    OUTLET_READ("outlet:read"),
    OUTLET_UPDATE("outlet:update"),
    OUTLET_CREATE("outlet:create"),
    OUTLET_DELETE("outlet:delete")
    ;

    @Getter
    private final String permission;
}
