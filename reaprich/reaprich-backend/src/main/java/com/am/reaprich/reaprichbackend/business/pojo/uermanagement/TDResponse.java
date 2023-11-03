package com.am.reaprich.reaprichbackend.business.pojo.uermanagement;

import com.am.reaprich.reaprichbackend.data.entities.actors.TD;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TDResponse {
    private TD td;
    private String error;
}
